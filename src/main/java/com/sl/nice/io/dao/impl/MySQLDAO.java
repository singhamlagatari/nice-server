package com.sl.nice.io.dao.impl;

import com.sl.nice.dto.MemberAuthDTO;
import com.sl.nice.dto.MenuDTO;
import com.sl.nice.dto.StoreDTO;
import com.sl.nice.dto.UserDTO;
import com.sl.nice.io.dao.DAO;
import com.sl.nice.io.entity.MemberAuthEntity;
import com.sl.nice.io.entity.MenuEntity;
import com.sl.nice.io.entity.StoreEntity;
import com.sl.nice.io.entity.UserEntity;
import com.sl.nice.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MySQLDAO implements DAO {

    Session session;

    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public UserDTO getUserByEmail(String email) {

        UserDTO userDto = null;

        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);

        //Query roots always reference entitie
        Root<UserEntity> profileRoot = criteria.from(UserEntity.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("email"), email));

        // Fetch single result
        Query<UserEntity> query = session.createQuery(criteria);
        List<UserEntity> resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            UserEntity userEntity = resultList.get(0);
            userDto = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDto);
        }

        return userDto;
    }

    public UserDTO getUserByMemberId(String memberId) {

        UserDTO userDto = null;

        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);

        //Query roots always reference entitie
        Root<UserEntity> profileRoot = criteria.from(UserEntity.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("member_id"), memberId));

        // Fetch single result
        Query<UserEntity> query = session.createQuery(criteria);
        List<UserEntity> resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            UserEntity userEntity = resultList.get(0);
            userDto = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDto);
        }

        return userDto;
    }

//    public UserDTO getUser(String id) {
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//
//        //Create Criteria against a particular persistent class
//        CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);
//
//        //Query roots always reference entitie
//        Root<UserEntity> profileRoot = criteria.from(UserEntity.class);
//        criteria.select(profileRoot);
//        criteria.where(cb.equal(profileRoot.get("userId"), id));
//
//        // Fetch single result
//        UserEntity userEntity = session.createQuery(criteria).getSingleResult();
//
//        UserDTO userDto = new UserDTO();
//        BeanUtils.copyProperties(userEntity, userDto);
//
//        return userDto;
//    }

    public UserDTO saveUser(UserDTO user) {
        UserDTO returnValue = null;
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        session.beginTransaction();
        session.save(userEntity);
        session.getTransaction().commit();

        returnValue = new UserDTO();
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    public void closeConnection() {
        if (session != null) {
            session.close();
        }
    }

    public void updateUser(UserDTO userProfile) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userProfile, userEntity);

        session.beginTransaction();
        session.update(userEntity);
        session.getTransaction().commit();
    }

    @Override
    public List<UserDTO> getUsers(int start, int limit) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);

        //Query roots always reference entities
        Root<UserEntity> userRoot = criteria.from(UserEntity.class);
        criteria.select(userRoot);

        // Fetch results from start to a number of "limit"
        List<UserEntity> searchResults = session.createQuery(criteria).
                setFirstResult(start).
                setMaxResults(limit).
                getResultList();

        List<UserDTO> returnValue = new ArrayList<UserDTO>();
        for (UserEntity userEntity : searchResults) {
            UserDTO userDto = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDto);
            returnValue.add(userDto);
        }

        return returnValue;
    }

//    @Override
//    public void deleteUser(UserDTO userPofile) {
//        UserEntity userEntity = new UserEntity();
//        BeanUtils.copyProperties(userPofile, userEntity);
//
//        session.beginTransaction();
//        session.delete(userEntity);
//        session.getTransaction().commit();
//    }

