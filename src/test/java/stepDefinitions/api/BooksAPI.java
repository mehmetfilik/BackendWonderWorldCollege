package stepDefinitions.api;
import hooks.api.HooksAPI;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import pojos.BookList.Pojo_Books;
import pojos.BookList.Pojo_BooksList;

import static hooks.api.HooksAPI.spec;
import static io.restassured.RestAssured.given;



public class BooksAPI {

    Response response;

    JSONObject reqBody;


    @Then("Then The contents of the list data with id: {string} in the BookList Response Body should be verified.")
    public void then_the_contents_of_the_list_data_with_id_in_the_book_list_response_body_should_be_verified(String id) {

       Pojo_Books pojoBooks = new Pojo_Books(
                "1","Multiplication and Division Grades 3-4","78878","","","110","Barbara Bando","Barbara Bando",
               "100","12.00","2022-05-04","The duo dump her in a nearby river after a failed attempt to hang her. Tonya survives, and the two men are arrested by Sheriff Ozzie Walls.",
               "yes","no","2022-05-02 03:02:39",""
       );

        Pojo_Books requestBody = pojoBooks;
        Pojo_BooksList expectedData = new Pojo_BooksList();

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + HooksAPI.token)
                .when()
                .body(requestBody.toString())
                .post(CommonAPI.fullPath);

        response.prettyPrint();

        Pojo_BooksList respPojo = response.as(Pojo_BooksList.class);




    }

}
