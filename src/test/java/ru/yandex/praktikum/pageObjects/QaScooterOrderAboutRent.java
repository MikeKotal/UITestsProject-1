package ru.yandex.praktikum.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QaScooterOrderAboutRent {

    private WebDriver driver;

    //Дата
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //Выбранная дата в блоке
    private By pickDate = By.xpath(".//div[contains(@class, 'selected')]");

    //Срок аренды
    private By durationRent = By.xpath(".//div[@class='Dropdown-control']/div[1]");

    private By durationField = By.className("Dropdown-root");

    //Блок выбора срока аренды
    private By downDropDates = By.xpath(".//div[@role='option']");

    //Выбор черного цвета
    private By blackRadioButton = By.id("black");

    //выбор серого цвета
    private By grayRadioButton = By.id("grey");

    //Комментарий
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Кнопка "Заказать"
    private By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public QaScooterOrderAboutRent(WebDriver driver) {
        this.driver = driver;
    }

    public void waitElementSecondOrderPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(dateField));
    }

    public void clickOrderButton() {
        Assert.assertTrue("Кнопка Заказать неактивна", driver.findElement(orderButton).isEnabled());
        driver.findElement(orderButton).click();
    }

    public void addComment(String comment) {
        Assert.assertTrue("Поле ввода комментария неактивно", driver.findElement(commentField).isEnabled());
        driver.findElement(commentField).sendKeys(comment);
    }

    public void selectDurationRent(String duration) {
        Assert.assertTrue("Поле Срок аренды неактивно", driver.findElement(durationField).isEnabled());
        driver.findElement(durationField).click();
        driver.findElement(By.xpath(".//div[text()='" + duration + "']")).click();
    }

    public void selectDate(String date) {
        Assert.assertTrue("Поле ввода даты неактивно", driver.findElement(dateField).isEnabled());
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(pickDate).click();
        Assert.assertTrue("Выбрана некорректная дата",
                driver.findElement(By.xpath(".//input[@value='" + date + "']")).isDisplayed());
    }

    public void selectColour(String colour) {
        if (colour.equals("чёрный жемчуг")) {
            Assert.assertTrue("Черный цвет неактивен", driver.findElement(blackRadioButton).isEnabled());
            driver.findElement(blackRadioButton).click();
        } else if (colour.equals("серая безысходность")) {
            Assert.assertTrue("Серый цвет неактивен", driver.findElement(grayRadioButton).isEnabled());
            driver.findElement(grayRadioButton).click();
        } else {
            Assert.fail("Что-то с цветом пошло не так");
        }
    }
}
