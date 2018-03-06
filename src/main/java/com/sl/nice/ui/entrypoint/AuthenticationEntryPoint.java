package com.sl.nice.ui.entrypoint;

import com.sl.nice.dto.UserDTO;
import com.sl.nice.exceptions.NoRecordFoundException;
import com.sl.nice.service.AuthenticationService;
import com.sl.nice.service.impl.AuthenticationServiceImpl;
import com.sl.nice.service.impl.UsersServiceImpl;
import com.sl.nice.ui.model.request.LoginCredentials;
import com.sl.nice.ui.model.request.UpdateUserRequest;
import com.sl.nice.ui.model.response.AuthenticationDetails;
import com.sl.nice.ui.model.response.ErrorMessages;
import com.sl.nice.ui.model.response.UserProfileRest;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public class AuthenticationEntryPoint {

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationDetails userLogin(LoginCredentials loginCredentials) {

        AuthenticationDetails authenticationDetails = new AuthenticationDetails();

        AuthenticationService authenticationService = new AuthenticationServiceImpl();
        UserProfileRest userProfileRest = authenticationService.authenticate(loginCredentials);

        System.out.println(userProfileRest.toString());

        authenticationDetails.setResult_code("200");
        authenticationDetails.setResult_msg("success");
        authenticationDetails.setResult_data(userProfileRest);

        return authenticationDetails;
    }

    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String userLogout(ContainerRequestContext requestContext) {

        String token = requestContext.getHeaderString("lunchpass_auth_token");
        String memberId = requestContext.getHeaderString("lunchpass_member_id");

        AuthenticationService authenticationService = new AuthenticationServiceImpl();

        return authenticationService.logoutUser(memberId, token);
    }

    @POST
    @Path("/chkStatus")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationDetails checkAuth(ContainerRequestContext requestContext) {
        AuthenticationDetails authenticationDetails = new AuthenticationDetails();

        AuthenticationService authenticationService = new AuthenticationServiceImpl();

        String token = requestContext.getHeaderString("lunchpass_auth_token");
        String memberId = requestContext.getHeaderString("lunchpass_member_id");

        UserProfileRest authenticatedUser = authenticationService.checkAutentication(memberId, token);

        authenticationDetails.setResult_code("200");
        authenticationDetails.setResult_msg("success");
        authenticationDetails.setResult_data(authenticatedUser);

        return authenticationDetails;
    }

    @POST
    @Path("/modify2")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationDetails updateUserInfo(UpdateUserRequest updateUserRequest) {

        String name = updateUserRequest.getName();
        String email = updateUserRequest.getEmail();
        String birthday = updateUserRequest.getBirthday();
        String gender = updateUserRequest.getGender();

        UsersServiceImpl usersService = new UsersServiceImpl();
        UserDTO userDTO = usersService.getUserByMemberId(updateUserRequest.getMember_id());

        if (userDTO == null) {
            throw new NoRecordFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        if (!name.isEmpty() && name != null) {
            userDTO.setName(name);
        }

        if (!birthday.isEmpty() && birthday != null) {
            userDTO.setBirthday(birthday);
        }

        if (!gender.isEmpty() && gender != null) {
            userDTO.setGender(gender);
        }

        if (!email.isEmpty() && email != null) {
            userDTO.setEmail(email);
        }

        usersService.updateUserDetails(userDTO);
        UserProfileRest userProfileRest = new UserProfileRest();

        BeanUtils.copyProperties(userDTO, userProfileRest);

        AuthenticationDetails authenticationDetails = new AuthenticationDetails();
        authenticationDetails.setResult_data(userProfileRest);

        return authenticationDetails;

    }

}
