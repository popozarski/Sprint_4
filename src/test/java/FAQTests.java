import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.MainPage;
import java.util.concurrent.TimeUnit;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FAQTests {
    private WebDriver driver;
    private final int accordionNum;
    private final String expectedAnswerText;
    private final String expectedQuestionText;

    public FAQTests(int accordionNum,String expectedQuestionText, String expectedAnswerText){
        this.expectedQuestionText = expectedQuestionText;
        this.expectedAnswerText = expectedAnswerText;
        this.accordionNum = accordionNum;
    }

    @Parameterized.Parameters
    public static Object[][] getFAQInfo() {
        return new Object[][]{
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
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

        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru");

    }

    @Test
    public void checkQuestionTextFAQ() {

        //создаем объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);

        objMainPage.clickRccConfirmButton();


        //проверка текста аккордионов
        objMainPage.unrollAccordion(accordionNum);

        String actualQuestion = objMainPage.copyQuestionAccordionText(accordionNum);
        String expectedQuestion = expectedQuestionText;

        assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    public void checkAnswerTextFAQ() {

        //создаем объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);

        objMainPage.clickRccConfirmButton();


        //проверка текста аккордионов
        objMainPage.unrollAccordion(accordionNum);

        String actualAnswer = objMainPage.copyAnswerAccordionText(accordionNum);
        String expectedAnswer = expectedAnswerText;

        assertEquals(expectedAnswer, actualAnswer);
    }



    @After
    public void tearDown() {
        driver.quit();
    }
}