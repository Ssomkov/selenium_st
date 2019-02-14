package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HomePage extends FieldWorker {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='box']//li[@class='product column shadow hover-light']")
    private List<WebElement> allProducts;

    @FindBy(xpath = "//div[@id='box-most-popular']//li")
    private List<WebElement> popularProducts;

    @FindBy(xpath = "//div[@id='box-campaigns']//li")
    private List<WebElement> campaignsProducts;

    @FindBy(xpath = "//div[@id='box-latest-products']//li")
    private List<WebElement> latestProducts;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage verifyAllProductsHaveLabels() {
        boolean res = true;
        try {
            for (WebElement product : allProducts) {
                product.findElement(By.xpath(".//div[@class='sticker sale' or @class='sticker new']")).isDisplayed();
            }
        } catch (NullPointerException | NoSuchElementException e) {
            res = false;
        }
        Assert.assertTrue(res);
        return this;
    }
}
