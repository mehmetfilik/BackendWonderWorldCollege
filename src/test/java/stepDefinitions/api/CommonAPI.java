package stepDefinitions.api;


import hooks.api.HooksAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.ObjectUtils;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import pojos.Pojo_VisitorsList;
import utilities.ConfigReader;

import java.util.Arrays;
import java.util.HashMap;

import static hooks.api.HooksAPI.spec;
import static hooks.api.HooksAPI.token;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CommonAPI {

    public static String fullPath;

    Response response;

    JSONObject reqBody;

    public static String deletedId;


    /*
    @Then("Login icin {string} ve {string} girilir.")
    public void loginIcinVeGirilir(String email, String password) {


        //{
        //  "email": "test@test.com",
        //  "password": "123123123"
        //}


        reqBody = new JSONObject();

        reqBody.put("email", ConfigReader.getProperty(email));
        reqBody.put("password", ConfigReader.getProperty(password));

    }

     */


    @Given("User sets {string} path param.")
    public void userSetsPathParam(String rawPaths) {
        // https://trendlifebuy.com/api/profile/allCountries

        // spec.pathParams("pp1","api","pp2","profile","pp3","allCountries");

        String [] paths = rawPaths.split("/"); // ["api","profile","allCountries"]

        System.out.println(Arrays.toString(paths));
       /*
        spec.pathParam("pp1","api");
        spec.pathParam("pp2","profile");
        spec.pathParam("pp3","allCountries");
        */

        // get("{pp1}/{pp2}/{pp3}")

        StringBuilder tempPath = new StringBuilder("/{");


        for (int i = 0; i < paths.length; i++) {

            String key = "pp" + i;
            String value = paths[i].trim();

            HooksAPI.spec.pathParam(key,value);

            tempPath.append(key + "}/{");
        }
        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));

        fullPath = tempPath.toString(); // {pp1}/{pp2}/{pp3}
        System.out.println("fullPath = " + fullPath);
    }


    @Then("Postrequest sent with {string} and {string} must have {string} and {string}")
    public void postrequestSentWithAndMustHaveAnd(String key, String value, String status, String message) {
        reqBody = new JSONObject();

        reqBody.put(key,value);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " +HooksAPI.token)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        int intStatus = Integer.parseInt(status);

        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));

    }




    @Then("With {string} Authorization is sent Get request must status: {string} and message: {string}")
    public void withAuthorizationIsSentGetRequestMustStatusAndMessage(String str, String status, String message) {
        if (str.equalsIgnoreCase("Valid")){
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization","Bearer " + HooksAPI.token)
                    .when()
                    .get(fullPath);

            response.prettyPrint();
        }else {
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization","Bearer " + HooksAPI.invalidToken)
                    .when()
                    .get(fullPath);

            response.prettyPrint();
        }

        int intStatus = Integer.parseInt(status);

        response
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));
    }



    @Given("With {string} Authorization is sent Delete request must id: {string}, delete_id_key: {string}, status: {int} and message: {string}")
    public void with_authorization_is_sent_delete_request_must_id_delete_id_key_status_and_message(String str, String id, String deleteId, Integer status, String message) {
        JSONObject reqBody = new JSONObject();

if (BooksAPI.addId== null || BooksAPI.addId == "0" ){
    reqBody.put("id", id);


    if (str.equalsIgnoreCase("Valid")){
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + HooksAPI.token)
                .when()
                .body(reqBody.toString())
                .delete(fullPath);

        response.prettyPrint();

        String responseBody = response.getBody().asString();
        JsonPath respJP = new JsonPath(responseBody);
        deletedId = respJP.getString(deleteId);

        System.out.println(deletedId);

        response
                .then()
                .assertThat()
                .statusCode(status)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message), deleteId,Matchers.equalTo(id));

    }else {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + HooksAPI.invalidToken)
                .when()
                .body(reqBody.toString())
                .delete(fullPath);

        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(status)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));
    }
}else {
    reqBody.put("id", BooksAPI.addId);


    if (str.equalsIgnoreCase("Valid")){
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + HooksAPI.token)
                .when()
                .body(reqBody.toString())
                .delete(fullPath);

        response.prettyPrint();

        String responseBody = response.getBody().asString();
        JsonPath respJP2 = new JsonPath(responseBody);
        deletedId = respJP2.getString(deleteId);

        System.out.println(deletedId);

        response
                .then()
                .assertThat()
                .statusCode(status)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message), deleteId,Matchers.equalTo(BooksAPI.addId));

    }else {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + HooksAPI.invalidToken)
                .when()
                .body(reqBody.toString())
                .delete(fullPath);

        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(status)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));
    }
}



    }
}


