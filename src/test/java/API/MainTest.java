package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.OffsetDateTime;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class MainTest {

        private final static String URL = "https://petstore.swagger.io/v2/";
        private static final Logger log = LoggerFactory.getLogger(MainTest.class);

        @AfterMethod
        public void resetSpecifications() {
                RestAssured.reset();  // Сбрасывает все настройки RestAssured
        }

        // Проверка на успешное создание нового заказа
        @Test
        public void CreateNewOrder() {

                Specifications.installSpecification(
                        Specifications.RequestSpec(URL)
                );

                OrderData orderData = new OrderData((int) (Math.random() * 99999999 + 1), 0, 0, OffsetDateTime.now(), "placed", true);

                try {
                        Response response = given()
                                .body(orderData)
                                .post("store/order")
                                .then()
                                .extract().response();

                        assert response != null;

                        if (response.statusCode() != 200) {
                            log.error("Тело ответа: \n{}", response.getBody().asPrettyString());
                        }

                    OrderData responseData = response.as(OrderData.class);
                    assertEquals(responseData.getId(), orderData.getId());

                } catch (AssertionError e) {
                    log.error("Ошибка при создании заказа: \n{}", e.getMessage());
                        throw e;
                }
        }

        // Проверка на успешное удаление заказа
        @Test
        public void DeleteOrder() {

                Specifications.installSpecification(
                        Specifications.RequestSpec(URL)
                );

                try {
                        Response response = given()
                                .delete("store/order/" + (int) (Math.random() * 100 + 1))
                                .then()
                                .extract().response();

                        assert response != null;

                        if (response.statusCode() != 400 && response.statusCode() != 404) {
                                log.error("Тело ответа: \n{}", response.getBody().asPrettyString());
                        }

                        DeleteOrderData responseData = response.as(DeleteOrderData.class);
                        assertNotNull(responseData.getCode());
                        assertNotNull(responseData.getType());
                        assertNotNull(responseData.getMessage());

                } catch (AssertionError e) {
                    log.error("Ошибка при удалении заказа: \n{}", e.getMessage());
                        throw e;
                }
        }

/*

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

*/

/*

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

 */

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

