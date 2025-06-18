package Selenide;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

public class LoginSuccessPage {

    @Getter
    private final static SelenideElement Title = $x("//title[normalize-space() = 'Log In ‹ Ultimate QA — WordPress']");

}
