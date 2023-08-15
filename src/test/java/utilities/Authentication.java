package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static hooks.api.HooksAPI.spec;
import static io.restassured.RestAssured.given;

public class Authentication {

    private static RequestSpecification spec;

    public static String generateToken(){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp1","api","pp2","getToken");

        JSONObject reqBody = new JSONObject();

        reqBody.put("email", ConfigReader.getProperty("adminEmail"));
        reqBody.put("password", ConfigReader.getProperty("adminPassword"));

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(reqBody.toString())
                .post("/{pp1}/{pp2}");

        JsonPath resJP = response.jsonPath();

        String token=resJP.getString("token");

        return token;
    }

    public static String generateInvalidToken(){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp1","api","pp2","getToken");

        JSONObject reqBody = new JSONObject();

        reqBody.put("email", ConfigReader.getProperty("adminInvalidEmail"));
        reqBody.put("password", ConfigReader.getProperty("adminInvalidPassword"));

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(reqBody.toString())
                .post("/{pp1}/{pp2}");

        JsonPath resJP = response.jsonPath();

        String invalidToken=resJP.getString("token");

        return invalidToken;
    }


    public static String teacherGenerateToken(){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp1","api","pp2","getToken");

        JSONObject reqBody = new JSONObject();

        reqBody.put("email", ConfigReader.getProperty("teacherEmail"));
        reqBody.put("password", ConfigReader.getProperty("teacherPassword"));

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(reqBody.toString())
                .post("/{pp1}/{pp2}");

        JsonPath resJP = response.jsonPath();

        String token=resJP.getString("token");

        return token;
    }

    public static String teacherGenerateInvalidToken(){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp1","api","pp2","getToken");

        JSONObject reqBody = new JSONObject();

        reqBody.put("email", ConfigReader.getProperty("teacherInvalidEmail"));
        reqBody.put("password", ConfigReader.getProperty("teacherInvalidPassword"));

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(reqBody.toString())
                .post("/{pp1}/{pp2}");

        JsonPath resJP = response.jsonPath();

        String invalidToken=resJP.getString("token");

        return invalidToken;
    }






}
