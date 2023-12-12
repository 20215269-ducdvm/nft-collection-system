package TestAutoSelenium;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RunFirstCodeSelenium  {
    public static void main(String[] args){
        List<String> postUrls = new ArrayList<>();
        int i = 0;
        // Hashtag để tìm kiếm trên Twitter
        String query = "#nft";
        System.out.println("Chương trình bắt đầu");

        // Thiết lập thuộc tính hệ thống để chỉ đến ChromeDriver
        System.setProperty("webdriver.chrome.driver", "D:\\CODE\\setup\\chromedriver-win64\\chromedriver.exe");

        // Khởi tạo WebDriver sử dụng ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Quản lý cài đặt trình duyệt
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Mở trang đăng nhập Twitter
        driver.get("https://twitter.com/login");

        // Đăng nhập tự động (vui lòng thay thế thông tin đăng nhập thực tế của bạn)
       // loginTwitter(driver, "na.thu06277@gmail.com", "nathu6277");
        driver.findElement(By.xpath("//input[@name='text']")).sendKeys("na.thu06277@gmail.com");
        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
        driver.findElement(By.xpath("//input[@name='text']")).sendKeys("@KuaKler");
        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("nathu6277");
        driver.findElement(By.xpath("//span[contains(text(),'Log in')]")).click();

        driver.findElement(By.xpath("//input[@aria-label=\"Search query\"]")).sendKeys(query);
        driver.findElement(By.xpath("//input[@aria-label=\"Search query\"]")).sendKeys(Keys.ENTER);


        // Thực hiện tìm kiếm hashtag #nft
        //searchHashtag(driver, query);

        // In ra tiêu đề của trang hiện tại
        System.out.println(driver.getTitle());

        // Tạm dừng thực thi trong 15 giây (để minh họa)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        while (true) {
            // Scroll xuống cuối trang
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 1000)");
            try {
                Thread.sleep(2000);
            } catch(InterruptedException e) {
                System.out.println("got interrupted!");
            }

            // Đợi cho đến khi thêm bài viết mới xuất hiện (hoặc có thể sử dụng điều kiện khác)
            //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[aria-label*='ago']")));
            WebElement postLinkElement = driver.findElement(By.cssSelector("a[aria-label*='ago']"));
            String postUrl = postLinkElement.getAttribute("href");
            postUrls.add(postUrl);
            System.out.println("URL post: " + postUrl);
            i++;
            if (i >= 200){
                break;
            }
        }

        // Đóng WebDriver, đóng trình duyệt
        System.out.println(" ");
        driver.quit();
        String json = convertUrlsToJson(postUrls);
        System.out.println(json);
        writeJsonToFile(json, "D:\\CODE\\crawldata\\data\\output.json");
    }
    private static String convertUrlsToJson(List<String> urls) {
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();

        for (String url : urls) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("url", url);
            jsonArray.add(jsonObject);
        }

        return gson.toJson(jsonArray);
    }
     private static void writeJsonToFile(String json, String fileName) {
         try (FileWriter fileWriter = new FileWriter(fileName)) {
             fileWriter.write(json);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

    // Hàm đăng nhập tự động
//    private static void loginTwitter(WebDriver driver, String username, String password) {
//        WebElement usernameInput = driver.findElement(By.cssSelector("input[name='session[username_or_email]']"));
//        WebElement passwordInput = driver.findElement(By.cssSelector("input[name='session[password]']"));
//        WebElement loginButton = driver.findElement(By.xpath("//span[text()='Log in']"));
//
//        usernameInput.sendKeys(username);
//        passwordInput.sendKeys(password);
//        loginButton.click();
//    }

    // Hàm thực hiện tìm kiếm hashtag
//    private static void searchHashtag(WebDriver driver, String hashtag) {
//        WebElement searchInput = driver.findElement(By.cssSelector("input[data-testid='SearchBox_Search_Input']"));
//        searchInput.sendKeys(hashtag);
//        searchInput.sendKeys(Keys.ENTER);
//    }
}
