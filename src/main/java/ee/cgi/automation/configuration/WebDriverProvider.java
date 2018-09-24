package ee.cgi.automation.configuration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.Setter;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

//Proxy related imports
//Standard imports for WebDriver

public class WebDriverProvider {
    public static WebDriver webDriver;

    //Use this if proxy is needed
    public static BrowserMobProxy proxyServer = new BrowserMobProxyServer();
    @Getter @Setter public static Proxy proxy;

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

        //To set global timeout
        Configuration.timeout = 10000;

        webDriver = WebDriverRunner.getWebDriver();
    }

    public static void startDriverWithProxy() {
        //Start Proxy server on port 0/random port
        proxyServer.start(0);

        //Set HAR capture types and set it as Selenium proxy
        proxyServer.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());
        setProxy(ClientUtil.createSeleniumProxy(proxyServer));

        //WebDriverManager manages driver binaries
        WebDriverManager.chromedriver().setup();

        //To set specific options for Chrome
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.PROXY, getProxy());

        //Set Chromedriver as our WebDriver to WebDriverRunner
        WebDriverRunner.setWebDriver(new ChromeDriver(options));
        webDriver = WebDriverRunner.getWebDriver();
    }
}
