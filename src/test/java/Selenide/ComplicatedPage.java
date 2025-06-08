package Selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ComplicatedPage{


    //private final static SelenideElement Button = $x("//a [contains(@href, 'complicated-page')]");
    private final static SelenideElement NameInput = $x("//input [@name = 'et_pb_contact_name_0']");
    private final static SelenideElement MessageInput = $x("//textarea [@name = 'et_pb_contact_message_0']");
    private final static SelenideElement EmailInput = $x("//input [@name = 'et_pb_contact_email_0']");
    private final static SelenideElement SolveExample = $x("//div [@id = 'et_pb_contact_form_0'] //span [@class = 'et_pb_contact_captcha_question']");
    private final static SelenideElement Solution = $x("//input [@name = 'et_pb_contact_captcha_0']");
    //private final static ElementsCollection Submit = $$x("//button [@name = 'et_builder_submit_button']");
    private final static SelenideElement Submit = $x("//div [@id = 'et_pb_contact_form_0'] //button [@name = 'et_builder_submit_button']");
    private final static SelenideElement FormSubmitFinalMessage = $x("//div [@class = 'et-pb-contact-message'] /p");
    private final static SelenideElement LoginUsername = $x("//div [@class = 'et_pb_module et_pb_login et_pb_login_0 et_pb_newsletter clearfix  et_pb_text_align_left et_pb_bg_layout_dark'] //input [@placeholder = 'Username']");
    private final static SelenideElement LoginPassword = $x("//div [@class = 'et_pb_module et_pb_login et_pb_login_0 et_pb_newsletter clearfix  et_pb_text_align_left et_pb_bg_layout_dark'] //input [@placeholder = 'Password']");
    private final static SelenideElement LoginButton = $x("//div [@class = 'et_pb_module et_pb_login et_pb_login_0 et_pb_newsletter clearfix  et_pb_text_align_left et_pb_bg_layout_dark'] //button[@name = 'et_builder_submit_button']");
    private final static SelenideElement HeaderTextLocator = $x("//span [@id='Skills_Improved']");
    private final static ElementsCollection PointsTextLocator = $$x("//div [@class = 'et_pb_module et_pb_text et_pb_text_0  et_pb_text_align_left et_pb_bg_layout_light'] //li");

    public static void Login(String username, String password){

        LoginUsername.setValue(username);
        LoginPassword.setValue(password);
        LoginButton.click();

    }

    public static void FormSubmit(String name, String message, String email){

        NameInput.setValue(name);
        MessageInput.setValue(message);
        EmailInput.setValue(email);
        Solution.setValue(CaptchaSolve());
        Submit.click();

    }

    public static String CaptchaSolve() {

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

    public static SelenideElement getFormSubmitFinalMessage() {
        return FormSubmitFinalMessage;
    }

    public static SelenideElement getHeaderTextLocator() {
        return HeaderTextLocator;
    }

    public static ElementsCollection getPointsTextLocator() {
        return PointsTextLocator;
    }


}
