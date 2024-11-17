package com.fly.page;

import com.fly.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public void printPage() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("---------------------------------");
        System.out.println("----       Java博客系统       ----");
        System.out.println("---------------------------------");
        System.out.println("1. 登录");
        System.out.println("2. 注册");
        System.out.println("3. 创作管理");
        System.out.println("4. 文章浏览");
        System.out.println("5. 退出系统");
        System.out.println("请输入您的操作: ");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("1");
                return;
            case 2:
                System.out.println("2");
                return;
            case 3:
                System.out.println("3");
                return;
            case 4:
                System.out.println("4");
                return;
            case 5:
                System.out.println("5");
                return;
            default:
                System.out.println("default");
        }
    }
}
