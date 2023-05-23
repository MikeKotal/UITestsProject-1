package ru.yandex.praktikum.qaScooterPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageObjects.QaScooterFaq;

import static ru.yandex.praktikum.helper.Constants.FAQ_ANSWERS;
import static ru.yandex.praktikum.helper.Constants.FAQ_QUESTIONS;

@RunWith(Parameterized.class)
public class QaScooterPageFaqTest {

    private final String[] textQuestions;
    private final String[] textAnswers;
    private final String browser;
    private WebDriver driver;

    public QaScooterPageFaqTest(String[] textQuestions, String[] textAnswers, String browser) {
        this.textQuestions = textQuestions;
        this.textAnswers = textAnswers;
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getFaqInfo() {
        return new Object[][] {
                {FAQ_QUESTIONS, FAQ_ANSWERS, "Chrome"},
                {FAQ_QUESTIONS, FAQ_ANSWERS, "Firefox"}
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void checkBlockFAQ() {

        if (browser.equals("Chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("Firefox")) {
            driver = new FirefoxDriver();
        }
        driver.get("https://qa-scooter.praktikum-services.ru");

        QaScooterFaq faq = new QaScooterFaq(driver);
        faq.waitQuestionsBlock();
        faq.checkQuestions();
        faq.checkQuestionAndAnswerTexts(textQuestions, textAnswers);
    }
}
