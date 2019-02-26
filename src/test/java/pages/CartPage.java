package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends FieldWorker {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CartPage deleteAllSameItems() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement firstItemInTable = driver.findElement(By.xpath("//div[@id='order_confirmation-wrapper']//tr[2]"));
        driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();
        wait.until(ExpectedConditions.stalenessOf(firstItemInTable));
        return this;
    }

    public CartPage deleteAllItems() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        List<WebElement> productPicts = driver.findElements(By.xpath("//li[contains(@class, 'shortcut')]/a[contains(@class, 'inact')]"));
        int pructCount = productPicts.size();
        wait.until(ExpectedConditions.visibilityOfAllElements(productPicts));
        List<WebElement> orderTableRows;
        int orderTableRowsCount;
        List<WebElement> deleteBtn;
        List<WebElement> shortcuts;
        for (int i = 0; i < pructCount; i++) {
            shortcuts = driver.findElements(By.xpath("//li[@class='shortcut']/a"));
            orderTableRows = driver.findElements(By.xpath("//div[@id='order_confirmation-wrapper']//tr"));
            orderTableRowsCount = orderTableRows.size();
            if (!shortcuts.isEmpty()) {
                wait.until(ExpectedConditions.elementToBeClickable(shortcuts.get(0))).click();
            }
            deleteBtn = driver.findElements(By.xpath("//button[@name='remove_cart_item']"));
            wait.until(ExpectedConditions.elementToBeClickable(deleteBtn.get(0))).click();
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.xpath("//div[@id='order_confirmation-wrapper']//tr"), orderTableRowsCount));
        }
        return this;
    }
}
