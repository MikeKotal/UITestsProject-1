package scooter_ui.qa_scooter_page;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import scooter_ui.helper.Initializer;
import scooter_ui.page_objects.*;

import static scooter_ui.helper.Constants.SCOOTER_PAGE;

@RunWith(Parameterized.class)
public class QaScooterOrderButtonDownTest extends Initializer {

    private final String name;
    private final String lastName;
    private final String address;
    private final String subwayStation;
    private final String phoneNumber;
    private final String date;
    private final String durationRent;
    private final String colour;
    private final String comment;
    private final String browser;
    private final String expectedStatus = "Заказ оформлен";
    private WebDriver driver;

    public QaScooterOrderButtonDownTest(String name, String lastName, String address, String subwayStation, String phoneNumber,
                                        String date, String durationRent, String colour, String comment, String browser) {

        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.durationRent = durationRent;
        this.colour = colour;
        this.comment = comment;
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getPersonalInfo() {
        return new Object[][] {
                {"Джейсон", "Стетхем", "ул. Цитатная", "Молодёжная", "89999999999",
                        "02.06.2023", "трое суток", "чёрный жемчуг", "Когда меня рожали, тогда я и родился", "Chrome"},
                {"Клавдия", "Петровна", "ул. Советская, 4", "Автозаводская", "84853333333",
                        "03.06.2023", "четверо суток", "серая безысходность", "Трехнем стариной", "Firefox"}
        };
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void checkSuccessOrderButtonDown() {
        driver = createDriver(browser);
        driver.get(SCOOTER_PAGE);

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
}
