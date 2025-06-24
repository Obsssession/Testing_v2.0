
package selenide;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;
import selenide.pages.*;
import selenide.settings.BrowserSettings;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
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

        assertTrue(WebDriverRunner
                .url()
                .contains(buttonSuccessPage.getButtonSuccessPageLink()), "Ошибка при попытке перехода по кнопке \"Click Me\"");

    }

    @Test(description = "Наличие трех текстовых строк в заголовке страницы")
    public void headerTextTest() {

        openUrl(complicatedPage.getCOMPLICATED_PAGE_LINK());

        complicatedPage
                .getHeaderText()
                .shouldHave(exactText(complicatedPage.getHEADER_TEXT()));
        complicatedPage
                .getPointTextFirst()
                .shouldHave(exactText(complicatedPage.getPOINT_TEXT_FIRST()));
        complicatedPage
                .getPointTextSecond()
                .shouldHave(exactText(complicatedPage.getPOINT_TEXT_SECOND()));

    }

    @Test(description = "Авторизация по логину и паролю")
    public void loginTest() {

        openUrl(complicatedPage.getCOMPLICATED_PAGE_LINK());
        complicatedPage.login("admin", "Password");
        loginSuccessPage
                .getTitle()
                .shouldBe(exist);

        assertTrue(WebDriverRunner
                .url()
                .contains(backOfficePage.getBackOfficePageLink()), "Ошибка при попытке авторизации");

    }

    @Test(description = "Заполнение формы")
    public void submitFormTest() {

        openUrl(complicatedPage.getCOMPLICATED_PAGE_LINK());
        complicatedPage.formSubmit("Вадим", "Привяу", "Testirovanie@OnJava.ru");

        complicatedPage
                .getFormMessage()
                .shouldHave(exactText(complicatedPage.getFORM_SUBMIT_TEXT())
                        .because("Произошла ошибка при сравнении ожидаемого текста после заполнения формы"));

    }
}
