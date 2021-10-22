package web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    @FindBy(id = "registerForm:username")
    WebElement userNameField;

    @FindBy(id = "registerForm:password")
    WebElement passwordField;

    @FindBy(id = "registerForm:confirmPassword")
    WebElement confirmPasswordField;

    @FindBy(id = "registerForm:email")
    WebElement emailField;

    @FindBy(xpath = "//input[@value='Regisration']")
    WebElement registrationButton;

    public MainPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void createFieldsOfUser(String userName) {
        userNameField.sendKeys(userName);
        passwordField.sendKeys("Password123$");
        confirmPasswordField.sendKeys("Password123$");
        emailField.sendKeys("User@mail.ru");
    }

    public void setPasswordField(String password) {
        passwordField.clear();
        confirmPasswordField.clear();
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
    }

    public void setEmailField(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void ClickRegistration() {
        registrationButton.click();
    }
}
