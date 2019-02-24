package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import models.Campaign;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.account.LoginPage;
import pages.account.MainPage;
import pages.account.catalog.CatalogPage;
import pages.account.catalog.ProductPage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static utils.DataGenerator.getRandomNumber;
import static utils.DataGenerator.getRandomString;

public class CreateNewProductTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private CatalogPage catalogPage;
    private ProductPage productPage;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "productData")
    public Object[] productData() {
        Product product = new Product();
        product.setStatus("Enabled");
        product.setName(getRandomString(5));
        product.setCode(getRandomNumber(5));
        product.setSex("Male");
        product.setQuantity(getRandomNumber(3));
        product.setImage("src/test/resources/duck.png");
        product.setDateValidFrom("28022019");
        product.setDateValidTo("15032019");
        product.setManufacturer("ACME Corp.");
        product.setSupplier("");
        product.setKeywords("duck rubber yellow");
        product.setDescription(getRandomString(30));
        product.setShortDescription(getRandomString(10));
        product.setHeadTitle(getRandomString(5));
        product.setMetaDescription(getRandomString(5));
        product.setPurchasePrice("135");
        product.setCurrencyCode("Euros");
        Campaign campaign = new Campaign();
        campaign.setStartDate("120320191215");
        campaign.setEndDate("120420191215");
        campaign.setPercentage("15");
        campaign.setUsd("12");
        campaign.setEur("13");
        Campaign campaign1 = new Campaign();
        campaign1.setStartDate("140320191215");
        campaign1.setEndDate("160420191215");
        campaign1.setPercentage("20");
        campaign1.setUsd("10");
        campaign1.setEur("8");
        List<Campaign> campaigns = Arrays.asList(campaign, campaign1);
        product.setCampaigns(campaigns);
        return new Object[]{product};
    }

    @Test(dataProvider = "productData")
    public void checkNewProductAdded(Product product) {
        driver.get("http://localhost/litecart/admin/login.php");
        String login = "admin";
        String password = "admin";
        loginPage = new LoginPage(driver);
        mainPage = loginPage.login(login, password);
        catalogPage = mainPage.openCatalogPage();
        productPage = catalogPage.openProductPage();
        catalogPage = productPage.addProduct(product);
        catalogPage.verifyProductIsPresent(product);
    }
}
