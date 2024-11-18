package com.fly.page;

import com.fly.entity.User;
import com.fly.page.login.LoginPage;
import com.fly.page.register.RegisterPage;
import com.fly.page.view.ViewPage;
import com.fly.page.write.WritePage;
import com.fly.util.ClearScreen;

import java.util.Objects;
import java.util.Scanner;

public class MenuPage extends BasePage {
    public void printPage(User currentUser) {
        try {
            ClearScreen.clear();
            System.out.println("---------------------------------");
            System.out.println("----       Java博客系统       ----");
            System.out.println("---------------------------------");
            if (currentUser.getUsername() != null) {
                System.out.println("当前登录用户：" + currentUser.getUsername());
            } else {
                System.out.println("当前无用户登录");
            }
            System.out.println();
            System.out.println("1. 登录");
            System.out.println("2. 注册");
            System.out.println("3. 创作管理");
            System.out.println("4. 文章浏览");
            System.out.println("5. 退出系统");
            System.out.println("请输入您的操作: ");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            if (Objects.equals(choice, "1")) {
                LoginPage loginPage = new LoginPage();
                loginPage.printPage(currentUser);
            } else if (Objects.equals(choice, "2")) {
                RegisterPage registerPage = new RegisterPage();
                registerPage.printPage(currentUser);
            } else if (Objects.equals(choice, "3")) {
                WritePage writePage = new WritePage();
                writePage.printPage(currentUser);
            } else if (Objects.equals(choice, "4")) {
                ViewPage viewPage = new ViewPage();
                viewPage.printPage(currentUser);
            } else if (Objects.equals(choice, "5")) {
                System.out.println("感谢使用！");
                Thread.sleep(1000);
            } else {
                System.out.println("请重新输入正确的序号！");
                Thread.sleep(1000);
                MenuPage menuPage = new MenuPage();
                menuPage.printPage(currentUser);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
