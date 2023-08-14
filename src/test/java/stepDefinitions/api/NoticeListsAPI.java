package stepDefinitions.api;


import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.json.JSONObject;

import hooks.api.HooksAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import pojos.Pojo_VisitorsList;
import utilities.ConfigReader;

import java.util.Arrays;

import static hooks.api.HooksAPI.spec;
import static hooks.api.HooksAPI.token;
import static io.restassured.RestAssured.given;
public class NoticeListsAPI {
    public static String fullPath;

    private Response apiResponse;

    JSONObject reqBody;

/*
    @Given ("the following fields should be verified:")
    public void theFollowingFieldsShouldBeVerified(){
        Assert.assertEquals("34 ", apiResponse.jsonPath().getString("id"));
        Assert.assertEquals("notice", apiResponse.jsonPath().getString("type"));
        Assert.assertEquals("wonder-world-college-receives-accreditation-for-its-outstanding-business-program", apiResponse.jsonPath().getString("slug"));
        Assert.assertEquals("Wonder World College Receives Accreditation for its Outstanding Business Program", apiResponse.jsonPath().getString("title"));
        Assert.assertEquals("2023-07-04", apiResponse.jsonPath().getString("date"));


        Assert.assertEquals("no", apiResponse.jsonPath().getString("is_active"));
        Assert.assertEquals("2023-05-30 17:47:20", apiResponse.jsonPath().getString("created_at"));

    }


 */

    @Then("The contents of the list data with id: {string} in the Notice List Response Body should be verified.")
    public void theContentsOfTheListDataWithIdInTheNoticeListResponseBodyShouldBeVerified(String id) {
        String url = "https://qa.wonderworldcollege.com/api/getNoticeById";
        reqBody = new JSONObject();

        reqBody.put("id",id);
        Response response = given().contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
                .when()
                .body(reqBody.toString())
                .get(url);
        response.prettyPrint();

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .body("lists.id", Matchers.equalTo("34"),
                        "lists.type",Matchers.equalTo("notice "),
                        "lists.slug",Matchers.equalTo("wonder-world-college-receives-accreditation-for-its-outstanding-business-program"),
                        "lists.url",Matchers.equalTo("read/wonder-world-college-receives-accreditation-for-its-outstanding-business-program"),
                        "lists.title",Matchers.equalTo("Wonder World College Receives Accreditation for its Outstanding Business Program"),
                        "lists.date",Matchers.equalTo("2023-07-04"),
                        "lists.event_start",Matchers.equalTo(""),
                        "lists.event_end",Matchers.equalTo(""),
                        "lists.event_venue",Matchers.equalTo(""),
                        "lists.description",Matchers.equalTo("Wonder World College's Business Program receives well-deserved recognition\n" +
                                "             as it receives accreditation from a renowned accrediting body. This accreditation affirms\n" +
                                "              the program's exceptional quality, rigorous curriculum, and industry-relevant approach,\n" +
                                "               ensuring that students receive a top-notch business education. Wonder World College takes\n" +
                                "                pride in providing students with the knowledge and skills necessary for success in the dynamic\n" +
                                "                 business world."),
                        "lists.is_active",Matchers.equalTo("no"),
                        "lists.created_at",Matchers.equalTo("2023-05-30 17:47:20"),
                        "lists.meta_title",Matchers.equalTo(""),
                        "lists.meta_keyword",Matchers.equalTo(""),
                        "lists.feature_image",Matchers.equalTo(""),
                        "lists.publish_date",Matchers.equalTo(""),
                        "lists.publish",Matchers.equalTo("0"),
                        "lists.sidebar",Matchers.equalTo("")
                );



    }

    @Given("The contents of the list data with different id {string} in the NoticeList Response Body should be verified.")
    public void theContentsOfTheListDataWithDifferentIdInTheNoticeListResponseBodyShouldBeVerified(String id) {
        String url = "https://qa.wonderworldcollege.com/api/getNoticeById";
        reqBody = new JSONObject();

        reqBody.put("id",id);
        Response response = given().contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
                .when()
                .body(reqBody.toString())
                .get(url);
        response.prettyPrint();

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .body("lists.id", Matchers.equalTo("33"),
                        "lists.type",Matchers.equalTo("notice"),
                        "lists.slug",Matchers.equalTo("students-from-wonder-world-college-honored-at-regional-art-exhibition"),
                        "lists.url",Matchers.equalTo("read/students-from-wonder-world-college-honored-at-regional-art-exhibition"),
                        "lists.title",Matchers.equalTo("Students from Wonder World College Honored at Regional Art Exhibition"),
                        "lists.date",Matchers.equalTo("2023-08-07"),
                        "lists.event_start",Matchers.equalTo(null),
                        "lists.event_end",Matchers.equalTo(null),
                        "lists.event_venue",Matchers.equalTo(null),
                        "lists.description",Matchers.equalTo("Talented artists from Wonder World College showcased their artistic " +
                                "prowess at the esteemed Regional Art Exhibition, earning recognition and accolades." +
                                " Their captivating artwork captivated the audience and impressed the judges, solidifying" +
                                " Wonder World College's reputation as a nurturing environment for artistic expression." +
                                " These talented students continue to push boundaries and inspire others through their creative endeavors."),
                        "lists.is_active",Matchers.equalTo("no"),
                        "lists.created_at",Matchers.equalTo("2023-07-19 08:00:19"),
                        "lists.meta_title",Matchers.equalTo(""),
                        "lists.meta_description",Matchers.equalTo(""),
                        "lists.meta_keyword",Matchers.equalTo(""),
                        "lists.feature_image",Matchers.equalTo(""),
                        "lists.publish_date",Matchers.equalTo(null),
                        "lists.publish",Matchers.equalTo("0"),
                        "lists.sidebar",Matchers.equalTo(null)
                );




    }

    @Then("Postrequest sent with {string}, {string}, {string}, {string}")
    public void postrequestSentWith(String type, String title, String description  , String slug) {

        String endPoint="https://qa.wonderworldcollege.com/api/addNotice";
        reqBody.put("type","testtype");
        reqBody.put("title","testtitle");
        reqBody.put("description","testdescription");
        reqBody.put("slug","testslug");

        apiResponse = given()
                .when().body(reqBody.toString()).contentType(ContentType.JSON)
                .put(endPoint);
        apiResponse.prettyPrint();

    }

    @Then("the response status code should be {int} And the response body's message should be {string}")
    public void theResponseStatusCodeShouldBeAndTheResponseBodySMessageShouldBe(int statusCode, String message) {
        Assert.assertEquals(statusCode,apiResponse.statusCode());
        Assert.assertEquals(message,apiResponse.contentType());

    }

}
