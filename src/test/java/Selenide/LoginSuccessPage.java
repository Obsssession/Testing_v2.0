package Selenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginSuccessPage {

    private final static SelenideElement Title = $x("//title[normalize-space() = 'Log In ‹ Ultimate QA — WordPress']");

    public static SelenideElement getTitle() {
        return Title;
    }

}
