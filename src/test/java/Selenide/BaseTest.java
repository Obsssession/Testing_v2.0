package Selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class BaseTest {

    protected final static String MainPage = "https://ultimateqa.com/automation";
    protected final static String HtmlElementsPage = "https://ultimateqa.com/simple-html-elements-for-automation";
    protected final static String ComplicatedPage = "https://ultimateqa.com/complicated-page";
    protected final static SelenideElement Button = $x("//a [contains(@href, 'complicated-page')]");
    protected final static SelenideElement NameInput = $x("//input [@name = 'et_pb_contact_name_0']");
    protected final static SelenideElement DataInput = $x("//textarea [@name = 'et_pb_contact_message_0']");
    protected final static SelenideElement EmailInput = $x("//input [@name = 'et_pb_contact_email_0']");
    protected final static SelenideElement SolveExample = $x("//div [@id = 'et_pb_contact_form_0'] //span [@class = 'et_pb_contact_captcha_question']");
    protected final static SelenideElement Solution = $x("//input [@name = 'et_pb_contact_captcha_0']");
    //protected final static ElementsCollection Submit = $$x("//button [@name = 'et_builder_submit_button']");
    protected final static SelenideElement Submit = $x("//div [@id = 'et_pb_contact_form_0'] //button [@name = 'et_builder_submit_button']");
    protected final static SelenideElement FinalMessage = $x("//div [@class = 'et-pb-contact-message'] /p");
    protected final static SelenideElement ButtonClick = $x("// div [@class = 'et_pb_module et_pb_cta_0 et_pb_promo  et_pb_text_align_center et_pb_bg_layout_dark'] //a [@class ='et_pb_button et_pb_promo_button']");
    protected final static SelenideElement ButtonSuccess = $x("//title[normalize-space() = 'Button success - Ultimate QA']");
    protected final static SelenideElement LoginUsername = $x("//div [@class = 'et_pb_module et_pb_login et_pb_login_0 et_pb_newsletter clearfix  et_pb_text_align_left et_pb_bg_layout_dark'] //input [@placeholder = 'Username']");
    protected final static SelenideElement LoginPassword = $x("//div [@class = 'et_pb_module et_pb_login et_pb_login_0 et_pb_newsletter clearfix  et_pb_text_align_left et_pb_bg_layout_dark'] //input [@placeholder = 'Password']");
    protected final static SelenideElement LoginButton = $x("//div [@class = 'et_pb_module et_pb_login et_pb_login_0 et_pb_newsletter clearfix  et_pb_text_align_left et_pb_bg_layout_dark'] //button[@name = 'et_builder_submit_button']");
    protected final static SelenideElement LoginSuccessPage = $x("//title[normalize-space() = 'Log In ‹ Ultimate QA — WordPress']");
    protected final static SelenideElement HeaderText = $x("//span [@id='Skills_Improved']");
    protected final static ElementsCollection PointsText = $$x("//div [@class = 'et_pb_module et_pb_text et_pb_text_0  et_pb_text_align_left et_pb_bg_layout_light'] //li");

    public void setUp(String url) {
        Configuration.headless = true;
        Configuration.browserSize = "2560x1440";
        Selenide.open(url);
    }

}
