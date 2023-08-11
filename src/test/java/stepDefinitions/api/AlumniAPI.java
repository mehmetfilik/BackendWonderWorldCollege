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
import static org.hamcrest.Matchers.*;

public class AlumniAPI {

    Response response;

    JSONObject reqBody;

    @Then("The contents of the list data with id: {string} in the Alumni Response Body should be verified.")
    public void the_contents_of_the_list_data_with_id_in_the_alumni_response_body_should_be_verified(String id) {
/*

{
            "id": "2",
            "student_id": "41",
            "current_email": "rohan@gmail.com",
            "current_phone": "0808080707",
            "occupation": "",
            "address": "",
            "photo": null,
            "created_at": "2023-03-11 03:04:50"
        }
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
                .post(CommonAPI.fullPath);

        response.prettyPrint();

        //JsonPath respJP = response.jsonPath();

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("lists.id", equalTo("3"))
                .body("lists.staff_id", nullValue());

    }

    @Then("The contents of the list data with id: {string} in the AlumniStudent Response Body should be verified.")
    public void theContentsOfTheListDataWithIdInTheAlumniStudentResponseBodyShouldBeVerified(String id) {

        /*
         {
            "class_id": "2",
            "student_session_id": "27",
            "id": "2",
            "class": "Class 2",
            "section_id": "1",
            "section": "A",
            "admission_no": "18002",
            "roll_no": "102",
            "admission_date": "2021-03-16",
            "firstname": "Robin",
            "middlename": null,
            "lastname": "Peterson",
            "image": "uploads/student_images/2.jpg",
            "mobileno": "69898565464",
            "email": "adarshwebfeb@gmail.com",
            "state": null,
            "city": null,
            "pincode": null,
            "religion": "",
            "dob": "2013-07-12",
            "current_address": "",
            "permanent_address": "",
            "category_id": "1",
            "category": "General",
            "adhar_no": "46465454",
            "samagra_id": "56465464",
            "bank_account_no": "56454564",
            "bank_name": "Capital Bank",
            "ifsc_code": "56465465",
            "guardian_name": "Lucas Peterson",
            "guardian_relation": "Father",
            "guardian_email": "lucass@gmail.com",
            "guardian_phone": "946545445",
            "guardian_address": "South Brooklyn",
            "is_active": "yes",
            "created_at": "2023-02-11 05:51:38",
            "updated_at": null,
            "father_name": "Lucas Peterson",
            "rte": "No",
            "gender": "Male",
            "user_tbl_id": "3",
            "username": "std2",
            "user_tbl_password": "password",
            "user_tbl_active": "yes"
        }
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
                .post(CommonAPI.fullPath);

        response.prettyPrint();

        //JsonPath respJP = response.jsonPath();

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("lists.class_id",equalTo("2"))
                .body("lists.student_session_id",equalTo("27"));



    }
}
