package pages.account.catalog;

import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.FieldWorker;

import java.util.List;

public class CatalogPage extends FieldWorker {

    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(), 'Add New Product')]")
    WebElement addProductBtn;

    @FindBy(xpath = "//tr[contains(@class, 'row')]/td[3]/a")
    List<WebElement> products;

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
        for (WebElement element : products) {
            if (element.getText().equals(product.getName()))
                res = true;
        }
        Assert.assertTrue(res);
        return this;
    }

    public CatalogPage openAllProductsInRubberDucks() {
        WebElement rubberDucks = driver.findElement(By.xpath("//table[@class='dataTable']//a[text()='Rubber Ducks']"));
        rubberDucks.click();
        List<WebElement> products = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]/img[contains(@style, '32px')]/../../td[5]"));
        int size = products.size();
        for (int i = 0; i < size; i++) {
            products = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]/img[contains(@style, '32px')]/../../td[5]"));
            products.get(i).click();
            driver.findElement(By.xpath("//li[@id='app-']/a/span[(text()='Catalog')]")).click();
            rubberDucks = driver.findElement(By.xpath("//table[@class='dataTable']//a[text()='Rubber Ducks']"));
            rubberDucks.click();
        }
        return this;
    }
}
