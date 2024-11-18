package com.fly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private String title;
    private String content;
    private String author;
    private Date createDate;
    private List<Comment> comments;
}
