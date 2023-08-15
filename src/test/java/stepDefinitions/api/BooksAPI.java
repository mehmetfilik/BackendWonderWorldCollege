package stepDefinitions.api;
import hooks.api.HooksAPI;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import java.util.HashMap;
import static hooks.api.HooksAPI.spec;
import static hooks.api.HooksAPI.token;
import static hooks.api.HooksAPI.*;
import static io.restassured.RestAssured.given;

public class BooksAPI {

    String fullPath;
    Response response;

    JSONObject reqBody;

    String addId;

    JsonPath resJP;

    String updatedId;


    @Then("Then The contents of the list data with id: {string} in the BookList Response Body should be verified.")
    public void then_the_contents_of_the_list_data_with_id_in_the_book_list_response_body_should_be_verified(String id) {

        String Url = "https://qa.wonderworldcollege.com/api/booksId";

        reqBody = new JSONObject();

        reqBody.put("id", id);
        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " +HooksAPI.token)
                .when()
                .body(reqBody.toString())
                .post(CommonAPI.fullPath);
        response.prettyPrint();

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .body("lists.id", Matchers.equalTo("1"),
                        "lists.book_title", Matchers.equalTo("nnnnnnnnnnnnnnnnn"),
                        "lists.book_no", Matchers.equalTo("788789"),
                        "lists.isbn_no", Matchers.equalTo(""),
                        "lists.subject", Matchers.equalTo(""),
                        "lists.rack_no", Matchers.equalTo("110"),
                        "lists.publish", Matchers.equalTo("Barbara Bando"),
                        "lists.author", Matchers.equalTo("Barbara Bando"),
                        "lists.qty", Matchers.equalTo("100"),
                        "lists.perunitcost", Matchers.equalTo("12.00"),
                        "lists.postdate", Matchers.equalTo("2022-05-04"),
                        "lists.description", Matchers.equalTo(" The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls."),
                        "lists.available", Matchers.equalTo("yes"),
                        "lists.is_active", Matchers.equalTo("no"),
                        "lists.created_at", Matchers.equalTo("2023-08-14 13:02:31"),
                        "lists.updated_at", Matchers.equalTo(null)
                );


    }


    @Then("Postrequest sent with {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},must have {string} and {string}")
    public void postrequestSentWithMustHaveAnd(String book_title, String book_no, String isbn_no, String subject, String rack_no, String publish, String author, String qty, String perunitcost, String postdate, String description, String status, String message) {

        reqBody = new JSONObject();

        reqBody.put("book_title",book_title);
        reqBody.put("book_no",book_no);
        reqBody.put("isbn_no",isbn_no);
        reqBody.put("subject",subject);
        reqBody.put("rack_no",rack_no);
        reqBody.put("publish",publish);
        reqBody.put("author",author);
        reqBody.put("qty",qty);
        reqBody.put("perunitcost",perunitcost);
        reqBody.put("postdate",postdate);
        reqBody.put("description",description);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .post(CommonAPI.fullPath);

        int intStatus = Integer.parseInt(status);

        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));
    }

    @Then("Postrequest with invalid Authorization sent with {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},must have {string} and {string}")
    public void PostrequestwithinvalidAuthorizationsentwith(String  book_title, String book_no, String isbn_no, String subject, String rack_no, String publish, String author, String qty, String perunitcost, String postdate, String description, String status, String message) {

        reqBody = new JSONObject();

        reqBody.put("book_title",book_title);
        reqBody.put("book_no",book_no);
        reqBody.put("isbn_no",isbn_no);
        reqBody.put("subject",subject);
        reqBody.put("rack_no",rack_no);
        reqBody.put("publish",publish);
        reqBody.put("author",author);
        reqBody.put("qty",qty);
        reqBody.put("perunitcost",perunitcost);
        reqBody.put("postdate",postdate);
        reqBody.put("description",description);

        response =  given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + invalidToken)
                .when()
                .body(reqBody.toString())
                .post(CommonAPI.fullPath);

        int intStatus = Integer.parseInt(status);

        response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(intStatus)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo(message));
    }

    @Then("in Books Postrequest sent with {string} must have status: {int} and message: {string}")
    public void inBooksPostrequestSentWithMustHaveStatusAndMessage(String id, int status, String message) {

        JSONObject reqBody = new JSONObject();

        HashMap<String,Object> respMap = response.as(HashMap.class);

        System.out.println("respMap"+respMap.toString());

        addId = respMap.get("addId").toString();
        System.out.println(addId);
    }


    @Then("in books with {string} Authorization is sent Patch request must id: {string}, update_id_key: {string}, status: {int} and message: {string}")
    public void inBooksWithAuthorizationIsSentPatchRequestMustIdUpdate_id_keyStatusAndMessage(String str, String id, String updateId, int status, String message) {

        JSONObject reqBody = new JSONObject();

        reqBody.put("id", id);
        reqBody.put("book_title","Division");
        reqBody.put("book_no", "11111");
        reqBody.put("isbn_no", "15");
        reqBody.put("subject", "qwert");
        reqBody.put("rack_no", "258");
        reqBody.put("publish", "258");
        reqBody.put("author", "Bruce Wayne");
        reqBody.put("qty", "150");
        reqBody.put("perunitcost", "15.00");
        reqBody.put("postdate", "2023-05-04");
        reqBody.put("description", "The duo dump her in a nearby river after a failed attempt to hang her.");

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

    @Then("After Books updating Postrequest sent with {string} must have status: {int} and message: {string}")
    public void afterBooksUpdatingPostrequestSentWithMustHaveStatusAndMessage(String id, int status, String message) {

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

    @Then("After Books deleting Postrequest sent with {string} must have status: {int} and message: {string}")
    public void afterBooksDeletingPostrequestSentWithMustHaveStatusAndMessage(String id, Integer status, String message) {

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






