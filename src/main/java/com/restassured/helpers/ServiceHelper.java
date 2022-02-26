package com.restassured.helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.restassured.constants.EndPoints;
import com.restassured.models.Datum;
import com.restassured.models.PojoEmployee;
import com.restassured.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ServiceHelper {

    // Fetch the data from endpoints
    //POST, PUT , PATCH, GET, DELETE
    // we need to read the config value from here such as baseurl, port etc etc

    private static final String BASE_URL = ConfigManager.getConfigManagerInstance().getKeyValue("baseURL");
    private static final String PORT = ConfigManager.getConfigManagerInstance().getKeyValue("port");
    Random random;
    PojoEmployee pojoEmployee;



    public ServiceHelper(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.useRelaxedHTTPSValidation(); // if using localhost
    }



    public List<PojoEmployee> getAllEmployees(int id) {
        List<PojoEmployee> pojoEmployees = new ArrayList<PojoEmployee>();
        Response response = RestAssured.
                given().
                contentType(ContentType.JSON).accept(ContentType.JSON).
                header("content-type", "application/json").
                //pathParam("id", id).
                log().
                all().
                get(EndPoints.GET_EMPLOYEE_MICHAEL_SILVA).
                andReturn();
        ResponseBody responseBody = response.getBody();
        if ((response.getStatusCode() == HttpStatus.SC_OK) && (response.getContentType().contains("application/json"))) {

            // We are mapping here the response with Persons pojo file - models -> persons
            // This process is called deserializing
            Type type = new TypeReference<List<PojoEmployee>>() {}.getType();
            System.out.println("Response Body :" + responseBody.asString());
            pojoEmployees = response.as(type);
        }

        return pojoEmployees;
    }
}