package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class OrderInfoForm {
    private WebDriver driver;


    //поле Имя
    private By inputNameContainer = By.xpath(".//input[@placeholder='* Имя']");

    //поле Фамилия
    private By inputSurnameContainer = By.xpath(".//input[@placeholder='* Фамилия']");

    //поле Адрес
    private By inputAddressContainer = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //поле Станция метро
    private By inputMetroStationContainer = By.xpath(".//input[@placeholder='* Станция метро']");

    //поле Телефон
    private By inputPhoneNumberContainer = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // кнопка далее
    private By buttonNext = By.xpath(".//button[text()='Далее']");

    //Кнопка принять куки
    private By rccConfirmButton = By.xpath("//*[@id=\"rcc-confirm-button\"]");
    //------------------------------------------------------------------------

    //Конструктор класса
    public OrderInfoForm(WebDriver driver){
        this.driver = driver;
    }

    // Заполнить поле имя
    public void inputName(String name){
        driver.findElement(inputNameContainer).sendKeys(name);
    }

    // Заполнить поле фамилия
    public void inputSurname(String surname){
        driver.findElement(inputSurnameContainer).sendKeys(surname);
    }

    //Заполнить поле Адрес
    public void inputAddress(String address){
        driver.findElement(inputAddressContainer).sendKeys(address);
    }

    //Заполнить поле станцию метро
    public void inputMetroStation(String metroStation){

        driver.findElement(inputMetroStationContainer).click();

        driver.findElement(inputMetroStationContainer).sendKeys(metroStation);

        driver.findElement(By.xpath(String.format(".//div[text()='%s']", metroStation))).click();

    }

    // Нажать на кнопку принять куки
    public void clickRccConfirmButton(){
        driver.findElement(rccConfirmButton).click();
    }


    //заполнить поле Телефон
    public void inputPhoneNumber(String phoneNumber){
        driver.findElement(inputPhoneNumberContainer).sendKeys(phoneNumber);
    }

    //Нажать кнопку далее
    public void clickButtonNext(){
        driver.findElement(buttonNext).click();
    }

    // заполнить форму на странице
    public void fillInfoForm(String name, String surname, String address,
                             String metroStation, String phoneNumber){
        //  создаем объекты page object
        OrderInfoForm objOrderInfoForm = new OrderInfoForm(driver);
        MainPage objMainPage = new MainPage(driver);
        //вводим имя
        objOrderInfoForm.inputName(name);
        //вводим фамилию
        objOrderInfoForm.inputSurname(surname);
        //вводим адрес
        objOrderInfoForm.inputAddress(address);
        //вводим станцию метро
        objOrderInfoForm.inputMetroStation(metroStation);
        //вводим номер телефона
        objOrderInfoForm.inputPhoneNumber(phoneNumber);

    }



}







