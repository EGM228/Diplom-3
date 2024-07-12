package org.example;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";
    private static final String COURIER_AUTH_PATH = "/api/auth";

    @Step("Creating correct user")
    public ValidatableResponse createUser(User user) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(COURIER_AUTH_PATH+"/register")
                .then().log().all();
    }

    @Step("Check of creating of correct user")
    public void checkCreatedSuccessfully(ValidatableResponse createResponse) {
        boolean created = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");
        assertTrue(created);
    }

    @Step("Logging user")
    public ValidatableResponse loginUser(UserCredentials creds) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(creds)
                .when()
                .post(COURIER_AUTH_PATH+"/login")
                .then().log().all();
    }

    @Step("Check logging successfully")
    public void checkLoggedInSuccessfully(ValidatableResponse loginResponse) {
        boolean logged = loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");
        assertTrue(logged);
    }

    @Step("Getting access token")
    public String getAccessToken(ValidatableResponse createResponse) {
        String accessToken;
        return accessToken = createResponse
                .extract()
                .path("accessToken");
    }

    @Step("Deleting user")
    public void deleteUser(String accessToken) {
        given().log().all()
                .contentType(ContentType.JSON)
                .header("authorization",accessToken)
                .baseUri(BASE_URI)
                .when()
                .delete(COURIER_AUTH_PATH+"/user")
                .then().log().all();
    }
}
