package selenide.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class HtmlElementsPage {

    private final String htmlElementsPageLink = "https://ultimateqa.com/simple-html-elements-for-automation";
    private final SelenideElement clickMeButton = $x("// div [@class = 'et_pb_module et_pb_cta_0 et_pb_promo  et_pb_text_align_center et_pb_bg_layout_dark'] //a [@class ='et_pb_button et_pb_promo_button']");

    @Step("Нажимаю на кнопку 'Click Me'")
    public void buttonClickMe() {
        clickMeButton.click();
    }

}
