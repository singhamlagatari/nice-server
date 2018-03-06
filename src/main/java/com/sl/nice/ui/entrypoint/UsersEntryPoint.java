package com.sl.nice.ui.entrypoint;

import com.google.gson.Gson;
import com.sl.nice.annotations.Secured;
import com.sl.nice.dto.UserDTO;
import com.sl.nice.service.UsersService;
import com.sl.nice.service.impl.UsersServiceImpl;
import com.sl.nice.ui.model.request.CreateUserRequestModel;
import com.sl.nice.ui.model.response.UserProfileRest;
import com.sl.nice.utils.Crypto;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class UsersEntryPoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON,  MediaType.APPLICATION_XML} )
    public UserProfileRest createUser(CreateUserRequestModel requestObject) {
        UserProfileRest returnValue = new UserProfileRest();

        // Prepare UserDTO
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(requestObject, userDto);

        // Create new user
        UsersService userService = new UsersServiceImpl();
        UserDTO createdUserProfile = userService.createUser(userDto);

        //Prepare response
        BeanUtils.copyProperties(createdUserProfile, returnValue);

        return returnValue;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getUsers(@DefaultValue("0") @QueryParam("start") int start,  @DefaultValue("50") @QueryParam("limit") int limit) {

        UsersService userService = new UsersServiceImpl();
        List<UserDTO> users = userService.getUsers(start, limit);

        return  users;

    }

}
