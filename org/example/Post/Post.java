package org.example.Post;

import java.util.Date;
public abstract class Post {
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReact(int react) {
        this.react = react;
    }

    private String author;
    private Date publishDate;
    private String content;
    private int react;

    public String getAuthor() {
        return author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getContent() {
        return content;
    }

    public int getReact() {
        return react;
    }
}
