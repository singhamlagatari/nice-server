package com.sl.nice.ui.entrypoint;

import com.sl.nice.dto.UserDTO;
import com.sl.nice.exceptions.NoRecordFoundException;
import com.sl.nice.service.AuthenticationService;
import com.sl.nice.service.impl.AuthenticationServiceImpl;
import com.sl.nice.service.impl.UsersServiceImpl;
import com.sl.nice.ui.model.response.AuthenticationDetails;
import com.sl.nice.ui.model.response.ErrorMessages;
import com.sl.nice.ui.model.response.UserProfileRest;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;

@Path("member")
public class MemberEntripont {

    @POST
    @Path("/me")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationDetails checkAuth(ContainerRequestContext requestContext) {

        AuthenticationService authenticationService = new AuthenticationServiceImpl();
        String token = requestContext.getHeaderString("lunchpass_auth_token");
        String memberId = requestContext.getHeaderString("lunchpass_member_id");

        UserProfileRest authenticatedUser = authenticationService.checkAutentication(memberId, token);

        AuthenticationDetails authenticationDetails = new AuthenticationDetails();
        authenticationDetails.setResult_data(authenticatedUser);

        return authenticationDetails;
    }

    @POST
    @Path("/profile/image/reset")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationDetails reserProfileImage(ContainerRequestContext requestContext) {

        String memberId = requestContext.getHeaderString("lunchpass_member_id");

        UsersServiceImpl usersService = new UsersServiceImpl();
        UserDTO userDTO = usersService.getUserByMemberId(memberId);

        if (userDTO == null) {
            throw new NoRecordFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        userDTO.setProfile_image(null);

        usersService.updateUserDetails(userDTO);

        UserProfileRest userProfileRest = new UserProfileRest();

        BeanUtils.copyProperties(userDTO, userProfileRest);

        AuthenticationDetails authenticationDetails = new AuthenticationDetails();
        authenticationDetails.setResult_data(userProfileRest);

        return authenticationDetails;

    }



}
