package utils;

import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.account.LoginPage;

public class Navigation {

    private WebDriver driver;

    public Navigation(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage openHomePage() {
        driver.get("http://localhost/litecart/en/");
        return new HomePage(driver);
    }

    public LoginPage openLoginPage() {
        driver.get("http://localhost/litecart/admin/login.php");
        return new LoginPage(driver);
    }
}
