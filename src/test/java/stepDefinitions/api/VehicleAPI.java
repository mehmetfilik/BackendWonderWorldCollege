package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.ConfigReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hooks.api.HooksAPI.spec;
import static hooks.api.HooksAPI.token;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class VehicleAPI {
    String fullPath;

    Response response;

    JSONObject reqBody;

    JsonPath responseJP;

    JSONObject requestBody;

    @Given("User sets {string} path param")
    public void user_sets_path_param(String rawPaths) {
        String[] paths = rawPaths.split("/");

        System.out.println(Arrays.toString(paths));


        StringBuilder tempPath = new StringBuilder("/{");


        for (int i = 0; i < paths.length; i++) {

            String key = "pp" + i;
            String value = paths[i].trim();

            HooksAPI.spec.pathParam(key, value);

            tempPath.append(key + "}/{");
        }
        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));

        fullPath = tempPath.toString(); // {pp1}/{pp2}/{pp3}
        System.out.println("fullPath = " + fullPath);
    }

    @Given("For {string} is sent Get request")
    public void for_is_sent_get_request(String string) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .get(fullPath);

        response.prettyPrint();
    }

    @Given("Verifies that the status code in Response is {int}")
    public void verifies_that_the_status_code_in_response_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("Verifies that {string} is {string} in Response")
    public void verifies_that_is_in_response(String bodyName, String value) {
        JsonPath responseJP = response.jsonPath();

        assertEquals(value, responseJP.get(bodyName));
    }

    @When("A GET request is sent with invalid authorization")
    public void aGETRequestIsSentWithInvalidAuthorization() {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + HooksAPI.invalidToken)
                .when()
                .get(fullPath);


    }

    @Then("Post request sent with {string} {string} and {string} must have {string} and {string}")
    public void postRequestSentWithAndMustHaveAnd(String str, String key, String value, String status, String message) {
//
        if (str.equalsIgnoreCase("valid authorization")) {
            reqBody = new JSONObject();

            reqBody.put(key, value);

            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Bearer " + HooksAPI.token)
                    .when()
                    .body(reqBody.toString())
                    .post(fullPath);

            int intStatus = Integer.parseInt(status);

            response.prettyPrint();

            responseJP = response.jsonPath();

            response
                    .then()
                    .assertThat()
                    .statusCode(intStatus)
                    .contentType(ContentType.JSON)
                    .body("message", Matchers.equalTo(message));
        } else {
            reqBody = new JSONObject();

            reqBody.put(key, value);

            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Bearer " + HooksAPI.invalidToken)
                    .when()
                    .body(reqBody.toString())
                    .post(fullPath);

            int intStatus = Integer.parseInt(status);


            response
                    .then()
                    .assertThat()
                    .statusCode(intStatus)
                    .contentType(ContentType.JSON)
                    .body("message", Matchers.equalTo(message));
        }


    }


    @When("With invalit Authorization is sent Get request must status: {int} and message: {string}")
    public void withInvalitAuthorizationIsSentGetRequestMustStatusAndMessage(int statusCode, String message) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + HooksAPI.invalidToken)
                .when()
                .get(fullPath);

        response.then().assertThat().statusCode(statusCode).body("message", Matchers.equalTo(message));


    }

    /*
      "status": 200,
    "message": "Success",
    "Token_remaining_time": 25,
    "lists"
     */

    @Given("Verify the data content with lists content id={int} in the response body.")
    public void verifyTheDataContentWithListsContentIdInTheResponseBody(int arg0) {
//
        JSONObject listData = new JSONObject();
        JSONObject expectedData = new JSONObject();
        listData.put("id", "1");
        listData.put("vehicle_no", "VH1001");
        listData.put("vehicle_photo", "1677502387-149436744063fca7b3a1796!fd.png");
        listData.put("manufacture_year", "2017");
        listData.put("registration_number", "FVFF-08797865");
        listData.put("chasis_number", "45453");
        listData.put("max_seating_capacity", "50");
        listData.put("driver_name", "Michel");
        listData.put("driver_licence", "R534534");
        listData.put("driver_contact", "8667777869");
        listData.put("note", "");
        listData.put("created_at", "2023-02-27 07:53:07");

        expectedData.put("status", 200);
        expectedData.put("message", "Success");
        expectedData.put("Token_remaining_time", 25);
        expectedData.put("lists", listData);


        reqBody = new JSONObject();

        reqBody.put("id", "1");

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        responseJP = response.jsonPath();


        assertEquals(expectedData.getJSONObject("lists").get("id"),
                responseJP.get("lists.id"));
        assertEquals(expectedData.getJSONObject("lists").get("vehicle_no"),
                responseJP.get("lists.vehicle_no"));
        assertEquals(expectedData.getJSONObject("lists").get("vehicle_photo"),
                responseJP.get("lists.vehicle_photo"));
        assertEquals(expectedData.getJSONObject("lists").get("manufacture_year"),
                responseJP.get("lists.manufacture_year"));
        assertEquals(expectedData.getJSONObject("lists").get("registration_number"),
                responseJP.get("lists.registration_number"));
        assertEquals(expectedData.getJSONObject("lists").get("chasis_number"),
                responseJP.get("lists.chasis_number"));
        assertEquals(expectedData.getJSONObject("lists").get("max_seating_capacity"),
                responseJP.get("lists.max_seating_capacity"));
        assertEquals(expectedData.getJSONObject("lists").get("driver_name"),
                responseJP.get("lists.driver_name"));
        assertEquals(expectedData.getJSONObject("lists").get("driver_licence"),
                responseJP.get("lists.driver_licence"));
        assertEquals(expectedData.getJSONObject("lists").get("driver_contact"),
                responseJP.get("lists.driver_contact"));
        assertEquals(expectedData.getJSONObject("lists").get("note"),
                responseJP.get("lists.note"));
        assertEquals(expectedData.getJSONObject("lists").get("created_at"),
                responseJP.get("lists.created_at"));

    }




    @Then("User posts valid authorization info and correct data to api.vehicleAdd, expecting status code {int} and confirming response body {string} as {string}.")
    public void userPostsValidAuthorizationInfoAndCorrectDataToApiVehicleAddExpectingStatusCodeAndConfirmingResponseBodyAs(int statusCode, String bodyName, String value) {

        //
        requestBody = new JSONObject();
        requestBody.put("vehicle_no", "TH5007");
        requestBody.put("vehicle_photo", "7584709375093705973097490479895!fd.png");
        requestBody.put("manufacture_year", "2023");
        requestBody.put("registration_number", "KMTT-957845");
        requestBody.put("chasis_number", "10643");
        requestBody.put("max_seating_capacity", "30");
        requestBody.put("driver_name", "Ahmet Enhakikiöz");
        requestBody.put("driver_licence", "T74879489");
        requestBody.put("driver_contact", "94578849850");
        requestBody.put("note", "");
        requestBody.put("created_at", "2023-08-11 14:43:05");

        RequestSpecification spec;
        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();
        spec.pathParams("pp1","api","pp2","vehicleAdd");

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .body(requestBody.toString())
                .post("/{pp1}/{pp2}");

        response.then().assertThat().statusCode(statusCode).body(bodyName,Matchers.equalTo(value));

    }

    @Then("When invalid auth or incomplete data is sent to api.vehicleAdd, confirm status code {int} and response {string} as {string}.")
    public void whenInvalidAuthOrIncompleteDataIsSentToApiVehicleAddConfirmStatusCodeAndResponseAs(int statusCode, String bodyName, String value) {
        //

        RequestSpecification spec;
        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();
        spec.pathParams("pp1", "api", "pp2", "vehicleAdd");
        requestBody=new JSONObject();
        requestBody.put("vehicle_no", "TH5007");
        requestBody.put("vehicle_photo", "7584709375093705973097490479895!fd.png");
        requestBody.put("manufacture_year", "2023");
        requestBody.put("registration_number", "KMTT-957845");
        requestBody.put("chasis_number", "10643");
        requestBody.put("max_seating_capacity", "30");
        requestBody.put("driver_name", "Ahmet Enhakikiöz");

        String url="https://qa.wonderworldcollege.com/api/vehicleAdd";
        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + HooksAPI.invalidToken)
                .when()
                .body(requestBody.toString())
                .post("/{pp1}/{pp2}");

        response.then().assertThat().statusCode(403).body("message", Matchers.equalTo("failed"));





    }

    @Then("The contents of the list data with id: {string} in the vehicleList Response Body should be verified.")
    public void theContentsOfTheListDataWithIdInTheVehicleListResponseBodyShouldBeVerified(String id) {
        String url = "https://qa.wonderworldcollege.com/api/vehicleList";

        reqBody = new JSONObject();

        reqBody.put("id", id);
        response = given().contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
                .when()
                .body(reqBody.toString())
                .get(url);

        response.prettyPrint();


        responseJP = response.jsonPath();

        response.then().assertThat().body("lists.vehicle_no", Matchers.equalTo("VH1001"));
        response.then().assertThat().body("lists.vehicle_model", Matchers.equalTo("Volvo Bus"));
        response.then().assertThat().body("lists.vehicle_photo", Matchers.equalTo("1677502387-149436744063fca7b3a1796!fd.png"));
        response.then().assertThat().body("lists.manufacture_year", Matchers.equalTo("2017"));
        response.then().assertThat().body("lists.registration_number", Matchers.equalTo("FVFF-08797865"));
        response.then().assertThat().body("lists.chasis_number", Matchers.equalTo("45453"));
        response.then().assertThat().body("lists.max_seating_capacity", Matchers.equalTo("50"));
        response.then().assertThat().body("lists.driver_name", Matchers.equalTo("Michel"));
        response.then().assertThat().body("lists.driver_licence", Matchers.equalTo("R534534"));
        response.then().assertThat().body("lists.driver_contact", Matchers.equalTo("8667777869"));
        response.then().assertThat().body("lists.note", Matchers.equalTo(""));
        response.then().assertThat().body("lists.created_at", Matchers.equalTo("2023-02-27 07:53:07"));
    }

    @Then("When a Get request is made with invalid Authorization, the status must be {int}, and the message must be failed.")
    public void whenAGetRequestIsMadeWithInvalidAuthorizationTheStatusMustBeAndTheMessageMustBeFailed(int arg0) {

        RequestSpecification spec;
        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();
        spec.pathParams("pp1", "api", "pp2", "vehicleList");

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + HooksAPI.invalidToken)
                .when()
                .get("/{pp1}/{pp2}");
            responseJP=response.jsonPath();

        response.then().assertThat().statusCode(403).body("message", Matchers.equalTo("failed"));

    }

    @Given("Create a new vehicle registration via API")
    public void createANewVehicleRegistrationViaAPI() {

        RequestSpecification spec;
        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();
        spec.pathParams("pp1", "api", "pp2", "vehicleAdd");

        requestBody = new JSONObject();
        requestBody.put("vehicle_no", "TH2023");
        requestBody.put("vehicle_model","Mercedes VITO");
        requestBody.put("vehicle_photo", "7584709375093705973097490479895!fd.png");
        requestBody.put("manufacture_year", "2023");
        requestBody.put("registration_number", "KMTT-957845");
        requestBody.put("chasis_number", "10643");
        requestBody.put("max_seating_capacity", "30");
        requestBody.put("driver_name", "Ahmet Enhakikiöz");
        requestBody.put("driver_licence", "T74879489");
        requestBody.put("driver_contact", "94578849850");
        requestBody.put("note", "");


        response=given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when().body(requestBody.toString())
                .post("/{pp1}/{pp2}");

        response.prettyPrint();




    }


    @Then("Api üzerinden yeni bir  arac kaydini post requestle, {string} {string} girerek dogrular.")
    public void apiÜzerindenYeniBirAracKaydiniPostRequestleGirerekDogrular(String key, String value) {

        reqBody = new JSONObject();

        reqBody.put(key, value);

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.then().assertThat().statusCode(200).body("lists.driver_name",Matchers.equalTo("Ahmet Enhakikiöz"));

    }
}