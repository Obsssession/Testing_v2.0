
package Selenide;

import com.codeborne.selenide.WebDriverRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static org.testng.Assert.assertTrue;

public class MainTest {

    private final static String HtmlElementsPageLink = "https://ultimateqa.com/simple-html-elements-for-automation";
    private final static String ButtonSuccessPageLink = "https://ultimateqa.com/button-success";
    private final static String ComplicatedPageLink = "https://ultimateqa.com/complicated-page";
    private final static String BackOfficePageLink = "https://ultimateqa.com/backoffice";
    private final static String FormSubmitText = "Thanks for contacting us";
    private final static String HeaderText = "Skills Improved:";
    private final static String PointsTextFirst = "How to deal with a large page that has many elements and divisions?";
    private final static String PointsTextSecond = "Properly structuring your page objects";
    private final static Logger log = LoggerFactory.getLogger(MainTest.class);

    // Проверка на успешный переход по нажатию на кнопку "Click Me"
    @Test
    public void ButtonTest() {

        BrowserSettings.setUp(HtmlElementsPageLink);
        HtmlElementsPage.ButtonClickMe();
        ButtonSuccessPage.getTitle().shouldBe(exist);

        try {
            assertTrue(WebDriverRunner
                    .url()
                    .contains(ButtonSuccessPageLink));

        } catch (AssertionError e) {

            log.error("Ошибка при переходе по кнопке \"Click Me\": \n{}", e.getMessage());
            throw e;
        }
    }

    // Проверка на наличие трех текстовых строк в заголовке страницы
    @Test
    public void HeaderTextTest() {

        BrowserSettings.setUp(ComplicatedPageLink);

        try {
            ComplicatedPage.getHeaderTextLocator().shouldBe(text(HeaderText));
            ComplicatedPage.getPointsTextLocator().get(0).shouldBe(text(PointsTextFirst));
            ComplicatedPage.getPointsTextLocator().get(1).shouldBe(text(PointsTextSecond));

        } catch (AssertionError e) {

            log.error("Ошибка при сопоставлении текста заголовка с шаблоном: \n{}", e.getMessage());
            throw e;
        }
    }

    // Проверка на успешное прохождение авторизации по логину и паролю
    @Test
    public void LoginTest() {

        BrowserSettings.setUp(ComplicatedPageLink);
        ComplicatedPage.Login("admin", "Password");
        LoginSuccessPage
                .getTitle()
                .shouldBe(exist);

        try {
            assertTrue(WebDriverRunner
                    .url()
                    .contains(BackOfficePageLink));

        } catch (AssertionError e) {

            log.error("Ошибка при открытии окна об успешном логине: \n{}", e.getMessage());
            throw e;
        }
    }

    // Проверка на получение текста об успешном заполнении формы
    @Test
    public void SubmitFormTest() {

        BrowserSettings.setUp(ComplicatedPageLink);
        ComplicatedPage.FormSubmit("Вадим", "Привяу", "Testirovanie@OnJava.ru");

        try {
            ComplicatedPage
                    .getFormSubmitFinalMessage()
                    .shouldHave(text(FormSubmitText));

        } catch (AssertionError e) {

            log.error("Ошибка при получении текста об успешном заполнении формы: \n{}", e.getMessage());
            throw e;
        }
    }

}
