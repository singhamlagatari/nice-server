package com.sl.nice.service;

import com.sl.nice.exceptions.AuthenticationException;
import com.sl.nice.dto.UserDTO;
import com.sl.nice.ui.model.request.LoginCredentials;
import com.sl.nice.ui.model.response.AuthenticationDetails;
import com.sl.nice.ui.model.response.UserProfileRest;

import javax.ws.rs.container.ContainerRequestContext;

public interface AuthenticationService {
    UserProfileRest authenticate(LoginCredentials loginCredentials) throws AuthenticationException;
    UserProfileRest checkAutentication(String memberId, String token) throws AuthenticationException;
    String issueAccessToken(String salt, String memberId, String deviceId) throws AuthenticationException;
    String logoutUser(String memberId, String token) throws AuthenticationException;
    UserDTO resetSecurityCridentials(String password, UserDTO userProfile);
}
