package selenide.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class LoginSuccessPage {

    private final SelenideElement title = $x("//title[normalize-space() = 'Log In ‹ Ultimate QA — WordPress']");

}
