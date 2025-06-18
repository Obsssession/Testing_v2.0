
package Selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.*;

public class BrowserSettings {

    @BeforeGroups (dependsOnGroups = "UI")
    public static void setUp(String url) {
        Configuration.headless = true;
        Configuration.browserSize = "2560x1440";
        Selenide.open(url);
    }

    @AfterGroups (dependsOnGroups = "UI")
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}
