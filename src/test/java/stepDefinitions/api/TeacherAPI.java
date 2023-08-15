package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import utilities.Authentication;
import utilities.ConfigReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TeacherAPI extends Authentication {

    CommonAPI commonApi = new CommonAPI();

    HooksAPI hooksAPI = new HooksAPI();

    String tokenTeacherApi;

    String fullEndpoint;

    Response response;

    @Given("a valid authorization is provided")
    public void a_valid_authorization_is_provided() {
        tokenTeacherApi = teacherGenerateToken();
    }
    @When("a GET request is sent to {string} endpoint")
    public void a_get_request_is_sent_to_endpoint(String params) {
       fullEndpoint = ConfigReader.getProperty("base_url") + "/" + params;
       response = given().contentType(ContentType.JSON)
                    .when().header("Authorization", "Bearer " + tokenTeacherApi)
                    .get(fullEndpoint);
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer int1) {
        response.then().assertThat().statusCode(int1);

    }
    @Then("the response message should be {string}")
    public void the_response_message_should_be(String string) {
        response.then().assertThat().body("message", Matchers.equalTo(string));
    }

    @Given("a invalid authorization is provided")
    public void aInvalidAuthorizationIsProvided() {
        tokenTeacherApi = teacherGenerateInvalidToken();
    }

    @Then("the content of the {string} in the response body should match the specified data")
    public void theContentOfTheInTheResponseBodyShouldMatchTheSpecifiedData(String arg0) {

    }
}
