package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private WebDriver driver;

    //-----------------------------------------------------------------------
    //Кнопка заказать верхняя
    private By orderButtonTop = By.xpath(".//button[@class='Button_Button__ra12g']");


    //Кнопка заказать в середине страницы
    private By orderButtonMid = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Кнопка принять куки
    private By rccConfirmButton = By.xpath(".//button[@id='rcc-confirm-button']");

    // общий xPath к кнопке аккордиона
    private String pathAccardionButtonGeneric = ".//div[@id='accordion__heading-%d']";

    //общий xPath к тексту ответа аккордиона
    private String pathAnswerAccardionTextGeneric = "//div[@id='accordion__panel-%d']";
    //общий xPath к тексту вопроса аккордиона
    private String pathQuestionAccardionTextGeneric = "//div[@id='accordion__heading-%d']";


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

    // Нажать на кнопку одну из кнопок заказа на главной странице
    public void clickOrderButton(String orderButton){
        if (orderButton == "orderButtonTop"){
            driver.findElement(orderButtonTop).click();
        }
        else {
            driver.findElement(orderButtonMid).click();
        }
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

    // Скопировать текст вопроса аккордиона согласно номеру по порядку, начиная c 0
    public String copyQuestionAccordionText(int accordionNum){
        String accordionTextPath = String.format(pathQuestionAccardionTextGeneric, accordionNum);
        return driver.findElement(By.xpath(accordionTextPath)).getText();

    }
    // Скопировать текст ответа аккордиона согласно номеру по порядку, начиная c 0
    public String copyAnswerAccordionText(int accordionNum){
        String accordionTextPath = String.format(pathAnswerAccardionTextGeneric, accordionNum);
        return driver.findElement(By.xpath(accordionTextPath)).getText();

    }





}
