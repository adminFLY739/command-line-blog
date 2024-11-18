package com.fly.page.write;

import com.fly.entity.Article;
import com.fly.entity.Comment;
import com.fly.entity.User;
import com.fly.page.BasePage;
import com.fly.util.ClearScreen;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Add extends BasePage {
    public void printPage(User currentUser) {
        ClearScreen.clear();
        System.out.println("---------------------------------");
        System.out.println("----         新增博客         ----");
        System.out.println("---------------------------------");
        System.out.println("当前登录用户：" + currentUser.getUsername());
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入标题：");
        String title = scanner.nextLine();
        System.out.println("请输入内容：");
        String content = scanner.nextLine();
        String filePath = "src/main/resources/article.json";
        Article article = new Article(title, content, currentUser.getUsername(), new Date(), new ArrayList<>());
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader reader = new FileReader(filePath);
            List<Article> articles = gson.fromJson(reader, new TypeToken<List<Article>>() {
            }.getType());
            if (articles == null) {
                articles = new ArrayList<>();
            }
            if (Objects.equals(title, "") || Objects.equals(content, "")) {
                System.out.println("相关信息不能为空，发布失败！");
                Thread.sleep(1000);
                WritePage writePage = new WritePage();
                writePage.printPage(currentUser);
            }
            articles.add(article);
            FileWriter fileWriter = new FileWriter(filePath);
            gson.toJson(articles, fileWriter);
            fileWriter.flush();
            System.out.println("博客发布成功！");
            Thread.sleep(1000);
            WritePage writePage = new WritePage();
            writePage.printPage(currentUser);
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
