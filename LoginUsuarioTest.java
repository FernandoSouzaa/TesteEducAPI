package br.ufpb.dcx.apps4society.users;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LoginUsuarioTest extends BaseApi {

    @Test
    public void testLoginUsuario(){

        String token = given()
                .body("{\n" +
                        "  \"email\": \"fernando.ribeiro@dcx.ufpb.br\",\n" +
                        "  \"password\": \"12345678\"\n" +
                        "}")
                .contentType(ContentType.JSON)
            .when()
                .post("/auth/login")
            .then()
                .log().all()
            .extract().path("token");

    }
}
