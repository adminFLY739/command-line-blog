package com.fly.page.write;

import com.fly.entity.User;
import com.fly.page.BasePage;
import com.fly.util.ClearScreen;

public class Update extends BasePage {
    public void printPage(User currentUser) {
        ClearScreen.clear();
        System.out.println("---------------------------------");
        System.out.println("----         更新博客         ----");
        System.out.println("---------------------------------");
        System.out.println("当前登录用户：" + currentUser.getUsername());
        System.out.println();
    }
}
