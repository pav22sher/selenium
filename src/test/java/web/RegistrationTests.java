package web;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static web.Utils.*;

import static junit.framework.TestCase.assertEquals;

public class RegistrationTests extends WebDriverSettings {

    /**
     * Успешная регистрация нового пользователя (функциональность).
     */
    @Test
    public void testSuccessRegistrationRandomUser() throws Exception {
        String userName = generateRandomUsername();
        registrationPage.registrationProcess(userName, true);
        WebElement result = webDriver.findElement(By.cssSelector("div[class='justRegisteredBlock']"));
        String expectedMessage = "You have successfully registered\n" + "Use your credentials to login";
        String actualMessage = result.getText();
        assertEquals(expectedMessage, actualMessage);
        makeScreenShot("testSuccessRegistrationRandomUser");
    }

    /**
     * Значение поля ‘Username’ является обязательным для заполнения,
     * состоит из максимум 6 символов:
     * только буквы и цифры (функциональность).
     */
    @Test
    public void testNecessaryUserNameField() throws Exception {
        String newUserName = "";
        registrationPage.registrationProcess(newUserName, true);
        WebElement result = webDriver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[1]/td[3]/span"));
        String expectedMessage = "Login must not be empty.";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
        makeScreenShot("testNecessaryUserNameField");
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
        registrationPage.registrationProcess(newUserName, "", true);
        WebElement result = webDriver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[2]/td[3]/span"));
        String expectedMessage = "Please enter password.";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
        makeScreenShot("testNecessaryPasswordField");
    }

    @Test
    public void testRequirementPasswordField() throws Exception {
        String newUserName = generateRandomUsername();
        registrationPage.registrationProcess(newUserName, "pass", true);
        WebElement result = webDriver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[2]/td[3]/span"));
        String expectedMessage = "Password length must me >= 8 and <= 50.";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
        makeScreenShot("testRequirementPasswordField");
    }

    @Test
    public void testRequirementPasswordField2() throws Exception {
        String newUserName = generateRandomUsername();
        registrationPage.registrationProcess(newUserName, "password123", true);
        WebElement result = webDriver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[2]/td[3]/span"));
        String expectedMessage = "At least one upper letter must be in password";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
        makeScreenShot("testRequirementPasswordField2");
    }

    @Test
    public void testRequirementPasswordField3() throws Exception {
        String newUserName = generateRandomUsername();
        registrationPage.registrationProcess(newUserName, "PASSWORD123", true);
        WebElement result = webDriver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[2]/td[3]/span"));
        String expectedMessage = "At least one lower letter must be in password";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
        makeScreenShot("testRequirementPasswordField3");
    }

    @Test
    public void testRequirementPasswordField4() throws Exception {
        String newUserName = generateRandomUsername();
        registrationPage.registrationProcess(newUserName, "PASSWORd", true);
        WebElement result = webDriver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[2]/td[3]/span"));
        String expectedMessage = "At least one digit must be in password";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
        makeScreenShot("testRequirementPasswordField4");
    }

    @Test
    public void testRequirementPasswordField5() throws Exception {
        String newUserName = generateRandomUsername();
        registrationPage.registrationProcess(newUserName, "PASSWORd1", true);
        WebElement result = webDriver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[2]/td[3]/span"));
        String expectedMessage = "At least one non alpha numeric symbol must be in password";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
        makeScreenShot("testRequirementPasswordField5");
    }

    /**
     * Значения поля ‘Email’ является обязательным для заполнения
     * и должно содержать только буквы, цифры, один символ ‘@’
     * и хотя бы один символ ‘.’ (функциональность).
     */
    @Test
    public void testNecessaryEmailField() throws Exception {
        String newUserName = generateRandomUsername();
        registrationPage.registrationProcess(newUserName, "Password123$", "", true);
        WebElement result = webDriver.findElement(By.xpath("/html/body/div/div[2]/form/table/tbody/tr[5]/td[3]/span"));
        String expectedMessage = "email field can't be empty";
        String actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
        makeScreenShot("testNecessaryEmailField");
    }

    /**
     * Поле ‘Role’ является необязательным для заполнения
     * и может быть заполнено только тремя значениями из выпадающего списка:
     * ‘Admin’, ‘Read Only’ и ‘Read / Write’ (функциональность).
     */
    @Test
    public void testNotNecessaryRoleFieldRO() throws Exception {
        String newUserName = generateRandomUsername();
        registrationPage.registrationProcess(newUserName, false);
        Select selectRole = new Select(webDriver.findElement(By.id("registerForm:role")));
        selectRole.selectByValue("RO");
        Assert.assertEquals("RO", selectRole.getFirstSelectedOption().getAttribute("value"));
        makeScreenShot("testNotNecessaryRoleFieldRO");
    }

    @Test
    public void testNotNecessaryRoleFieldRW() throws Exception {
        String newUserName = generateRandomUsername();
        registrationPage.registrationProcess(newUserName, false);
        Select selectRole = new Select(webDriver.findElement(By.id("registerForm:role")));
        selectRole.selectByValue("RW");
        Assert.assertEquals("RW", selectRole.getFirstSelectedOption().getAttribute("value"));
        makeScreenShot("testNotNecessaryRoleFieldRW");
    }

    @Test
    public void testNotNecessaryRoleField3() throws Exception {
        String newUserName = generateRandomUsername();
        registrationPage.registrationProcess(newUserName, false);
        Select selectRole = new Select(webDriver.findElement(By.id("registerForm:role")));
        selectRole.selectByValue("Admin");
        Assert.assertEquals("Admin", selectRole.getFirstSelectedOption().getAttribute("value"));
        makeScreenShot("testNotNecessaryRoleFieldAdmin");

    }
}
