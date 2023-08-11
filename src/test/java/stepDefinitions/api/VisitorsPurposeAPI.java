package stepDefinitions.api;


import hooks.api.HooksAPI;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import pojos.VisitorsPurpose.Pojo_VisitorsPurpose;
import pojos.VisitorsPurpose.Pojo_VisitorsPurposeLists;

import static hooks.api.HooksAPI.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class VisitorsPurposeAPI {



    Response response;

    JSONObject reqBody;




    @Then("The contents of the list data with id: {string} in the VisitorPurpose Response Body should be verified.")
    public void the_contents_of_the_list_data_with_id_in_the_visitor_purpose_response_body_should_be_verified(String id) {
        String url = "https://qa.wonderworldcollege.com/api/visitorsPurposeId";
        /*

        reqBody = new JSONObject();

        reqBody.put("id",id);
        //reqBody.put("visitors_purpose","Marketing");
        //reqBody.put("description","");
        //reqBody.put("created_at","2023-01-18 01:07:12");

        JSONObject expData = new JSONObject();
        expData.put("id",id);
        expData.put("visitors_purpose","Marketing");
        expData.put("description","");
        expData.put("created_at","2023-01-18 01:07:12");

        response = given()
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(url);
        response.prettyPrint();
        // Assertion
        JsonPath respJP = response.jsonPath();

        // Assert.assertEquals(expData.getJSONObject("lists").get("id"),respJP.get("id"));
         */
        reqBody = new JSONObject();

        reqBody.put("id",id);
        response=given().contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                        .when()
                                .body(reqBody.toString())
                                        .get(url);
        response.prettyPrint();

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .body("lists.id", Matchers.equalTo("1"),
                "lists.visitors_purpose",Matchers.equalTo("Marketing "),
                        "lists.description",Matchers.equalTo(""),
                        "lists.created_at",Matchers.equalTo("2023-01-18 01:07:12")
                );



/*
        Pojo_VisitorsPurpose data = new Pojo_VisitorsPurpose(
                id,"Marketing","","2023-01-18 01:07:12"
        );
        Pojo_VisitorsPurpose requestBody = data;
        Pojo_VisitorsPurposeLists expData = new Pojo_VisitorsPurposeLists();

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + HooksAPI.token)
                .when()
                .body(requestBody.toString())
                .post(CommonAPI.fullPath);

        response.prettyPrint();

        Pojo_VisitorsPurposeLists respPojo = response.as(Pojo_VisitorsPurposeLists.class);

        assertEquals(expData.getLists().getId(),respPojo.getLists().getId());
        assertEquals(expData.getLists().getVisitors_purpose(),respPojo.getLists().getVisitors_purpose());
        assertEquals(expData.getLists().getCreated_at(),respPojo.getLists().getCreated_at());

 */

    }


    @Then("Postrequest sent with {string} and {string}and {string} and {string} must have {string} and {string}")
    public void postrequestSentWithAndAndAndMustHaveAnd(String key1, String value1, String key2, String value2, String status, String message) {
        reqBody = new JSONObject();

        reqBody.put(key1,value1);
        reqBody.put(key2,value2);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(CommonAPI.fullPath);

        int intStatus = Integer.parseInt(status);

        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));
    }

    @Then("Postrequest with invalid Authorization sent with {string} and {string}and {string} and {string} must have {string} and {string}")
    public void postrequestWithInvalidAuthorizationSentWithAndAndAndMustHaveAnd(String key1, String value1, String key2, String value2, String status, String message) {
        reqBody = new JSONObject();

        reqBody.put(key1,value1);
        reqBody.put(key2,value2);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + invalidToken)
                .when()
                .body(reqBody.toString())
                .post(CommonAPI.fullPath);

        int intStatus = Integer.parseInt(status);

        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));
    }
}
