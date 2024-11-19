package com.fly.page.write;

import com.fly.entity.Article;
import com.fly.entity.User;
import com.fly.page.BasePage;
import com.fly.util.ClearScreen;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class ListDetail extends BasePage {
    public void printPage(User currentUser) {
        ClearScreen.clear();
        System.out.println("---------------------------------");
        System.out.println("----         博客列表         ----");
        System.out.println("---------------------------------");
        System.out.println("当前登录用户：" + currentUser.getUsername());
        System.out.println();
        String filePath = "src/main/resources/article.json";
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(filePath);
            Article[] articles = gson.fromJson(reader, Article[].class);
            List<Article> filteredArticles = Arrays.stream(articles).filter(article -> Objects.equals(article.getAuthor(), currentUser.getUsername())).collect(Collectors.toList());
            Article[] filteredArray = filteredArticles.toArray(new Article[0]);
            if (filteredArray == null || filteredArray.length == 0) {
                System.out.println();
                System.out.println("当前用户未发布博客！输入任意键返回！");
                Scanner scanner = new Scanner(System.in);
                String index = scanner.nextLine();
                System.out.println("正在返回创作管理！");
                Thread.sleep(1000);
                WritePage writePage = new WritePage();
                writePage.printPage(currentUser);
            }
            for (int i = 0; i < filteredArray.length; i++) {
                System.out.println(i + 1 + ". " + filteredArray[i].getTitle());
            }
            System.out.println();
            System.out.println("输入对应序号查看博客内容！输入#键返回！");
            Scanner scanner = new Scanner(System.in);
            String index = scanner.nextLine();
            if (Objects.equals(index, "#")) {
                System.out.println("正在返回创作管理！");
                Thread.sleep(1000);
                WritePage writePage = new WritePage();
                writePage.printPage(currentUser);
            } else {
                try {
                    int parsedIndex = Integer.parseInt(index) - 1;
                    if (parsedIndex >= 0 && parsedIndex < filteredArray.length) {
                        System.out.println("查看博客！");
                        Thread.sleep(1000);
                        Detail detail = new Detail();
                        detail.printPage(currentUser, filteredArray[parsedIndex]);
                    } else {
                        System.out.println("输入无效，请检查输入内容！");
                        Thread.sleep(1000);
                        ListDetail list = new ListDetail();
                        list.printPage(currentUser);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("输入无效，请输入一个有效的数字！");
                    Thread.sleep(1000);
                    ListDetail list = new ListDetail();
                    list.printPage(currentUser);
                }
            }
        } catch (FileNotFoundException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
