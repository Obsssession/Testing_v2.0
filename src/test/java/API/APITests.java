package API;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.OffsetDateTime;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@Test
@Epic("API тесты")
public class APITests {

    SoftAssert softAssert = new SoftAssert();
    private final static String URL = "https://petstore.swagger.io/v2/";

    @AfterMethod
    public void resetSpecifications() {
        RestAssured.reset();  // Сбрасывает все настройки RestAssured
    }

    @Test
    @Description("Создание заказа")
    public void CreateNewOrder() {

        softAssert = new SoftAssert();
        Specifications.installSpecification(
                Specifications.RequestSpec(URL)
        );

        OrderData orderData = new OrderData((int) (Math.random() * 99999999 + 1), 0, 0, OffsetDateTime.now(), "placed", true);

        Response response = given()
                .body(orderData)
                .post("store/order")
                .then()
                .extract().response();

        if (response.statusCode() != 200) {

                softAssert.fail("Запрос завершился с кодом " + response.statusCode());
                softAssert.fail("Тело ответа: \n" + response.getBody().asPrettyString());
                softAssert.assertAll("Произошла ошибка при выполнении запроса:");

        }

        OrderData responseData = response.as(OrderData.class);
        assertEquals(responseData.getId(), orderData.getId(), "Ошибка при создании заказа:");

    }

    @Test
    @Description("Удаление заказа")
    public void DeleteOrder() {

        softAssert = new SoftAssert();
        Specifications.installSpecification(
                Specifications.RequestSpec(URL)
        );

        Response response = given()
                .delete("store/order/" + (int) (Math.random() * 100 + 1))
                .then()
                .extract().response();

        if (response.statusCode() != 200 && response.statusCode() != 404) {

            softAssert.fail("Запрос завершился с кодом " + response.statusCode());
            softAssert.fail("Тело ответа: \n" + response.getBody().asPrettyString());
            softAssert.assertAll("Произошла ошибка при выполнении запроса:");

        }

        DeleteOrderData responseData = response.as(DeleteOrderData.class);

            softAssert.assertNotNull(responseData.getCode(), "Ошибка при получении кода сообщения:");
            softAssert.assertNotNull(responseData.getType(), "Ошибка при получении типа сообщения:");
            softAssert.assertNotNull(responseData.getMessage(), "Ошибка при получении сообщения:");
            softAssert.assertAll("Проваленные проверки:");

    }

}
