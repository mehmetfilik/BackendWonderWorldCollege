package stepDefinitions.api;


import hooks.api.HooksAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import pojos.VisitorsPurpose.Pojo_VisitorsPurpose;
import pojos.VisitorsPurpose.Pojo_VisitorsPurposeLists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hooks.api.HooksAPI.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class VisitorsPurposeAPI {

    Response response;

    JSONObject reqBody;
    String addId;

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

        int neuId = Integer.parseInt(id);
        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .body("lists.id", Matchers.equalTo(id),
                        "lists.visitors_purpose",Matchers.equalTo("Parent Teacher Meeting"),
                        "lists.description",Matchers.equalTo(""),
                        "lists.created_at",Matchers.equalTo("2023-01-18 01:07:12")
                );
        /*
        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .body("lists.id", Matchers.equalTo(id),
                "lists.visitors_purpose",Matchers.equalTo("Marketing "),
                        "lists.description",Matchers.equalTo(""),
                        "lists.created_at",Matchers.equalTo("2023-01-18 01:07:12")
                );

         */

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

        // Bu bölüm TC_03
        HashMap<String,Object> respMap = response.as(HashMap.class);

        System.out.println("respMap"+respMap.toString());

        addId = respMap.get("addId").toString();
        System.out.println(addId);

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

    @Then("Patchrequest sent with {string} , {string}, {string} and {string},{string} and {string} and must have {string} and {string}")
    public void patchrequestSentWithAndAndAndMustHaveAnd(String key1, String value1, String key2, String value2, String key3, String value3, String status, String message) {
        int value1Id= Integer.parseInt(value1);
        reqBody = new JSONObject();

        reqBody.put(key1,value1Id);
        reqBody.put(key2,value2);
        reqBody.put(key3,value3);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .patch(CommonAPI.fullPath);

        int intStatus = Integer.parseInt(status);

        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message),
                        "updateId",Matchers.equalTo(value1Id));  // Bu kisim [API_US4-->TC_03]
        HashMap<String,Object> respMap = response.as(HashMap.class);

        // Bu kisim [API_US4-->TC_04]
        Assert.assertEquals(value1Id,respMap.get("updateId"));
        System.out.println("respMap"+respMap.toString());

        String updateId = respMap.get("updateId").toString();
        System.out.println(updateId);
        JSONObject reqBody1 = new JSONObject();
        int intaddId = Integer.parseInt(addId);
        reqBody.put("id",intaddId);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody1.toString())
                .post(CommonAPI.fullPath);
        response.prettyPrint();

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .body("lists.id", Matchers.equalTo(intaddId));
    }

    @Then("Patchrequest sent with invalid Authorization with {string} , {string}, {string}, {string},{string} and {string} and must have {string} and {string}")
    public void patchrequestSentWithInvalidAuthorizationWithAndAndMustHaveAnd(String key1, String value1, String key2, String value2, String key3, String value3, String status, String message) {
        int value1Id= Integer.parseInt(value1);
        reqBody = new JSONObject();

        reqBody.put(key1,value1Id);
        reqBody.put(key2,value2);
        reqBody.put(key3,value3);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + invalidToken)
                .when()
                .body(reqBody.toString())
                .patch(CommonAPI.fullPath);

        int intStatus = Integer.parseInt(status);

        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));
    }

    @Given("Checking the id updated with Postrequest id: {int} from API")
    public void checkingTheIdUpdatedWithPostrequestIdFromAPI(int id) {
        String url = "https://qa.wonderworldcollege.com/api/visitorsPurposeId";
        reqBody = new JSONObject();

        reqBody.put("id",id);
        response=given().contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(url);
        response.prettyPrint();

        String newid=id+"";
        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .body("lists.id", Matchers.equalTo(newid));

    }

    @Given("Creating a new record with Post Request and deleting it with deleterequest.")
    public void creatingANewRecordWithPostRequestAndDeletingItWithDeleterequest() {
        String urlPost ="https://qa.wonderworldcollege.com/api/visitorsPurposeAdd";
        String key1 = "visitors_purpose";
        String key2 = "description";
        String value1 = "Veli Ziyareti";
        String value2 = "Delete icin Kayit";
        reqBody = new JSONObject();

        reqBody.put(key1,value1);
        reqBody.put(key2,value2);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(urlPost);

        int intStatus = 200;
        String message="Success";

        response.prettyPrint();

        /*
        response
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));

         */

        HashMap<String,Object> respMap = response.as(HashMap.class);

        System.out.println("respMap"+respMap.toString());

        addId = respMap.get("addId").toString();
        int addIdint = Integer.parseInt(addId);
        System.out.println(addId);

        // Delete islemine geciyoruz
        String urlDelete = "https://qa.wonderworldcollege.com/api/visitorsPurposeDelete";
        reqBody.put("id",addIdint);
        Response response2 =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .delete(urlDelete);
// Assertion
        response2
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));

        response2.prettyPrint();

    }

    @Given("Creating a new record with postrequest and verifying that it has not been deleted with invalid authorization")
    public void creatingANewRecordWithPostrequestAndVerifyingThatItHasNotBeenDeletedWithInvalidAuthorization() {
        String urlPost ="https://qa.wonderworldcollege.com/api/visitorsPurposeAdd";
        String key1 = "visitors_purpose";
        String key2 = "description";
        String value1 = "Veli Ziyareti";
        String value2 = "Delete icin Kayit";
        reqBody = new JSONObject();

        reqBody.put(key1,value1);
        reqBody.put(key2,value2);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(urlPost);

        int intStatus = 403;
        String message="failed";

        response.prettyPrint();

        /*
        response
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));

         */

        HashMap<String,Object> respMap = response.as(HashMap.class);

        System.out.println("respMap"+respMap.toString());

        addId = respMap.get("addId").toString();
        int addIdint = Integer.parseInt(addId);
        System.out.println(addId);

        // Delete islemine geciyoruz
        String urlDelete = "https://qa.wonderworldcollege.com/api/visitorsPurposeDelete";
        reqBody.put("id",addIdint);
        Response response2 =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + invalidToken)
                .when()
                .body(reqBody.toString())
                .delete(urlDelete);
