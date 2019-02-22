package pages;

import models.Product;
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

    @FindBy(xpath = "//div[@id='box-account']//a[text()='Logout']")
    private WebElement logoutBtn;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@name='login']")
    private WebElement loginBtn;

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

    public Product getFirstCampaignsProductInfo() {
        Product product = new Product();
        //первый товар из списка товаров в разделе campaigns
        List<WebElement> productList = driver.findElements(By.xpath("//div[@id='box-campaigns']//li[contains(@class, 'product')]"));
        WebElement firstProduct = productList.get(0);
        //чтение свойств товара
        String name = firstProduct.findElement(By.xpath(".//div[@class='name']")).getText();
        //обычная цена
        String commonPrice = firstProduct.findElement(By.xpath(".//s")).getText();
        commonPrice = commonPrice.substring(commonPrice.indexOf("$") + 1);
        String commonPriceFontStyle = firstProduct.findElement(By.xpath(".//s")).getCssValue("text-decoration");
        String commonPriceColor = firstProduct.findElement(By.xpath(".//s")).getCssValue("color");
        String commonPriceFontSize = firstProduct.findElement(By.xpath(".//s")).getCssValue("font-size");
        commonPriceFontSize = commonPriceFontSize.substring(0, commonPriceFontSize.indexOf("px"));
        //цена со скидкой
        String salePrice = firstProduct.findElement(By.xpath(".//strong")).getText();
        salePrice = salePrice.substring(salePrice.indexOf("$") + 1);
        String salePriceFontStyle = firstProduct.findElement(By.xpath(".//strong")).getCssValue("font-weight");
        String salePriceColor = firstProduct.findElement(By.xpath(".//strong")).getCssValue("color");
        String salePriceFontSize = firstProduct.findElement(By.xpath(".//strong")).getCssValue("font-size");
        salePriceFontSize = salePriceFontSize.substring(0, salePriceFontSize.indexOf("px"));
        //запись данных о товаре
        product.setName(name);
        //обычная цена
        product.setCommonPrice(commonPrice);
        product.setCommonPriceFontStyle(commonPriceFontStyle);
        product.setCommonPriceColor(commonPriceColor);
        product.setCommonPriceFontSize(commonPriceFontSize);
        //цена со скидкой
        product.setSalePrice(salePrice);
        product.setSalePriceFontStyle(salePriceFontStyle);
        product.setSalePriceColor(salePriceColor);
        product.setSalePriceFontSize(salePriceFontSize);
        return product;
    }

    public ProductPage openFirstCampaignsProductInfo() {
        //первый товар из списка товаров в разделе campaigns
        List<WebElement> productList = driver.findElements(By.xpath("//div[@id='box-campaigns']//li[contains(@class, 'product')]"));
        WebElement firstProduct = productList.get(0);
        firstProduct.click();
        return new ProductPage(driver);
    }

    public RegistrationPage openRegistrationPage() {
        WebElement regUrl = driver.findElement(By.xpath("//form[@name='login_form']//a[text()='New customers click here']"));
        regUrl.click();
        return new RegistrationPage(driver);
    }

    public HomePage logout() {
        logoutBtn.click();
        return this;
    }

    public HomePage login(String username, String password) {
        setTextField(usernameInput, username);
        setTextField(passwordInput, password);
        loginBtn.click();
        return this;
    }
}
