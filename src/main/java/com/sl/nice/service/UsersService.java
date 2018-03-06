package com.sl.nice.service;

import com.sl.nice.dto.UserDTO;

import java.util.List;

public interface UsersService {
    UserDTO createUser(UserDTO user);
//    UserDTO getUser(String id);
    UserDTO getUserByEmail(String email);
    UserDTO getUserByMemberId(String memberId);
    List<UserDTO> getUsers(int start, int limit);
    void updateUserDetails(UserDTO userDetails);
//    void deleteUser(UserDTO userDto);
//    boolean verifyEmail(String token);
}
