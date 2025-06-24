
package selenide.settings;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.logging.Level;

public class BrowserSettings {

    @BeforeClass
    public void setUp() {

        Configuration.headless = true;
        Configuration.browserSize = "2560x1440";
        Selenide.open("about:blank");
        Configuration.screenshots = true;
        Configuration.savePageSource = false;
        Configuration.reportsFolder = "target/allure-results";

        SelenideLogger.removeListener("AllureSelenide");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
                .includeSelenideSteps(false)
                .enableLogs(LogType.BROWSER, Level.ALL));

    }

    @Step("Help me... {url}")
    public void openUrl(String url) {
        Selenide.open(url);
        //Allure.addAttachment("Переход на страницу", url);
    }

    @AfterClass
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}
