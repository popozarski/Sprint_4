package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private WebDriver driver;

    //-----------------------------------------------------------------------
    //Кнопка заказать верхняя
    private By orderButtonTop = By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]");


    //Кнопка заказать в середине страницы
    private By orderButtonMid = By.xpath("//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button");

    //Кнопка принять куки
    private By rccConfirmButton = By.xpath(".//button[@id='rcc-confirm-button']");

    // общий xPath к кнопке аккордиона
    private String pathAccardionButtonGeneric = ".//div[@id='accordion__heading-%d']";

    //общий xPath к тексту аккордиона
    private String pathAccardionTextGeneric = "//div[@id='accordion__panel-%d']";

    //Тексты к аккордионам согласно требованиям
    public String [] accordionExpectedTexts = {
                                "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                                "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                                "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                                "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                                "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                                "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                                "Да, обязательно. Всем самокатов! И Москве, и Московской области."};

    //---------------------------------------------------------------------
    //Конструктор класса page object MainPage
    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    // скопировать текущий URL
    public String copyCurrentURL(){
        return driver.getCurrentUrl();

    }
    // Нажать на кнопку заказа вверху страницы
    public void clickOrderButtonTop(){
        driver.findElement(orderButtonTop).click();
    }

    //Нажать на кнопку заказа в середине страницы
    public void clickOrderButtonMid(){
        driver.findElement(orderButtonMid).click();
    }

    // Нажать на кнопку заказа на главной странице
    public void clickOrderButton(By orderButton){
        driver.findElement(orderButton).click();
    }
    // Нажать на кнопку принять куки
    public void clickRccConfirmButton(){
        driver.findElement(rccConfirmButton).click();
    }
    //----------------------------------------------------------------------

    // Раскрыть аккордион согласно номеру по порядку, начиная с 0
    public void unrollAccordion(int accordionNum){
        String accordionButton = String.format(pathAccardionButtonGeneric, accordionNum);
        WebElement element = driver.findElement(By.xpath(accordionButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        element.click();

    }


    // Скопировать текст аккордиона согласно номеру по порядку, начиная c 0
    public String copyAccordionText(int accordionNum){
        String accordionTextPath = String.format(pathAccardionTextGeneric, accordionNum);
        return driver.findElement(By.xpath(accordionTextPath)).getText();

    }


}
