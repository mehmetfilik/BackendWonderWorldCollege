package stepDefinitions.api;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import static hooks.api.HooksAPI.*;
import static io.restassured.RestAssured.given;

public class BooksAPI {

    Response response;

    JSONObject reqBody;


    @Then("Then The contents of the list data with id: {string} in the BookList Response Body should be verified.")
    public void then_the_contents_of_the_list_data_with_id_in_the_book_list_response_body_should_be_verified(String id) {

        String Url = "https://qa.wonderworldcollege.com/api/booksId";

        reqBody = new JSONObject();

        reqBody.put("id",id);
        response=given().contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + token)
                .when()
                .body(reqBody.toString())
                .get(Url);
        response.prettyPrint();

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .body("lists.id",Matchers.equalTo("1"),
                        "lists.book_title",Matchers.equalTo("Multiplication and Division Grades 3-4 23456"),
                        "lists.book_no",Matchers.equalTo("788789"),
                        "lists.isbn_no",Matchers.equalTo(""),
                        "lists.subject",Matchers.equalTo(""),
                        "lists.rack_no",Matchers.equalTo("110"),
                        "lists.publish",Matchers.equalTo("Barbara Bando"),
                        "lists.author",Matchers.equalTo("Barbara Bando"),
                        "lists.qty",Matchers.equalTo("100"),
                        "lists.perunitcost",Matchers.equalTo("12.00"),
                        "lists.postdate",Matchers.equalTo("2022-05-04"),
                        "lists.description",Matchers.equalTo(" The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls."),
                        "lists.available",Matchers.equalTo("yes"),
                        "lists.is_active",Matchers.equalTo("no"),
                        "lists.created_at",Matchers.equalTo("2023-08-10 06:52:41"),
                        "lists.updated_at",Matchers.equalTo(null)
                );


    }
}
