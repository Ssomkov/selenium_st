package pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.FieldWorker;

public class MainPage extends FieldWorker {

    private WebDriver driver;

    @FindBy(xpath = "//li[@id='widget-stats']")
    private WebElement statisticsPanel;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Assert.assertTrue(statisticsPanel.isDisplayed());
    }
}
