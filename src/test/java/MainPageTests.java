import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObject.MainPage;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;

public class MainPageTests {
    private WebDriver driver;

   @Before
   public void launch(){
       // драйвер для браузера Chrome
       driver = new ChromeDriver();

       //драйвер для браузера FireFox
       //System.setProperty("webdriver.gecko.driver","C:\\JavaProjects\\geckodriver.exe");
        //driver = new FirefoxDriver();


       //Неявное ожидание для каждого findElement
       driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

       // переход на страницу тестового приложения
       driver.get("https://qa-scooter.praktikum-services.ru");

   }
    @Test
    public void checkTextFAQ() {

        //создаем объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);

        objMainPage.clickRccConfirmButton();


        //проверка текста аккордионов
        for (int i = 0; i < objMainPage.accordionExpectedTexts.length - 1; i++){
            objMainPage.unrollAccordion(i);

            String actual = objMainPage.copyAccordionText(i);
            String expected = objMainPage.accordionExpectedTexts[i];

            assertEquals(expected, actual);
        }
    }

    // Проверка верхней кнопки "Заказать"
    // Если кнопка работает, то меняется URL
    @Test
    public void orderButtonTopRedirectToOrderForm() {
        MainPage objMainPage = new MainPage(driver);
        String expectedURL = "https://qa-scooter.praktikum-services.ru/order";
        //принимаем условия использования куки
        objMainPage.clickRccConfirmButton();

        objMainPage.clickOrderButtonTop();
        String actualURL = objMainPage.copyCurrentURL();

        assertEquals(expectedURL, actualURL);

    }
    // Проверка кнопки "Заказать" в середине страницы
    // Если кнопка работает, то меняется URL
    @Test
    public void orderButtonMidRedirectToOrderForm() {
        MainPage objMainPage = new MainPage(driver);
        String expectedURL = "https://qa-scooter.praktikum-services.ru/order";

        //принимаем условия использования куки
        objMainPage.clickRccConfirmButton();

        objMainPage.clickOrderButtonMid();
        String actualURL = objMainPage.copyCurrentURL();

        assertEquals(expectedURL, actualURL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
