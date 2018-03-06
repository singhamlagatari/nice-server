package com.sl.nice.service.impl;

import com.sl.nice.exceptions.CouldNotCreateRecordException;
import com.sl.nice.exceptions.CouldNotUpdateRecordException;
import com.sl.nice.exceptions.NoRecordFoundException;
import com.sl.nice.io.dao.DAO;
import com.sl.nice.dto.UserDTO;
import com.sl.nice.io.dao.impl.MySQLDAO;
import com.sl.nice.service.UsersService;
import com.sl.nice.ui.model.response.ErrorMessages;
import com.sl.nice.utils.UserProfileUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class UsersServiceImpl implements UsersService {

    UserProfileUtils userProfileUtils = new UserProfileUtils();
    DAO database;

    public UsersServiceImpl() {
        this.database = new MySQLDAO();
    }

    public UserDTO createUser(UserDTO user) {
        UserDTO returnValue = null;

        // Validate the required fields
        userProfileUtils.validateRequiredFields(user);

        // Check if user already exists
//        UserDTO existingUser = this.getUserByEmail(user.getEmail());
//        if (existingUser != null) {
//            throw new CouldNotCreateRecordException(ErrorMessages.RECORD_ALREADY_EXISTS.name());
//        }

        // Generate salt
        String salt = userProfileUtils.getSalt(30);
        // Generate secure password
        String encryptedPassword = userProfileUtils.generateSecurePassword(user.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(encryptedPassword);
//        user.set

        // Record data into a database
        returnValue = this.saveUser(user);

        // Return back the user profile
        return returnValue;
    }

    public void updateUserDetails(UserDTO userDetails) {
        try {
            // Connect to database
            this.database.openConnection();
            // Update User Details
            this.database.updateUser(userDetails);

        } catch (Exception ex) {
            throw new CouldNotUpdateRecordException(ex.getMessage());
        } finally {
            this.database.closeConnection();
        }
    }
    
    @Override
    public List<UserDTO> getUsers(int start, int limit) {

        List<UserDTO> users = null;

        // Get users from database
        try {
            this.database.openConnection();
            users = this.database.getUsers(start, limit);
        } finally {
            this.database.closeConnection();
        }

        return users;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserDTO userDto = null;

        if (email == null || email.isEmpty()) {
            return userDto;
        }

        // Connect to database
        try {
            this.database.openConnection();
            userDto = this.database.getUserByEmail(email);
        } finally {
            this.database.closeConnection();
        }

        return userDto;
    }

    @Override
    public UserDTO getUserByMemberId(String memberId) {
        UserDTO userDto = null;

        if (memberId == null || memberId.isEmpty()) {
            return userDto;
        }

        // Connect to database
        try {
            this.database.openConnection();
            userDto = this.database.getUserByMemberId(memberId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NoRecordFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        } finally {
            this.database.closeConnection();
        }

        return userDto;
    }

    private UserDTO saveUser(UserDTO user) {
        UserDTO returnValue = null;
        // Connect to database
        try {
            this.database.openConnection();
            returnValue = this.database.saveUser(user);
        } finally {
            this.database.closeConnection();
        }

        return returnValue;
    }

}

