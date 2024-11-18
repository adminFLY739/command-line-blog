package com.fly;

import com.fly.entity.User;
import com.fly.page.MenuPage;

public class BlogSystemApplication {
    public static void main(String[] args) {
        MenuPage menuPage = new MenuPage();
        User currentUser = new User();
        menuPage.printPage(currentUser);
    }
}
