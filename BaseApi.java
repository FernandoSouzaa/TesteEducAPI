package br.ufpb.dcx.apps4society.users;

import org.junit.BeforeClass;

import static io.restassured.RestAssured.*;

public class BaseApi {
    @BeforeClass
    public static void preCondicao(){

        //Configurar caminho de acesso a API REST
        baseURI = "http://localhost";
        port = 8080;
        basePath = "v1/api";
    }
}
