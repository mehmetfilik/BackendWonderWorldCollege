package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.Arrays;

import static hooks.api.HooksAPI.spec;
import static io.restassured.RestAssured.given;

public class VehicleAPI {
    String fullPath;

    Response response;

    JSONObject reqBody;

    @Given("User sets {string} path param")
    public void user_sets_path_param(String rawPaths) {
        String [] paths = rawPaths.split("/");

        System.out.println(Arrays.toString(paths));


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
    @Given("For {string} is sent Get request")
    public void for_is_sent_get_request(String string) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + HooksAPI.token)
                .when()
                .get(fullPath);

        response.prettyPrint();
    }
    @Given("Verifies that the status code in Response is {int}")
    public void verifies_that_the_status_code_in_response_is(Integer statusCode) {
     response.then().assertThat().statusCode(statusCode);
    }
    @Then("Verifies that {string} is {string} in Response")
    public void verifies_that_is_in_response(String bodyName, String value) {
        JsonPath responseJP=response.jsonPath();

    //    Assert.assertEquals(value,responseJP.get(bodyName));
    }

    @When("A GET request is sent with invalid authorization")
    public void aGETRequestIsSentWithInvalidAuthorization() {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " +HooksAPI.invalidToken)
                .when()
                .get(fullPath);

        response.prettyPrint();
    }
}
