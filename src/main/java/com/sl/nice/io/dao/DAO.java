package com.sl.nice.io.dao;

import com.sl.nice.dto.MemberAuthDTO;
import com.sl.nice.dto.MenuDTO;
import com.sl.nice.dto.StoreDTO;
import com.sl.nice.dto.UserDTO;

import java.util.List;

public interface DAO {
    void openConnection();

    //Table User
    UserDTO getUserByEmail(String email);
    UserDTO getUserByMemberId(String memberId);
    UserDTO saveUser(UserDTO user);
//    UserDTO getUser(String id);
    List<UserDTO> getUsers(int start, int limit);
    void updateUser(UserDTO userProfile);
//    void deleteUser(UserDTO userProfile);
//    UserDTO getUserByEmailToken(String token);

    // Table Member Auth
    MemberAuthDTO saveMemberAuth(MemberAuthDTO memberAuthDTO);
    MemberAuthDTO getMemberAuth(String deviceId, String memberId);
    MemberAuthDTO getMemberAuthByToken(String token);
    void deleteMemberAuth(MemberAuthDTO memberAuthDTO);

    // Table Store Master
    List<StoreDTO> getStores(int start, int limit);

    //Table Store Menu

    List<MenuDTO> getMenu(String storeCode);

    void closeConnection();
}
