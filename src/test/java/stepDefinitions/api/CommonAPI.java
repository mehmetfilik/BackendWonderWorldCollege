package stepDefinitions.api;


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
import static org.junit.Assert.assertEquals;

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
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));

    }


    @Then("The contents of the list data with id: {string} in the Visitors Response Body should be verified.")
    public void theContentsOfTheListDataWithIdInTheVisitorsResponseBodyShouldBeVerified(String id) {
        /*
        Pojo_VisitorsList expectedData = new Pojo_VisitorsList
        */

        JSONObject reqBody = new JSONObject();

        reqBody.put("id",ConfigReader.getProperty("visitors_id"));
        reqBody.put("staff_id",ConfigReader.getProperty("visitors_staff_id"));
        reqBody.put("session_id",ConfigReader.getProperty("visitors_student_session_id"));
        reqBody.put("source",ConfigReader.getProperty("visitors_source"));
        reqBody.put("purpose",ConfigReader.getProperty("visitors_purpose"));
        reqBody.put("name",ConfigReader.getProperty("visitors_name"));
        reqBody.put("email",ConfigReader.getProperty("visitors_email"));
        reqBody.put("contact",ConfigReader.getProperty("visitors_contact"));
        reqBody.put("id_proof",ConfigReader.getProperty("visitors_id_proof"));
        reqBody.put("no_of_people",ConfigReader.getProperty("visitors_no_of_people"));
        reqBody.put("date",ConfigReader.getProperty("visitors_date"));
        reqBody.put("in_time",ConfigReader.getProperty("visitors_in_time"));
        reqBody.put("out_time",ConfigReader.getProperty("visitors_out_time"));
        reqBody.put("note",ConfigReader.getProperty("visitors_note"));
        reqBody.put("image",ConfigReader.getProperty("visitors_image"));
        reqBody.put("meeting_with",ConfigReader.getProperty("visitors_meeting_with"));
        reqBody.put("created_at",ConfigReader.getProperty("visitors_created_at"));
        reqBody.put("section",ConfigReader.getProperty("visitors_section"));
        reqBody.put("staff_name",ConfigReader.getProperty("visitors_staff_name"));
        reqBody.put("staff_surname",ConfigReader.getProperty("visitors_staff_surname"));
        reqBody.put("staff_employee_id",ConfigReader.getProperty("visitors_staff_employee_id"));
        reqBody.put("class_id",ConfigReader.getProperty("visitors_class_id"));
        reqBody.put("section_id",ConfigReader.getProperty("visitors_section_id"));
        reqBody.put("students_id",ConfigReader.getProperty("visitors_students_id"));
        reqBody.put("admission_no",ConfigReader.getProperty("visitors_admission_no"));
        reqBody.put("student_firstname",ConfigReader.getProperty("visitors_student_firstname"));
        reqBody.put("student_middlename",ConfigReader.getProperty("visitors_student_middlename"));
        reqBody.put("student_lastname",ConfigReader.getProperty("visitors_student_lastname"));
        reqBody.put("role_id",ConfigReader.getProperty("visitors_role_id"));


        JSONObject expectedData = new JSONObject();

        expectedData.put("lists", reqBody);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();

        JsonPath respJP = response.jsonPath();

        assertEquals(expectedData.getJSONObject("lists").get("id"), respJP.get("lists.id") );
        //assertEquals(expectedData.getJSONObject("lists").get("staff_id"), respJP.get("lists.staff_id") );



/*
        Pojo_VisitorsList respPojo = response.as(Pojo_VisitorsList.class);

        assertEquals(expectedData.getId(),respPojo.getId());
        assertEquals(expectedData.getStaff_id(),respPojo.getStaff_id());
        assertEquals(expectedData.getStudent_session_id(),respPojo.getStudent_session_id());
        assertEquals(expectedData.getSource(),respPojo.getSource());
        assertEquals(expectedData.getPurpose(),respPojo.getPurpose());
        assertEquals(expectedData.getName(),respPojo.getName());
        assertEquals(expectedData.getEmail(),respPojo.getEmail());
        assertEquals(expectedData.getContact(),respPojo.getContact());
        assertEquals(expectedData.getId_proof(),respPojo.getId_proof());
        assertEquals(expectedData.getNo_of_people(),respPojo.getNo_of_people());
        assertEquals(expectedData.getDate(),respPojo.getDate());
        assertEquals(expectedData.getIn_time(),respPojo.getIn_time());
        assertEquals(expectedData.getOut_time(),respPojo.getOut_time());
        assertEquals(expectedData.getNote(),respPojo.getNote());
        assertEquals(expectedData.getImage(),respPojo.getImage());
        assertEquals(expectedData.getMeeting_with(),respPojo.getMeeting_with());
        assertEquals(expectedData.getCreated_at(),respPojo.getCreated_at());
        assertEquals(expectedData.getSection(),respPojo.getSection());
        assertEquals(expectedData.getStaff_name(),respPojo.getStaff_name());
        assertEquals(expectedData.getStaff_surname(),respPojo.getStaff_surname());
        assertEquals(expectedData.getStaff_employee_id(),respPojo.getStaff_employee_id());
        assertEquals(expectedData.getClass_id(),respPojo.getClass_id());
        assertEquals(expectedData.getSection_id(),respPojo.getSection_id());
        assertEquals(expectedData.getStudents_id(),respPojo.getStudents_id());
        assertEquals(expectedData.getAdmission_no(),respPojo.getAdmission_no());
        assertEquals(expectedData.getStudent_firstname(),respPojo.getStudent_firstname());
        assertEquals(expectedData.getStudent_middlename(),respPojo.getStudent_middlename());
        assertEquals(expectedData.getStudent_lastname(),respPojo.getStudent_lastname());
        assertEquals(expectedData.getRole_id(),respPojo.getRole_id());
*/

    }
}


