package scooter_ui.qa_scooter_page;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import scooter_ui.helper.Initializer;
import scooter_ui.page_objects.QaScooterFaq;
import scooter_ui.helper.Constants;

import static scooter_ui.helper.Constants.SCOOTER_PAGE;

@RunWith(Parameterized.class)
public class QaScooterPageFaqTest extends Initializer {

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
                {Constants.FAQ_QUESTIONS, Constants.FAQ_ANSWERS, "Chrome"},
                {Constants.FAQ_QUESTIONS, Constants.FAQ_ANSWERS, "Firefox"}
        };
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void checkBlockFAQ() {
        driver = createDriver(browser);
        driver.get(SCOOTER_PAGE);

        QaScooterFaq faq = new QaScooterFaq(driver);
        faq.waitQuestionsBlock();
        faq.checkQuestions();
        faq.checkQuestionAndAnswerTexts(textQuestions, textAnswers);
    }
}
