package selenide.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ButtonSuccessPage {

    private final String buttonSuccessPageLink = "https://ultimateqa.com/button-success";
    private final SelenideElement title = $x("//title[normalize-space() = 'Button success - Ultimate QA']");

}
