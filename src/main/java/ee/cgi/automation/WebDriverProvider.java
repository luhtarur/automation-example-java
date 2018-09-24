package ee.cgi.automation;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverProvider {
    public static WebDriver webDriver;


    public static void startChromeDriver() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
        //WebDriverManager manages driver binaries
        WebDriverManager.chromedriver().setup();

        //To set specific options for Chrome
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);

        //Set Chromedriver as our WebDriver to WebDriverRunner
        WebDriverRunner.setWebDriver(new ChromeDriver(options));
        webDriver = WebDriverRunner.getWebDriver();
    }
}
