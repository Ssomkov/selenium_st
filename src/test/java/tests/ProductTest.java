package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;

public class ProductTest {

    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
/*        //for IE
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability("ignoreZoomSetting", true);
        driver = new InternetExplorerDriver(caps);*/
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkProductInfo() {
        driver.get("http://localhost/litecart/en/");
        homePage = new HomePage(driver);
        //данные о товаре с главной страницы
        Product productFromMainPage = homePage.getFirstCampaignsProductInfo();
        //переход на страницу товара
        productPage = homePage.openFirstCampaignsProductInfo();
        //данные со страницы товара
        Product productFromProductPage = productPage.getProductInfo();
        //проверки
        //на главной странице и на странице товара совпадает текст названия товара
        Assert.assertEquals(productFromMainPage.getName(), productFromProductPage.getName());
        //на главной странице и на странице товара совпадают цены (обычная и акционная)
        Assert.assertEquals(productFromMainPage.getCommonPrice(), productFromProductPage.getCommonPrice());
        Assert.assertEquals(productFromMainPage.getSalePrice(), productFromProductPage.getSalePrice());
        //обычная цена зачёркнутая и серая
        Assert.assertTrue(productFromMainPage.isCommonPriceStrikeThrough());
        Assert.assertTrue(productFromMainPage.isCommonPriceGrey());
        Assert.assertTrue(productFromMainPage.isCommonPriceStrikeThrough());
        Assert.assertTrue(productFromMainPage.isCommonPriceGrey());
        //акционная жирная и красная
        Assert.assertTrue(productFromMainPage.isSalePriceColorRed());
        Assert.assertTrue(productFromMainPage.isSalePriceBold());
        Assert.assertTrue(productFromMainPage.isSalePriceColorRed());
        Assert.assertTrue(productFromMainPage.isSalePriceBold());
        //акционная цена крупнее, чем обычная
        Assert.assertTrue(productFromMainPage.isSalePriceSizeMoreCommonPriceSize());
        Assert.assertTrue(productFromMainPage.isSalePriceSizeMoreCommonPriceSize());
    }
}
