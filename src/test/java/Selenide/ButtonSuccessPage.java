package Selenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ButtonSuccessPage {

    private final static SelenideElement Title = $x("//title[normalize-space() = 'Button success - Ultimate QA']");

    public static SelenideElement getTitle() {
        return Title;
    }

}
