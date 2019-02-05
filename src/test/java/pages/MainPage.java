package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MainPage extends FieldWorkerPage {

    private WebDriver driver;

    @FindBy(xpath = "//li[@id='widget-stats']")
    private WebElement statisticsPanel;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Assert.assertTrue(statisticsPanel.isDisplayed());
    }
}
