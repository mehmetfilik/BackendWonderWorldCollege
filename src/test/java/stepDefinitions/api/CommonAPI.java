package stepDefinitions.api;


import hooks.api.HooksAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import utilities.ConfigReader;

import java.util.Arrays;

import static hooks.api.HooksAPI.spec;
import static hooks.api.HooksAPI.token;
import static io.restassured.RestAssured.given;

public class CommonAPI {

    String fullPath;

    Response response;

    JSONObject reqBody;


    /*
    @Then("Login icin {string} ve {string} girilir.")
    public void loginIcinVeGirilir(String email, String password) {


        //{
        //  "email": "test@test.com",
        //  "password": "123123123"
        //}


        reqBody = new JSONObject();

        reqBody.put("email", ConfigReader.getProperty(email));
        reqBody.put("password", ConfigReader.getProperty(password));

    }

     */


    @Then("Login icin Post request gonderilir.")
    public void loginIcinPostRequestGonderilir() {

        response = given()
                                .spec(spec)
                                .contentType(ContentType.JSON)
                                .header("Accept","application/json")
                            .when()
                                .body(reqBody.toString())
                                .post(fullPath);

        response.prettyPrint();


    }


    @Then("VisitorsPurpose icin POST request gonderilir.")
    public void visitorspurposeIcinPOSTRequestGonderilir() {

        /*
        {
            "visitors_purpose":"Veli Ziyareti",
            "description":"Veli Ziyareti Icin Gelindi"
         }
         */

        /*
            "status": 200,
            "message": "Success",
            "Token_remaining_Time": 26
            "
         */

        reqBody = new JSONObject();

        reqBody.put("visitors_purpose","Veli Ziyareti");
        reqBody.put("description","Veli Ziyareti Icin Gelindi");

        response =  given()
                        .spec(spec)
                        .contentType(ContentType.JSON)
                        .headers("Authorization","Bearer " + token)
                    .when()
                        .body(reqBody.toString())
                        .post(fullPath);

        response.prettyPrint();



    }

    @Then("For VisitorsPurpose is sent Get request.")
    public void forVisitorsPurposeIsSentGetRequest() {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer " + HooksAPI.token)
                .when()
                .get(fullPath);

        response.prettyPrint();

    }

    @Given("User sets {string} path param.")
    public void userSetsPathParam(String rawPaths) {
        // https://trendlifebuy.com/api/profile/allCountries

        // spec.pathParams("pp1","api","pp2","profile","pp3","allCountries");

        String [] paths = rawPaths.split("/"); // ["api","profile","allCountries"]

        System.out.println(Arrays.toString(paths));
       /*
        spec.pathParam("pp1","api");
        spec.pathParam("pp2","profile");
        spec.pathParam("pp3","allCountries");
        */

        // get("{pp1}/{pp2}/{pp3}")

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
}

/*

git init
git add .
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/mehmetfilik/BackendWonderWorldCollege.git
git push -u origin main

 */
