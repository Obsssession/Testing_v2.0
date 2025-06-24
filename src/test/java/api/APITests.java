package api;

import api.data.DeleteOrderData;
import api.data.OrderData;
import api.specs.Specifications;
import groovy.util.logging.Log4j;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.OffsetDateTime;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@Slf4j
@Log4j
@Epic("API тесты")
public class APITests {

    SoftAssert softAssert = new SoftAssert();
    private final static String URL = "https://petstore.swagger.io/v2/";

    @AfterMethod
    public void resetSpecifications() {
        RestAssured.reset();  // Сбрасывает все настройки RestAssured
    }

    @Step
    @Test(description = "Создание заказа")
    public void createNewOrder() {

        softAssert = new SoftAssert();
        Specifications.installSpecification(
                Specifications.requestSpec(URL)
        );

        OrderData orderData = new OrderData((int) (Math.random() * 99999999 + 1), 0, 0, OffsetDateTime.now(), "placed", true);

        Response response = given()
                .body(orderData)
                .post("store/order")
                .then()
                .extract().response();

        assertEquals(response.statusCode(), 200, "Ошибка в статусе кода, тело ответа :\n" + response.getBody().asPrettyString());

        OrderData responseData = response.as(OrderData.class);
        assertEquals(responseData.getId(), orderData.getId(), "Ошибка при создании заказа");

    }

    @Test(description = "Удаление заказа")
    public void deleteOrder() {

        softAssert = new SoftAssert();
        Specifications.installSpecification(
                Specifications.requestSpec(URL)
        );

        Response response = given()
                .delete("store/order/" + (int) (Math.random() * 100 + 1))
                .then()
                .extract().response();

        int actualStatus = response.statusCode();
        if (actualStatus != 100 && actualStatus != 104) {
            softAssert.fail(String.format("Некорректный статус-код. Ожидался 100 или 104, но получен %d. Тело ответа:\n%s",
                    actualStatus,
                    response.getBody().asPrettyString()));
        }

        DeleteOrderData responseData = response.as(DeleteOrderData.class);

        softAssert.assertNull(responseData.getCode(), "Ошибка при получении кода сообщения:");
        softAssert.assertNotNull(responseData.getType(), "Ошибка при получении типа сообщения:");
        softAssert.assertNull(responseData.getMessage(), "Ошибка при получении сообщения:");
        softAssert.assertAll("Проваленные проверки:");

    }

}
