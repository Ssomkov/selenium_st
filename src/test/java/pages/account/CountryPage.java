package pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FieldWorker;

import java.util.ArrayList;
import java.util.List;

public class CountryPage extends FieldWorker {

    private WebDriver driver;

    @FindBy(xpath = "//i[contains(@class, 'fa fa-external-link')]")
    private List<WebElement> editLinks;

    public CountryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CountryPage verifyAllLinksOpenInNewWindow() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        //текущее окно
        String originalWindow = driver.getWindowHandle();
        int linksCount = editLinks.size();
        //проход по всем ссылкам редактирования
        for (int i = 0; i < linksCount; i++) {
            editLinks = driver.findElements(By.xpath("//i[contains(@class, 'fa fa-external-link')]"));
            editLinks.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            //список окон
            ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
            //переключение в новое окно
            for (String window : windows) {
                if (!window.equals(originalWindow)) {
                    driver.switchTo().window(window);
                    System.out.println("Переключились в: " + window);
                }
            }
            //переключение в исходное окно
            driver.close();
            driver.switchTo().window(originalWindow);
            System.out.println("Переключились в исходное окно: " + originalWindow);
        }
        return this;
    }
}
