package com.fly.page;

import com.fly.entity.User;
import com.fly.util.ClearScreen;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterPage extends BasePage {
    public void printPage() {
        ClearScreen.clear();
        System.out.println("---------------------------------");
        System.out.println("----         博客注册         ----");
        System.out.println("---------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String inputUsername = scanner.nextLine();
        System.out.println("请输入密码：");
        String inputPassword = scanner.nextLine();
        System.out.println("请输入邮箱：");
        String inputEmail = scanner.nextLine();
        User newUser = new User(inputUsername, inputPassword, inputEmail);
        String filePath = "src/main/resources/user.json";
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<User> userList;
            try (FileReader reader = new FileReader(filePath)) {
                userList = gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
                if (userList == null) {
                    userList = new ArrayList<>();
                }
            }
            userList.add(newUser);
            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(userList, writer);
                writer.flush();
                System.out.println("用户注册成功！");
                Thread.sleep(1500);
                MenuPage menuPage = new MenuPage();
                menuPage.printPage();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            System.out.println("文件操作失败：" + e.getMessage());
        } catch (Exception e) {
            System.out.println("捕获到其他异常：" + e);
        }
    }
}
