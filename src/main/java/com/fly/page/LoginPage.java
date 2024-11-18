package com.fly.page;

import com.fly.entity.User;
import com.fly.util.ClearScreen;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LoginPage extends BasePage {
    public void printPage() {
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
            Reader reader = new FileReader(filePath);
            User[] users = gson.fromJson(reader, User[].class);
            boolean userFound = false;
            for (User user : users) {
                if (user.getUsername().equals(inputUsername)) {
                    userFound = true;
                    if (user.getPassword().equals(inputPassword)) {
                        System.out.println("登录成功！");
                        Thread.sleep(1500);
                        MenuPage menuPage = new MenuPage();
                        menuPage.printPage();
                    } else {
                        System.out.println("密码错误！");
                        Thread.sleep(1500);
                        MenuPage menuPage = new MenuPage();
                        menuPage.printPage();
                    }
                    break;
                }
            }
            if (!userFound) {
                System.out.println("用户不存在！");
                Thread.sleep(1500);
                MenuPage menuPage = new MenuPage();
                menuPage.printPage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("捕获到其他异常：" + e);
        }
    }
}
