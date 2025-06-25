package selenide.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

@Getter
public class ComplicatedPage {

    private final String POINT_TEXT_FIRST = "How to deal with a large page that has many elements and divisions?";
    private final String POINT_TEXT_SECOND = "Properly structuring your page objects";
    private final String HEADER_TEXT = "Skills Improved:";
    private final String FORM_SUBMIT_TEXT = "Thanks for contacting us";
    private final String COMPLICATED_PAGE_LINK = "https://ultimateqa.com/complicated-page";

    private static final String NAME_INPUT_XPATH = "//input[@name='et_pb_contact_name_0']";
    private static final String MESSAGE_INPUT_XPATH = "//textarea[@name='et_pb_contact_message_0']";
    private static final String EMAIL_INPUT_XPATH = "//input[@name='et_pb_contact_email_0']";
    private static final String CAPTCHA_QUESTION_XPATH = "//div[@id='et_pb_contact_form_0']//span[@class='et_pb_contact_captcha_question']";
    private static final String CAPTCHA_SOLUTION_XPATH = "//input[@name='et_pb_contact_captcha_0']";
    private static final String SUBMIT_BUTTON_XPATH = "//div[@id='et_pb_contact_form_0']//button[@name='et_builder_submit_button']";
    private static final String FORM_MESSAGE_XPATH = "//div[@class='et-pb-contact-message']/p";
    private static final String LOGIN_SECTION_XPATH = "//div[@class='et_pb_module et_pb_login et_pb_login_0 et_pb_newsletter clearfix  et_pb_text_align_left et_pb_bg_layout_dark']";
    private static final String HEADER_TEXT_XPATH = "//span[@id='Skills_Improved']";
    private static final String POINTS_TEXT_XPATH = "//div[@class='et_pb_module et_pb_text et_pb_text_0  et_pb_text_align_left et_pb_bg_layout_light']//li";

    private final SelenideElement nameInput = $x(NAME_INPUT_XPATH);
    private final SelenideElement messageInput = $x(MESSAGE_INPUT_XPATH);
    private final SelenideElement emailInput = $x(EMAIL_INPUT_XPATH);
    private final SelenideElement captchaQuestion = $x(CAPTCHA_QUESTION_XPATH);
    private final SelenideElement captchaSolution = $x(CAPTCHA_SOLUTION_XPATH);
    private final SelenideElement submitButton = $x(SUBMIT_BUTTON_XPATH);
    private final SelenideElement formMessage = $x(FORM_MESSAGE_XPATH);
    private final SelenideElement loginUsername = $x(LOGIN_SECTION_XPATH + "//input[@placeholder='Username']");
    private final SelenideElement loginPassword = $x(LOGIN_SECTION_XPATH + "//input[@placeholder='Password']");
    private final SelenideElement loginButton = $x(LOGIN_SECTION_XPATH + "//button[@name='et_builder_submit_button']");
    private final SelenideElement headerText = $x(HEADER_TEXT_XPATH);
    private final SelenideElement pointTextFirst = $$x(POINTS_TEXT_XPATH).get(0);
    private final SelenideElement pointTextSecond = $$x(POINTS_TEXT_XPATH).get(1);

    @Step("Авторизация")
    public void login(String username, String password) {

        step("Заполнение логина: " + username, () -> loginUsername.setValue(username));

        step("Заполнение пароля: " + password, () -> loginPassword.setValue(password));

        step("Отправка запроса на авторизацию", () -> loginButton.click());

    }

    public void formSubmit(String name, String message, String email) {

        step("Заполнение имени: " + name, () -> nameInput.setValue(name));

        step("Заполнение сообщения: " + message, () -> messageInput.setValue(message));

        step("Заполнение почты: " + email, () -> emailInput.setValue(email));

        step("Решение капчи", () -> captchaSolution.setValue(captchaSolve()));

        step("Отправка формы", () -> submitButton.click());

    }

    public String captchaSolve() {

        String captcha = captchaQuestion.getText();
        String[] parts = captcha.split(" ");
        int num1 = Integer.parseInt(parts[0]);
        String operator = parts[1];
        int num2 = Integer.parseInt(parts[2]);

        int result = switch (operator) {
            //case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new IllegalArgumentException("Неизвестный оператор: " + operator);

        };

        return String.valueOf(result);

    }

}
