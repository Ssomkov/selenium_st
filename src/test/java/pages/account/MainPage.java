package pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.FieldWorker;

import java.util.List;

public class MainPage extends FieldWorker {

    private WebDriver driver;

    @FindBy(xpath = "//li[@id='widget-stats']")
    private WebElement statisticsPanel;

    @FindBy(xpath = "//li[@id='app-']/a/span[contains(@class, 'name')]")
    private List<WebElement> categoryList;

    @FindBy(xpath = "//li[@id='app-']//li//span")
    private List<WebElement> subCategoryList;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Assert.assertTrue(statisticsPanel.isDisplayed());
    }

    public MainPage verifyAllCategoryHaveHeaders() {
        boolean res = true;
        List<WebElement> header;
        int categoryListSize = categoryList.size();
        for (int i = 0; i < categoryListSize; i++) {
            categoryList = driver.findElements(By.xpath("//li[@id='app-']/a/span[contains(@class, 'name')]"));
            categoryList.get(i).click();
            //проверка хедера
            header = driver.findElements(By.xpath("//h1"));
            if (header.isEmpty()) {
                res = false;
                break;
            }
            subCategoryList = driver.findElements(By.xpath("//li[@id='app-']//li//span"));
            int subCategoryListSize = subCategoryList.size();
            //проход по вложенным пунктам категорий
            if (!subCategoryList.isEmpty()) {
                for (int j = 0; j < subCategoryListSize; j++) {
                    subCategoryList = driver.findElements(By.xpath("//li[@id='app-']//li//span"));
                    subCategoryList.get(j).click();
                    //проверка хедера
                    header = driver.findElements(By.xpath("//h1"));
                    if (header.isEmpty()) {
                        res = false;
                        break;
                    }
                }
            }
        }
        Assert.assertTrue(res);
        return this;
    }
}
