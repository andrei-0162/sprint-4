package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.homePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.After;

import java.time.Duration;


@RunWith(Parameterized.class)
    public class faqTest {
    // драйвер для браузера Chrome
    private WebDriver driver = new ChromeDriver();

    private final int questionIndex;
    private final int answerIndex;
    public faqTest(int questionIndex, int answerIndex){
        this.questionIndex = questionIndex;
        this.answerIndex = answerIndex;
    }
    @Parameterized.Parameters
    public static Object [][] dataIndex() {
        return new Object[][] {
                {0, 0},
                {1, 1},
                {2, 2},
                {3, 3},
                {4, 4},
                {5, 5},
                {6, 6},
                {7, 7}
        };
    }

    @Test
    public void faqTest() {
        homePage homePageObj = new homePage(driver);
        homePageObj.openHomePage();
        homePageObj.closeCookiePopUp();
        homePageObj.clickFAQPoint(questionIndex);


        //ожидание. проверка на видимость элемента
        new WebDriverWait(driver, Duration.ofSeconds(1))
               .until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-" + answerIndex)));
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }

}
