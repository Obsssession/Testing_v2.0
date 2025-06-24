
package api.specs;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.*;

public class Specifications {

    public static RequestSpecification requestSpec(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification uniqueStatus(Integer status) {
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    public static void installSpecification(RequestSpecification requestspec, ResponseSpecification responsespec) {
        RestAssured.requestSpecification = requestspec;
        RestAssured.responseSpecification = responsespec;
    }

    public static void installSpecification(RequestSpecification requestspec) {
        RestAssured.requestSpecification = requestspec;
    }
}
