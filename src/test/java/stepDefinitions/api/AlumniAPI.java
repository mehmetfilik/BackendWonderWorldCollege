package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import utilities.ConfigReader;

import static hooks.api.HooksAPI.spec;
import static hooks.api.HooksAPI.token;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static stepDefinitions.api.CommonAPI.fullPath;

public class AlumniAPI {

    Response response;

    JSONObject reqBody;

    @Then("The contents of the list data with id: {string} in the Alumni Response Body should be verified.")
    public void the_contents_of_the_list_data_with_id_in_the_alumni_response_body_should_be_verified(String id) {
        String url = "https://qa.wonderworldcollege.com/api/visitorsPurposeId";
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

            {
            "id": "2",
            "student_id": "41",
            "current_email": "deneme@gmail.com",
            "current_phone": "9809967867",
            "occupation": "",
            "address": "",
            "photo": "",
            "created_at": "2023-08-12 07:16:21"
        },
        }
 */

        reqBody = new JSONObject();

        reqBody.put("id",id);
        response=given().contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .get(url);
        response.prettyPrint();


        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("lists.id", equalTo("2"))
                .body("lists.student_id", equalTo("41"))
                .body("lists.current_email", equalTo("deneme@gmail.com"))
                .body("lists.current_phone", equalTo("9809967867"))
                .body("lists.created_at", equalTo("2023-08-12 07:16:21"));




        //reqBody.put("id", ConfigReader.getProperty("visitors_id"));
        //reqBody.put("staff_id",ConfigReader.getProperty("visitors_staff_id"));
        //reqBody.put("session_id",ConfigReader.getProperty("visitors_student_session_id"));
        //reqBody.put("source",ConfigReader.getProperty("visitors_source"));
        //reqBody.put("purpose",ConfigReader.getProperty("visitors_purpose"));
        //reqBody.put("name",ConfigReader.getProperty("visitors_name"));
        //reqBody.put("email",ConfigReader.getProperty("visitors_email"));
        //reqBody.put("contact",ConfigReader.getProperty("visitors_contact"));
        //reqBody.put("id_proof",ConfigReader.getProperty("visitors_id_proof"));
        //reqBody.put("no_of_people",ConfigReader.getProperty("visitors_no_of_people"));
        //reqBody.put("date",ConfigReader.getProperty("visitors_date"));
        //reqBody.put("in_time",ConfigReader.getProperty("visitors_in_time"));
        //reqBody.put("out_time",ConfigReader.getProperty("visitors_out_time"));
        //reqBody.put("note",ConfigReader.getProperty("visitors_note"));
        //reqBody.put("image",ConfigReader.getProperty("visitors_image"));
        //reqBody.put("meeting_with",ConfigReader.getProperty("visitors_meeting_with"));
        //reqBody.put("created_at",ConfigReader.getProperty("visitors_created_at"));
        //reqBody.put("section",ConfigReader.getProperty("visitors_section"));
        //reqBody.put("staff_name",ConfigReader.getProperty("visitors_staff_name"));
        //reqBody.put("staff_surname",ConfigReader.getProperty("visitors_staff_surname"));
        //reqBody.put("staff_employee_id",ConfigReader.getProperty("visitors_staff_employee_id"));
        //reqBody.put("class_id",ConfigReader.getProperty("visitors_class_id"));
        //reqBody.put("section_id",ConfigReader.getProperty("visitors_section_id"));
        //reqBody.put("students_id",ConfigReader.getProperty("visitors_students_id"));
        //reqBody.put("admission_no",ConfigReader.getProperty("visitors_admission_no"));
        //reqBody.put("student_firstname",ConfigReader.getProperty("visitors_student_firstname"));
        //reqBody.put("student_middlename",ConfigReader.getProperty("visitors_student_middlename"));
        //reqBody.put("student_lastname",ConfigReader.getProperty("visitors_student_lastname"));
        //reqBody.put("role_id",ConfigReader.getProperty("visitors_role_id"));
//
/*
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
                .body("lists.id", equalTo("2"))
                .body("lists.student_id", equalTo("41"))
                .body("lists.current_email", equalTo("deneme@gmail.com"))
                .body("lists.current_phone", equalTo("9809967867"))
                .body("lists.created_at", equalTo("2023-08-12 07:16:21"));
*/

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

