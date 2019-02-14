package pages;

import org.openqa.selenium.WebElement;

public class FieldWorker {

    protected void setTextField(WebElement element, String value) {
        element.sendKeys(value);
    }
}
