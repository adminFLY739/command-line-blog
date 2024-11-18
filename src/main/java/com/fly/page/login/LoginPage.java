package com.fly.page.login;

import com.fly.entity.User;
import com.fly.page.BasePage;
import com.fly.page.MenuPage;
import com.fly.util.ClearScreen;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoginPage extends BasePage {
    public void printPage(User currentUser) {
        ClearScreen.clear();
        System.out.println("---------------------------------");
        System.out.println("----         博客登录         ----");
        System.out.println("---------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String inputUsername = scanner.nextLine();
        System.out.println("请输入密码：");
        String inputPassword = scanner.nextLine();
        String filePath = "src/main/resources/user.json";
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(filePath);
            User[] users = gson.fromJson(reader, User[].class);
            boolean userFound = false;
            for (User user : users) {
                if (user.getUsername().equals(inputUsername)) {
                    userFound = true;
                    if (user.getPassword().equals(inputPassword)) {
                        currentUser.setUsername(user.getUsername());
                        currentUser.setPassword(user.getPassword());
                        currentUser.setEmail(user.getEmail());
                        System.out.println("登录成功！");
                        Thread.sleep(1000);
                        MenuPage menuPage = new MenuPage();
                        menuPage.printPage(currentUser);
                    } else {
                        System.out.println("密码错误！");
                        Thread.sleep(1000);
                        MenuPage menuPage = new MenuPage();
                        menuPage.printPage(currentUser);
                    }
                }
            }
            if (!userFound) {
                System.out.println("用户不存在！");
                Thread.sleep(1000);
                MenuPage menuPage = new MenuPage();
                menuPage.printPage(currentUser);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
