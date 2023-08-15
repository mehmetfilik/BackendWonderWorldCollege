package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.Hook;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import utilities.Authentication;
import utilities.ConfigReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.junit.Assert.assertEquals;

public class TeacherAPI extends Authentication {


    public String tokenTeacherApi;
    public String invalidTokenTeacher;
    public String fullEndpoint;

    public Response response;

    public Response deletedResponse;

    String addId;

    public int deletedId;

    JSONObject requestBody = new JSONObject();
    JsonPath responseJP;

    public static JSONObject setRequestBody(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("class_id", "1");
        requestBody.put("section_id", "1");
        requestBody.put("session_id", "18");
        requestBody.put("staff_id", "13");
        requestBody.put("subject_group_subject_id", "21");
        requestBody.put("subject_id", "1");
        requestBody.put("homework_date", "2023-08-15");
        requestBody.put("submit_date", "2023-08-23");
        requestBody.put("marks", "100.00");
        requestBody.put("description", "delete");
        requestBody.put("create_date", "2023-08-15");
        requestBody.put("evaluation_date", JSONObject.NULL);
        requestBody.put("document", JSONObject.NULL);
        requestBody.put("created_by", "155");
        requestBody.put("evaluated_by", JSONObject.NULL);

        return requestBody;
    }

    //=================================================

    //=========================== =======================




    @Given("a valid authorization is provided")
    public void a_valid_authorization_is_provided() {
        tokenTeacherApi = teacherGenerateToken();
    }
    @When("a GET request is sent to {string} endpoint")
    public void a_get_request_is_sent_to_endpoint(String params) {
       fullEndpoint = ConfigReader.getProperty("base_url") + "/" + params;
       response = given().contentType(ContentType.JSON)
                    .when().header("Authorization", "Bearer " + tokenTeacherApi)
                    .get(fullEndpoint);
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }
    @Then("the response message should be {string}")
    public void the_response_message_should_be(String expectedMessage) {
        response.then().assertThat().body("message", Matchers.equalTo(expectedMessage));
    }

    @Given("GET request is sent to {string} endpoint with invalid authorization")
    public void getRequestIsSentToEndpointWithInvalidAuthorization(String params) {
        invalidTokenTeacher = teacherGenerateInvalidToken();
        fullEndpoint = ConfigReader.getProperty("base_url") + "/" + params;
        response = given().contentType(ContentType.JSON)
                .when().header("Authorization", "Bearer " + invalidTokenTeacher)
                .get(fullEndpoint);
    }

    @When("a PUT request is sent to {string} endpoint")
    public void aPUTRequestIsSentToEndpoint(String params) {
        requestBody.put("id",605);
        fullEndpoint = ConfigReader.getProperty("base_url") + "/" + params;
        response = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .header("Authorization", "Bearer " + tokenTeacherApi)
                .post(fullEndpoint);
    }

    @Then("the content of the response except for {string} should match the specified data")
    public void theContentOfTheResponseExceptForShouldMatchTheSpecifiedData(String arg0) {
        JSONObject expectedData= new JSONObject();
        JSONObject specifiedData = new JSONObject();
        specifiedData.put("id", "605");
        specifiedData.put("class_id", "3");
        specifiedData.put("section_id", "1");
        specifiedData.put("session_id", "18");
        specifiedData.put("subject_group_subject_id", "41");
        //...

        expectedData.put("lists",specifiedData);

        responseJP = response.jsonPath();

        assertEquals(expectedData.getJSONObject("lists").get("class_id")
                        ,responseJP.getString("lists.class_id"));
        assertEquals(expectedData.getJSONObject("lists").get("section_id")
                ,responseJP.getString("lists.section_id"));
        assertEquals(expectedData.getJSONObject("lists").get("session_id")
                ,responseJP.getString("lists.session_id"));

    }

    @When("a POST request is sent to {string} endpoint")
    public void aPOSTRequestIsSentToEndpoint(String params) {
        requestBody.put("id","605");
        fullEndpoint = ConfigReader.getProperty("base_url") + "/" + params;
        response = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .header("Authorization", "Bearer " + tokenTeacherApi)
                .post(fullEndpoint);

        response.prettyPrint();
    }

    @Given("POST request is sent to {string} endpoint with invalid authorization")
    public void postRequestIsSentToEndpointWithInvalidAuthorization(String params) {
        invalidTokenTeacher = teacherGenerateInvalidToken();
        requestBody.put("id","60");
        fullEndpoint = ConfigReader.getProperty("base_url") + "/" + params;
        response = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .header("Authorization", "Bearer " + invalidTokenTeacher)
                .post(fullEndpoint);
    }

    @Then("The contents of the list data within the response body are verified.")
    public void theContentsOfTheListDataWithinTheResponseBodyAreVerified() {
        JSONObject expectedData = new JSONObject();
        expectedData.put("class_id", "3");
        expectedData.put("submit_date","2023-08-03");
        expectedData.put("marks", "100.00");
        expectedData.put("created_by", "155");

        responseJP = response.jsonPath();

        assertEquals(expectedData.getString("class_id"),responseJP.getString("lists.class_id"));
        assertEquals(expectedData.getString("submit_date"),responseJP.getString("lists.submit_date"));
        assertEquals(expectedData.getString("marks"),responseJP.getString("lists.marks"));
        assertEquals(expectedData.getString("created_by"),responseJP.getString("lists.created_by"));
        //...
    }

