import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration; // Thêm import cho Duration
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TwitterHashtagScraper {

    public static void main(String[] args) {
        try {
            runTwitterHashtagScraper();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void runTwitterHashtagScraper() throws InterruptedException {
        // Khai báo đường dẫn đến driver của trình duyệt Chrome
        System.setProperty("webdriver.chrome.driver", "đường_dẫn_đến_chromedriver.exe");

        // Khởi tạo trình duyệt Chrome
        WebDriver driver = new ChromeDriver();

        // Mở trang web Twitter
        driver.get("https://twitter.com/explore");

        // Chờ trang web load hoàn tất và cài đặt timeout
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Sử dụng Duration.ofSeconds()

        try {
            // Đợi cho đến khi thẻ input tìm kiếm xuất hiện
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-testid='SearchBox_Search_Input']")));

            // Tìm thẻ input tìm kiếm và nhập hashtag #nft
            WebElement searchInput = driver.findElement(By.cssSelector("input[data-testid='SearchBox_Search_Input']"));
            searchInput.sendKeys("#nft");

            // Đợi cho đến khi kết quả tìm kiếm xuất hiện và nhấp Enter
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[data-testid='TypeaheadUser']")));
            searchInput.sendKeys(Keys.ENTER);

            // Đợi cho đến khi kết quả tìm kiếm hashtag #nft xuất hiện
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='/hashtag/nft']")));

            // Nhấp vào kết quả hashtag #nft
            WebElement hashtagLink = driver.findElement(By.cssSelector("a[href='/hashtag/nft']"));
            hashtagLink.click();

            // Đợi cho đến khi kết quả xuất hiện và in các URL bài viết ra console
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[aria-label*='ago']")));

            List<WebElement> postLinks = driver.findElements(By.cssSelector("a[aria-label*='ago']"));
            for (WebElement postLink : postLinks) {
                String postUrl = postLink.getAttribute("href");
                System.out.println("URL post: " + postUrl);
            }

        } finally {
            // Đóng trình duyệt khi hoàn thành
            driver.quit();
        }
    }
}
