package com.c.refactoring.menuexamples;

import java.util.Arrays;
import java.util.List;

public class MenuAccess2 {

    public void setAuthorizationsInEachMenus(List<MenuItem> menuItemsList,
                                             Role[] roles) {
        if (roles == null) return;
        menuItemsList.stream().forEach(menuItem -> setAccessForMenuItem(roles, menuItem));
    }


    private void setAccessForMenuItem(Role[] roles, MenuItem menuItem) {
        if (doesHasAccessRole(roles, menuItem.getWriteAccessRole())) {
            menuItem.setAccess(Constants.WRITE);
            menuItem.setVisible(true);
            return;
        }

        if (doesHasAccessRole(roles, menuItem.getReadAccessRole())) {
            menuItem.setAccess(Constants.READ);
            menuItem.setVisible(true);
        }
    }

    private boolean doesHasAccessRole(Role[] roles, String writeAccessRole) {
        return Arrays.stream(roles).anyMatch(role -> role.getName().equals(writeAccessRole));
    }
}
