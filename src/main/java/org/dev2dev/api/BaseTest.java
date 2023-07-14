package org.dev2dev.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public RequestSpecification requestSpec;
    public String baseUrl;

    public String authorizationToken;

    @BeforeClass
    public void setup() {
        loadConfiguration();
        RestAssured.baseURI = baseUrl;
        requestSpec = RestAssured.given()
                .contentType(ContentType.JSON);

        loginTest();
        requestSpec.header("Authorization", "Bearer " + authorizationToken);
    }

    private void loadConfiguration() {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        baseUrl = properties.getProperty("api-base-url");
    }

    public void loginTest() {
        String requestBody = "{\"email\": \"user1@example.com\", \"password\": \"password1\"}";
        String endpoint = "/auth/login";
        String expectedSchema = "src/test/resources/json/login-schema.json";

        Response response = requestSpec.body(requestBody).post(endpoint);

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(expectedSchema)));
        System.out.println("Logging Successful");

        authorizationToken = response.jsonPath().getString("data.token");
    }
}
