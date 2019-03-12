package pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.FieldWorker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZonesPage extends FieldWorker {

    private WebDriver driver;

    @FindBy(xpath = "//form[@name='geo_zones_form']//td[3]/a")
    List<WebElement> zoneList;

    @FindBy(xpath = "//table[@id='table-zones']//td[3]//option[@selected='selected']")
    List<WebElement> selectedZones;

    public ZonesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ZonesPage verifyZonesSorted() {
        boolean isZonesSorted = true;
        zoneList = driver.findElements(By.xpath("//form[@name='geo_zones_form']//td[3]/a"));
        int zoneListCount = zoneList.size();
        for (int i = 0; i < zoneListCount; i++) {
            //проход по зонам
            zoneList = driver.findElements(By.xpath("//form[@name='geo_zones_form']//td[3]/a"));
            zoneList.get(i).click();
            //получение списка выбранных зон в дропдаунах
            List<String> zoneNames;
            zoneNames = getSelectedZones();
            //создание эталонного сортированного списка
            List<String> sortedList = new ArrayList<>();
            sortedList.addAll(zoneNames);
            Collections.sort(sortedList);
            //проверка равенства отсортированного списка зон и исходного
            for (int j = 0; j < zoneNames.size(); j++) {
                if (!zoneNames.get(j).equals(sortedList.get(j))) {
                    isZonesSorted = false;
                    break;
                }
            }
            //переход на страницу зон
            driver.findElement(By.xpath("//li[@id='app-']/a/span[(text()='Geo Zones')]")).click();
        }
        Assert.assertTrue(isZonesSorted);
        return this;
    }

    private List<String> getSelectedZones() {
        List<String> zoneNames = new ArrayList<>();
        //получение названий выбранных зон
        selectedZones = driver.findElements(By.xpath("//table[@id='table-zones']//td[3]//option[@selected='selected']"));
        for (WebElement zone : selectedZones) {
            zoneNames.add(zone.getText());
        }
        return zoneNames;
    }
}