    @When("a POST request body is sent to {string} endpoint")
    public void aPOSTRequestBodyIsSentToEndpoint(String params) {
        requestBody = setRequestBody();
        fullEndpoint = ConfigReader.getProperty("base_url") + "/" + params;
        response = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .header("Authorization", "Bearer " + tokenTeacherApi)
                .post(fullEndpoint);

        response.prettyPrint();

        String responseBody = response.getBody().asString();
        JsonPath respJP = new JsonPath(responseBody);
        addId = respJP.getString("addId");

        System.out.println(addId);
    }

    @Then("The validity of a created record is verified through the API")
    public void theValidityOfACreatedRecordIsVerifiedThroughTheAPI() {

        requestBody.put("id",addId);
        response = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .header("Authorization", "Bearer " + tokenTeacherApi)
                .post(fullEndpoint);

        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("message",Matchers.equalTo("Success"));

    }
    @When("a PATCH request is sent to {string} endpoint")
    public void aPATCHRequestIsSentToEndpoint(String params) {
        requestBody = setRequestBody();
        requestBody.put("id","784");

        fullEndpoint = ConfigReader.getProperty("base_url") + "/" + params;
        response = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .header("Authorization", "Bearer " + tokenTeacherApi)
                .patch(fullEndpoint);

        System.out.println(requestBody);
        response.prettyPrint();
    }


    @Given("PATCH request is sent to {string} endpoint with invalid authorization")
    public void patchRequestIsSentToEndpointWithInvalidAuthorization(String params) {
        invalidTokenTeacher = teacherGenerateInvalidToken();
        requestBody = setRequestBody();
        requestBody.put("id","784");
        fullEndpoint = ConfigReader.getProperty("base_url") + "/" + params;
        response = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .header("Authorization", "Bearer " + invalidTokenTeacher)
                .patch(fullEndpoint);

        response.prettyPrint();
    }

    @Then("Successful verification of the update record is confirmed.")
    public void successfulVerificationOfTheUpdateRecordIsConfirmed() {

        responseJP = response.jsonPath();
        Assert.assertEquals(requestBody.get("id"),responseJP.get("updatedId"));

    }

    @Then("The update of the homework entry to be modified through the API is performed, and its confirmation is validated via the API")
    public void theUpdateOfTheHomeworkEntryToBeModifiedThroughTheAPIIsPerformedAndItsConfirmationIsValidatedViaTheAPI() {

        requestBody.put("id","784");
        fullEndpoint = ConfigReader.getProperty("base_url") + "/" + "apiteacher/homeworkbyId";

         Response responsebyId = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .header("Authorization", "Bearer " + tokenTeacherApi)
                .post(fullEndpoint);


        responsebyId.prettyPrint();
        responseJP = responsebyId.jsonPath();

        assertEquals(requestBody.get("description"),responseJP.getString("lists.description"));
        //...
    }

    @When("a DELETE request body is sent to {string} endpoint")
    public void aDELETERequestBodyIsSentToEndpoint(String params) {

        requestBody = setRequestBody();
        fullEndpoint = ConfigReader.getProperty("base_url") + "/" + "apiteacher/homeworkAdd";
        response = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .header("Authorization", "Bearer " + tokenTeacherApi)
                .post(fullEndpoint);

        response.prettyPrint();

        JSONObject jsonResponse = new JSONObject(response.asString());
        deletedId = jsonResponse.getInt("addId");
        System.out.println("Deleted ID: " + deletedId);

        JSONObject deleteBody = new JSONObject();
        deleteBody.put("id",deletedId);
        fullEndpoint = ConfigReader.getProperty("base_url") + "/" + params;
                deletedResponse = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when().body(deleteBody.toString())
                .header("Authorization", "Bearer " + tokenTeacherApi)
                .delete(fullEndpoint);

        deletedResponse.prettyPrint();
    }

    @Given("DELETE request is sent to {string} endpoint with invalid authorization")
    public void deleteRequestIsSentToEndpointWithInvalidAuthorization(String params) {
        invalidTokenTeacher = teacherGenerateInvalidToken();
        JSONObject deleteBody = new JSONObject();
        deleteBody.put("id","755");
        fullEndpoint = ConfigReader.getProperty("base_url") + "/" + params;
        response = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when().body(deleteBody.toString())
                .header("Authorization", "Bearer " + invalidTokenTeacher)
                .delete(fullEndpoint);

        response.prettyPrint();
    }


    @Then("Verify DeletedId in response body matches id in DELETE request body to endpoint.")
    public void verifyDeletedIdInResponseBodyMatchesIdInDELETERequestBodyToEndpoint() {
        responseJP = response.jsonPath();
        JsonPath deletedResponseJP = deletedResponse.jsonPath();
        Assert.assertEquals(responseJP.getInt("addId"),deletedResponseJP.getInt("DeletedId"));
    }

    @Then("The deletion of the record is confirmed through the API")
    public void theDeletionOfTheRecordIsConfirmedThroughTheAPI() {

        requestBody.put("id",deletedId);
        fullEndpoint = ConfigReader.getProperty("base_url") + "/" + "apiteacher/homeworkbyId";
        response = given().spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .header("Authorization", "Bearer " + tokenTeacherApi)
                .post(fullEndpoint);

        response.prettyPrint();

        response.then().assertThat().statusCode(403);
        response.then().assertThat().body("message",Matchers.equalTo("failed"));
    }
}
