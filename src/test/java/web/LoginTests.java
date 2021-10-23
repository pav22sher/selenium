package web;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static junit.framework.TestCase.assertEquals;
import static web.Utils.generateRandomUsername;
import static web.Utils.makeScreenShot;

public class LoginTests extends WebDriverSettings {

    /**
     * Успешная авторизация нового пользователя (функциональность).
     */
    @Test
    public void testSuccessLoginRandomUser() throws Exception {
        String userName = generateRandomUsername();
        registrationPage.registrationProcess(userName, true);
        WebElement result = webDriver.findElement(By.cssSelector("div[class='justRegisteredBlock']"));
        String expectedMessage = "You have successfully registered\n" + "Use your credentials to login";
        String actualMessage = result.getText();
        assertEquals(expectedMessage, actualMessage);
        loginPage.loginProcess(userName);
        result = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/table/tbody/tr/td[3]"));
        expectedMessage = userName;
        actualMessage = result.getText();
        assertEquals(actualMessage, expectedMessage);
        makeScreenShot("testSuccessLoginRandomUser");
    }

}
