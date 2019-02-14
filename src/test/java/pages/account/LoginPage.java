package pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.FieldWorker;

public class LoginPage extends FieldWorker {

    private WebDriver driver;

    @FindBy(xpath = "//input[@name='username']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@name='login']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPage login(String login, String password) {
        setTextField(loginField, login);
        setTextField(passwordField, password);
        loginButton.click();
        return new MainPage(driver);
    }
}
