package API;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class MainTest {

        private final static String URL = "https://petstore.swagger.io/v2/";
        CallMethods callMethods = new CallMethods();

        @Test
        public void ConfirmCreateNewOrder() {

                Specifications.installSpecification(
                        Specifications.RequestSpec(URL),
                        Specifications.UniqueStatus(200)
                );

                 OrderData response = given()
                        .get("store/order/" + callMethods.CreateNewOrder())
                        .then().log().all()
                        .extract().body().as(OrderData.class);

                assertTrue(response.isComplete());

        }

        @Test
        public void CreateNewOrder() {

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

                assertEquals(response.getId(), orderData.getId());

        }

        @Test
        public void DeleteOrder() {

                Specifications.installSpecification(
                        Specifications.RequestSpec(URL),
                        Specifications.UniqueStatus(200)
                );

                DeleteOrderData response = given()
                        .delete("store/order/" + callMethods.CreateNewOrder())
                        .then().log().all()
                        .extract()
                        .body()
                        .as(DeleteOrderData.class);

                assertNotNull(response.getCode());
                assertNotNull(response.getType());
                assertNotNull(response.getMessage());
                assertEquals(response.getType(), "unknown");

        }

        @Test
        public void ConfirmDeleteOrder(){

                Specifications.installSpecification(
                        Specifications.RequestSpec(URL),
                        Specifications.UniqueStatus(404)
                );

                DeleteOrderData ConfirmDelete = given()
                        .get("store/order/" + callMethods.DeleteOrder())
                        .then().log().all()
                        .extract()
                        .body()
                        .as(DeleteOrderData.class);

                assertEquals(ConfirmDelete.getMessage(), "Order not found");
        }

/*
        @Test
        public void secondTest(){
                List<OrderData> Shop = given()
                        .when()
                        .contentType(ContentType.JSON)
                        .get(URL)
                        .then().extract().body().jsonPath().getList("1", OrderData.class);
                Shop.forEach(x -> assertEquals(x.getPetId(), x.getQuantity()));
        }
*/

}

