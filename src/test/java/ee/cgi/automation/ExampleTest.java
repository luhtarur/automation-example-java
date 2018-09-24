package ee.cgi.automation;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import ee.cgi.automation.configuration.WebDriverProvider;
import ee.cgi.automation.pageobjects.MainPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Slf4j //Initialize logger with Lombok annotation
public class ExampleTest {
    MainPage mainPage = new MainPage();

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

    @ParameterizedTest
    @DisplayName("Search for keyword")
    @CsvSource({"Tarkvara, Tarkvaraarendus", "Kosmos, Kosmosetööstus"})
    public void searchKeyword(String keyword, String link) {
        open("http://www.cgi.ee");
        mainPage.getSearchBox().should(Condition.appear).sendKeys(keyword);
        mainPage.getSearchBtn().should(Condition.appear).click();
        $(By.linkText(link)).should(Condition.appear).click();
    }

}
