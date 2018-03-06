package com.sl.nice.ui.model.response;

import com.sl.nice.dto.GroupDTO;
import com.sl.nice.dto.MenuDTO;

import java.util.List;

public class MenuListValue {

    private List<MenuDTO> menu_list;
    private List<GroupDTO> group_list;

    public List<MenuDTO> getMenu_list() {
        return menu_list;
    }

    public void setMenu_list(List<MenuDTO> menu_list) {
        this.menu_list = menu_list;
    }

    public List<GroupDTO> getGroup_list() {
        return group_list;
    }

    public void setGroup_list(List<GroupDTO> group_list) {
        this.group_list = group_list;
    }
}
