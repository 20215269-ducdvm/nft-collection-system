package org.example.Post;

import java.util.List;
import java.util.Date;


public class News extends Post{
    private String author;
    private Date publishDate;
    private String content;
    private int react;

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReact() {
        return react;
    }

    public void setReact(int react) {
        this.react = react;
    }

    private List<String> tags;

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
}
