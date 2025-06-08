
package Selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;

public class BrowserSettings {

    // Настройки при открытии браузера
    public static void setUp(String url) {
        Configuration.headless = true;
        Configuration.browserSize = "2560x1440";
        Selenide.open(url);
    }

    // После каждого теста - браузер принудительно закрывается
    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}
