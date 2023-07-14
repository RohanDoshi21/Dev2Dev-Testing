package org.dev2dev.api;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class AnswerTest extends BaseTest {

    @Test(description = "Answer Test")
    public void getAnswer() {
        String endpoint = "/answers/1";
        String expectedSchema = "src/test/resources/json/answer-schema.json";

        Response response = requestSpec.get(endpoint);

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(expectedSchema)));
    }
}
