package pages.account.catalog;

import models.Campaign;
import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.FieldWorker;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProductPage extends FieldWorker {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='tabs']//a[text()='General']")
    WebElement generalTab;

    @FindBy(xpath = "//input[@name='name[en]']")
    WebElement name;

    @FindBy(xpath = "//input[@name='code']")
    WebElement code;

    @FindBy(xpath = "//input[@name='quantity']")
    WebElement quantity;

    @FindBy(xpath = "//input[@name='new_images[]']")
    WebElement addFileDialogBtn;

    @FindBy(xpath = "//input[@name='date_valid_from']")
    WebElement dateValidFrom;

    @FindBy(xpath = "//input[@name='date_valid_to']")
    WebElement dateValidTo;

    @FindBy(xpath = "//div[@class='tabs']//a[text()='Information']")
    WebElement informationTab;

    @FindBy(xpath = "//select[@name='manufacturer_id']")
    Select manufacturer;

    @FindBy(xpath = "//input[@name='keywords']")
    WebElement keywords;

    @FindBy(xpath = "//input[@name='short_description[en]']")
    WebElement shortDescription;

    @FindBy(xpath = "//div[@class='trumbowyg-editor']")
    WebElement description;

    @FindBy(xpath = "//input[@name='head_title[en]']")
    WebElement headTitle;

    @FindBy(xpath = "//input[@name='meta_description[en]']")
    WebElement metaDescription;

    @FindBy(xpath = "//div[@class='tabs']//a[text()='Prices']")
    WebElement pricesTab;

    @FindBy(xpath = "//input[@name='purchase_price']")
    WebElement purchasePrice;

    @FindBy(xpath = "//select[@name='purchase_price_currency_code']")
    Select currencyCode;

    @FindBy(xpath = "//a[@id='add-campaign']")
    WebElement addCampaignBtn;

    @FindBy(xpath = "//button[@name='save']")
    WebElement saveBtn;


    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CatalogPage addProduct(Product product) {
        fillGeneralTab(product);
        fillInformationTab(product);
        fillPricesTab(product);
        saveBtn.click();
        return new CatalogPage(driver);
    }

    public ProductPage fillGeneralTab(Product product) {
        generalTab.click();
        waitForElement(driver, name);
        selectStatus(product.getStatus());
        selectName(product.getName());
        selectCode(product.getCode());
        selectSex(product.getSex());
        selectQuantity(product.getQuantity());
        selectImage(product.getImage());
        selectDateValidFrom(product.getDateValidFrom());
        selectDateValidTo(product.getDateValidTo());
        return this;
    }

    public ProductPage fillInformationTab(Product product) {
        informationTab.click();
        waitForElement(driver, keywords);
        selectManufacturer(product.getManufacturer());
        selectSupplier(product.getSupplier());
        selectKeywords(product.getKeywords());
        selectShortDescription(product.getShortDescription());
        selectDescription(product.getDescription());
        selectHeadTitle(product.getHeadTitle());
        selectMetaDescription(product.getMetaDescription());
        return this;
    }

    public ProductPage fillPricesTab(Product product) {
        pricesTab.click();
        waitForElement(driver, purchasePrice);
        selectPurchasePrice(product.getPurchasePrice());
        selectCurrencyCode(product.getCurrencyCode());
        fillCampaigns(product);
        return this;
    }

    public ProductPage selectStatus(String value) {
        if (!value.isEmpty()) {
            driver.findElement(By.xpath("//label[contains(text(), '" + value + "')]/input")).sendKeys(Keys.SPACE);
        }
        return this;
    }

    public ProductPage selectName(String value) {
        name.clear();
        name.sendKeys(value);
        return this;
    }

    public ProductPage selectCode(String value) {
        code.clear();
        code.sendKeys(value);
        return this;
    }

    public ProductPage selectSex(String value) {
        if (!value.isEmpty()) {
            driver.findElement(By.xpath("//tr/td[contains(text(), '" + value + "')]/../td/input")).sendKeys(Keys.SPACE);
        }
        return this;
    }

    public ProductPage selectQuantity(String value) {
        quantity.clear();
        quantity.sendKeys(value);
        return this;
    }

    public ProductPage selectImage(String value) {
        Path relativePath = Paths.get(value);
        Path absPath = relativePath.toAbsolutePath();
        addFileDialogBtn.sendKeys(absPath.toString());
        return this;
    }

    public ProductPage selectDateValidFrom(String value) {
        dateValidFrom.sendKeys(value);
        return this;
    }

    public ProductPage selectDateValidTo(String value) {
        dateValidTo.sendKeys(value);
        return this;
    }

    public ProductPage selectManufacturer(String value) {
        manufacturer = new Select(driver.findElement(By.xpath("//select[@name='manufacturer_id']")));
        manufacturer.selectByVisibleText(value);
        return this;
    }

    public ProductPage selectSupplier(String value) {
        if (!value.isEmpty()) {
            manufacturer = new Select(driver.findElement(By.xpath("//select[@name='supplier_id']")));
            manufacturer.selectByVisibleText(value);
        }
        return this;
    }

    public ProductPage selectKeywords(String value) {
        keywords.clear();
        keywords.sendKeys(value);
        return this;
    }

    public ProductPage selectDescription(String value) {
        description.clear();
        description.sendKeys(value);
        return this;
    }

    public ProductPage selectShortDescription(String value) {
        shortDescription.clear();
        shortDescription.sendKeys(value);
        return this;
    }

    public ProductPage selectHeadTitle(String value) {
        headTitle.clear();
        headTitle.sendKeys(value);
        return this;
    }

    public ProductPage selectMetaDescription(String value) {
        metaDescription.clear();
        metaDescription.sendKeys(value);
        return this;
    }

    public ProductPage selectPurchasePrice(String value) {
        purchasePrice.clear();
        purchasePrice.sendKeys(value);
        return this;
    }

    public ProductPage selectCurrencyCode(String value) {
        if (!value.isEmpty()) {
            currencyCode = new Select(driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']")));
            currencyCode.selectByVisibleText(value);
        }
        return this;
    }

    public ProductPage fillCampaigns(Product product) {
        List<Campaign> campaigns = product.getCampaigns();
        if (!campaigns.isEmpty()) {
            for (int i = 0; i < campaigns.size(); i++) {
                addCampaignBtn.click();
                Campaign campaign = campaigns.get(i);
                int j = i + 1;
                WebElement startDate = driver.findElement(By.xpath("//input[@name='campaigns[new_" + j + "][start_date]']"));
                startDate.sendKeys(campaign.getStartDate().substring(0, 8));
                startDate.sendKeys(Keys.TAB);
                startDate.sendKeys(campaign.getStartDate().substring(8));
                WebElement endDate = driver.findElement(By.xpath("//input[@name='campaigns[new_" + j + "][end_date]']"));
                endDate.sendKeys(campaign.getEndDate().substring(0, 8));
                endDate.sendKeys(Keys.TAB);
                endDate.sendKeys(campaign.getEndDate().substring(8));
                WebElement percentage = driver.findElement(By.xpath("//input[@name='campaigns[new_" + j + "][percentage]']"));
                percentage.clear();
                percentage.sendKeys(campaign.getPercentage());
                driver.findElement(By.xpath("//input[@name='campaigns[new_" + j + "][USD]']")).sendKeys(campaign.getUsd());
                driver.findElement(By.xpath("//input[@name='campaigns[new_" + j + "][EUR]']")).sendKeys(campaign.getEur());
            }
        }
        return this;
    }
}