// Assertion
        response2
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));

        response2.prettyPrint();

    }

    @Given("Checking for a deleted record")
    public void checkingForADeletedRecord() {
        String urlPost ="https://qa.wonderworldcollege.com/api/visitorsPurposeAdd";
        String key1 = "visitors_purpose";
        String key2 = "description";
        String value1 = "Veli Ziyareti";
        String value2 = "Delete icin Kayit";
        reqBody = new JSONObject();

        reqBody.put(key1,value1);
        reqBody.put(key2,value2);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(urlPost);

        int intStatus = 200;
        String message="Success";

        response.prettyPrint();

        /*
        response
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));

         */

        HashMap<String,Object> respMap = response.as(HashMap.class);

        System.out.println("respMap"+respMap.toString());

        addId = respMap.get("addId").toString();
        int addIdint = Integer.parseInt(addId);
        System.out.println(addId);

        // Delete islemine geciyoruz
        String urlDelete = "https://qa.wonderworldcollege.com/api/visitorsPurposeDelete";
        reqBody.put("id",addIdint);
        Response response2 =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .delete(urlDelete);
// Assertion
        response2
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));

        response2.prettyPrint();

        // API kontrol

        reqBody = new JSONObject();

        reqBody.put("id",addIdint);
        response=given().contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(urlPost);
        response.prettyPrint();

        String newid=addIdint+"";
        response.then()
                .assertThat()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo("failed")
                );
    }



    /*
    @And("The successful creation of a new visitor purpose")
    public void theSuccessfulCreationOfANewVisitorPurpose() {
        JSONObject json = new JSONObject(response.body());
        int newPurposeId = json.getInt("id");
        System.out.println(newPurposeId);
    }

     */
}
