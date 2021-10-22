package web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void enterLogin(String userName) {
        userNameField.sendKeys(userName);
        passwordField.sendKeys("Password123$");
    }

    public void ClickLogin() {
        loginButton.click();
    }
}
