package stepDefinitions.api;


import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.json.JSONObject;

import hooks.api.HooksAPI;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;

import static hooks.api.HooksAPI.spec;
import static hooks.api.HooksAPI.token;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class NoticeListsAPI {
    public static String fullPath;

    private Response apiResponse;
    Response response;
    String noticeAddId;
    String updatedId;

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

        reqBody.put("id", id);
        Response response = given().contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
                .when()
                .body(reqBody.toString())
                .get(url);
        response.prettyPrint();

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .body("lists.id", Matchers.equalTo("31"),
                        "lists.type", Matchers.equalTo("notice"),
                        "lists.slug", Matchers.equalTo("wonder-world-college-wins-national-stem-competition-for-innovative-robotics-design"),
                        "lists.url", Matchers.equalTo("read/wonder-world-college-wins-national-stem-competition-for-innovative-robotics-design"),
                        "lists.title", Matchers.equalTo("Wonder World College Wins National STEM Competition for Innovative Robotics Design"),
                        "lists.date", Matchers.equalTo("2023-09-01"),
                        "lists.event_start", Matchers.equalTo(null),
                        "lists.event_end", Matchers.equalTo(null),
                        "lists.event_venue", Matchers.equalTo(null),
                        "lists.description", Matchers.equalTo("Wonder World College's Robotics Team emerged victorious at the prestigious National" +
                                " STEM Competition, showcasing their exceptional skills and innovative designs. Their cutting-edge robotics project impressed " +
                                "the judges, earning them top honors among fierce competitors from across the country. This achievement highlights Wonder World " +
                                "College's commitment to excellence in STEM education and fostering a culture of innovation."),
                        "lists.is_active", Matchers.equalTo("no"),
                        "lists.created_at", Matchers.equalTo("2023-07-19 12:00:49"),
                        "lists.meta_title", Matchers.equalTo(""),
                        "lists.meta_keyword", Matchers.equalTo(""),
                        "lists.feature_image", Matchers.equalTo(""),
                        "lists.publish_date", Matchers.equalTo(null),
                        "lists.publish", Matchers.equalTo("0"),
                        "lists.sidebar", Matchers.equalTo(null)
                );


    }

    @Given("The contents of the list data with different id {string} in the NoticeList Response Body should be verified.")
    public void theContentsOfTheListDataWithDifferentIdInTheNoticeListResponseBodyShouldBeVerified(String id) {
        String url = "https://qa.wonderworldcollege.com/api/getNoticeById";
        reqBody = new JSONObject();

        reqBody.put("id", id);
        Response response = given().contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
                .when()
                .body(reqBody.toString())
                .get(url);
        response.prettyPrint();

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .body("lists.id", Matchers.equalTo("31"),
                        "lists.type", Matchers.equalTo("notice"),
                        "lists.slug", Matchers.equalTo("wonder-world-college-wins-national-stem-competition-for-innovative-robotics-design"),
                        "lists.url", Matchers.equalTo("read/wonder-world-college-wins-national-stem-competition-for-innovative-robotics-design"),
                        "lists.title", Matchers.equalTo("Wonder World College Wins National STEM Competition for Innovative Robotics Design"),
                        "lists.date", Matchers.equalTo("2023-09-01"),
                        "lists.event_start", Matchers.equalTo(null),
                        "lists.event_end", Matchers.equalTo(null),
                        "lists.event_venue", Matchers.equalTo(null),
                        "lists.description", Matchers.equalTo("Wonder World College's Robotics Team emerged victorious at the prestigious" +
                                " National STEM Competition, showcasing their exceptional skills and innovative designs. Their cutting-edge robotics " +
                                "project impressed the judges, earning them top honors among fierce competitors from across the country. This achievement " +
                                "highlights Wonder World College's commitment to excellence in STEM education and fostering a culture of innovation."),
                        "lists.is_active", Matchers.equalTo("no"),
                        "lists.created_at", Matchers.equalTo("2023-07-19 12:00:49"),
                        "lists.meta_title", Matchers.equalTo(""),
                        "lists.meta_description", Matchers.equalTo(""),
                        "lists.meta_keyword", Matchers.equalTo(""),
                        "lists.feature_image", Matchers.equalTo(""),
                        "lists.publish_date", Matchers.equalTo(null),
                        "lists.publish", Matchers.equalTo("0"),
                        "lists.sidebar", Matchers.equalTo(null)
                );
    }

    @Then("Postrequest sent with {string} Authorization {string}, {string}, {string}, {string} must have {int} and {string}")
    public void postrequest_sent_with_authorization_must_have_and(String str, String type, String title, String description, String slug,  int status, String message) {
        reqBody = new JSONObject();

        reqBody.put("type",type);
        reqBody.put("title",title);
        reqBody.put("description",description);
        reqBody.put("slug",slug);

        if (str.equalsIgnoreCase("Valid")) {
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Bearer " + HooksAPI.token)
                    .when()
                    .body(reqBody.toString())
                    .post(CommonAPI.fullPath);

            response.prettyPrint();


        } else {
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Bearer " + HooksAPI.invalidToken)
                    .when()
                    .body(reqBody.toString())
                    .post(CommonAPI.fullPath);

            response.prettyPrint();
        }

        response
                .then()
                .assertThat()
                .statusCode(status)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));

        String responseBody = response.getBody().asString();
        JsonPath respJP = new JsonPath(responseBody);
        noticeAddId = respJP.getString("addId");

        System.out.println(noticeAddId);


    }

    @Then("In NoticeList Postrequest sent with {string} must have status: {int} and message: {string}")
    public void inNoticeListPostrequestSentWithMustHaveStatusAndMessage(String id, int status, String message) {
        JSONObject reqBody = new JSONObject();

        reqBody.put(id, noticeAddId);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(CommonAPI.fullPath);

        response.prettyPrint();

        String[] expectedArray = {"id", "type","title", "description", "slug"};

        JsonPath resJP = response.jsonPath();

        String actualData = resJP.get("lists").toString();
        System.out.println(actualData);
        resJP.prettyPrint();

        for (int i = 0; i < expectedArray.length; i++) {
            assertTrue(actualData.contains(expectedArray[i]));
        }
    }

    @Then("In Notice List with {string} Authorization is sent Patch request must id: {string}, update_id_key: {string}, status: {int} and message: {string}")
    public void inNoticeListWithAuthorizationIsSentPatchRequestMustIdUpdate_id_keyStatusAndMessage(String str, String id, String updateId, int status, String message) {
        JSONObject reqBody = new JSONObject();

        reqBody.put("id", id);
        reqBody.put("type", "testtype14");
        reqBody.put("title", "testtitle14");
        reqBody.put("description", "testdescription14");
        reqBody.put("slug", "testslug14");


        if (str.equalsIgnoreCase("Valid")){
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization","Bearer " + HooksAPI.token)
                    .when()
                    .body(reqBody.toString())
                    .patch(CommonAPI.fullPath);

            response.prettyPrint();

            response
                    .then()
                    .assertThat()
                    .statusCode(status)
                    .contentType(ContentType.JSON)
                    .body("message", Matchers.equalTo(message),updateId,Matchers.equalTo(id));

            String responseBody = response.getBody().asString();
            JsonPath respJP = new JsonPath(responseBody);
            updatedId = respJP.getString(updateId);

            System.out.println("sout updated Id: "+updatedId);

        }else {
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization","Bearer " + HooksAPI.invalidToken)
                    .when()
                    .body(reqBody.toString())
                    .patch(CommonAPI.fullPath);

            response.prettyPrint();

            response
                    .then()
                    .assertThat()
                    .statusCode(status)
                    .contentType(ContentType.JSON)
                    .body("message", Matchers.equalTo(message));
        }

    }


    @Then("After Notice List updating Postrequest sent with {string} must have status: {int} and message: {string}")
    public void afterNoticeListUpdatingPostrequestSentWithMustHaveStatusAndMessage(String id, int status, String message) {
        JSONObject reqBody = new JSONObject();

        reqBody.put(id,updatedId);
        System.out.println(updatedId);


        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(CommonAPI.fullPath);

        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(status)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message),"lists.id",Matchers.equalTo(updatedId));


    }


    @Then("After Notice deleting Postrequest sent with {string} must have status: {int} and message: {string}")
    public void afterNoticeDeletingPostrequestSentWithMustHaveStatusAndMessage(String id, int status, String message) {
        JSONObject reqBody = new JSONObject();

        reqBody.put(id,CommonAPI.deletedId);


        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(CommonAPI.fullPath);

        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(status)
                .body("message",Matchers.equalTo(message));


    }
}

