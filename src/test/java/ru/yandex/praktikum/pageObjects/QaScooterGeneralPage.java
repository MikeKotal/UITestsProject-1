package ru.yandex.praktikum.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QaScooterGeneralPage {

    private WebDriver driver;

    //Кнопка принятия куки
    private By cookiesButton = By.id("rcc-confirm-button");

    //Верхняя кнопка "Заказать"
    private By orderButtonUp = By.className("Button_Button__ra12g");

    //Нижняя кнопка заказать
    private By orderButtonDown = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    //Область нижней кнопки
    private By titleButtonDown = By.className("Home_FinishButton__1_cWm");

    public QaScooterGeneralPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitOrderButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(orderButtonUp));
    }

    public void clickUpOrderButton() {
        Assert.assertTrue("Верхняя кнопка заказа неактивна", driver.findElement(orderButtonUp).isEnabled());
        driver.findElement(orderButtonUp).click();
    }

    public void clickCookiesButton() {
        Assert.assertTrue("Кнопка кук неактивна", driver.findElement(cookiesButton).isEnabled());
        driver.findElement(cookiesButton).click();
    }

    public void clickDownOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(titleButtonDown));
        Assert.assertTrue("Нижняя кнопка заказа неактивна", driver.findElement(orderButtonDown).isEnabled());
        driver.findElement(orderButtonDown).click();
    }
}
