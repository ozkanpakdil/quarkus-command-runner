package com.mascix;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testing() throws Exception {
//        Optional t = Optional.of(null);

    }

    @Test
    public void testHelloEndpoint() {
        String r = "Directory";
        if (!System.getProperty("os.name").toLowerCase().contains("windows"))// check is OS windows
        {
            r = "Whois";
        }
        given()
                .when()
                .queryParam("ip", "1.2.3.4")
                .get("/whois")
                .then()
                .statusCode(200)
                .body(containsString(r));
    }

}