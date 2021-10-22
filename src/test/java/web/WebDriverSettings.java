package web;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebDriverSettings {
    ChromeDriver webDriver;
    MainPage mainPage;
    LoginPage loginPage;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainPage = new MainPage(webDriver);
        loginPage = new LoginPage(webDriver);
        webDriver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");
    }

    @After
    public void closeBrowser() {
        webDriver.quit();
    }
}
