package crawlcode.SetupSelenium.src.crawlers;
import modules.post.Post;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import crawlcode.SetupSelenium.src.services.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class WebCrawler<T> {
    protected List<T> objects;
    protected static IWebDriver<WebElement, By> driver;
    protected final IJsonHandler gson;
    protected final List<String> PAGE_URLs = new ArrayList<>();
    protected final String JSON_FILE_PATH;

    public WebCrawler(String json_file_path, String... page_urls) {
        driver = new ChromeDriverSelenium();
        gson = new GsonHandler();
        objects = new ArrayList<>();
        this.JSON_FILE_PATH = json_file_path;
        Collections.addAll(this.PAGE_URLs, page_urls);
        setUpChromeDriver();
    }

    public void execute() throws IOException {
        crawlData();
        saveDataToFile();
        driver.quit();
    }

    public void saveDataToFile() throws IOException {
        // convert the list to a JSON array
        String json = gson.toJson(objects);
        FileWriter fileWriter = new FileWriter(JSON_FILE_PATH);
        // write the JSON array to a file
        try (fileWriter) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();
        }
    }

    public void waitForPresenceOfElements(String[] cssOfElements) {
        Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = driver.createWebDriverWait(timeout);
        for (String cssOfEl : cssOfElements) {
            String cssSelector = "." + cssOfEl.replace(" ", ".");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
        }
    }

    public void waitForPresenceOfElements(String elementType) {
        Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = driver.createWebDriverWait(timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(elementType)));
    }
    protected void setUpChromeDriver() {
       // Thiết lập thuộc tính hệ thống để chỉ đến ChromeDriver
       System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\OneDrive\\Desktop\\oop\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        // Quản lý cài đặt trình duyệt
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    protected abstract void crawlData() throws UnsupportedEncodingException;
}
