package crawlcode.SetupSelenium.src.services;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public interface IWebDriver<TypeElement, Mechanism> {
    void get(String url);

    void quit();

    TypeElement findElement(Mechanism by);

    List<TypeElement> findElements(Mechanism by);

    WebDriverWait createWebDriverWait(Duration timeout);

    WebDriver.Options manage();

    JavascriptExecutor createJavascriptExecutor();
}