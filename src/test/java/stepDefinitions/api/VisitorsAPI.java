package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utils;
import utilities.ConfigReader;

import java.util.HashMap;
import java.util.Map;

import static hooks.api.HooksAPI.spec;
import static hooks.api.HooksAPI.token;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class VisitorsAPI {

    String fullPath;

    Response response;

    JSONObject reqBody;

    JsonPath resJP;

    String addId;

    String updatedId;




    @Then("The contents of the list data with id: {string} in the Visitors Response Body should be verified.")
    public void theContentsOfTheListDataWithIdInTheVisitorsResponseBodyShouldBeVerified(String id) {
        /*
        Pojo_VisitorsList expectedData = new Pojo_VisitorsList
        */

        JSONObject reqBody = new JSONObject();

        reqBody.put("id", id);
      // reqBody.put("staff_id",ConfigReader.getProperty("visitors_staff_id"));
      // reqBody.put("session_id",ConfigReader.getProperty("visitors_student_session_id"));
      // reqBody.put("source",ConfigReader.getProperty("visitors_source"));
      // reqBody.put("purpose",ConfigReader.getProperty("visitors_purpose"));
      // reqBody.put("name",ConfigReader.getProperty("visitors_name"));
      // reqBody.put("email",ConfigReader.getProperty("visitors_email"));
      // reqBody.put("contact",ConfigReader.getProperty("visitors_contact"));
      // reqBody.put("id_proof",ConfigReader.getProperty("visitors_id_proof"));
      // reqBody.put("no_of_people",ConfigReader.getProperty("visitors_no_of_people"));
      // reqBody.put("date",ConfigReader.getProperty("visitors_date"));
      // reqBody.put("in_time",ConfigReader.getProperty("visitors_in_time"));
      // reqBody.put("out_time",ConfigReader.getProperty("visitors_out_time"));
      // reqBody.put("note",ConfigReader.getProperty("visitors_note"));
      // reqBody.put("image",ConfigReader.getProperty("visitors_image"));
      // reqBody.put("meeting_with",ConfigReader.getProperty("visitors_meeting_with"));
      // reqBody.put("created_at",ConfigReader.getProperty("visitors_created_at"));
      // reqBody.put("section",ConfigReader.getProperty("visitors_section"));
      // reqBody.put("staff_name",ConfigReader.getProperty("visitors_staff_name"));
      // reqBody.put("staff_surname",ConfigReader.getProperty("visitors_staff_surname"));
      // reqBody.put("staff_employee_id",ConfigReader.getProperty("visitors_staff_employee_id"));
      // reqBody.put("class_id",ConfigReader.getProperty("visitors_class_id"));
      // reqBody.put("section_id",ConfigReader.getProperty("visitors_section_id"));
      // reqBody.put("students_id",ConfigReader.getProperty("visitors_students_id"));
      // reqBody.put("admission_no",ConfigReader.getProperty("visitors_admission_no"));
      // reqBody.put("student_firstname",ConfigReader.getProperty("visitors_student_firstname"));
      // reqBody.put("student_middlename",ConfigReader.getProperty("visitors_student_middlename"));
      // reqBody.put("student_lastname",ConfigReader.getProperty("visitors_student_lastname"));
      // reqBody.put("role_id",ConfigReader.getProperty("visitors_role_id"));


       // JSONObject expectedData = new JSONObject();

       // expectedData.put("lists", reqBody);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(CommonAPI.fullPath);

        response.prettyPrint();

        //JsonPath respJP = response.jsonPath();

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("message",Matchers.equalTo("Success"),"lists.id", Matchers.equalTo(id));
               // .body("lists.staff_id", Matchers.nullValue());



        //assertEquals(expectedData.getJSONObject("lists").get("id"), respJP.get("lists.id"));
        //assertEquals(expectedData.getJSONObject("lists").get("staff_id"), respJP.get("lists.staff_id") );
        //assertNull(expectedData.getJSONObject("lists").get("staff_id"));



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

    @Then("In Visitors with {string} Authorization sent Post request with {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} must have {int} and {string}")
    public void ınVisitorsWithAuthorizationSentPostRequestWithMustHaveAnd(String str, String purpose, String name, String contact, String id_proof, String no_of_people, String date, String in_time, String out_time, String note, int status, String message) {

        JSONObject reqBody = new JSONObject();

        reqBody.put("purpose", purpose);
        reqBody.put("name", name);
        reqBody.put("contact", contact);
        reqBody.put("id_proof", id_proof);
        reqBody.put("no_of_people", no_of_people);
        reqBody.put("date", date);
        reqBody.put("in_time", in_time);
        reqBody.put("out_time", out_time);
        reqBody.put("note", note);


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
        addId = respJP.getString("addId");

        System.out.println(addId);


    }

    @Then("In Visitors with {string} Authorization is sent Patch request must id: {string}, update_id_key: {string}, status: {int} and message: {string}")
    public void ın_visitors_with_authorization_is_sent_patch_request_must_id_update_id_key_status_and_message(String str, String id, String updateId, int status, String message) {
        JSONObject reqBody = new JSONObject();

        reqBody.put("id", id);
        reqBody.put("purpose", "Meeting");
        reqBody.put("name", "Yusuf");
        reqBody.put("contact", "8086786861120");
        reqBody.put("id_proof", "121213");
        reqBody.put("no_of_people", "15");
        reqBody.put("date", "2023-04-16");
        reqBody.put("in_time", "07:00 PM");
        reqBody.put("out_time", "07:30 PM");
        reqBody.put("note", "ATM meeting");


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

            System.out.println(updatedId);

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

    @Then("In Visitors Postrequest sent with {string} must have status: {int} and message: {string}")
    public void ınVisitorsPostrequestSentWithMustHaveStatusAndMessage(String id, int status, String message) {
        JSONObject reqBody = new JSONObject();

        reqBody.put(id,addId);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(CommonAPI.fullPath);

        response.prettyPrint();

        String[] expectedArray = {"id", "purpose","name", "contact", "id_proof", "no_of_people", "date", "in_time", "out_time", "note"};

        JsonPath resJP = response.jsonPath();

        String actualData = resJP.get("lists").toString();
        System.out.println(actualData);
        resJP.prettyPrint();

        for (int i = 0; i < expectedArray.length; i++) {
            assertTrue(actualData.contains(expectedArray[i]));
        }
    }

    @Then("After Visitors updating Postrequest sent with {string} must have status: {int} and message: {string}")
    public void afterVisitorsUpdatingPostrequestSentWithMustHaveStatusAndMessage(String id, int status, String message) {

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

    @Then("After Visitors deleting Postrequest sent with {string} must have status: {int} and message: {string}")
    public void after_visitors_deleting_postrequest_sent_with_must_have_status_and_message(String id, Integer status, String message) {
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
