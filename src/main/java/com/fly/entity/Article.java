package com.fly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private String title;
    private String content;
    private String author;
    private Date createDate;
    private List<Comment> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title) && Objects.equals(content, article.content) && Objects.equals(author, article.author) && Objects.equals(createDate, article.createDate) && Objects.equals(comments, article.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, author, createDate, comments);
    }
}
