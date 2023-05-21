package ru.yandex.praktikum.qaScooterPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageObjects.*;

@RunWith(Parameterized.class)
public class QaScooterOrderTest {

    private final String name;
    private final String lastName;
    private final String address;
    private final String subwayStation;
    private final String phoneNumber;
    private final String date;
    private final String durationRent;
    private final String colour;
    private final String comment;
    private final String expectedStatus = "Заказ оформлен";
    private WebDriver driver;

    public QaScooterOrderTest(String name, String lastName, String address, String subwayStation, String phoneNumber,
                              String date, String durationRent, String colour, String comment) {

        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.durationRent = durationRent;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getCities() {
        return new Object[][] {
                {"Иван", "Иванов", "ул. Ленина, 1", "Черкизовская", "89801232323",
                        "31.05.2023", "сутки", "чёрный жемчуг", "Покатаемся"},
                {"Петр", "Петров", "ул. Сахарова, 5", "Лубянка", "79159334455",
                        "01.06.2023", "двое суток", "серая безысходность", "Погоняем"},
                {"Джейсон", "Стетхем", "ул. Цитатная", "Молодёжная", "89999999999",
                        "02.06.2023", "трое суток", "чёрный жемчуг", "Когда меня рожали, тогда я и родился"},
                {"Клавдия", "Петровна", "ул. Советская, 4", "Автозаводская", "84853333333",
                        "03.06.2023", "четверо суток", "серая безысходность", "Трехнем стариной"}
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
    public void checkSuccessOrderButtonUpChrome() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        QaScooterGeneralPage generalPage = new QaScooterGeneralPage(driver);
        QaScooterOrder scooterOrder = new QaScooterOrder(driver);
        QaScooterOrderAboutRent aboutRent = new QaScooterOrderAboutRent(driver);
        QaScooterOrderFinalChoice finalChoice = new QaScooterOrderFinalChoice(driver);
        QaScooterCompleteOrder completeOrder = new QaScooterCompleteOrder(driver);

        //Нажимаем верхнюю кнопку заказа
        generalPage.waitOrderButton();
        generalPage.clickCookiesButton();
        generalPage.clickUpOrderButton();

        //Вводим тестовые данные в форму первой страницы
        scooterOrder.waitElementFirstOrderPage();
        scooterOrder.fillPersonaInfo(name, lastName, address, phoneNumber);
        scooterOrder.selectSubwayStation(subwayStation);
        scooterOrder.clickNextButton();

        //Вводим тестовые данные в форму второй страницы
        aboutRent.waitElementSecondOrderPage();
        aboutRent.selectDate(date);
        aboutRent.selectDurationRent(durationRent);
        aboutRent.selectColour(colour);
        aboutRent.addComment(comment);
        aboutRent.clickOrderButton();

        //Подтверждаем заказ
        finalChoice.waitElementThirdOrderPage();
        finalChoice.clickConfirmButton();

        //Выполняем проверку создания заказа
        completeOrder.waitElementLastOrderPage();
        Assert.assertTrue("Заказ не создан, что-то пошло не так",
                completeOrder.orderStatus().contains(expectedStatus));
        Assert.assertNotNull("Отсутствует номер заказа", completeOrder.orderNumber());
        completeOrder.clickCheckStatusButton();
    }

    @Test
    public void checkSuccessOrderButtonDownChrome() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        QaScooterGeneralPage generalPage = new QaScooterGeneralPage(driver);
        QaScooterOrder scooterOrder = new QaScooterOrder(driver);
        QaScooterOrderAboutRent aboutRent = new QaScooterOrderAboutRent(driver);
        QaScooterOrderFinalChoice finalChoice = new QaScooterOrderFinalChoice(driver);
        QaScooterCompleteOrder completeOrder = new QaScooterCompleteOrder(driver);

        //Нажимаем верхнюю кнопку заказа
        generalPage.waitOrderButton();
        generalPage.clickCookiesButton();
        generalPage.clickDownOrderButton();

        //Вводим тестовые данные в форму первой страницы
        scooterOrder.waitElementFirstOrderPage();
        scooterOrder.fillPersonaInfo(name, lastName, address, phoneNumber);
        scooterOrder.selectSubwayStation(subwayStation);
        scooterOrder.clickNextButton();

        //Вводим тестовые данные в форму второй страницы
        aboutRent.waitElementSecondOrderPage();
        aboutRent.selectDate(date);
        aboutRent.selectDurationRent(durationRent);
        aboutRent.selectColour(colour);
        aboutRent.addComment(comment);
        aboutRent.clickOrderButton();

        //Подтверждаем заказ
        finalChoice.waitElementThirdOrderPage();
        finalChoice.clickConfirmButton();

        //Выполняем проверку создания заказа
        completeOrder.waitElementLastOrderPage();
        Assert.assertTrue("Заказ не создан, что-то пошло не так",
                completeOrder.orderStatus().contains(expectedStatus));
        Assert.assertNotNull("Отсутствует номер заказа", completeOrder.orderNumber());
        completeOrder.clickCheckStatusButton();
    }

    @Test
    public void checkSuccessOrderButtonUpFirefox() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        QaScooterGeneralPage generalPage = new QaScooterGeneralPage(driver);
        QaScooterOrder scooterOrder = new QaScooterOrder(driver);
        QaScooterOrderAboutRent aboutRent = new QaScooterOrderAboutRent(driver);
        QaScooterOrderFinalChoice finalChoice = new QaScooterOrderFinalChoice(driver);
        QaScooterCompleteOrder completeOrder = new QaScooterCompleteOrder(driver);

        generalPage.waitOrderButton();
        generalPage.clickCookiesButton();
        generalPage.clickUpOrderButton();

        scooterOrder.waitElementFirstOrderPage();
        scooterOrder.fillPersonaInfo(name, lastName, address, phoneNumber);
        scooterOrder.selectSubwayStation(subwayStation);
        scooterOrder.clickNextButton();

        aboutRent.waitElementSecondOrderPage();
        aboutRent.selectDate(date);
        aboutRent.selectDurationRent(durationRent);
        aboutRent.selectColour(colour);
        aboutRent.addComment(comment);
        aboutRent.clickOrderButton();

        finalChoice.waitElementThirdOrderPage();
        finalChoice.clickConfirmButton();

        completeOrder.waitElementLastOrderPage();
        Assert.assertTrue("Заказ не создан, что-то пошло не так",
                completeOrder.orderStatus().contains(expectedStatus));
        Assert.assertNotNull("Отсутствует номер заказа", completeOrder.orderNumber());
        completeOrder.clickCheckStatusButton();
    }

