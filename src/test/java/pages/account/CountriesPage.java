package pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.FieldWorker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountriesPage extends FieldWorker {

    private WebDriver driver;

    @FindBy(xpath = "//form[@name='countries_form']//tr[@class='row']")
    private List<WebElement> countryList;

    @FindBy(xpath = "//table[@id='table-zones']//tr")
    private List<WebElement> zoneList;

    public CountriesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CountriesPage verifyCountriesListSorted() {
        boolean isCountrySorted = true;
        List<String> countriesNames;
        //получение названий стран
        countriesNames = getCountriesNames();
        //создание эталонного сортированного списка
        List<String> sortedList = new ArrayList<>();
        sortedList.addAll(countriesNames);
        Collections.sort(sortedList);
        //проверка равенства отсортированного списка стран и исходного
        for (int i = 0; i < countriesNames.size(); i++) {
            if (!countriesNames.get(i).equals(sortedList.get(i))) {
                isCountrySorted = false;
                break;
            }
        }
        Assert.assertTrue(isCountrySorted);
        return this;
    }

    public CountriesPage verifyZonesInCountrySorted() {
        //проверка сортровки зон
        boolean isZonesInCountrySorted = true;
        List<String> countriesNames;
        countriesNames = getCountriesNames();
        for (int i = 0; i < countriesNames.size(); i++) {
            countryList = driver.findElements(By.xpath("//form[@name='countries_form']//tr[@class='row']"));
            if (!countryList.get(i).findElement(By.xpath("./td[6]")).getText().equals("0")) {
                System.out.println(countryList.get(i).findElement(By.xpath("./td[5]/a")).getText());
                WebElement country = countryList.get(i).findElement(By.xpath("./td[5]/a"));
                country.click();
                //проверяем список зон
                List<String> zonesNames = getZonesNames();
                //создание эталонного сортированного списка
                List<String> sortedList = new ArrayList<>();
                sortedList.addAll(zonesNames);
                Collections.sort(sortedList);
                //переход к списку стран
                for (int j = 0; j < zonesNames.size(); j++) {
                    if (!zonesNames.get(j).equals(sortedList.get(j))) {
                        isZonesInCountrySorted = false;
                        break;
                    }
                }
                driver.findElement(By.xpath("//li[@id='app-']/a/span[(text()='Countries')]")).click();
            }
        }
        Assert.assertTrue(isZonesInCountrySorted);
        return this;
    }

    private List<String> getCountriesNames() {
        List<String> countriesNames = new ArrayList<>();
        //получение названий стран
        countryList = driver.findElements(By.xpath("//form[@name='countries_form']//tr[@class='row']"));
        for (WebElement country : countryList) {
            String countryName = country.findElement(By.xpath("./td[5]/a")).getText();
            countriesNames.add(countryName);
        }
        return countriesNames;
    }

    private List<String> getZonesNames() {
        List<String> zonesNames = new ArrayList<>();
        zoneList = driver.findElements(By.xpath("//table[@id='table-zones']//tr"));
        //удаление заголовка таблицы и футера
        zoneList.remove(0);
        zoneList.remove(zoneList.size() - 1);
        //получение названия зон
        for (WebElement zone : zoneList) {
            String zoneName = zone.findElement(By.xpath("./td[3]")).getText();
            zonesNames.add(zoneName);
        }
        return zonesNames;
    }
}
