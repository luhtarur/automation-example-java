package ee.cgi.automation.pageobjects;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public @Data class MainPage {
    private SelenideElement searchBox = $(By.id("edit-keywords"));
    private SelenideElement searchBtn = $(By.id("edit-submit-search-api-view"));

}
