package API;

import static io.restassured.RestAssured.given;

public class CallMethods {

    private final static String URL = "https://petstore.swagger.io/v2/";

    public int CreateNewOrder() {

        Specifications.installSpecification(
                Specifications.RequestSpec(URL),
                Specifications.UniqueStatus(200)
        );

        OrderData orderData = new OrderData((int) (Math.random() * 999999999 + 1), 0, 0, "2025-05-26T18:02:22.516+0000", "placed", true);

        OrderData response = given()
                .body(orderData)
                .post("store/order")
                .then().log().all()
                .extract().body().as(OrderData.class);

        return response.getId();

    }

    public String DeleteOrder() {

        Specifications.installSpecification(
                Specifications.RequestSpec(URL),
                Specifications.UniqueStatus(200)
        );

        DeleteOrderData response = given()
                .delete("store/order/" + CreateNewOrder())
                .then().log().all()
                .extract()
                .body()
                .as(DeleteOrderData.class);

        return response.getMessage();

    }

}
