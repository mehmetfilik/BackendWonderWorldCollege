package stepDefinitions.api;


import hooks.api.HooksAPI;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import pojos.VisitorsPurpose.Pojo_VisitorsPurpose;
import pojos.VisitorsPurpose.Pojo_VisitorsPurposeLists;

import static hooks.api.HooksAPI.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class VisitorsPurposeAPI {



    Response response;

    JSONObject reqBody;




    @Then("The contents of the list data with id: {string} in the VisitorPurpose Response Body should be verified.")
    public void the_contents_of_the_list_data_with_id_in_the_visitor_purpose_response_body_should_be_verified(String id) {


        Pojo_VisitorsPurpose data = new Pojo_VisitorsPurpose(
                id,"Marketing","","2023-01-18 01:07:12"
        );
        Pojo_VisitorsPurpose requestBody = data;
        Pojo_VisitorsPurposeLists expData = new Pojo_VisitorsPurposeLists();

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + HooksAPI.token)
                .when()
                .body(requestBody.toString())
                .post(CommonAPI.fullPath);

        response.prettyPrint();

        Pojo_VisitorsPurposeLists respPojo = response.as(Pojo_VisitorsPurposeLists.class);

        assertEquals(expData.getLists().getId(),respPojo.getLists().getId());
        assertEquals(expData.getLists().getVisitors_purpose(),respPojo.getLists().getVisitors_purpose());
        assertEquals(expData.getLists().getCreated_at(),respPojo.getLists().getCreated_at());

    }


}
