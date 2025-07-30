package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class OrderAcceptedWindow {
    WebDriver driver;

    // Окно
    private By acceptedOrderPopupWindow = By.className("Order_Modal__YZ-d3");

    //Заголовок окна
    private By acceptedOrderText = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");


    //----------------------------------------------------------------------
    // Конструктор класса
    public OrderAcceptedWindow(WebDriver driver){
        this.driver = driver;
    }
    public String copyHeadlineText(){
        //new WebDriverWait(driver,Duration.ofSeconds(3))
              // .until(ExpectedConditions.visibilityOfElementLocated(acceptedOrderPopupWindow));
        // Заголовок окна оформленного заказа
        String headline = driver.findElement(acceptedOrderText).getText().split("\n", 2)[0];
        return headline;
    }

}
