package com.sl.nice.service.impl;

import com.google.gson.Gson;
import com.sl.nice.dto.MemberAuthDTO;
import com.sl.nice.exceptions.AuthenticationException;

import com.sl.nice.exceptions.CouldNotDeleteRecordException;
import com.sl.nice.exceptions.NoRecordFoundException;
import com.sl.nice.io.dao.DAO;
import com.sl.nice.io.dao.impl.MySQLDAO;
import com.sl.nice.service.AuthenticationService;
import com.sl.nice.dto.UserDTO;
import com.sl.nice.ui.model.request.LoginCredentials;
import com.sl.nice.ui.model.response.ErrorMessages;
import com.sl.nice.ui.model.response.UserProfileRest;
import com.sl.nice.utils.UserProfileUtils;
import org.springframework.beans.BeanUtils;

import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {

    DAO database;

    public AuthenticationServiceImpl() {
        this.database = new MySQLDAO();
    }

    public UserProfileRest authenticate(LoginCredentials loginCredentials) throws AuthenticationException {

        String memberId = loginCredentials.getMember_id();
        String password = loginCredentials.getPassword();
        String deviceId = loginCredentials.getDevice_id();

        UserProfileRest userProfileRest = new UserProfileRest();

        UsersServiceImpl usersService = new UsersServiceImpl();
        UserDTO storedUser = usersService.getUserByMemberId(memberId); // User name must be unique in our system


        if (storedUser == null) {
            throw new AuthenticationException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
        }

        String encryptedPassword = null;

        encryptedPassword = new UserProfileUtils().generateSecurePassword(password, storedUser.getSalt());

        boolean authenticated = false;
        if (encryptedPassword != null && encryptedPassword.equalsIgnoreCase(storedUser.getPassword())) {
            if (memberId != null && memberId.equalsIgnoreCase(storedUser.getMember_id())) {
                authenticated = true;
            }
        }

        if (!authenticated) {
            throw new AuthenticationException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
        }


        MemberAuthDTO memberAuthDTO = new MemberAuthDTO();

        try {
            memberAuthDTO = getMemberAuth(deviceId, storedUser.getMember_id());
        } catch (NoRecordFoundException ex) {
            memberAuthDTO = null;
        }

        System.out.println(new Gson().toJson(memberAuthDTO));
        if (memberAuthDTO == null || memberAuthDTO.getMember_id() == null) {
            System.out.println("memberAuthDTO is null");
            MemberAuthDTO memberAuthNew = new MemberAuthDTO();
            memberAuthNew.setAuth_token(issueAccessToken(storedUser.getSalt(), storedUser.getMember_id(), deviceId));
            memberAuthNew.setDevice_id(deviceId);
            memberAuthNew.setMember_id(storedUser.getMember_id());
            MemberAuthDTO memberAuthResult = saveMemberAuth(memberAuthNew);
            BeanUtils.copyProperties(memberAuthResult, memberAuthDTO);
        }

        userProfileRest.setMember_id(memberAuthDTO.getMember_id());
        userProfileRest.setName(storedUser.getName());
        userProfileRest.setToken(memberAuthDTO.getAuth_token());
        userProfileRest.setDevice_id(memberAuthDTO.getDevice_id());

        return userProfileRest;

    }

    private MemberAuthDTO saveMemberAuth (MemberAuthDTO memberAuthDTO) {
        MemberAuthDTO returnValues = null;

        try {
            database.openConnection();
            returnValues = database.saveMemberAuth(memberAuthDTO);
        } finally {
            database.closeConnection();
        }

        return returnValues;
    }

    private MemberAuthDTO getMemberAuth (String deviceId, String memberId) {
        MemberAuthDTO returnValue = new MemberAuthDTO();

        if (deviceId == null || deviceId.isEmpty()) {
            return returnValue;
        }

        // Connect to database
        try {
            this.database.openConnection();
            returnValue = this.database.getMemberAuth(deviceId, memberId);
        } finally {
            this.database.closeConnection();
        }

        return returnValue;
    }

    private MemberAuthDTO getMemberAuthByToken (String token) {
        MemberAuthDTO returnValue = new MemberAuthDTO();

        if (token == null || token.isEmpty()) {
            return returnValue;
        }

        // Connect to database
        try {
            this.database.openConnection();
            returnValue = this.database.getMemberAuthByToken(token);
        } finally {
            this.database.closeConnection();
        }

        return returnValue;
    }

    public String logoutUser(String memberId, String token) {


        MemberAuthDTO memberAuth = getMemberAuthByToken(token);
        deleteMemberAuth(memberAuth);

        return "success";
    }

    private void deleteMemberAuth(MemberAuthDTO memberAuthDTO) {

        try {
            this.database.openConnection();
            this.database.deleteMemberAuth(memberAuthDTO);
        } catch (Exception ex) {
            System.out.println("ini catch delete");
            throw new CouldNotDeleteRecordException(ex.getMessage());
        } finally {
            System.out.println("ini finally delete");
            this.database.closeConnection();
        }

        // Verify that user is deleted
        try {
            System.out.println("ini try Verify");
            memberAuthDTO = this.getMemberAuth(memberAuthDTO.getDevice_id(), memberAuthDTO.getMember_id());
        } catch (NoRecordFoundException ex) {
            System.out.println("ini catch Verify");
            memberAuthDTO = null;
        }

        if (memberAuthDTO != null) {
            throw new CouldNotDeleteRecordException(
                    ErrorMessages.COULD_NOT_DELETE_RECORD.getErrorMessage());
        }
    }

    public UserProfileRest checkAutentication(String memberId, String token) throws AuthenticationException {
        UsersServiceImpl usersService = new UsersServiceImpl();
        MemberAuthDTO memberAuth = getMemberAuthByToken(token);

        boolean authenticated = false;
        if (token != null && token.equalsIgnoreCase(memberAuth.getAuth_token())) {
            authenticated = true;
        }

        if (!authenticated) {
            throw new AuthenticationException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
        }

        UserDTO userDTO = usersService.getUserByMemberId(memberAuth.getMember_id());


        UserProfileRest userProfileRest = new UserProfileRest();
        BeanUtils.copyProperties(userDTO,userProfileRest);
        userProfileRest.setToken(memberAuth.getAuth_token());
        userProfileRest.setMember_id(memberAuth.getMember_id());
        userProfileRest.setDevice_id(memberAuth.getDevice_id());

        return userProfileRest;

    }

    public String issueAccessToken(String salt, String memberId, String deviceId) throws AuthenticationException {


        String accessTokenMaterial = memberId + salt;

        byte[] encryptedAccessToken = null;
        try {
            encryptedAccessToken = new UserProfileUtils().encrypt(deviceId, accessTokenMaterial);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AuthenticationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new AuthenticationException("Faled to issue secure access token");
        }

        String encryptedAccessTokenBase64Encoded = Base64.getEncoder().encodeToString(encryptedAccessToken);

        // Split token into equal parts
        int tokenLength = encryptedAccessTokenBase64Encoded.length();

//        String tokenToSaveToDatabase = encryptedAccessTokenBase64Encoded.substring(0, tokenLength / 2);
//        String returnValue = encryptedAccessTokenBase64Encoded.substring(tokenLength / 2, tokenLength);

//        userProfile.setToken(tokenToSaveToDatabase);
//        updateUserProfile(userProfile);

        return encryptedAccessTokenBase64Encoded.substring(tokenLength / 2, tokenLength);
    }

    private void updateUserProfile(UserDTO userProfile) {
        try {
            database.openConnection();
            database.updateUser(userProfile);
        } finally {
            database.closeConnection();
        }
    }

    public UserDTO resetSecurityCridentials(String password, UserDTO userProfile) {
        // Gerenerate a new salt
        UserProfileUtils userUtils = new UserProfileUtils();
        String salt = userUtils.getSalt(30);

        // Generate a new password
        String securePassword = userUtils.generateSecurePassword(password, salt);
        userProfile.setSalt(salt);
        userProfile.setPassword(securePassword);

        // Update user profile
        updateUserProfile(userProfile);

        return userProfile;
    }

}
