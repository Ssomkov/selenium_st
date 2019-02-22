package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends FieldWorker {

    private WebDriver driver;

    @FindBy(xpath = "//input[@name='firstname']")
    WebElement firstNameInput;

    @FindBy(xpath = "//input[@name='lastname']")
    WebElement lastNameInput;

    @FindBy(xpath = "//input[@name='address1']")
    WebElement address1Input;

    @FindBy(xpath = "//input[@name='postcode']")
    WebElement postcodeInput;

    @FindBy(xpath = "//input[@name='city']")
    WebElement cityInput;

    @FindBy(xpath = "//span[@class='select2-selection__arrow']")
    WebElement countryDropDownBtn;

    @FindBy(xpath = "//input[@name='email']")
    WebElement emailInput;

    @FindBy(xpath = "//input[@name='phone']")
    WebElement phoneInput;

    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//input[@name='confirmed_password']")
    WebElement confirmedPasswordInput;

    @FindBy(xpath = "//button[@name='create_account']")
    WebElement createAccountBtn;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage registerUser(User user) {
        fillRegistrationForm(user);
        createAccountBtn.click();
        return new HomePage(driver);
    }

    private void fillRegistrationForm(User user) {
        setTextField(firstNameInput, user.getFirstName());
        setTextField(lastNameInput, user.getLastName());
        setTextField(address1Input, user.getAddress1());
        setTextField(postcodeInput, user.getPostcode());
        setTextField(cityInput, user.getCity());
        selectCountry(user.getCountry());
        setTextField(emailInput, user.getEmail());
        setTextField(phoneInput, user.getPhone());
        setTextField(passwordInput, user.getPassword());
        setTextField(confirmedPasswordInput, user.getPassword());
    }

    private void selectCountry(String countryName) {
        countryDropDownBtn.click();
        driver.findElement(By.xpath("//li[text()='" + countryName + "']")).click();
    }
}
