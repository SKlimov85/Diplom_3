package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.pom.*;
import site.nomoreparties.stellarburgers.utilities.DriverFactory;
import site.nomoreparties.stellarburgers.utilities.UserApi;
import site.nomoreparties.stellarburgers.utilities.UserCreate;
import site.nomoreparties.stellarburgers.utilities.UserGenerator;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LoginInTest {
    private final UserGenerator userGenerator = new UserGenerator();
    private final UserApi userApi = new UserApi();
    UserCreate uniqueUser = userGenerator.randomDataCourier();
    private String accessToken, email, password;
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;

    @Before
    public void setupAndCreateUser() {
        //driver = DriverFactory.getBrowser("yandex");
        driver = DriverFactory.getBrowser("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        ValidatableResponse create = userApi.create(uniqueUser);
        accessToken = userApi.assertCreationSusses(create);
    }

    @After
    public void deleteUserAndCloseBrow() {
        ValidatableResponse response = userApi.delete(accessToken);
        userApi.assertDeleteSusses(response);
        driver.quit();
    }

    @Test
    @DisplayName("Вход в аккаунт на главной странице")
    public void homePageSignInButtonCheck() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        email = uniqueUser.getEmail();
        password = uniqueUser.getPassword();
        mainPage
                .openPage()
                .clickButtonSignInAccount();
        loginPage
                .dataUserLogin(email, password)
                .clickButtonLoginIn();
        mainPage
                .getExpectedTextButtonСreateOrder();
        assertEquals("Ошибка входа", mainPage.getExpectedTextButtonСreateOrder(), mainPage.getTextButtonСreateOrder());
    }

    @Test
    @DisplayName("Вход в аккаунт на главной странице через кнопку Личный кабинет")
    public void accountButtonSighInCheck() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        email = uniqueUser.getEmail();
        password = uniqueUser.getPassword();
        mainPage
                .openPage()
                .clickPersonalAccountButton();
        loginPage
                .dataUserLogin(email, password)
                .clickButtonLoginIn();
        mainPage
                .getExpectedTextButtonСreateOrder();
        assertEquals("Ошибка входа", mainPage.getExpectedTextButtonСreateOrder(), mainPage.getTextButtonСreateOrder());
    }
    @Test
    @DisplayName("Вход в аккаунт через кнопку на форме регистрации")
    public void regPageSignInLinkCheck() {
        mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        email = uniqueUser.getEmail();
        password = uniqueUser.getPassword();
        registerPage
                .openPage()
                .clickButtonSignInButton();
        loginPage
                .dataUserLogin(email, password)
                .clickButtonLoginIn();
        mainPage
                .getExpectedTextButtonСreateOrder();
        assertEquals("Ошибка входа", mainPage.getExpectedTextButtonСreateOrder(), mainPage.getTextButtonСreateOrder());
    }

    @Test
    @DisplayName("Вход в аккаунт через лин Войти на форме восстановления пароля")
    public void forgotPageSignInLinkCheck() {
        mainPage = new MainPage(driver);
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        loginPage = new LoginPage(driver);
        email = uniqueUser.getEmail();
        password = uniqueUser.getPassword();
        passwordRecoveryPage
                .openPage()
                .clickSignInLink();
        loginPage
                .dataUserLogin(email, password)
                .clickButtonLoginIn();
        mainPage
                .getTextButtonСreateOrder();
        assertEquals("Ошибка входа", mainPage.getExpectedTextButtonСreateOrder(), mainPage.getTextButtonСreateOrder());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void LogOtuAccount() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        email = uniqueUser.getEmail();
        password = uniqueUser.getPassword();
        mainPage
                .openPage()
                .clickPersonalAccountButton();
        loginPage
                .dataUserLogin(email, password)
                .clickButtonLoginIn();
        mainPage
                .clickPersonalAccountButton();
        profilePage
                  .clickLogOffButton();
        loginPage
                .getTextButtonSingIn();
        assertEquals("Ошибка выхода из аккаунта", loginPage.getExpectedTextButtonSingIn(), loginPage.getTextButtonSingIn());
    }
}