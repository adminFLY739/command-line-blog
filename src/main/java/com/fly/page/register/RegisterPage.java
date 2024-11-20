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
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String inputUsername = scanner.nextLine();
        System.out.println("请输入密码：");
        String inputPassword = scanner.nextLine();
        System.out.println("请输入邮箱：");
        String inputEmail = scanner.nextLine();
        try {
            if (!inputEmail.matches(emailRegex)) {
                System.out.println("邮箱格式不正确！");
                Thread.sleep(1000);
                MenuPage menuPage = new MenuPage();
                menuPage.printPage(currentUser);
            }
            if (!inputPassword.matches(passwordRegex)) {
                System.out.println("密码不符合要求！请确保密码至少8个字符，包含大小写字母、数字和特殊字符。");
                Thread.sleep(1000);
                MenuPage menuPage = new MenuPage();
                menuPage.printPage(currentUser);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
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
