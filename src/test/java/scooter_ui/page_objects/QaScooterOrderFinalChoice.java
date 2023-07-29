package scooter_ui.page_objects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QaScooterOrderFinalChoice {

    private WebDriver driver;

    //Кнопка подтверждения заказа
    private By confirmOrder = By.xpath(".//button[text()='Да']");

    public QaScooterOrderFinalChoice(WebDriver driver) {
        this.driver = driver;
    }

    public void waitElementThirdOrderPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(confirmOrder));
    }

    public void clickConfirmButton() {
        Assert.assertTrue("Кнопка подтверждения заказа неактивна", driver.findElement(confirmOrder).isEnabled());
        driver.findElement(confirmOrder).click();
    }

}
