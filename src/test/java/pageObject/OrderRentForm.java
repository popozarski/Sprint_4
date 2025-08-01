package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderRentForm {
 private WebDriver driver;



 //Поле ввода когда привезти самокат
 private By datePickerContainer = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

 //  общий путь к выбору даты, когда приввезти самокат
 private String datePickerCalendarGenericPath = ".//div[text()='%d']";

 //общий xpath к выбору количетсва суток
 private String rentalDurationGenericPath = ".//div[text()='%s']";

 //Поле ввода Срок аренды
 private By rentalDurationContainer = By.className("Dropdown-placeholder");



 //Чекбоксы Цвет самоката
 private By checkBoxBlack = By.xpath("//*[@id=\"black\"]");
 private By checkBoxGrey = By.xpath("//*[@id=\"grey\"]");

 //Кнопка Заказать
 private By buttonOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
 //--------------------------------------------------------------------
 // Конструктор класса
 public OrderRentForm(WebDriver driver){
  this.driver = driver;
 }

 // выбрать день месяца когда привезти самокат
 public void  inputDayOfMonth(int day){
  driver.findElement(datePickerContainer).click();
  driver.findElement(By.xpath(String.format(datePickerCalendarGenericPath, day))).click();
 }
 //вводим количество суток в строку срок аренды
 public void inputRentalDuration(String days){

  driver.findElement(rentalDurationContainer).click();
  String rentalDurationPath = String.format(rentalDurationGenericPath, days);
  driver.findElement(By.xpath(rentalDurationPath)).click();

 }

 //выбираем цвет самоката 0 - black, 1 - grey
 public void inputColor(int color){
  final By [] checkBoxes = {checkBoxBlack, checkBoxGrey};
  driver.findElement(checkBoxes[color]).click();
 }

 public void inputComment(String comment){

 }

 public void fillRentForm(int deliveryDay, String rentalDuration, int codeColor, String comment){
  OrderRentForm objRentForm = new OrderRentForm(driver);
  objRentForm.inputDayOfMonth(deliveryDay);
  objRentForm.inputRentalDuration(rentalDuration);
  objRentForm.inputColor(codeColor);
  objRentForm.inputComment(comment);
 }


 //нажать кнопку заказать
 public void clickOrderButton(){
  driver.findElement(buttonOrder).click();
 }
}
