package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FieldWorker {

    protected void setTextField(WebElement element, String value) {
        element.sendKeys(value);
    }

    protected void waitForElement(WebDriver driver, WebElement element) {
        Wait<WebDriver> wait;
        wait = new WebDriverWait(driver, 5).withMessage("Element was not found");
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
