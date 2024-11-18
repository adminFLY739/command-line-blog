package com.fly.page.write;

import com.fly.entity.User;
import com.fly.page.BasePage;
import com.fly.page.MenuPage;
import com.fly.util.ClearScreen;

import java.util.Objects;
import java.util.Scanner;

public class WritePage extends BasePage {
    public void printPage(User currentUser) {
        try {
            if (currentUser.getUsername() != null) {
                ClearScreen.clear();
                System.out.println("---------------------------------");
                System.out.println("----         创作管理         ----");
                System.out.println("---------------------------------");
                System.out.println("当前登录用户：" + currentUser.getUsername());
                System.out.println();
                System.out.println("1. 发布");
                System.out.println("2. 查看");
                System.out.println("3. 更新");
                System.out.println("4. 删除");
                System.out.println("5. 返回");
                System.out.println("请输入您的操作: ");
                Scanner scanner = new Scanner(System.in);
                String choice = scanner.nextLine();
                if (Objects.equals(choice, "1")) {
                    Add add = new Add();
                    add.printPage(currentUser);
                } else if (Objects.equals(choice, "2")) {
                    ListOfArticle list = new ListOfArticle();
                    list.printPage(currentUser);
                } else if (Objects.equals(choice, "3")) {
                    // todo
                } else if (Objects.equals(choice, "4")) {
                    // todo
                } else if (Objects.equals(choice, "5")) {
                    System.out.println("正在返回首页！");
                    Thread.sleep(1000);
                    MenuPage menuPage = new MenuPage();
                    menuPage.printPage(currentUser);
                } else {
                    System.out.println("请重新输入正确的序号！");
                    Thread.sleep(1000);
                    WritePage writePage = new WritePage();
                    writePage.printPage(currentUser);
                }
            } else {
                System.out.println("请先登录！");
                Thread.sleep(1000);
                MenuPage menuPage = new MenuPage();
                menuPage.printPage(currentUser);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
