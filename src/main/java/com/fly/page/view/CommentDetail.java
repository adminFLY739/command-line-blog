package com.fly.page.view;

import com.fly.entity.Article;
import com.fly.entity.Comment;
import com.fly.entity.Reply;
import com.fly.entity.User;
import com.fly.page.BasePage;
import com.fly.page.write.ListDetail;
import com.fly.page.write.WritePage;
import com.fly.util.ClearScreen;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CommentDetail extends BasePage {
    public void printPage(User currentUser, int parsedIndex) {
        ClearScreen.clear();
        System.out.println("---------------------------------");
        System.out.println("----         评论博客         ----");
        System.out.println("---------------------------------");
        if (currentUser.getUsername() == null) {
            System.out.println("当前登录用户：" + "匿名用户");
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
            System.out.println("标题：" + filteredArray[parsedIndex].getTitle());
            System.out.println("内容：" + filteredArray[parsedIndex].getContent());
            System.out.println("作者：" + filteredArray[parsedIndex].getAuthor());
            System.out.println("创作时间：" + filteredArray[parsedIndex].getCreateDate());
            System.out.println();
            System.out.println("评论区：");
            for (int i = 0; i < filteredArray[parsedIndex].getComments().size(); i++) {
                System.out.println();
                System.out.println("第" + (i + 1) + "条评论");
                System.out.println(filteredArray[parsedIndex].getComments().get(i).getUsername() + ": " + filteredArray[parsedIndex].getComments().get(i).getContent());
                System.out.println(filteredArray[parsedIndex].getComments().get(i).getCommentDate());
                for (int j = 0; j < filteredArray[parsedIndex].getComments().get(i).getReplies().size(); j++) {
                    System.out.print("  |--");
                    System.out.println(filteredArray[parsedIndex].getComments().get(i).getReplies().get(j).getUsername() + ": " + filteredArray[parsedIndex].getComments().get(i).getReplies().get(j).getContent());
                    System.out.print("     ");
                    System.out.println(filteredArray[parsedIndex].getComments().get(i).getReplies().get(j).getReplyDate());
                }
            }
            System.out.println();
            System.out.println("1. 评论");
            System.out.println("2. 回复");
            System.out.println("输入#返回博客列表");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (Objects.equals(input, "1")) {
                System.out.println("请输入评论：");
                String scannerComment = scanner.nextLine();
                if (Objects.equals(scannerComment, "")) {
                    System.out.println("评论不能为空！");
                    Thread.sleep(1000);
                    CommentDetail commentPage = new CommentDetail();
                    commentPage.printPage(currentUser, parsedIndex);
                }
                Comment commentTemp = new Comment(currentUser.getUsername() == null ? "匿名用户" : currentUser.getUsername(), scannerComment, new Date());
                filteredArray[parsedIndex].getComments().add(commentTemp);
                FileWriter fileWriter = new FileWriter(filePath);
                gson.toJson(articles, fileWriter);
                fileWriter.flush();
                System.out.println("评论成功！");
                Thread.sleep(1000);
                CommentDetail commentPage = new CommentDetail();
                commentPage.printPage(currentUser, parsedIndex);
            } else if (Objects.equals(input, "2")) {
                try {
                    System.out.println("请输入要回复的评论：");
                    String index = scanner.nextLine();
                    int replyIndex = Integer.parseInt(index) - 1;
                    if (replyIndex >= 0 && replyIndex < filteredArray[parsedIndex].getComments().size()) {
                        System.out.println("回复评论：" + filteredArray[parsedIndex].getComments().get(replyIndex).getContent());
                        String scannerReplyComment = scanner.nextLine();
                        if (Objects.equals(scannerReplyComment, "") || scannerReplyComment == null) {
                            System.out.println("请勿输入空回复！");
                            Thread.sleep(1000);
                            CommentDetail commentPage = new CommentDetail();
                            commentPage.printPage(currentUser, parsedIndex);
                        } else {
                            List<Reply> replyList = filteredArray[parsedIndex].getComments().get(replyIndex).getReplies();
                            replyList.add(new Reply(scannerReplyComment, currentUser.getUsername() == null ? "匿名用户" : currentUser.getUsername(), new Date()));
                            filteredArray[parsedIndex].getComments().get(replyIndex).setReplies(replyList);
                            FileWriter fileWriter = new FileWriter(filePath);
                            gson.toJson(filteredArray, fileWriter);
                            fileWriter.flush();
                            System.out.println("回复成功！");
                            Thread.sleep(1000);
                            CommentDetail commentPage = new CommentDetail();
                            commentPage.printPage(currentUser, parsedIndex);
                        }
                    } else {
                        System.out.println("输入无效，请检查输入内容！");
                        Thread.sleep(1000);
                        CommentDetail commentPage = new CommentDetail();
                        commentPage.printPage(currentUser, parsedIndex);
                    }
                } catch (NumberFormatException | InterruptedException e) {
                    System.out.println("输入无效，请输入一个有效的数字！");
                    Thread.sleep(1000);
                    CommentDetail commentPage = new CommentDetail();
                    commentPage.printPage(currentUser, parsedIndex);
                }
            } else if (Objects.equals(input, "#")) {
                System.out.println("返回博客列表！");
                Thread.sleep(1000);
                ViewPage viewPage = new ViewPage();
                viewPage.printPage(currentUser);
            } else {
                System.out.println("输入错误，请检查输入内容");
                Thread.sleep(1000);
                CommentDetail commentPage = new CommentDetail();
                commentPage.printPage(currentUser, parsedIndex);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printPage(User currentUser) {

    }
}
