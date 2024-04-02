package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    private final String ORDER_PAGE_URL = "https://qa-scooter.praktikum-services.ru/order";

    //---------- локаторы формы
    // локатор поля "Имя"
    private By firstNameInput = By.xpath(".//input[@placeholder= '* Имя']");
    // локатор поля "Фамилия"
    private By lastNameInput = By.xpath(".//input[@placeholder= '* Фамилия']");
    // локатор поля "Адрес"
    private By addressInput = By.xpath(".//input[@placeholder= '* Адрес: куда привезти заказ']");
    //локатор поля "Станция метро"
    private By subwayStationInput = By.xpath(".//input[@class='select-search__input']");
    //локатор поля "Станция метро Бульвар Рокосовского"
    private By subwayPointBlvrR = By.xpath(".//li[@data-index='0']");
    //локатор поля "Станция метро Черкизовская"
    private By subwayPointChrkz = By.xpath(".//li[@data-index='1']");
    //локатор поля "Телефон"
    private By  phoneNumberInput = By.xpath(".//input[@placeholder= '* Телефон: на него позвонит курьер']");
    //локатор кнопки "Далее"
    private By  nextButton = By.xpath(".//button[text()='Далее']");
    //локатор поля даты "Когда привезти самокат"
    private By  sendDateInput = By.xpath(".//input[@placeholder= '* Когда привезти самокат']");
    //локатор поля "Срок аренды"
    private By  leaseTermInput  = By.xpath(".//div[@class='Dropdown-placeholder']");
    //локатор элемента списка "Срок аренды" - "Сутки"
    private By  leaseTerm1dayInput  = By.xpath(".//div[@class='Dropdown-option' and text()='сутки']");
    //локатор элемента списка "Срок аренды" - "Двое суток"
    private By  leaseTerm2daysInput  = By.xpath(".//div[@class='Dropdown-option' and text()='двое суток']");
    //локатор поля "Цвет самоката" - "черный жемчуг"
    private By  blackColourScooterInput  = By.xpath(".//input[@id='black']");
    //локатор поля "Цвет самоката" - "серая безысходность"
    private By grayColourScooterInput = By.xpath(".//input[@id='grey']");
    //локатор поля "Комментарий для курьера"
    private By  commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //локатор кнопки "Заказать" под формой заказа
    private By  orderButtonOnOrderPage = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //локатор кнопки "Да" на поп-апе "Хотите оформить заказ?"
    private By  yesButtonOnPopupOrderPage = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //локатор поп-апа "Заказ оформлен"
    private By  orderCreatedPopUp = By.xpath("div[text()='Заказ оформлен']");

    //---------- методы для ввода значений в поля

    public void setFirstNameInput(String firstName) {
         driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void setLastNameInput(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }
    public void setAddressInput(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    public void setSubwayStationInput(String subPoint) {
        driver.findElement(subwayStationInput).click();
        if (subPoint.equals("Бульвар Рокосовского")) {
            driver.findElement(subwayPointBlvrR).click();
        } else {
            driver.findElement(subwayPointChrkz).click();
        }
    }

    public void setPhoneNumberInput(String phoneNumber) {
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void setSendDateInput(String sendDate) {
        driver.findElement(sendDateInput).sendKeys(sendDate);
        driver.findElement(By.xpath(".//div[@class='Order_Header__BZXOb']")).click();
    }

    public void setLeaseTermInput(String LeaseTerm) {
        driver.findElement(leaseTermInput).click();
        if (LeaseTerm.equals("сутки")) {
            driver.findElement(leaseTerm1dayInput).click();
        } else {
            driver.findElement(leaseTerm2daysInput).click();
        }
    }

    public void setColourScooterInput(String Colour) {
        if (Colour.equals("черный")) {
            driver.findElement(blackColourScooterInput).click();
        } else {
            driver.findElement(grayColourScooterInput).click();
        }
    }
    public void setCommentInput(String comment) {
        driver.findElement(commentInput).click();
        driver.findElement(commentInput).sendKeys(comment);
    }
    public void clickOrderButtonOnOrderPage() {
        driver.findElement(orderButtonOnOrderPage).click();
    }
    public void clickYesButtonOnPopUpOrderPage() {
        driver.findElement(yesButtonOnPopupOrderPage).click();
    }

    public By getOrderCreatedPopUp () {
        return orderCreatedPopUp;
        }

    //---------- метод создания заказа

    public void createOrderData(
            String firstName,
            String lastName,
            String address,
            String subwayStation,
            String phoneNumber,
            String sendDate,
            String leaseTerm,
            String colourScooter,
            String comment
    ) {
        setFirstNameInput(firstName);
        setLastNameInput(lastName);
        setAddressInput(address);
        setSubwayStationInput(subwayStation);
        setPhoneNumberInput(phoneNumber);
        clickNextButton();
        setSendDateInput(sendDate);
        setLeaseTermInput(leaseTerm);
        setColourScooterInput(colourScooter);
        setCommentInput(comment);
        clickOrderButtonOnOrderPage();
        clickYesButtonOnPopUpOrderPage();
    }

    //метод для перехода на "Главную страницу"
    public void openOrderPage () {
        driver.get(ORDER_PAGE_URL);
    }

    //метод проверки успешного оформления заказа
    public void checkOrderSuccessfullyCreated() {
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(orderCreatedPopUp));
    }



}