//    @Override
//    public UserDTO getUserByEmailToken(String token) {
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//
//        //Create Criteria against a particular persistent class
//        CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);
//
//        //Query roots always reference entitie
//        Root<UserEntity> profileRoot = criteria.from(UserEntity.class);
//        criteria.select(profileRoot);
//        criteria.where(cb.equal(profileRoot.get("emailVerificationToken"), token));
//
//        // Fetch single result
//        UserEntity userEntity = session.createQuery(criteria).getSingleResult();
//
//        UserDTO userDto = new UserDTO();
//        BeanUtils.copyProperties(userEntity, userDto);
//
//        return userDto;
//    }

    public MemberAuthDTO saveMemberAuth(MemberAuthDTO memberAuthDTO) {
        MemberAuthDTO returnValue = null;
        MemberAuthEntity memberAuthEntity = new MemberAuthEntity();
        BeanUtils.copyProperties(memberAuthDTO, memberAuthEntity);

        session.beginTransaction();
        System.out.println("sebelum copyProperties");
        session.save(memberAuthEntity);
        System.out.println("sesudah copyProperties");
        session.getTransaction().commit();

        returnValue = new MemberAuthDTO();
        BeanUtils.copyProperties(memberAuthEntity, returnValue);

        return returnValue;
    }

    @Override
    public void deleteMemberAuth(MemberAuthDTO memberAuthDTO) {
        MemberAuthEntity memberAuthEntity = new MemberAuthEntity();
        BeanUtils.copyProperties(memberAuthDTO, memberAuthEntity);

        session.beginTransaction();
        session.delete(memberAuthEntity);
        session.getTransaction().commit();
    }

    public MemberAuthDTO getMemberAuth(String deviceId, String memberId) {
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<MemberAuthEntity> criteria = cb.createQuery(MemberAuthEntity.class);

        //Query roots always reference entitie
        Root<MemberAuthEntity> memberAuthRoot = criteria.from(MemberAuthEntity.class);
        criteria.select(memberAuthRoot);
        criteria.where(cb.equal(memberAuthRoot.get("device_id"), deviceId));
        criteria.where(cb.equal(memberAuthRoot.get("member_id"), memberId));

        // Fetch single result
        MemberAuthEntity memberAuthEntity = session.createQuery(criteria).getSingleResult();

        MemberAuthDTO memberAuthDTO = new MemberAuthDTO();
        BeanUtils.copyProperties(memberAuthEntity, memberAuthDTO);

        return memberAuthDTO;
    }

    public MemberAuthDTO getMemberAuthByToken(String token) {
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<MemberAuthEntity> criteria = cb.createQuery(MemberAuthEntity.class);

        //Query roots always reference entitie
        Root<MemberAuthEntity> memberAuthRoot = criteria.from(MemberAuthEntity.class);
        criteria.select(memberAuthRoot);
        criteria.where(cb.equal(memberAuthRoot.get("auth_token"), token));

        // Fetch single result
        MemberAuthEntity memberAuthEntity = session.createQuery(criteria).getSingleResult();

        MemberAuthDTO memberAuthDTO = new MemberAuthDTO();
        BeanUtils.copyProperties(memberAuthEntity, memberAuthDTO);

        return memberAuthDTO;
    }

    @Override
    public List<StoreDTO> getStores(int start, int limit) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<StoreEntity> criteria = cb.createQuery(StoreEntity.class);

        //Query roots always reference entities
        Root<StoreEntity> storeRoot = criteria.from(StoreEntity.class);
        criteria.select(storeRoot);

        // Fetch results from start to a number of "limit"
        List<StoreEntity> searchResults = session.createQuery(criteria).
                setFirstResult(start).
                setMaxResults(limit).
                getResultList();

        List<StoreDTO> returnValue = new ArrayList<StoreDTO>();
        for (StoreEntity storeEntity : searchResults) {
            StoreDTO storeDTO = new StoreDTO();
            BeanUtils.copyProperties(storeEntity, storeDTO);
            returnValue.add(storeDTO);
        }

        return returnValue;
    }

    @Override
    public List<MenuDTO> getMenu(String storeCode) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<MenuEntity> criteria = cb.createQuery(MenuEntity.class);

        //Query roots always reference entities
        Root<MenuEntity> menuRoot = criteria.from(MenuEntity.class);
        criteria.select(menuRoot);

        // Fetch results from start to a number of "limit"
        List<MenuEntity> searchResults = session.createQuery(criteria).getResultList();


        List<MenuDTO> returnValue = new ArrayList<MenuDTO>();
        for (MenuEntity menuEntity : searchResults) {
            MenuDTO menuDTO = new MenuDTO();
            BeanUtils.copyProperties(menuEntity, menuDTO);
            returnValue.add(menuDTO);
        }

        return returnValue;

    }

}
