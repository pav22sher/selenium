package web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Страница авторизации.
 */
public class LoginPage {

    @FindBy(xpath = "//input[@name='j_username']")
    WebElement userNameField;

    @FindBy(xpath = "//input[@name='j_password']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@class='button']")
    WebElement loginButton;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void loginProcess(String userName) {
        loginProcess(userName, "Password123$");
    }

    public void loginProcess(String userName, String password) {
        setUserNameField(userName);
        setPasswordField(password);
        loginButton.click();
    }

    public void setUserNameField(String userName) {
        userNameField.clear();
        userNameField.sendKeys(userName);
    }

    public void setPasswordField(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }
}
