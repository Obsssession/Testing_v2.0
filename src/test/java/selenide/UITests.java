
package selenide;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;
import selenide.pages.*;
import selenide.settings.BrowserSettings;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static io.qameta.allure.Allure.step;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("UI тесты")
public class UITests extends BrowserSettings {

    ComplicatedPage complicatedPage = new ComplicatedPage();
    HtmlElementsPage htmlElementsPage = new HtmlElementsPage();
    LoginSuccessPage loginSuccessPage = new LoginSuccessPage();
    ButtonSuccessPage buttonSuccessPage = new ButtonSuccessPage();
    BackOfficePage backOfficePage = new BackOfficePage();

    @Test(description = "Переход по нажатию на кнопку \"Click Me\"")
    public void buttonTest() {

        openUrl(htmlElementsPage.getHtmlElementsPageLink());
        htmlElementsPage.buttonClickMe();
        buttonSuccessPage.getTitle().shouldBe(exist);

        step("Проверка ожидаемой страницы с фактически открытой", () -> {
            assertTrue(WebDriverRunner
                    .url()
                    .contains(buttonSuccessPage.getButtonSuccessPageLink()),
                    "Ожидалось: " + buttonSuccessPage.getButtonSuccessPageLink() +
                            ", фактически: " + WebDriverRunner.url() + "\n");
        });

    }

    @Test(description = "Наличие трех текстовых строк в заголовке страницы")
    public void headerTextTest() {

        openUrl(complicatedPage.getCOMPLICATED_PAGE_LINK());

        step("Проверка на корректность надписи в первой строке", () -> {
            complicatedPage
                    .getHeaderText()
                    .shouldHave(exactText(complicatedPage.getHEADER_TEXT()));
        });

        step("Проверка на корректность надписи во второй строке", () -> {
            complicatedPage
                    .getPointTextFirst()
                    .shouldHave(exactText(complicatedPage.getPOINT_TEXT_SECOND()));
        });

        step("Проверка на корректность надписи в третьей строке", () -> {
            complicatedPage
                    .getPointTextSecond()
                    .shouldHave(exactText(complicatedPage.getPOINT_TEXT_SECOND()));
        });

    }

    @Test(description = "Авторизация по логину и паролю")
    public void loginTest() {

        openUrl(complicatedPage.getCOMPLICATED_PAGE_LINK());
        complicatedPage.login("admin", "Password");
        loginSuccessPage
                .getTitle()
                .shouldBe(exist);

        step("Проверка ожидаемой страницы с фактически открытой", () -> {
            assertEquals(backOfficePage.getBackOfficePageLink(), WebDriverRunner
                    .url(),
                    "Ожидалось: " + backOfficePage.getBackOfficePageLink() +
                            ", фактически: " + WebDriverRunner.url() + "\n");
        });

    }

    @Test(description = "Заполнение формы")
    public void submitFormTest() {

        openUrl(complicatedPage.getCOMPLICATED_PAGE_LINK());
        complicatedPage.formSubmit("Вадим", "Привяу", "Testirovanie@OnJava.ru");

        step("Проверка ожидаемого текста с фактическим", () -> {
            complicatedPage
                    .getFormMessage()
                    .shouldHave(exactText(complicatedPage.getFORM_SUBMIT_TEXT()));
        });

    }

}
