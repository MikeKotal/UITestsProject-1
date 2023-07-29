package scooter_ui.helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static scooter_ui.helper.Constants.CHROME;
import static scooter_ui.helper.Constants.FIREFOX;


public class Initializer {

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    public WebDriver createDriver(String browser) {
        WebDriver driver = null;
        switch (browser) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
        }
        return driver;
    }

}
