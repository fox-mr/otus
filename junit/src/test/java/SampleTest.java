import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class SampleTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        driver.manage().window().maximize();

    }
    @Test
    public void openPage() {
        driver.get("https://otus.ru/");
        logger.info("Открыта страница отус");
    }

    @Test
    public void lesson(){
        driver.get("https://ng-bootstrap.github.io/#/components/alert/examples");
        WebElement button = driver.findElement(new By.ByLinkText("Change message"));
        button.click();
        String date1 = driver.findElements(By.cssSelector("ngb-alert.alert.alert-success.alert-dismissible")).get(2).getText();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        button.click();
        String date2 = driver.findElements(By.cssSelector("ngb-alert.alert.alert-success.alert-dismissible")).get(2).getText();
        Assert.assertNotEquals(date1, date2);
    }

    @Test
    public void yandexMarket(){
        driver.get("https://market.yandex.ru/");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement searchLine = driver.findElement(By.id("header-search"));

        searchLine.sendKeys("Xiaomi nokia");
        searchLine.sendKeys(Keys.ENTER);

        List<WebElement> sorting = driver.findElements(By.className("n-filter-sorter__link"));
        wait.until(ExpectedConditions.visibilityOfAllElements(sorting));
        sorting.get(1).click();
        wait.until(ExpectedConditions.visibilityOfAllElements(sorting));
        sorting.get(1).click();

        List<WebElement> itemsList = driver.findElements(By.className("n-snippet-cell2__brand-name"));
         for(int i = 0; itemsList.get(i).getText().contains("XIAOMI"); i++){
             WebElement addToCompare = driver.findElements(By.className("n-user-lists_type_compare")).get(i);
             Actions actions = new Actions(driver);
             actions.moveToElement(addToCompare).perform();
             wait.until(ExpectedConditions.elementToBeClickable(addToCompare));
             addToCompare.click();
             WebElement popup = driver.findElement(By.className("popup-informer__text"));
             wait.until(ExpectedConditions.visibilityOf(popup));
             popup.getText().contains("добавлен к сравнению");
         }

        System.out.println("test");


    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}