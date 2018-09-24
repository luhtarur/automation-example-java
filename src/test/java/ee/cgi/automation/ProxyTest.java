package ee.cgi.automation;

import com.codeborne.selenide.WebDriverRunner;
import ee.cgi.automation.configuration.WebDriverProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class ProxyTest {
    private FileOutputStream fileOutputStream;

    @BeforeAll
    public static void setUpAll() {
        WebDriverProvider.startDriverWithProxy();
    }

    @AfterAll
    public static void tearDownAll() {
        WebDriverRunner.closeWebDriver();
    }

    @Test
    public void captureHTTPTraffic() throws IOException {
        //Example of writing network traffic into HAR (HTTP Archive) file
        fileOutputStream = new FileOutputStream("traffic.har");
        WebDriverProvider.proxyServer.newHar("example");

        //Generate some traffic
        open("http://www.cgi.ee");

        WebDriverProvider
                .proxyServer.getHar()
                .writeTo(fileOutputStream);
    }
}
