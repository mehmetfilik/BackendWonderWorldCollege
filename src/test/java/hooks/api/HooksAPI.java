package hooks.api;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import utilities.Authentication;
import utilities.ConfigReader;


public class HooksAPI {

    public static RequestSpecification spec;
    public static String token;
    public static String invalidToken;

    public static String tokenTeacher;
    public static String tokenInvalidTeacher;

    @Before(order = 0)
    public void setUp() {

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

    }

    @Before(order = 1)
    public void beforeGenerateToken() {

        token = Authentication.generateToken();
        invalidToken = Authentication.generateInvalidToken();

        tokenTeacher = Authentication.teacherGenerateToken();
        tokenInvalidTeacher = Authentication.teacherGenerateInvalidToken();
    }
}