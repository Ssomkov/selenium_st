package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HomePage extends FieldWorker {

    private WebDriver driver;

    @FindBy(xpath = "//li[contains(@class, 'product')]")
    private List<WebElement> allProducts;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage verifyAllProductsHaveLabels() {
        boolean res = true;
        for (WebElement product : allProducts) {
            if (product.findElements(By.xpath(".//div[contains(@class,'sticker')]")).isEmpty()) {
                res = false;
            }
        }
        Assert.assertTrue(res);
        return this;
    }
}