    @Test
    public void checkSuccessOrderButtonDownFirefox() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        QaScooterGeneralPage generalPage = new QaScooterGeneralPage(driver);
        QaScooterOrder scooterOrder = new QaScooterOrder(driver);
        QaScooterOrderAboutRent aboutRent = new QaScooterOrderAboutRent(driver);
        QaScooterOrderFinalChoice finalChoice = new QaScooterOrderFinalChoice(driver);
        QaScooterCompleteOrder completeOrder = new QaScooterCompleteOrder(driver);

        generalPage.waitOrderButton();
        generalPage.clickCookiesButton();
        generalPage.clickDownOrderButton();

        scooterOrder.waitElementFirstOrderPage();
        scooterOrder.fillPersonaInfo(name, lastName, address, phoneNumber);
        scooterOrder.selectSubwayStation(subwayStation);
        scooterOrder.clickNextButton();

        aboutRent.waitElementSecondOrderPage();
        aboutRent.selectDate(date);
        aboutRent.selectDurationRent(durationRent);
        aboutRent.selectColour(colour);
        aboutRent.addComment(comment);
        aboutRent.clickOrderButton();

        finalChoice.waitElementThirdOrderPage();
        finalChoice.clickConfirmButton();

        completeOrder.waitElementLastOrderPage();
        Assert.assertTrue("Заказ не создан, что-то пошло не так",
                completeOrder.orderStatus().contains(expectedStatus));
        Assert.assertNotNull("Отсутствует номер заказа", completeOrder.orderNumber());
        completeOrder.clickCheckStatusButton();
    }
}
