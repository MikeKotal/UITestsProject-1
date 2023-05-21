package ru.yandex.praktikum.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QaScooterCompleteOrder {

    private WebDriver driver;

    //Статус заказа
    private By orderStatus = By.className("Order_ModalHeader__3FDaJ");

    //Номер заказа
    private By orderNumber = By.className("Order_Text__2broi");

    //Кнопка "Посмотреть статус"
    private By checkStatusButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");

    public QaScooterCompleteOrder(WebDriver driver) {
        this.driver = driver;
    }

    public void waitElementLastOrderPage() {
        new WebDriverWait(driver, 3)
                .until(driver -> (driver.findElement(orderStatus).isDisplayed() &&
                        driver.findElement(orderNumber).isDisplayed()
                ));
    }

    public void clickCheckStatusButton() {
        Assert.assertTrue("Кнопка просмотра статуса неактивна", driver.findElement(checkStatusButton).isEnabled());
        driver.findElement(checkStatusButton).click();
    }

    public String orderStatus() {
        return driver.findElement(orderStatus).getText();
    }

    public String orderNumber() {
        String[] description = driver.findElement(orderNumber).getText().split(" ");
        return description[5];
    }

}
