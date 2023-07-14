package org.dev2dev.api;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class QuestionTest extends BaseTest{
    @Test(description = "Question test")
    public void questionTest() {
        String endpoint = "/questions";
        String expectedSchema = "src/test/resources/json/question-schema.json";

        Response response = requestSpec.get(endpoint);

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(expectedSchema)));
    }

    @Test(description = "My Question test")
    public void myQuestionTest() {
        String endpoint = "/questions/my_questions";
        String expectedSchema = "src/test/resources/json/question-schema.json";

        Response response = requestSpec.get(endpoint);

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(expectedSchema)));
    }

    @Test(description = "Questions from tag")
    public void questionsFromTagTest() {
        String endpoint = "/tags/node";
        String expectedSchema = "src/test/resources/json/question-schema.json";

        Response response = requestSpec.get(endpoint);

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(expectedSchema)));
    }

    @Test(description = "Questions from search")
    public void questionsFromSearchTest() {
        String endpoint = "/search/node";
        String expectedSchema = "src/test/resources/json/question-schema.json";

        Response response = requestSpec.get(endpoint);

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(expectedSchema)));
    }
}
