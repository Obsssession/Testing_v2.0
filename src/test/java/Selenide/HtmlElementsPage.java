package Selenide;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

public class HtmlElementsPage{

    @Getter
    private final static String HtmlElementsPageLink = "https://ultimateqa.com/simple-html-elements-for-automation";
    private final static SelenideElement ClickMeButton = $x("// div [@class = 'et_pb_module et_pb_cta_0 et_pb_promo  et_pb_text_align_center et_pb_bg_layout_dark'] //a [@class ='et_pb_button et_pb_promo_button']");

    public static void ButtonClickMe(){
        ClickMeButton.click();
    }

}
