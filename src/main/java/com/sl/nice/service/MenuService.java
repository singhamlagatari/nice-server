package com.sl.nice.service;

import com.sl.nice.dto.MenuDTO;

import java.util.List;

public interface MenuService {
    List<MenuDTO> getMenu(String storeCode);
}
