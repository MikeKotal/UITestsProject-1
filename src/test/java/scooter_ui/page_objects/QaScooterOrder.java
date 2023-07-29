package scooter_ui.page_objects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QaScooterOrder {

    private WebDriver driver;

    //Имя
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");

    //Фамилия
    private By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");

    //Адрес
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //Метро
    private By subwayField = By.xpath(".//input[@placeholder='* Станция метро']");

    //Список станций метро
    private By subwayStations = By.xpath(".//li[@class='select-search__row']/button");

    //Телефон
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка "Далее"
    private By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");

    public QaScooterOrder(WebDriver driver) {
        this.driver = driver;
    }

    public void waitElementFirstOrderPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }

    public void clickNextButton() {
        Assert.assertTrue("Кнопка Далее неактивна", driver.findElement(nextButton).isEnabled());
        driver.findElement(nextButton).click();
    }

    public void fillPersonaInfo(String name, String lastName, String address, String phoneNumber) {
        Assert.assertTrue("Поле ввода имени неактивно", driver.findElement(nameField).isEnabled());
        Assert.assertTrue("Поле ввода фамилии неактивно", driver.findElement(lastNameField).isEnabled());
        Assert.assertTrue("Поле ввода адреса неактивно", driver.findElement(addressField).isEnabled());
        Assert.assertTrue("Поле ввода номера телефона неактивно",
                driver.findElement(phoneNumberField).isEnabled());

        driver.findElement(nameField).sendKeys(name);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void selectSubwayStation(String station) {
        Assert.assertTrue("Поле выбора станции метро неактивно", driver.findElement(subwayField).isEnabled());
        driver.findElement(subwayField).click();
        driver.findElement(By.xpath(".//div[text()='" + station + "']")).click();
    }

}
