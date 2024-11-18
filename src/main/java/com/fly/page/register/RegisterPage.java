package com.fly.page.register;

import com.fly.entity.User;
import com.fly.page.BasePage;
import com.fly.page.MenuPage;
import com.fly.util.ClearScreen;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class RegisterPage extends BasePage {
    public void printPage(User currentUser) {
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
            FileReader reader = new FileReader(filePath);
            List<User> userList = gson.fromJson(reader, new TypeToken<List<User>>() {
            }.getType());
            if (userList == null) {
                userList = new ArrayList<>();
            }
            boolean isDuplicate = userList.stream().anyMatch(user -> user.getUsername().equals(inputUsername));
            if (isDuplicate) {
                System.out.println("用户名已存在，注册失败！");
                Thread.sleep(1000);
                MenuPage menuPage = new MenuPage();
                menuPage.printPage(currentUser);
            }
            if (Objects.equals(inputUsername, "") || Objects.equals(inputPassword, "") || Objects.equals(inputEmail, "")) {
                System.out.println("相关信息不能为空，注册失败！");
                Thread.sleep(1000);
                MenuPage menuPage = new MenuPage();
                menuPage.printPage(currentUser);
            }
            userList.add(newUser);
            FileWriter writer = new FileWriter(filePath);
            gson.toJson(userList, writer);
            writer.flush();
            System.out.println("用户注册成功！");
            Thread.sleep(1000);
            MenuPage menuPage = new MenuPage();
            menuPage.printPage(currentUser);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
