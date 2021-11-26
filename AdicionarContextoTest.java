package br.ufpb.dcx.apps4society.contexts;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class AdicionarContextoTest {
    @Test
    public void testCriarUsuario(){

        //Configurar caminho de acesso a API REST
        baseURI = "http://localhost";
        port = 8080;
        basePath = "v1/api";

        //Criar usuario
        given()
                .body("{\n" +
                        "  \"email\": \"fernando.ribeiro@dcx.ufpb.br\",\n" +
                        "  \"name\": \"Nando Ribeiro\",\n" +
                        "  \"password\": \"12345678\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/users")
                .then()
                .log().all();
    }

    @Test
    public void testAdcionarContexto(){

        //Configurar caminho de acesso a API REST
        baseURI = "http://localhost";
        port = 8080;
        basePath = "v1/api";

        //Login do usuario - POST
        //a varivel token serve para guardar o token do usuario após ele ter si logado no sistema
        String token = given()
                .body("{\n" +
                        "  \"email\": \"fernando.ribeiro@dcx.ufpb.br\",\n" +
                        "  \"password\": \"12345678\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when().post("/auth/login")
                .then().log().all()
                .extract().path("token");


        //Adicionar novo contexto
        given().header("Authorization", "Bearer "+token)
                .body("{\n" +
                "  \"imageUrl\": \"https://encrypted-tbn0.gstatic.com/" +
                        "images?q=tbn%3AANd9GcQg_Sy9i5dqOVGZcUpP078oQsWydrMrjfh_" +
                        "G4W2jQRlsr0DTHLG&usqp=CAU\",\n" +
                "  \"name\": \"Parque de diversões\",\n" +
                "  \"soundUrl\": \"\",\n" +
                "  \"videoUrl\": \"\"\n" +
                "}")
            .contentType(ContentType.JSON)
                .when().post("auth/contexts")
            .then()
                .statusCode(200);

        // Consultar uma lista de todos contextos registrados pelo usuario - GET
        Map<String, String> headers = new HashMap<String, String>(){
            {
                put("ContentType", "application/json");
                put("Authorization", "Bearer "+token);
            }
        };
        /*given()
                .headers(headers)
            .when().get("/auth/contexts")
                .then().log().all();*/

        //Arualizar contexto - PUT
        /*given()
                .body("{\n" +
                "  \"imageUrl\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQg_Sy9i5dqOVGZcUpP078oQsWydrMrjfh_G4W2jQRlsr0DTHLG&usqp=CAU\",\n" +
                "  \"name\": \"Parque\",\n" +
                "  \"soundUrl\": \"\",\n" +
                "  \"videoUrl\": \"\"\n" +
                "}")
            .headers("Authorization", "Bearer "+token)
                .contentType(ContentType.JSON)
                .when().put("/auth/contexts/3")
            .then()
                .log().all();*/

        //Deletar contexto - DELETE
        /*given().headers(headers)
                .when().delete("auth/contexts/4")
            .then()
                .log().all();*/

        //Consultar listas de contextos registtados no serviço -> GET -> Não ta bem especificado - cabe um ajuste
        /*given().headers(headers)
            .queryParam("email", "fernando.ribeiro@dcx.ufpb.br")
                .queryParam("name", "Nando Ribeiro")
                .queryParam("offset",60)
                .queryParam("page", 0)
                .queryParam("pageNumber", 3)
                .queryParam("pageSize", 20)
                .queryParam("paged", true)
                .queryParam("size", 20)
                .queryParam("sort.sorted", true)
                .queryParam("unpaged", true)
            .when().get("/contexts")
                .then()
            .log().all();*/

        //Consultar um contexto especifico - GET
        /*given()
                .headers(headers)
            .when().get("contexts/3")
                .then()
            .log().all();*/
    }
}
