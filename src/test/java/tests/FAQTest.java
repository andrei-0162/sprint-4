package tests;

import pageobject.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.After;

@RunWith(Parameterized.class)
    public class FAQTest {
    // драйвер для браузера Chrome
    private WebDriver driver = new ChromeDriver();

    private final int questionIndex;
    private final int answerIndex;

    private final String answerActText;
    public FAQTest(int questionIndex, int answerIndex, String answerActText){
        this.questionIndex = questionIndex;
        this.answerIndex = answerIndex;
        this.answerActText = answerActText;
    }
    @Parameterized.Parameters
    public static Object [][] testDataOfFAQ() {
        return new Object[][] {
                {0, 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Test
    public void faqTest() {
        HomePage homePageObj = new HomePage(driver);
        homePageObj.openHomePage();
        homePageObj.closeCookiePopUp();
        homePageObj.clickFAQPoint(questionIndex);

        // проверка, на что по клику на вопрос открывается соответсвующий ответ
        homePageObj.checkAnswerVisibility(answerIndex, answerActText);
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }

}
