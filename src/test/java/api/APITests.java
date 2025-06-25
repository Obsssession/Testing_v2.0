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

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

        Specifications.installSpecification(
                Specifications.requestSpec(URL)
        );

        OrderData orderData = new OrderData((int) (Math.random() * 99999999 + 1), 0, 0,
                OffsetDateTime.now(), "placed", true);

        step("Отправка POST запроса на создание заказа");
        Response response = given()
                .body(orderData)
                .post("store/order")
                .then()
                .extract().response();

        step("Проверка на корректность статуса кода", () -> {
            assertEquals(response.statusCode(), 200,
                    "Ошибка в статусе кода, тело ответа :\n" + response.getBody().asPrettyString());
        });

        OrderData responseData = response.as(OrderData.class);

        step("Проверка на корректность создания заказа по переданным данным", () -> {
            assertEquals(responseData.getId(), orderData.getId(), "Ошибка при создании заказа");
        });

    }

    @Test(description = "Удаление заказа")
    public void deleteOrder() {

        Specifications.installSpecification(
                Specifications.requestSpec(URL)
        );

        step("Отправка DELETE запроса на удаление заказа");
        Response response = given()
                .delete("store/order/" + (int) (Math.random() * 100 + 1))
                .then()
                .extract().response();

        step("Проверка на корректность статуса кода", () -> {
            int actualStatus = response.statusCode();
            assertTrue(actualStatus == 200 || actualStatus == 404,
                    String.format("Ожидался статус 200 или 404, но получен %d. Тело ответа:\n%s",
                            actualStatus,
                            response.getBody().asPrettyString()));
        });

        DeleteOrderData responseData = response.as(DeleteOrderData.class);

        step("Проверка на непустые атрибуты сообщения", () -> {
            softAssert.assertNull(responseData.getCode(), "Ошибка при получении кода сообщения:");
            softAssert.assertNotNull(responseData.getType(), "Ошибка при получении типа сообщения:");
            softAssert.assertNull(responseData.getMessage(), "Ошибка при получении сообщения:");
            softAssert.assertAll("Проваленные проверки:");
        });

    }

}
