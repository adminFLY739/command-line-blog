package com.fly.page.write;

import com.fly.entity.Article;
import com.fly.entity.User;
import com.fly.page.BasePage;
import com.fly.util.ClearScreen;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Update extends BasePage {
    public void printPage(User currentUser) {

    }

    public void printPage(User currentUser, int indexToUpdate) {
        ClearScreen.clear();
        System.out.println("---------------------------------");
        System.out.println("----         更改博客         ----");
        System.out.println("---------------------------------");
        System.out.println("当前登录用户：" + currentUser.getUsername());
        System.out.println();
        String filePath = "src/main/resources/article.json";
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader reader = new FileReader(filePath);
            Article[] articles = gson.fromJson(reader, Article[].class);
            List<Article> filteredArticles = Arrays.stream(articles).filter(article -> Objects.equals(article.getAuthor(), currentUser.getUsername())).collect(Collectors.toList());
            List<Article> updatedArticles = Arrays.stream(articles).filter(article -> !Objects.equals(article.getAuthor(), currentUser.getUsername())).collect(Collectors.toList());
            Article[] filteredArray = filteredArticles.toArray(new Article[0]);
            System.out.println("原标题：" + filteredArray[indexToUpdate].getTitle());
            System.out.println("原内容：" + filteredArray[indexToUpdate].getContent());
            Scanner scanner = new Scanner(System.in);
            System.out.println("请更新标题：");
            String title = scanner.nextLine();
            System.out.println("请更新内容：");
            String content = scanner.nextLine();
            if (Objects.equals(title, "") || Objects.equals(content, "")) {
                System.out.println("相关信息不能为空，更改失败！");
                Thread.sleep(1000);
                ListUpdate listUpdate = new ListUpdate();
                listUpdate.printPage(currentUser);
            }
            filteredArray[indexToUpdate].setTitle(title);
            filteredArray[indexToUpdate].setContent(content);
            updatedArticles.addAll(Arrays.asList(filteredArray));
            FileWriter fileWriter = new FileWriter(filePath);
            gson.toJson(filteredArray, fileWriter);
            fileWriter.flush();
            System.out.println("博客更改成功！");
            Thread.sleep(1000);
            WritePage writePage = new WritePage();
            writePage.printPage(currentUser);
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
