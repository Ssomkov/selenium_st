package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

import java.util.concurrent.TimeUnit;

public class CartTest {

    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkCartItemCount() {
        driver.get("http://localhost/litecart/en/");
        homePage = new HomePage(driver);
        //добавление 1 товара
        productPage = homePage.openFirstPopularProductInfo();
        productPage.addToCart();
        homePage = productPage.openHomePage();
        //добавление 2 товара
        productPage = homePage.openFirstPopularProductInfo();
        productPage.addToCart();
        homePage = productPage.openHomePage();
        //добавление 3 товара
        productPage = homePage.openFirstPopularProductInfo();
        productPage.addToCart();
        homePage = productPage.openHomePage();
        //удаление товаров
        cartPage = homePage.openCartPage();
        cartPage.deleteAllItems();
    }
}
