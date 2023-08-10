package stepDefinitions.api;

import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import utilities.ConfigReader;

import static hooks.api.HooksAPI.spec;
import static hooks.api.HooksAPI.token;
import static io.restassured.RestAssured.given;

public class VisitorsAPI {

    String fullPath;

    Response response;

    JSONObject reqBody;



    @Then("The contents of the list data with id: {string} in the Visitors Response Body should be verified.")
    public void theContentsOfTheListDataWithIdInTheVisitorsResponseBodyShouldBeVerified(String id) {
        /*
        Pojo_VisitorsList expectedData = new Pojo_VisitorsList
        */

        JSONObject reqBody = new JSONObject();

        reqBody.put("id", ConfigReader.getProperty("visitors_id"));
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

        //JsonPath respJP = response.jsonPath();

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("lists.id", Matchers.equalTo("3"))
                .body("lists.staff_id", Matchers.nullValue());



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
}
