package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.en.Then;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import utilities.ConfigReader;

import java.util.Objects;

import static hooks.api.HooksAPI.spec;
import static hooks.api.HooksAPI.token;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class AlumniEventsAPI {
    String fullPath;

    Response response;

    JSONObject reqBody;

    JsonPath responseJP;
//-----------------------------SessionList-----------------------------------------------
    @Then("Confirm the content of Session List response body  data with Id {string}.")
    public void confirm_the_content_of_response_body_data_with_id(String id) {
        String url = "https://qa.wonderworldcollege.com/api/sessionList";

        reqBody = new JSONObject();

        reqBody.put("id",id);
        Response response = given().contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
                .when()
                .body(reqBody.toString())
                .get(url);
        response.prettyPrint();

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .body("lists.id", Matchers.equalTo("11"),
                        "lists.session",Matchers.equalTo("2017-18"),
                        "lists.is_active",Matchers.equalTo("no"),
                        "lists.created_at",Matchers.equalTo("2017-04-20 02:41:37"),
                        "lists.updated_at",Matchers.equalTo("0000-00-00"));

    }

//-----------------------AlumniEventList--------------------------------

    @Then("Confirm the content of Alumni Event List response body list data with Id {string}.")
    public void confirm_the_content_of_alumni_event_list_response_body_list_data_with_id(String id) {

        String url = "https://qa.wonderworldcollege.com/api/alumniEventsId";


        reqBody = new JSONObject();

        reqBody.put("id",id);
        response=given().spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(url);

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("lists.id", equalTo("2"),
                        "lists.title",equalTo("Reunion For 2020-21 Batch"),
                        "lists.event_for",equalTo("class"),
                        "lists.session_id",equalTo("15"),
                        "lists.class_id",equalTo("1"),
                        "lists.from_date",equalTo("2021-03-07 00:00:00"),
                        "lists.to_date",equalTo("2021-03-10 00:00:00"),
                        "lists.note",equalTo(""),
                        "lists.photo",equalTo(""),
                        "lists.is_active",equalTo("0"),
                        "lists.event_notification_message",equalTo(""),
                        "lists.show_onwebsite",equalTo("0"),
                        "lists.created_at",equalTo("2021-03-23 02:53:47")
                );


    }

//------------------------------AlumniEventAdd-----------------------------

    @Then("Send a Post request  with {string} and {string}, {string},{string}, {string},{string},{string},{string},{string} and {string} correct data  should have {int} and {string}")
    public void send_a_post_request_with_and_and_correct_data_should_have_and(String str, String title, String event_for, String session_id, String section, String from_date, String to_date, String note, String event_notification_message, String show_onwebsite, int status, String message) {

        reqBody = new JSONObject();

        reqBody.put("title", title);
        reqBody.put("event_for", event_for);
        reqBody.put("session_id", session_id);
        reqBody.put("section", section);
        reqBody.put("from_date", from_date);
        reqBody.put("to_date", to_date);
        reqBody.put("note", note);
        reqBody.put("event_notification_message", event_notification_message);
        reqBody.put("show_onwebsite", show_onwebsite);

        if (str.equalsIgnoreCase("valid authorization")) {

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


    }
//-------------------------------AlumniEventsByDateRange--------------------------------

    @Then("Send a Post request  with {string}, {string} on {string},{string} on {string} and must have {int} and {string}")
    public void sendAPostRequestWithOnOnAndMustHaveStatusAnd(String str, String key, String value, String key1, String value1, int status, String message) {

        reqBody = new JSONObject();

        reqBody.put(key,value);
        reqBody.put(key1,value1);

        if (str.equalsIgnoreCase("valid authorization")) {

            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Bearer " + token)
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
    }


    private static boolean validateDataWithId1(JSONObject dataObject) {
        return dataObject.getString("title").equals("Covid-19 Seminar")
                && dataObject.getString("event_for").equals("class")
                && dataObject.getString("session_id").equals("16")
                && dataObject.getString("class_id").equals("1")
                && dataObject.getString("section").equals("[\\\"1\\\"]")
                && dataObject.getString("from_date").equals("2021-03-01 00:00:00")
                && dataObject.getString("to_date").equals("2021-03-16 00:00:00")
                && dataObject.getString("note").equals("COVID-19 is the disease caused by a new coronavirus called SARS-CoV-2.  WHO first learned of this new virus on 31 December 2019, following a report of a cluster of cases of â€˜viral pneumoniaâ€™ in Wuhan, Peopleâ€™s Republic of China.")
                && dataObject.getString("photo").isEmpty()
                && dataObject.getString("is_active").equals("0")
                && dataObject.getString("event_notification_message").equals("GAVI'S RESPONSE\\r\\nRespond and protect: With COVID-19 now reported in almost all Gavi-eligible countries, the Vaccine Alliance is providing immediate funding to health systems, enabling countries to protect health care workers, perform vital surveillance and training, and purchase diagnostic tests.\\r\\n\\r\\nMaintain, restore and strengthen: Gavi will support countries to adapt and restart immunisation services, rebuild community trust and catch up those who have been missed both before and during the pandemic, while also investing in strengthening immunisation systems to be more resilient and responsive to the communities they serve.\\r\\n\\r\\nEnsure global equitable access: Gavi is co-leading COVAX, the global effort to securing a global response to COVID-19 that is effective and fair, using its unique expertise to help identify and rapidly accelerate development, production and delivery of COVID-19 vaccines so that anyone that needs them, gets them.")
                && dataObject.getString("show_onwebsite").equals("0")
                && dataObject.getString("created_at").equals("2021-03-23 02:54:29");
    }




    @Then("Confirm the content of Alumni Events By Date Range response body list data in which Id {string} when \"start and \"end\" is sent with Posted request.")
    public void confirm_the_content_of_alumni_events_by_date_range_response_body_list_data_in_which_id_when_start_and_end_is_sent_with_posted_request(String string) {

        // String url = "https://qa.wonderworldcollege.com/api/alumniEventsByDateRange";

        reqBody = new JSONObject();

        reqBody.put("start", "2021-03-01 00:00:00");
        reqBody.put("end", "2023-03-16 00:00:00");

        response = given().spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(CommonAPI.fullPath);

        String responseBody = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(responseBody);

        JSONArray listsArray = jsonObject.getJSONArray("lists");

        for (int i = 0; i < listsArray.length(); i++) {
            JSONObject eventObject = listsArray.getJSONObject(i);
            if (Objects.equals(eventObject.getString("id"), "1")) {
                System.out.println("Event Object for ID 1: " + eventObject.toString());
                validateDataWithId1(eventObject);
                break;
            }


            // response.then()
            //         .assertThat()
            //         .contentType(ContentType.JSON)
            //         .body("lists.id", Matchers.equalTo("1"),
            //                 "lists.title", Matchers.equalTo("Covid-19 Seminar"),
            //                 "lists.event_for", Matchers.equalTo("class"),
            //                 "lists.session_id", Matchers.equalTo("16"),
            //                 "lists.class_id", Matchers.equalTo("1"),
            //                 "lists.section", Matchers.equalTo("[\"1\"]"),
            //                 "lists.from_date", Matchers.equalTo("2021-03-01 00:00:00"),
            //                 "lists.to_date", Matchers.equalTo("2021-03-16 00:00:00"),
            //                 "lists.note", Matchers.equalTo("COVID-19 is the disease caused by a new coronavirus called SARS-CoV-2.  WHO first learned of this new virus on 31 December 2019, following a report of a cluster of cases of â€˜viral pneumoniaâ€™ in Wuhan, Peopleâ€™s Republic of China."),
            //                 "lists.photo", Matchers.equalTo(""),
            //                 "lists.is_active", Matchers.equalTo("0"),
            //                 "lists.event_notification_message", Matchers.equalTo("GAVI'S RESPONSE\\r\\nRespond and protect: With COVID-19 now reported in almost all Gavi-eligible countries, the Vaccine Alliance is providing immediate funding to health systems, enabling countries to protect health care workers, perform vital surveillance and training, and purchase diagnostic tests.\\r\\n\\r\\nMaintain, restore and strengthen: Gavi will support countries to adapt and restart immunisation services, rebuild community trust and catch up those who have been missed both before and during the pandemic, while also investing in strengthening immunisation systems to be more resilient and responsive to the communities they serve.\\r\\n\\r\\nEnsure global equitable access: Gavi is co-leading COVAX, the global effort to securing a global response to COVID-19 that is effective and fair, using its unique expertise to help identify and rapidly accelerate development, production and delivery of COVID-19 vaccines so that anyone that needs them, gets them."),
            //                 "lists.show_onwebsite", Matchers.equalTo("0"),
            //                 "lists.created_at", Matchers.equalTo("2021-03-23 02:54:29")
            //         );

        }
    }
//--------------------------AlumniEventsId------------------------------------

    @Then("Send a Post request  with {string} {string} and {int} should have {string} and {string}")
    public void sendAPostRequestWithAndValueShouldHaveAnd(String str, String key,int value, String status, String message) {

        reqBody = new JSONObject();

        reqBody.put(key,value);

        if (str.equalsIgnoreCase("valid authorization")) {


            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Bearer " + HooksAPI.token)
                    .when()
                    .body(reqBody.toString())
                    .post(CommonAPI.fullPath);


            response.prettyPrint();


        } else {
            reqBody = new JSONObject();

            reqBody.put(key, value);

            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Bearer " + HooksAPI.invalidToken)
                    .when()
                    .body(reqBody.toString())
                    .post(CommonAPI.fullPath);


        }
        int intStatus = Integer.parseInt(status);

        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message",Matchers.equalTo(message));

    }



    @Then("The content of the list data should be validated with id:{string} in the Alumni Events")
    public void theContentOfTheListDataShouldBeValidatedWithIdInTheAlumniEvents(String id) {


        //String url = "https://qa.wonderworldcollege.com/api/alumniEventsId";


        reqBody = new JSONObject();

        reqBody.put("id",id);
        response=given().spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(CommonAPI.fullPath);

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("lists.id", equalTo("930"),
                        "lists.title",equalTo("Art Activite"),
                        "lists.event_for",equalTo("art"),
                        "lists.session_id",equalTo("13"),
                        "lists.class_id",equalTo(null),
                        "lists.section",Matchers.equalTo("null"),
                        "lists.from_date",equalTo("2023-11-14 00:00:00"),
                        "lists.to_date",equalTo("2023-11-24 23:59:00"),
                        "lists.note",equalTo("Paint"),
                        "lists.photo",equalTo(null),
                        "lists.is_active",equalTo("0"),
                        "lists.event_notification_message",equalTo("Art"),
                        "lists.show_onwebsite",equalTo("0"),
                        "lists.created_at",equalTo("2023-08-11 11:57:41")
                );


    }


    @Then("Generate a new record with {string}, {string}, {string}, {string},{string} in Alumni Events Id and correct data should have {string} and {string}")
    public void generateANewRecordWithInAlumniEventsIdAndCorrectDataShouldHaveAnd(String id, String title, String event_for, String session_id, String note, String status, String message) {

        RequestSpecification spec;
        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();
        spec.pathParams("pp1", "api", "pp2", "alumniEventsId ");

        JSONObject reqBody=new JSONObject();

        reqBody.put("id","1071");
        reqBody.put("title","Sport Activite");
        reqBody.put("event_for","all");
        reqBody.put("session_id","18");
        reqBody.put("note","Paint");

        JSONObject expData=new JSONObject();

        expData.put("lists",reqBody);

        response=given().spec(spec)
                .contentType(ContentType.JSON)
                .when()
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .body(reqBody.toString())
                .post("/{pp1}/{pp2}");


        response.prettyPrint();
    }

}
