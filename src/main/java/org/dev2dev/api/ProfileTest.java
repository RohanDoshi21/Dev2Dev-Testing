package org.dev2dev.api;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ProfileTest extends BaseTest {
    @Test(description = "Profile test")
    public void profileTest() {
        String endpoint = "/auth/profile";
        String expectedSchema = "src/test/resources/json/profile-schema.json";

        Response response = requestSpec.get(endpoint);

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(expectedSchema)));
    }
}
