package Selenide;

import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static org.testng.Assert.assertEquals;

public class MainTest extends BaseTest {

    @Test
    public void ButtonTest() {

        setUp(HtmlElementsPage);
        ButtonClick.click();
        ButtonSuccess.shouldBe(exist);

        assertEquals(WebDriverRunner.url(), "https://ultimateqa.com/button-success");

    }

    @Test
    public void HeaderTextTest() {

        setUp(ComplicatedPage);

        HeaderText.shouldHave(text("Skills Improved:"));
        PointsText.get(0).shouldHave(text("How to deal with a large page that has many elements and divisions?"));
        PointsText.get(1).shouldHave(text("Properly structuring your page objects"));

    }

    @Test
    public void LoginTest() {

        setUp(ComplicatedPage);
        LoginUsername.setValue("admin");
        LoginPassword.setValue("Password");
        LoginButton.click();
        LoginSuccessPage.shouldBe(exist);
        assertEquals(WebDriverRunner.url(), "https://ultimateqa.com/backoffice");

    }

    @Test
    public void SubmitFormTest() {

        setUp(MainPage);
        Button.click();
        NameInput.setValue("Вадим");
        DataInput.setValue("Легенда Тестирования");
        EmailInput.setValue("Testirovanie@OnJava.ru");
        Solution.setValue(CaptchaSolve());
        Submit.click();
        //Assert.assertEquals(FinalMessage.getText(), "Thanks for contacting us");
        FinalMessage.shouldHave(text("Thanks for contacting us"));

    }

    public String CaptchaSolve() {

        String captchaQuestion = SolveExample.getText();
        String[] parts = captchaQuestion.split(" ");
        int num1 = Integer.parseInt(parts[0]);
        String operator = parts[1];
        int num2 = Integer.parseInt(parts[2]);

        int result = switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new IllegalArgumentException("Неизвестный оператор: " + operator);

        };

        return String.valueOf(result);

    }

}
