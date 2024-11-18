package com.fly;

import com.fly.page.MenuPage;

public class BlogSystemApplication {
    public static void run() throws InterruptedException {
        MenuPage menuPage = new MenuPage();
        menuPage.printPage();
    }
}
