import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObject.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.concurrent.TimeUnit;
import static junit.framework.Assert.assertEquals;


@RunWith(Parameterized.class)
public class OrderTests {
    private WebDriver driver;
    private final ClientData clientData;
    private final RentData rentData;
    private final String orderButton;

    

    // Классы для хранения тестовых данных
    public static class ClientData {
        String name;
        String surname;
        String address;
        String metroStation;
        String phoneNumber;

        public ClientData(String name, String surname, String address,
                          String metroStation, String phoneNumber) {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.metroStation = metroStation;
            this.phoneNumber = phoneNumber;
        }
    }


    public static class RentData {
        int deliveryDay;
        String rentalPeriod;
        int colorCode;
        String comment;

        public RentData(int deliveryDay, String rentalPeriod,
                        int colorCode, String comment) {
            this.deliveryDay = deliveryDay;
            this.rentalPeriod = rentalPeriod;
            this.colorCode = colorCode;
            this.comment = comment;
        }
    }

    public OrderTests(String orderButton, ClientData clientData, RentData rentData) {
        this.clientData = clientData;
        this.rentData = rentData;
        this.orderButton = orderButton;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderInfo(){
        return new Object[][] {
                {"orderButtonTop",
                        new ClientData("Михаил", "Зубенко",
                                "г. Москва, Земляной Вал 31, квартира 12",
                                "Курская", "88005553535"),
                        new RentData(25, "сутки", 0, "")},

                {"orderButtonMid", new ClientData("Катя", "Кошечкина",
                        "г. Москва, ул Плещеева 12 квартира 120",
                        "Бибирево",  "79001236677"),
                        new RentData(14, "трое суток", 1, "WP")}
        };
    }

    @Before
    public void launch(){
        // драйвер для браузера Chrome
        driver = new ChromeDriver();

        //драйвер для браузера FireFox
        //System.setProperty("webdriver.gecko.driver","C:\\JavaProjects\\geckodriver.exe");
        //driver = new FirefoxDriver();

        //Неявное ожидание для каждого findElement
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // переход на тестируемую страницу
        driver.get("https://qa-scooter.praktikum-services.ru");
        //Принимаем условия использования куки
        new OrderInfoForm(driver).clickRccConfirmButton();



    }

    @Test
    public void testPositiveFlow(){

        // Нажать на кнопку "Заказать" на главной странице
        new MainPage(driver).clickOrderButton(orderButton);

        // Заполнение формы "Для кого самокат"
        OrderInfoForm infoForm = new OrderInfoForm(driver);

        infoForm.fillInfoForm(clientData.name, clientData.surname,
                clientData.address, clientData.metroStation,
                clientData.phoneNumber);
        infoForm.clickButtonNext();

        // Заполнение формы "Про аренду"
        OrderRentForm rentForm = new OrderRentForm(driver);
        rentForm.fillRentForm(rentData.deliveryDay, rentData.rentalPeriod,
                rentData.colorCode, rentData.comment);
        rentForm.clickOrderButton();

        //Подтверждение заказа
        OrderConfirmationWindow objConfirmWindow = new OrderConfirmationWindow(driver);

        // Подтвердить заказ - нажать кнопку "Да"
        objConfirmWindow.clickYesbutton();

        // Сравнить текст заголовка о подтверждении заказа с ожидаемым
        OrderAcceptedWindow objAcceptWindow = new OrderAcceptedWindow(driver);
        String actual = objAcceptWindow.copyHeadlineText();
        String expected = "Заказ оформлен";
        assertEquals(expected, actual);
    }



    @After
    public void tearDown() {
        driver.quit();
    }
}




