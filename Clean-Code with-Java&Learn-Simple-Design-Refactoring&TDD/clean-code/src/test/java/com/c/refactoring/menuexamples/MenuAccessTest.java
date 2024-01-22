package com.c.refactoring.menuexamples;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuAccessTest {

    @Test
    public void testSetAuthorizationsInEachMenus() {

        Role[] userRoles = {new Role("MenuARead"), new Role("MenuBWrite"),
                new Role("MenuCRead"), new Role("MenuCWrite")};

        MenuItem[] menuItemsArray = {
                new MenuItem("A", "MenuARead", "MenuAWrite"),
                new MenuItem("B", "MenuBRead", "MenuBWrite"),
                new MenuItem("C", "MenuCRead", "MenuCWrite"),
                new MenuItem("D", "MenuDRead", "MenuDWrite")
        };

        List<MenuItem> menuItems = Arrays.asList(menuItemsArray);

        MenuAccess2 menuAccess = new MenuAccess2();

        menuAccess.setAuthorizationsInEachMenus(menuItems, userRoles);

        MenuItem menuItemA = menuItems.get(0);
        assertEquals(Constants.READ, menuItemA.getAccess());
        assertEquals(true, menuItemA.isVisible());

        MenuItem menuItemB = menuItems.get(1);
        assertEquals(Constants.WRITE, menuItemB.getAccess());
        assertEquals(true, menuItemB.isVisible());

        MenuItem menuItemC = menuItems.get(2);
        assertEquals(Constants.WRITE, menuItemC.getAccess());
        assertEquals(true, menuItemC.isVisible());

        MenuItem menuItemD = menuItems.get(3);
        assertEquals(null, menuItemD.getAccess());
        assertEquals(false, menuItemD.isVisible());

    }
}
