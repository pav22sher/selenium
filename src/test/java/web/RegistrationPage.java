package web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Страница регистрации.
 */
public class RegistrationPage {

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

    public RegistrationPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void registrationProcess(String userName, boolean register) {
        registrationProcess(userName, "Password123$", "User@mail.ru", register);
    }

    public void registrationProcess(String userName, String password, boolean register) {
        registrationProcess(userName, password, "User@mail.ru", register);
    }

    public void registrationProcess(String userName, String password, String email, boolean register) {
        setUserNameField(userName);
        setPassword(password);
        setEmailField(email);
        if (register) {
            registrationButton.click();
        }
    }

    public void setUserNameField(String userName) {
        userNameField.clear();
        userNameField.sendKeys(userName);
    }

    public void setPassword(String password) {
        setPasswordField(password);
        setConfirmPasswordField(password);
    }

    public void setPasswordField(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void setConfirmPasswordField(String password) {
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);
    }

    public void setEmailField(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }
}
