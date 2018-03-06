package com.sl.nice.service.impl;

import com.sl.nice.dto.MenuDTO;
import com.sl.nice.io.dao.DAO;
import com.sl.nice.io.dao.impl.MySQLDAO;
import com.sl.nice.service.MenuService;
import com.sl.nice.utils.UserProfileUtils;

import java.util.List;

public class MenuServiceImpl implements MenuService {

    UserProfileUtils userProfileUtils = new UserProfileUtils();
    DAO database;

    public MenuServiceImpl() {
        this.database = new MySQLDAO();
    }

    @Override
    public List<MenuDTO> getMenu(String storeCode) {

        List<MenuDTO> menu = null;

        try {
            this.database.openConnection();
            menu = this.database.getMenu(storeCode);
        } finally {
            this.database.closeConnection();
        }

        return menu;
    }

}

