package tests;

import pageobject.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.junit.After;


@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver = new ChromeDriver();
    //параметры для формиривания заказа
    private final int entryPoint;
    private final String firstName;
    private final   String lastName;
    private final   String address;
    private final   String subwayStation;
    private final   String phoneNumber;
    private final   String sendDate;
    private final   String leaseTerm;
    private final   String colourScooter;
    private final   String comment;
    public OrderTest(
            int entryPoint,
            String firstName,
            String lastName,
            String address,
            String subwayStation,
            String phoneNumber,
            String sendDate,
            String leaseTerm,
            String colourScooter,
            String comment) {
        this.entryPoint = entryPoint;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.sendDate = sendDate;
        this.leaseTerm = leaseTerm;
        this.colourScooter = colourScooter;
        this.comment = comment;
    }


    @Parameterized.Parameters
    public static Object [][] testDataForOrder() {
        return new Object[][] {
                {
                    1,
                    "Александр",
                    "Македонский",
                    "Пронягина 7",
                    "Бульвар Рокосовского",
                    "79151234567",
                    "10.05.2024",
                    "сутки",
                    "черный",
                    "По возможности предупердить за час"
                },
                {
                    2,
                    "Иван",
                    "Васильевич",
                    "Карбышева 20",
                    "Черкизовская",
                    "79167654321",
                    "01.04.2024",
                    "двое суток",
                    "серый",
                    "По возможности предупердить за два час"
                }
        };
    }

    @Test
    public void testOrderTest() {
        // ---- старт выплнения кейса
        HomePage newHomePageObj = new HomePage(driver);
        newHomePageObj.openHomePage();
        //закрытие окна полиси куки
        newHomePageObj.closeCookiePopUp();
        // клик по кнопке "Заказать" в заголовке страницы или в рабочей области
        if (entryPoint == 1) {
            newHomePageObj.clickOrderButtonInTitle();
        } else {
            newHomePageObj.clickOrderButtonInBody();
        }

        //формирование заказа
        OrderPage newOrderObj = new OrderPage(driver);
        newOrderObj.createOrderData(
                 firstName,
                 lastName,
                 address,
                 subwayStation,
                 phoneNumber,
                 sendDate,
                 leaseTerm,
                 colourScooter,
                 comment);

        //  проверка результата теста: заказ оформлен успешно
        newOrderObj.checkOrderSuccessfullyCreated();
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}



