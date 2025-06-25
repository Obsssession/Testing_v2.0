
package selenide.settings;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static io.qameta.allure.Allure.step;

public class BrowserSettings {

    @BeforeClass
    public void setUp() {

        Configuration.headless = true;
        Configuration.browserSize = "2560x1440";
        Selenide.open("about:blank");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
                .includeSelenideSteps(false));

    }

    public void openUrl(String url) {
        step("Переход на страницу: " + url, () -> Selenide.open(url));
    }

    @AfterClass
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}
