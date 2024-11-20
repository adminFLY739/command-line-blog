package com.fly.page.view;

import com.fly.entity.Article;
import com.fly.entity.User;
import com.fly.page.BasePage;
import com.fly.page.MenuPage;
import com.fly.util.ClearScreen;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ViewPage extends BasePage {
    public void printPage(User currentUser) {
        ClearScreen.clear();
        System.out.println("---------------------------------");
        System.out.println("----          博客           ----");
        System.out.println("---------------------------------");
        if (currentUser.getUsername() == null) {
            System.out.println("当前登录用户：" + "游客");
        } else {
            System.out.println("当前登录用户：" + currentUser.getUsername());
        }
        System.out.println();
        String filePath = "src/main/resources/article.json";
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader reader = new FileReader(filePath);
            List<Article> articles = gson.fromJson(reader, new TypeToken<List<Article>>() {
            }.getType());
            Article[] filteredArray = articles.toArray(new Article[0]);
            if (filteredArray == null || filteredArray.length == 0) {
                System.out.println();
                System.out.println("当前无任何博客！输入任意键返回！");
                Scanner scanner = new Scanner(System.in);
                String index = scanner.nextLine();
                System.out.println("正在返回主页！");
                Thread.sleep(1000);
                MenuPage menuPage = new MenuPage();
                menuPage.printPage(currentUser);
            }
            for (int i = 0; i < filteredArray.length; i++) {
                System.out.println(i + 1 + ". " + filteredArray[i].getTitle());
            }
            System.out.println();
            System.out.println("输入对应序号进行评论！");
            System.out.println("#️⃣键返回！");
            Scanner scanner = new Scanner(System.in);
            String index = scanner.nextLine();
            if (Objects.equals(index, "#")) {
                System.out.println("正在返回主页！");
                Thread.sleep(1000);
                MenuPage menuPage = new MenuPage();
                menuPage.printPage(currentUser);
            } else {
                try {
                    int parsedIndex = Integer.parseInt(index) - 1;
                    if (parsedIndex >= 0 && parsedIndex < filteredArray.length) {
                        System.out.println("评论博客！");
                        Thread.sleep(1000);
                        CommentDetail commentPage = new CommentDetail();
                        commentPage.printPage(currentUser, parsedIndex);
                    } else {
                        System.out.println("输入无效，请检查输入内容！");
                        Thread.sleep(1000);
                        ViewPage viewPage = new ViewPage();
                        viewPage.printPage(currentUser);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("输入无效，请输入一个有效的数字！");
                    Thread.sleep(1000);
                    ViewPage viewPage = new ViewPage();
                    viewPage.printPage(currentUser);
                }
            }
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
