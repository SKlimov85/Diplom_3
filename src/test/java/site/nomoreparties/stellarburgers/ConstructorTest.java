package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.pom.MainPage;
import site.nomoreparties.stellarburgers.utilities.DriverFactory;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ConstructorTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setup() {
        //driver = DriverFactory.getBrowser("yandex");
        driver = DriverFactory.getBrowser("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

   @After
   public void closeBrow() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход в раздел Булки")
    public void shouldSwitchToBunsTab() {
        mainPage = new MainPage(driver);
        mainPage
                .openPage()
                .clickFillingsTabConstructor()
                .clickBunsTabConstructor();
        mainPage
                .getTextActiveTab();
        assertEquals("Ошибка перехода к разделу конструктора", mainPage.getExpectedNameActiveTubBuns(), mainPage.getTextActiveTab());
    }
    @Test
    @DisplayName("Переход в раздел Соусы")
    public void shouldSwitchToSaucesTab() {
        mainPage = new MainPage(driver);
        mainPage
                .openPage()
                .clickSaucesTabConstructor();
        mainPage
                .getTextActiveTab();
        assertEquals("Ошибка перехода к разделу конструктора", mainPage.getExpectedNameActiveTubSauces(), mainPage.getTextActiveTab());
    }
    @Test
    @DisplayName("Переход в раздел Начинки")
    public void shouldSwitchToFillingsTab() {
        mainPage = new MainPage(driver);
        mainPage
                .openPage()
                .clickFillingsTabConstructor();
        mainPage
                .getTextActiveTab();
        assertEquals("Ошибка перехода к разделу конструктора", mainPage.getExpectedNameActiveTubFillings(), mainPage.getTextActiveTab());
    }
}
