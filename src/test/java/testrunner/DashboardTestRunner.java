package testrunner;

import com.github.javafaker.Faker;
import config.EmployeeModel;
import config.Setup;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class DashboardTestRunner extends Setup {
    @BeforeTest
    public void login(){
      LoginPage loginPage=new LoginPage(driver);
      loginPage.doLogin("Admin","admin123");
    }
    /*@Test(priority = 1, description= "Admin can search employee")
    public void clickOnSearchButton(){
        driver.findElement(By.cssSelector("[type=submit]")).click();
    }*/
    @Test(priority = 1, description = "Admin can create new employee")
    public void createEmployee() throws InterruptedException, IOException, ParseException {
        DashboardPage dashboardPage=new DashboardPage(driver);
        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String username=faker.name().username();
        String password=faker.internet().password();

        EmployeeModel model=new EmployeeModel();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        model.setUsername(username);
        model.setPassword(password);

        dashboardPage.createEmployee(model); //create new user
        Thread.sleep(700);
        /*String textTitleExpected= By.xpath(("//*[contains(text(),\"personal Details\")]")
        ).findElement(driver).getText();
        Thread.sleep(700);
        if(textTitleExpected.contains("Personal Details")) {
            Utils.saveEmployeeInfo(firstName, lastName, username, password);
        }*/
        Utils.saveEmployeeInfo(model);

    }
}
