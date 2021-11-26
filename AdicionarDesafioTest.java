package br.ufpb.dcx.apps4society.challenge;

import br.ufpb.dcx.apps4society.users.BaseApi;;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AdicionarDesafioTest{

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
    public void testAdicionarDesafio(){

        //Configurar caminho de acesso a API REST
        baseURI = "http://localhost";
        port = 8080;
        basePath = "v1/api";

        //Login do usuario
        //a varivel token serve para guardar o token do usuario após ele ter logado no sistema
        String token = given()
                .body("{\n" +
                        "  \"email\": \"fernando.ribeiro@dcx.ufpb.br\",\n" +
                        "  \"password\": \"12345678\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when().post("/auth/login")
                .then().log().all()
                .extract().path("token");

        //Adicionar novo contexto - POST -> Cabe um ajuste no tipo de url que pode ser passada no campo
        /*given().header("Authorization", "Bearer "+token)
                .body("{\n" +
                "  \"imageUrl\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQg_Sy9i5dqOVGZcUpP078oQsWydrMrjfh_G4W2jQRlsr0DTHLG&usqp=CAU\",\n" +
                "  \"name\": \"Parque de diversões\",\n" +
                "  \"soundUrl\": \"\",\n" +
                "  \"videoUrl\": \"\"\n" +
                "}")
            .contentType(ContentType.JSON)
                .when().post("auth/contexts")
            .then()
                .log().all();

        // Consultar uma lista de todos contextos registrados pelo usuario - GET
        Map<String, String> headers = new HashMap<String, String>(){
            {
                put("ContentType", "application/json");
                put("Authorization", "Bearer "+token);
            }
        };
        given()
                .headers(headers)
            .when().get("/auth/contexts")
                .then().log().all();*/

        //Adicionar desafio a um contexto
        /*given()
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "  \"imageUrl\": \"https://www.google.com/images/bola_furada.png\",\n" +
                        "  \"soundUrl\": \"https://www.palcomp3.com/musicalega.mp3\",\n" +
                        "  \"videoUrl\": \"https://www.youtube.com/sVx219203.mp4\",\n" +
                        "  \"word\": \"BOLAS\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                    .when().post("/auth/challenges/3")
                .then()
                .log().all();*/

        //Retornar lista de todos os challengens
        Map<String, String> headers = new HashMap<String, String>(){
            {
                put("ContentType", "application/json");
                put("Authorization", "Bearer "+token);
            }
        };
        /*given()
                .headers(headers)
            .when()
                .get("auth/challenges")
            .then().log().all();*/

        //Retornar um challenge cadastrado
        given()
                .headers(headers)
            .when()
                .get("auth/challenges/6")
            .then()
                .log().all();

        //Atualizar desafio de um usuario
        /*given()
                .header("Authorization", "Bearer "+token)
            .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"imageUrl\": \"https://cec-a.akamaihd.net/img-prod/images/standard/porta-pivotante-em-madeira-macica-quartier-eucalipto-210x100cm-natural-cruzeiro-1309907-foto-20180405173925121_225172_A.png\",\n" +
                        "  \"soundUrl\": \"https://cec-a.akamaihd.net/img-prod/images/standard/porta-pivotante-em-madeira-macica-quartier-eucalipto-210x100cm-natural-cruzeiro-1309907-foto-20180405173925121_225172_A.png\",\n" +
                        "  \"videoUrl\": \"teste\",\n" +
                        "  \"word\": \"Porta\"\n" +
                        "}")
                .when()
                    .put("auth/challenges/6")
                .then()
                    .log().all();*/

        //Deletar um challenge de um usuario
        /*given()
                .headers(headers)
            .when()
                .delete("auth/challenges/6")
            .then()
                .log().all();*/

        //Retornar uma pagina com desafios cadstrados
        /*given()
                .when()
            .get("/challenges?offset=0?page=0?pageNumber=0?pageSize=0?paged=true?prefix=prefix?size=20?sort.sorted=false?sort.unsorted=false?unpaged=false")
                .then()
                    .log().all();*/
    }

}
