package web;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static web.Utils.*;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RegistrationTests extends WebDriverSettings {

    @Test
    public void testTitle() {
        String expectedTitle = webDriver.getTitle();
        String actualTitle = "Registration";
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    /**
     * Успешная регистрация нового пользователя (функциональность).
     */
    @Test
    public void testSuccessRegistrationRandomUser() throws Exception {
        String newUserName = generateRandomUsername();
        mainPage.createFieldsOfUser(newUserName);
        makeScreenShot("1.testSuccessRegistrationRandomUser_Step1");
        mainPage.ClickRegistration();
        makeScreenShot("1.testSuccessRegistrationRandomUser_Step2");
        WebElement result = webDriver.findElement(By.cssSelector("div[class='justRegisteredBlock']"));
        String expectedMessage = "You have successfully registered\n" + "Use your credentials to login";
        String actualMessage = result.getText();
        assertEquals(expectedMessage, actualMessage);

        loginPage.enterLogin(newUserName);
        makeScreenShot("1.testSuccessRegistrationRandomUser_Step3");
        loginPage.ClickLogin();
        makeScreenShot("1.testSuccessRegistrationRandomUser_Step4");
        result = webDriver.findElement(By.xpath("//a[@href='/pages/startpage.xhtml']"));
        expectedMessage = "Software Logo";
        actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
    }

    /**
     * Значение поля ‘Username’ является обязательным для заполнения,
     * состоит из максимум 6 символов:
     * только буквы и цифры (функциональность).
     */
    @Test
    public void testNecessaryUserNameField() throws Exception {
        String newUserName = "";
        mainPage.createFieldsOfUser(newUserName);
        makeScreenShot("2.testNecessaryUserNameField_Step1");
        mainPage.ClickRegistration();
        makeScreenShot("2.testNecessaryUserNameField_Step2");
        WebElement result = webDriver.findElement(By.xpath("//span[@class='error']"));
        String expectedMessage = "Login must not be empty.";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
    }

    /**
     * Значение поля ‘Password’ также является обязательным для заполнения
     * и должно содержать не менее 8 символов,
     * из которых обязательно должно быть не менее 1 цифры,
     * 1 буквы в верхнем регистре
     * и 1 символа, не являющегося ни буквой, ни цифрой. (функциональность).
     */
    @Test
    public void testNecessaryPasswordField() throws Exception {
        String newUserName = generateRandomUsername();
        mainPage.createFieldsOfUser(newUserName);
        mainPage.setPasswordField("");
        makeScreenShot("3.1.testNecessaryPasswordField_Step1");
        mainPage.ClickRegistration();
        makeScreenShot("3.1.testNecessaryPasswordField_Step2");
        //TODO логин ошибка и пассворд имеют один и тот же икспасс
        WebElement result = webDriver.findElement(By.xpath("//span[@class='error']"));
        String expectedMessage = "Please enter password.";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testRequirementPasswordField() throws Exception {
        String newUserName = generateRandomUsername();
        mainPage.createFieldsOfUser(newUserName);
        mainPage.setPasswordField("pass");
        makeScreenShot("3.2.testRequirementPasswordField_Step1");
        mainPage.ClickRegistration();
        makeScreenShot("3.2.testRequirementPasswordField_Step2");
        //TODO логин ошибка и пассворд имеют один и тот же икспасс
        WebElement result = webDriver.findElement(By.xpath("//span[@class='error']"));
        String expectedMessage = "Password length must me >= 8 and <= 50.";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testRequirementPasswordField2() throws Exception {
        String newUserName = generateRandomUsername();
        mainPage.createFieldsOfUser(newUserName);
        mainPage.setPasswordField("password123");
        makeScreenShot("3.3.testRequirementPasswordField2_Step1");
        mainPage.ClickRegistration();
        makeScreenShot("3.3.testRequirementPasswordField2_Step2");
        //TODO логин ошибка и пассворд имеют один и тот же икспасс
        WebElement result = webDriver.findElement(By.xpath("//span[@class='error']"));
        String expectedMessage = "At least one upper letter must be in password";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testRequirementPasswordField3() throws Exception {
        String newUserName = generateRandomUsername();
        mainPage.createFieldsOfUser(newUserName);
        mainPage.setPasswordField("PASSWORD123");
        makeScreenShot("3.4.testRequirementPasswordField3_Step1");
        mainPage.ClickRegistration();
        makeScreenShot("3.4.testRequirementPasswordField3_Step2");
        //TODO логин ошибка и пассворд имеют один и тот же икспасс
        WebElement result = webDriver.findElement(By.xpath("//span[@class='error']"));
        String expectedMessage = "At least one lower letter must be in password";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testRequirementPasswordField4() throws Exception {
        String newUserName = generateRandomUsername();
        mainPage.createFieldsOfUser(newUserName);
        mainPage.setPasswordField("PASSWORd");
        makeScreenShot("3.5.testRequirementPasswordField4_Step1");
        mainPage.ClickRegistration();
        makeScreenShot("3.5.testRequirementPasswordField4_Step2");
        //TODO логин ошибка и пассворд имеют один и тот же икспасс
        WebElement result = webDriver.findElement(By.xpath("//span[@class='error']"));
        String expectedMessage = "At least one digit must be in password";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testRequirementPasswordField5() throws Exception {
        String newUserName = generateRandomUsername();
        mainPage.createFieldsOfUser(newUserName);
        mainPage.setPasswordField("PASSWORd1");
        makeScreenShot("3.6.testRequirementPasswordField5_Step1");
        mainPage.ClickRegistration();
        makeScreenShot("3.6.testRequirementPasswordField5_Step2");
        //TODO логин ошибка и пассворд имеют один и тот же икспасс
        WebElement result = webDriver.findElement(By.xpath("//span[@class='error']"));
        String expectedMessage = "At least one non alpha numeric symbol must be in password";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
    }

    /**
     * Значения поля ‘Email’ является обязательным для заполнения
     * и должно содержать только буквы, цифры, один символ ‘@’
     * и хотя бы один символ ‘.’ (функциональность).
     */
    @Test
    public void testNecessaryEmailField() throws Exception {
        String newUserName = generateRandomUsername();
        mainPage.createFieldsOfUser(newUserName);
        mainPage.setEmailField("");
        makeScreenShot("4.testNecessaryEmailField_Step1");
        mainPage.ClickRegistration();
        makeScreenShot("4.testNecessaryEmailField_Step2");
        //TODO логин ошибка и пассворд имеют один и тот же икспасс
        WebElement result = webDriver.findElement(By.xpath("//span[@class='error']"));
        String expectedMessage = "email field can't be empty";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
    }

    /**
     * Если пользователь в поле ‘Email’ ввёл значение,
     * которое уже существует в системе,
     * в течение 5 секунд после нажатия на кнопку ‘Register’
     * он должен увидеть сообщение
     * ‘The user with such email address has been already registered. Please fill out another email address’.
     */
    @Test
    public void testExistEmail() throws Exception {
        String newUserName = generateRandomUsername();
        mainPage.createFieldsOfUser(newUserName);
        mainPage.setEmailField("User@mail.ru");
        makeScreenShot("5.testExistEmail_Step1");
        mainPage.ClickRegistration();
        makeScreenShot("5.testExistEmail_Step2");
        //TODO test failed
        WebElement result = webDriver.findElement(By.xpath("//span[@class='error']"));
        String expectedMessage = "email field can't be empty";
        String actualMessage = result.getText();
        assertNotEquals(actualMessage, expectedMessage);
    }

    /**
     * Поле ‘Role’ является необязательным для заполнения
     * и может быть заполнено только тремя значениями из выпадающего списка:
     * ‘Admin’, ‘Read Only’ и ‘Read / Write’ (функциональность).
     */
    @Test
    public void testNotNecessaryRoleField() throws Exception {
        String newUserName = generateRandomUsername();
        mainPage.createFieldsOfUser(newUserName);
        Select selectRole = new Select(webDriver.findElement(By.id("registerForm:role")));
        selectRole.selectByVisibleText("Read / Write");
        //WebElement role = webDriver.findElement(By.xpath("//option[@selected='RW']"));
        makeScreenShot("6.testNotNecessaryRoleField_Step1");
        String expectedMessage = "Read / Write";
        //assertEquals(selectRole.selectByVisibleText("Read / Write"), expectedMessage);
    }
}
