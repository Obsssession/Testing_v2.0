package Selenide;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

public class ButtonSuccessPage {

    @Getter
    private final static String ButtonSuccessPageLink = "https://ultimateqa.com/button-success";
    @Getter
    private final static SelenideElement Title = $x("//title[normalize-space() = 'Button success - Ultimate QA']");

}
