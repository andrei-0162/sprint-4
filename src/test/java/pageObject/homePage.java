package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class homePage {
    private WebDriver driver;

    public homePage (WebDriver driver) {
        this.driver = driver;
    }

    // ---------- локаторы кнопок [Заказать]
    //локатор кнопки [Заказать] в заголовке страницы
    private By orderButtonInTitle = By.xpath(".//button[@class='Button_Button__ra12g']");
    //локатор кнопки [Заказать] в конце страницы
    private By orderButtonInBody = By.xpath(".//div[@class=\"Home_FinishButton__1_cWm\"]");


    // ---------- методы клика для кнопок [Заказать]
    //метод для клика по кнопке [Заказать] в заголовке страниц
    public void clickOrderButtonInTitle () {
        driver.findElement(orderButtonInTitle).click();
    }
    //метод для клика по кнопке [Заказать] в конце страницы
    public void clickOrderButtonInBody () {
        driver.findElement(orderButtonInBody).click();
    }

    //метод для перехода на "Главную страницу"
    public void openHomePage () {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    //метод для клика по элементу спсика FAQ
    public void clickFAQPoint(int questionIndex) {
        driver.findElement(By.id("accordion__heading-" + questionIndex)).click();
    }

    //метод для закрытия окна полиси куки
    public void closeCookiePopUp () {
        driver.findElement(By.id("rcc-confirm-button")).click();
    }

}
