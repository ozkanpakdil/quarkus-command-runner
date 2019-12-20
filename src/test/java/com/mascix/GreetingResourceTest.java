package com.mascix;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Properties;

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
            r = "1.2.3.4";
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