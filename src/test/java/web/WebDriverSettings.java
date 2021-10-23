package web;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Базовые настройки для тестов!
 */
public class WebDriverSettings {
    ChromeDriver webDriver;
    RegistrationPage registrationPage;
    LoginPage loginPage;

    /**
     * Открыть браузер до теста.
     */
    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\driver\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        registrationPage = new RegistrationPage(webDriver);
        loginPage = new LoginPage(webDriver);
        webDriver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
    }

    /**
     * Закрыть браузер после теста.
     */
    @After
    public void closeBrowser() {
        webDriver.quit();
    }
}
