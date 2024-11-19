package com.fly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String username;
    private String content;
    private Date commentDate;
    private List<Reply> replies;
    public Comment(String username, String content, Date commentDate) {
        this.username = username;
        this.content = content;
        this.commentDate = commentDate;
        this.replies = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(username, comment.username) && Objects.equals(content, comment.content) && Objects.equals(commentDate, comment.commentDate) && Objects.equals(replies, comment.replies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, content, commentDate, replies);
    }
}
