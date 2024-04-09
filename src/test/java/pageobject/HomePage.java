package pageobject;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;


public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final String homePageUrl = "https://qa-scooter.praktikum-services.ru/";
    private final int defaultTimer = 1;

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
        driver.get(homePageUrl);
    }

    //метод для клика по элементу спсика FAQ
    public void clickFAQPoint(int questionIndex) {
        driver.findElement(By.id("accordion__heading-" + questionIndex)).click();
    }

    //метод для закрытия окна полиси куки
    public void closeCookiePopUp () {
        driver.findElement(By.id("rcc-confirm-button")).click();
    }

    //метод проверки видимости текста ответа на вопрос
    public void checkAnswerVisibility (int answerIndex, String answerActText) {
        By answerLocator = By.id("accordion__panel-" + answerIndex);
        new WebDriverWait(driver, Duration.ofSeconds(defaultTimer))
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        String answerExpText = driver.findElement(answerLocator).getText();
        MatcherAssert.assertThat(answerActText, is(answerExpText));
    }

}
