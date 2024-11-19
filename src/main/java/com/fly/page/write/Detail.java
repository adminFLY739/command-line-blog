package com.fly.page.write;

import com.fly.entity.Article;
import com.fly.entity.User;
import com.fly.page.BasePage;
import com.fly.util.ClearScreen;

import java.util.Scanner;

public class Detail extends BasePage {
    public void printPage(User currentUser, Article article) {
        try {
            ClearScreen.clear();
            System.out.println("---------------------------------");
            System.out.println("----         博客详情         ----");
            System.out.println("---------------------------------");
            System.out.println("当前登录用户：" + currentUser.getUsername());
            System.out.println();
            System.out.println("标题：" + article.getTitle());
            System.out.println("内容：" + article.getContent());
            System.out.println("作者：" + article.getAuthor());
            System.out.println("创作时间：" + article.getCreateDate());
            System.out.println();
            System.out.println("评论区：");
            System.out.println();
            for (int i = 0; i < article.getComments().size(); i++) {
                System.out.println(article.getComments().get(i).getUsername() + ": " + article.getComments().get(i).getContent());
                System.out.println(article.getComments().get(i).getCommentDate());
            }
            System.out.println();
            System.out.println("输入任意键返回！");
            Scanner scanner = new Scanner(System.in);
            String temp = scanner.nextLine();
            System.out.println("正在返回文章列表！");
            Thread.sleep(1000);
            ListDetail list = new ListDetail();
            list.printPage(currentUser);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printPage(User currentUser) {

    }
}
