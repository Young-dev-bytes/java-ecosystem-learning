package com.c.refactoring.menuexamples;

import java.util.Arrays;
import java.util.List;

public class MenuAccessRefactored {

    public void setAuthorizationsInEachMenus(List<MenuItem> menuItemsList,
                                             Role[] roles) {
        if (roles == null) return;
        menuItemsList.stream().forEach(menuItem -> setAccessForMenuItem(roles, menuItem));

    }

    private void setAccessForMenuItem(Role[] roles, MenuItem menuItem) {
        if (doesUserHasRole(roles, menuItem.getReadAccessRole())) {
            menuItem.setAccess(Constants.READ);
            menuItem.setVisible(true);
        }

        if (doesUserHasRole(roles, menuItem.getWriteAccessRole())) {
            menuItem.setAccess(Constants.WRITE);
            menuItem.setVisible(true);
        }
    }

    private boolean doesUserHasRole(Role[] roles, String roleToCheckFor) {
        return Arrays.stream(roles).anyMatch(role -> role.getName().equals(roleToCheckFor));
    }

}
