package modules.post;

import org.openqa.selenium.WebElement;

import java.util.Date;
import java.util.List;

public class News extends Post {
    private List<String> tags;
    private String image;

    public News(String title, Date date, String content, String author, List<String> tags, String image) {
        this(title, date, content);
        this.setAuthor(author);
        this.tags = tags;
        this.image = image;
    }

    public News(String title, Date date, String content) {
        super();
        this.title = title;
        this.publishDate = date;
        this.content = content;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getImage(String image) {
        return image;
    }
}