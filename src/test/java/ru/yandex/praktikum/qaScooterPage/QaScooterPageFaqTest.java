package ru.yandex.praktikum.qaScooterPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageObjects.QaScooterFaq;

public class QaScooterPageFaqTest {

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    private WebDriver driver;

    @Test
    public void checkBlockFAQChrome() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        QaScooterFaq faq = new QaScooterFaq(driver);
        faq.waitQuestionsBlock();
        faq.checkQuestions();
    }

    @Test
    public void checkBlockFAQFirefox() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        QaScooterFaq faq = new QaScooterFaq(driver);
        faq.waitQuestionsBlock();
        faq.checkQuestions();
    }

}
