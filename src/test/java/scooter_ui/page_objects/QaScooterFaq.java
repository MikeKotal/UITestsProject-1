package scooter_ui.page_objects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class QaScooterFaq {

    private WebDriver driver;

    //Заголовок блока вопросов
    private By questionsTitle = By.xpath(".//div[@class='Home_FourPart__1uthg']/div[@class='Home_SubHeader__zwi_E']");

    //Поиск всех кнпок блока вопросов
    private By questionButtonsList = By.cssSelector(".accordion__button");

    //Поиск блоков ответа на вопрос
    private By answerFields = By.cssSelector(".accordion__panel");

    //Поиск текста блоков ответа
    private By answerTexts = By.xpath(".//div[@class='accordion__panel']/p");

    public QaScooterFaq(WebDriver driver){
        this.driver = driver;
    }

    public void waitQuestionsBlock() {
        new WebDriverWait(driver, 3)
                .until(driver -> (driver.findElement(questionsTitle).getText() != null
                && !driver.findElement(questionsTitle).getText().isEmpty()
        ));
    }

    public void checkQuestions() {
        List<WebElement> buttons = driver.findElements(questionButtonsList);
        List<WebElement> fields = driver.findElements(answerFields);
        List<WebElement> texts = driver.findElements(answerTexts);

        if (buttons.size() > 0) {
            for (int i = 0; i < buttons.size(); i++) {
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                        buttons.get(i));
                Assert.assertTrue("Кнопка " + (i + 1) + " неактивна", buttons.get(i).isEnabled());
                Assert.assertFalse("Блок ответа на вопрос не скрыт", fields.get(i).isDisplayed());
                buttons.get(i).click();
                Assert.assertNotNull("Отсутствует текст ответа", texts.get(i).getText());
            }
        } else {
            Assert.fail("На страние не хватает раздела FAQ");
        }
    }

    public void checkQuestionAndAnswerTexts(String[] question, String[] answer) {
        List<WebElement> questions = driver.findElements(questionButtonsList);
        List<WebElement> answers = driver.findElements(answerTexts);

        List<String> perfectQuestions = Arrays.asList(question);
        List<String> perfectAnswers = Arrays.asList(answer);

        for (int i = 0; i < questions.size(); i++) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                    questions.get(i));
            questions.get(i).click();
            int count = i;
            new WebDriverWait(driver, 3)
                    .until(driver -> (!questions.get(count).getText().isEmpty()
                            && !answers.get(count).getText().isEmpty()
                    ));

            Assert.assertEquals("Текст вопроса должен иметь ввид: " + perfectQuestions.get(i) + " .",
                    perfectQuestions.get(i), questions.get(i).getText());
            Assert.assertEquals("Текст ответа должен иметь ввид: " + perfectAnswers.get(i) + " .",
                    perfectAnswers.get(i), answers.get(i).getText());
        }
    }
}