        reqBody.put("id",id);

/*        reqBody.put("class_id", ConfigReader.getProperty("alumni_2"));
        reqBody.put("student_session_id",ConfigReader.getProperty("alumni_27"));
        reqBody.put("id",ConfigReader.getProperty("alumni_2"));
        reqBody.put("class",ConfigReader.getProperty("alumni_Class 2"));
        reqBody.put("section_id",ConfigReader.getProperty("alumni_1"));
        reqBody.put("section",ConfigReader.getProperty("A"));
        reqBody.put("admission_no",ConfigReader.getProperty("alumni_18002"));
        reqBody.put("roll_no",ConfigReader.getProperty("alumni_102"));
        reqBody.put("admission_date",ConfigReader.getProperty("alumni_2021-03-16"));
        reqBody.put("firstname",ConfigReader.getProperty("alumni_Robin"));
        reqBody.put("middlename",ConfigReader.getProperty("alumni_null"));
        reqBody.put("lastname",ConfigReader.getProperty("alumni_Peterson"));
        reqBody.put("image",ConfigReader.getProperty("alumni_uploads/student_images/2.jpg"));
        reqBody.put("mobileno",ConfigReader.getProperty("alumni_69898565464"));
        reqBody.put("email",ConfigReader.getProperty("alumni_adarshwebfeb@gmail.com"));
        reqBody.put("dob",ConfigReader.getProperty("alumni_2013-07-12"));
        reqBody.put("category_id",ConfigReader.getProperty("alumni_1"));
        reqBody.put("category",ConfigReader.getProperty("alumni_General"));
        reqBody.put("adhar_no",ConfigReader.getProperty("alumni_46465454"));
        reqBody.put("samagra_id",ConfigReader.getProperty("alumni_56465464"));
        reqBody.put("bank_account_no",ConfigReader.getProperty("alumni_56454564"));
        reqBody.put("bank_name",ConfigReader.getProperty("alumni_Capital Bank"));
        reqBody.put("ifsc_code",ConfigReader.getProperty("alumni_56465465"));
        reqBody.put("guardian_name",ConfigReader.getProperty("alumni_Lucas Peterson"));
        reqBody.put("guardian_relation",ConfigReader.getProperty("alumni_Father"));
        reqBody.put("guardian_email",ConfigReader.getProperty("alumni_lucass@gmail.com"));
        reqBody.put("guardian_phone",ConfigReader.getProperty("alumni_946545445"));
        reqBody.put("guardian_address",ConfigReader.getProperty("alumni_South Brooklyn"));
        reqBody.put("is_active",ConfigReader.getProperty("alumni_yes"));
        reqBody.put("created_at",ConfigReader.getProperty("alumni_2023-02-11 05:51:38"));
        reqBody.put("father_name",ConfigReader.getProperty("alumni_Lucas Peterson"));
        reqBody.put("rte",ConfigReader.getProperty("alumni_No"));
        reqBody.put("gender",ConfigReader.getProperty("alumni_Male"));
        reqBody.put("user_tbl_id",ConfigReader.getProperty("male_3"));
        reqBody.put("username",ConfigReader.getProperty("alumni_std2"));
        reqBody.put("user_tbl_password",ConfigReader.getProperty("alumni_password"));
        reqBody.put("user_tbl_active",ConfigReader.getProperty("alumni_yes"));

*/


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
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo("Success"),"lists.id",Matchers.equalTo(id));


        //JsonPath respJP = response.jsonPath();

    }


    @Then("With {string} Authorization is sent Patch request must id: {string}, update_id_key: {string}, status: {int} and message: {string}")
    public void withAuthorizationIsSentPatchRequestMustIdUpdate_id_keyStatusAndMessage(String str, String id, String updateId, int status, String message) {


        JSONObject reqBody = new JSONObject();


        reqBody.put("id", id);                         //  <--burda guncelleyecegimiz body nin id sini girmemiz gerek
        reqBody.put("purpose", "Principal Meeting");
        reqBody.put("purpose", "Principal Meeting");
        reqBody.put("name", "fero");
        reqBody.put("contact", "9808678686112");
        reqBody.put("id_proof", "312121");
        reqBody.put("no_of_people", "13");
        reqBody.put("date", "2023-03-16");
        reqBody.put("in_time", "06:00 PM");
        reqBody.put("out_time", "06:30 PM");
        reqBody.put("note", "PTM meeting");



        if (str.equalsIgnoreCase("Valid")){
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization","Bearer " + HooksAPI.token)
                    .when()
                    .body(reqBody.toString())
                    .patch(CommonAPI.fullPath);

            response.prettyPrint();
        }else {
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization","Bearer " + HooksAPI.invalidToken)
                    .when()
                    .body(reqBody.toString())
                    .patch(CommonAPI.fullPath);

            response.prettyPrint();
        }

        response
                .then()
                .assertThat()
                .statusCode(status)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message),updateId,Matchers.equalTo("29"));

    }

    @When("I want to create a new Alumni record through API connection")
    public void ıWantToCreateANewAlumniRecordThroughAPIConnection() {

/*
        "student_id": "29",
        "current_email": "",
        "current_phone": "9809967867",
        "occupation": "",
        "address": "",
        "photo": ""

      dönunce asagıdakını vermeli

    "status": 200,
    "message": "Success",
    "Token_remaining_time": 1006,
    "addId": 8
}
 */
        JSONObject reqBody = new JSONObject();

        reqBody.put("student_id", "29");
        reqBody.put("current_email", "");
        reqBody.put("current_phone", "9809967867");
        reqBody.put( "occupation", "");
        reqBody.put("address", "");
        reqBody.put("photo", "");


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




    }


    @Then("In Visitors with {string} Authorization sent Post request with {string}, {string}, {string}, {string}, {string}, {string},{string} must have {int} and {string}")
    public void ınVisitorsWithAuthorizationSentPostRequestWithMustHaveStatusAnd(String str, String student_id, String current_email, String current_phone, String occupation, String address, String photo,String created_at, int status,String message) {


        JSONObject reqBody = new JSONObject();

        reqBody.put("student_id", student_id);
        reqBody.put("current_email", current_email);
        reqBody.put("current_phone", current_phone);
        reqBody.put("occupation", occupation);
        reqBody.put("photo", photo);
        reqBody.put("address", address);
        reqBody.put("created_at", created_at);



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

     /*   String responseBody = response.getBody().asString();
        JsonPath respJP = new JsonPath(responseBody);
        addId = respJP.getString("addId");

        System.out.println(addId);

*/

    }
}
