package ee.cgi.automation;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class APITest {

    @Test
    public void apiTest() {
        when().get("https://postman-echo.com/get?foo1={bar1}&foo2={bar2}", "1", "2")
                .then().statusCode(200).body("args.foo1", equalTo("1"),"args.foo2", equalTo("2"));
    }
}
