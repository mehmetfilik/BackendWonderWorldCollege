package stepDefinitions.api;


import hooks.api.HooksAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import pojos.Pojo_VisitorsList;
import utilities.ConfigReader;

import java.util.Arrays;

import static hooks.api.HooksAPI.spec;
import static hooks.api.HooksAPI.token;
import static io.restassured.RestAssured.given;

public class CommonAPI {

    String fullPath;

    Response response;

    JSONObject reqBody;


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


    @Then("Login icin Post request gonderilir.")
    public void loginIcinPostRequestGonderilir() {

        response = given()
                                .spec(spec)
                                .contentType(ContentType.JSON)
                                .header("Accept","application/json")
                            .when()
                                .body(reqBody.toString())
                                .post(fullPath);

        response.prettyPrint();


    }

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

    @Then("For {string} is sent Get request.")
    public void forIsSentGetRequest(String arg0) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + HooksAPI.token)
                .when()
                .get(fullPath);

        response.prettyPrint();
    }

    @Then("Postrequest sent with {string} and {string} must have {string} and {string}")
    public void postrequestSentWithAndMustHaveAnd(String key, String value, String status, String message) {
        reqBody = new JSONObject();

        reqBody.put(key,value);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        int intStatus = Integer.parseInt(status);

        response.prettyPrint();

        response
                .then().assertThat().statusCode(intStatus).contentType(ContentType.JSON).body("message", Matchers.equalTo(message));

    }


    @Then("The contents of the list data with id: {string} in the Visitors Response Body should be verified.")
    public void theContentsOfTheListDataWithIdInTheVisitorsResponseBodyShouldBeVerified(String id) {
        Pojo_VisitorsList reqBody = new Pojo_VisitorsList(
                ConfigReader.getProperty("visitors_id"),
                ConfigReader.getProperty("visitors_staff_id"),
                ConfigReader.getProperty("visitors_student_session_id"),
                ConfigReader.getProperty("visitors_source"),
                ConfigReader.getProperty("visitors_purpose"),
                ConfigReader.getProperty("visitors_name"),
                ConfigReader.getProperty("visitors_email"),
                ConfigReader.getProperty("visitors_contact"),
                ConfigReader.getProperty("visitors_id_proof"),
                ConfigReader.getProperty("visitors_no_of_people"),
                ConfigReader.getProperty("visitors_date"),
                ConfigReader.getProperty("visitors_in_time"),
                ConfigReader.getProperty("visitors_out_time"),
                ConfigReader.getProperty("visitors_note"),
                ConfigReader.getProperty("visitors_image"),
                ConfigReader.getProperty("visitors_meeting_with"),
                ConfigReader.getProperty("visitors_created_at"),
                ConfigReader.getProperty("visitors_section"),
                ConfigReader.getProperty("visitors_staff_name"),
                ConfigReader.getProperty("visitors_staff_surname"),
                ConfigReader.getProperty("visitors_staff_employee_id"),
                ConfigReader.getProperty("visitors_class_id"),
                ConfigReader.getProperty("visitors_section_id"),
                ConfigReader.getProperty("visitors_students_id"),
                ConfigReader.getProperty("visitors_admission_no"),
                ConfigReader.getProperty("visitors_student_firstname"),
                ConfigReader.getProperty("visitors_student_middlename"),
                ConfigReader.getProperty("visitors_student_lastname"),
                ConfigReader.getProperty("visitors_role_id")
        );
    }
}


