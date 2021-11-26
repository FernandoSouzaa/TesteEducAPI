package br.ufpb.dcx.apps4society.users;

import io.restassured.http.ContentType;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class CriandoUsuarioTest extends BaseApi {

    //LoginUsuarioTest tokenUsuario = new LoginUsuarioTest();

    @Test
    public void testCriarUsuario(){

        //Configurar caminho de acesso a API REST
        baseURI = "http://localhost";
        port = 8080;
        basePath = "v1/api";

        //Criar usuario - POST
        /*String name =*/ given()
                .body("{\n" +
                        "  \"email\": \"fernando.ribeiro@dcx.ufpb.br\",\n" +
                        "  \"name\": \"Nando Ribeiro\",\n" +
                        "  \"password\": \"12345678\"\n" +
                        "}")
                .contentType(ContentType.JSON)
            .when()
                .post("/users")
            .then()
                .statusCode(201)
                .assertThat().body("email", Is.is("fernando.ribeiro@dcx.ufpb.br"),
                                        "name", Is.is("Nando Ribeiro"),
                                            "password", Is.is("12345678")).log().all();


        //Validande campo name, se esta de acordo com o esperado
        //Assert.assertTrue(name.equals("Nando Ribeiro"));

    }

    @Test
    public void testLoginUsuario() {
        //Login do usuario
        //a varivel token serve para guardar o token do usuario após ele si logado no sistema
        String token = given()
                .body("{\n" +
                        "  \"email\": \"fernando.ribeiro@dcx.ufpb.br\",\n" +
                        "  \"password\": \"12345678\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when().post("/auth/login")
                .then().log().all()
                .extract().path("token");

        //Modificando usuario - PUT
        given().header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "  \"email\": \"fernando.ribeiro@dcx.ufpb.br\",\n" +
                        "  \"name\": \"Nando Ribeiro\",\n" +
                        "  \"password\": \"12345678\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .put("/auth/users")
                .then()
                .statusCode(200)
                .log().all();

        Map<String, String> headers = new HashMap<String, String>(){
            {
                put("ContentType", "application/json");
                put("Authorization", "Bearer "+token);
            }
        };

        //Retornar usuario cadastrado -GET
        given().headers(headers)
                .contentType(ContentType.JSON)
        .when().get("/auth/users")
                .then().log().all();

        //Deletar usuario - DELETE
        given().headers(headers)
                .when().delete("/auth/users")
            .then().log().all();
    }


}
