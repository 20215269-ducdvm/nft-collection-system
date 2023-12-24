package crawlcode.SetupSelenium.src.crawlers;

import crawlcode.SetupSelenium.src.services.ChromeDriverSelenium;
import crawlcode.SetupSelenium.src.services.WebDriverSelenium;
import modules.post.News;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class NewsCrawler extends WebCrawler<News>{
    private static String url = "https://nftplazas.com/nft-collectibles-news/?fbclid=IwAR2Y-yx5Psch3O-m2-bznyPD4KUdqakwz7PJ2AaYWHmokQ7tUjYR2T4UT1A";
    private static final String JSON_FILE_PATH = "data/News.json";
    public NewsCrawler(String filePath, String url) {
        super(filePath, url);
    }
    private Date date;
    @Override
    protected void crawlData() {
        driver.get(url);

        waitForPresenceOfElements("article");
        List<WebElement> articleElements = driver.findElements(By.tagName("article"));

        waitForPresenceOfElements(new String[]{"post_title entry-title"});
        // Lặp qua các articleElements để lấy thông tin
        for (WebElement articleElement : articleElements) {
            String articleURL = articleElement.findElement(By.cssSelector(".post_title.entry-title a")).getAttribute("href");
            String dateString = driver.findElement(By.cssSelector(".post_date.post_meta_item a")).getText();
            SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy");

            try {
                date = formatter.parse(dateString);
//                // Chuyển đổi thành múi giờ GMT
//                Calendar TimeZone = null;
//                formatter.setTimeZone(TimeZone.getTimeZone());
//                String gmtDate = formatter.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            crawlPostDetail(new ChromeDriverSelenium(), articleURL);
        }
    }



    protected void crawlPostDetail(WebDriverSelenium driver, String postUrl) {
        driver.get(postUrl);

        String title = driver.findElement(By.cssSelector(".post_header.entry-header h1")).getText();

        // Lấy nội dung bài viết
        List<WebElement> contentElements = driver.findElements(By.cssSelector(".post_content.entry-content *"));
        StringBuilder content = new StringBuilder();
        for (WebElement contentElement : contentElements) {
            if (contentElement.getTagName().contains("p")) {
                for (WebElement element: contentElement.findElements(By.cssSelector("p *")))
                    content.append(element.getText());
            } else if (contentElement.getTagName().contains("h")) {
                content.append(contentElement.getText());
            }
        }

        String author = driver.findElement(By.cssSelector(".vcard.author span")).getText();
        // Lấy thẻ tag, chuyển thành mảng String
        List<WebElement> tags = driver.findElements(By.cssSelector(".post_meta_item.post_tags a"));
        List<String> tagList = new ArrayList<>();
        for (WebElement tag : tags) {
            tagList.add(tag.getText());
        }

        String image = driver.findElement(By.cssSelector(".post_featured img")).getAttribute("src");

        objects.add(new News(title, date, content.toString(), author, tagList, image));
        driver.quit();
    }
    public static void main(String[] args) throws IOException {
        NewsCrawler newsCrawler = new NewsCrawler(JSON_FILE_PATH, url);
        newsCrawler.execute();
    }
}
