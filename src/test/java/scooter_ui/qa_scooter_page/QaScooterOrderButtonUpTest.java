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
public class QaScooterOrderButtonUpTest extends Initializer {

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

    public QaScooterOrderButtonUpTest(String name, String lastName, String address, String subwayStation, String phoneNumber,
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
                {"Иван", "Иванов", "ул. Ленина, 1", "Черкизовская", "89801232323",
                        "31.05.2023", "сутки", "чёрный жемчуг", "Покатаемся", "Chrome"},
                {"Петр", "Петров", "ул. Сахарова, 5", "Лубянка", "79159334455",
                        "01.06.2023", "двое суток", "серая безысходность", "Погоняем", "Firefox"}
        };
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void checkSuccessOrderButtonUp() {
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
}
