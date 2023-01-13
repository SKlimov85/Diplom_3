package site.nomoreparties.stellarburgers.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    public static WebDriver getBrowser(String browserName) {
        switch (browserName) {
            case "yandex": {
                System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");
                return new ChromeDriver();
            }
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
                return new ChromeDriver();
            }
            default: throw new RuntimeException("Только Хром и Яндекс!");
        }
    }
}
