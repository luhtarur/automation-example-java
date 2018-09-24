package ee.cgi.automation;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class ExampleTest {
    @BeforeAll
    public static void setUpAll() {
        WebDriverProvider.startChromeDriver();
    }

    @AfterAll
    public static void tearDownAll() {
        WebDriverRunner.closeWebDriver();
    }

    @Test
    public void exampleTest() {
        open("http://www.google.com");
        $(By.id("lst-ib")).should(Condition.appear).sendKeys("CGI eesti");
        $(By.id("lst-ib")).should(Condition.appear).sendKeys(Keys.ENTER);
        $("a[href='https://www.cgi.ee/'").should(Condition.appear).click();
    }

    @Test
    public void logTest() {
        log.info("INFO MESSAGE");
        log.debug("DEBUG MESSAGE");
        log.error("ERROR MESSAGE");
    }
}
