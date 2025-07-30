package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationWindow {
    WebDriver driver;

    // кнопка Да
    private By buttonYes =  By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    //--------------------------------------------------------------------
    // Конструктор класса
    public OrderConfirmationWindow(WebDriver driver){
        this.driver = driver;
    }

    //Нажать на кнопку Да
    public void clickYesbutton(){
        driver.findElement(buttonYes).click();
    }


}
