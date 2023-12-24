package modules.post;
import java.util.Date;
public abstract class Post {
    protected String title;
    protected String author;
    protected Date publishDate;
    protected String content;
    protected int react;
    protected String link;
    public Post() {
    }
    public Post(String title, String author, Date publishDate, String content, int react) {
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.content = content;
        this.react = react;
    }

    public Post(String author, Date publishDate, String content, int react) {
    }

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

    public String getTitle() {
        return title;
    }
}
