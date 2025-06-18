
package Selenide;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.assertTrue;

@Test (groups = "UI")
@Epic("UI тесты")
public class UITests {

    @Test
    @Description ("Переход по нажатию на кнопку \"Click Me\"")
    public void ButtonTest() {

        BrowserSettings.setUp(HtmlElementsPage.getHtmlElementsPageLink());
        HtmlElementsPage.ButtonClickMe();
        ButtonSuccessPage.getTitle().shouldBe(exist);

        assertTrue(WebDriverRunner
                .url()
                .contains(ButtonSuccessPage.getButtonSuccessPageLink()), "Ошибка при попытке перехода по кнопке \"Click Me\"");

    }

    @Test
    @Description ("Наличие трех текстовых строк в заголовке страницы")
    public void HeaderTextTest() {

        BrowserSettings.setUp(ComplicatedPage.getComplicatedPageLink());

        ComplicatedPage
                .getHeaderTextLocator()
                .shouldHave(exactText(ComplicatedPage.getHeaderText()));
        ComplicatedPage
                .getPointsTextLocator()
                .get(0)
                .shouldHave(exactText(ComplicatedPage.getPointsTextFirst()));
        ComplicatedPage
                .getPointsTextLocator()
                .get(1)
                .shouldHave(exactText(ComplicatedPage.getPointsTextSecond()));

    }

    @Test
    @Description ("Авторизация по логину и паролю")
    public void LoginTest() {

        BrowserSettings.setUp(ComplicatedPage.getComplicatedPageLink());
        ComplicatedPage.Login("admin", "Password");
        LoginSuccessPage
                .getTitle()
                .shouldBe(exist);

        assertTrue(WebDriverRunner
                .url()
                .contains(BackOfficePage.getBackOfficePageLink()), "Ошибка при попытке авторизации");

    }

    @Test
    @Description ("Заполнение формы")
    public void SubmitFormTest() {

        BrowserSettings.setUp(ComplicatedPage.getComplicatedPageLink());
        ComplicatedPage.FormSubmit("Вадим", "Привяу", "Testirovanie@OnJava.ru");

        ComplicatedPage
                .getFormSubmitFinalMessage()
                .shouldHave(exactText(ComplicatedPage.getFormSubmitText())
                        .because("Произошла ошибка при сравнении ожидаемого текста после заполнения формы"));

    }
}
