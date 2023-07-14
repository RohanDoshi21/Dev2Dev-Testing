package org.dev2dev.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProfileTest extends BaseTest{
    @Test(description = "Profile test")
    public void profileTest() {
        String endpoint = "/auth/profile";
        String expectedSchema = "src/test/resources/json/profile-schema.json";

        Response response = requestSpec.get(endpoint);

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(expectedSchema)));
    }
}
