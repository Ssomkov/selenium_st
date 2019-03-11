package pages;

import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage extends FieldWorker {

    private WebDriver driver;

    @FindBy(xpath = "//nav[@id='breadcrumbs']//a[text()='Home']")
    WebElement homeLink;

    @FindBy(xpath = "//div[@id='cart']//span[@class='quantity']")
    WebElement quantity;

    @FindBy(xpath = "//button[@name='add_cart_product']")
    WebElement addToCartBtn;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage openHomePage() {
        homeLink.click();
        return new HomePage(driver);
    }

    public ProductPage addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String quantityFromSpan = quantity.getText();
        //добавление в корзину
        addToCartBtn.click();
        //проверка изменения количества товаров
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath("//div[@id='cart']//span[@class='quantity']"), quantityFromSpan)));
        return this;
    }

    public Product getProductInfo() {
        Product product = new Product();
        List<WebElement> productList = driver.findElements(By.xpath("//div[@id='box-product']"));
        WebElement firstProduct = productList.get(0);
        //чтение свойств товара
        String name = firstProduct.findElement(By.xpath(".//h1[@itemprop='name']")).getText();
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
}
