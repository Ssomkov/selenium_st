package pages;

import org.openqa.selenium.WebElement;

public class FieldWorkerPage {

    protected void setTextField(WebElement element, String value) {
        element.sendKeys(value);
    }
}
