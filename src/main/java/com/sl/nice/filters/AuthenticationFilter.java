package com.sl.nice.filters;

import com.sl.nice.annotations.Secured;
import com.sl.nice.dto.UserDTO;
import com.sl.nice.service.UsersService;
import com.sl.nice.service.impl.UsersServiceImpl;
import com.sl.nice.utils.UserProfileUtils;

import javax.annotation.Priority;
import javax.security.sasl.AuthenticationException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
 
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString("token");
        String emailHeader = requestContext.getHeaderString("email");

        if (
            authorizationHeader == null ||
            authorizationHeader.isEmpty() ||
            emailHeader == null ||
            emailHeader.isEmpty()
        ) {
            throw new AuthenticationException("Authorization header must be provided");
        }

        // Extract the token
        String token = authorizationHeader.trim();

        validateToken(token, emailHeader);
        
    }
    
private void validateToken(String token, String email) throws AuthenticationException {
      
        // Get user profile details
        UsersService usersService = new UsersServiceImpl();
        UserDTO userProfile = usersService.getUserByEmail(email);
        
        // Asseble Access token using two parts. One from DB and one from http request.
//        String completeToken = userProfile.getToken() + token;
        
        // Create Access token material out of the useId received and salt kept database
//        String securePassword = userProfile.getPassword();
//        String salt = userProfile.getSalt();
//        String accessTokenMaterial = email + salt;
//        byte[] encryptedAccessToken = null;
       
//        try {
//            encryptedAccessToken = new UserProfileUtils().encrypt(securePassword, accessTokenMaterial);
//        } catch (InvalidKeySpecException ex) {
//            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
//            throw new AuthenticationException("Faled to issue secure access token");
//        }
        
//        String encryptedAccessTokenBase64Encoded = Base64.getEncoder().encodeToString(encryptedAccessToken);
       
        // Compare two access tokens 
//        if (!token.equalsIgnoreCase(userProfile.getToken())) {
//            throw new AuthenticationException("Authorization token did not match");
//        }
    }
}