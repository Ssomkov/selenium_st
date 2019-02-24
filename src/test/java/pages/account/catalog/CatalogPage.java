package pages.account.catalog;

import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.FieldWorker;

import java.util.List;

public class CatalogPage extends FieldWorker {

    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(), 'Add New Product')]")
    WebElement addProductBtn;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductPage openProductPage() {
        addProductBtn.click();
        return new ProductPage(driver);
    }

    public CatalogPage verifyProductIsPresent(Product product) {
        boolean res = false;
        List<WebElement> products = driver.findElements(By.xpath("//tr[contains(@class, 'row')]/td[3]/a"));
        for (WebElement element : products) {
            if (element.getText().equals(product.getName()))
                res = true;
        }
        Assert.assertTrue(res);
        return this;
    }
}
