import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    @Test(priority = 2)
    public void  doLogin(){
        loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
       Assert.assertTrue(driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed());
    }
    @Test(priority = 1)
    public void doLoginWithWrongCreds(){
        loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","wrong");
        String textActual= driver.findElement(By.className("oxd-alert-content-text")).getText();
        String textExpected= "Invalid credentials";
        Assert.assertTrue(textActual.contains(textExpected));

    }
    @Test(priority = 3)
    public void logout(){
        loginPage=new LoginPage(driver);
        loginPage.doLogout();
        String textActual= driver.findElement(By.className("orangehrm-login-title")).getText();
        String textExpected= "Login";
        Assert.assertEquals(textActual,textExpected);


    }
}
