import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SampleTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @Test
    public void search(){
        driver.get("https://yandex.ru");
        WebElement input = driver.findElement(By.className("input__control"));
        input.sendKeys("otus");
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.className("mini-suggest__item"))).click();
    }

    @Test
    public void openPage() {
    //    ChromeOptions options = new ChromeOptions();
    //    options.addArguments("headless");
        driver.get(cfg.url());
        logger.info("Открыта страница отус");
    //    driver.close();

    //    Dimension dimension = new Dimension(800, 600);
    //    Point point = new Point(100, 100);
    //    driver.manage().window().setSize(dimension);
    //    driver.manage().window().setPosition(point);

    //    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    //    driver.findElement(By.xpath("//jdiv[@class='hoverl_6R']"));

    //    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    //    driver.findElement(By.xpath("//jdiv[@class='hoverl_6R']"));


    //    Cookie cookie = new Cookie("otus3", "value3");
    //    driver.manage().addCookie(new Cookie("Otus1", "value1"));
    //    driver.manage().addCookie(new Cookie("Otus2", "value2"));
    //    driver.manage().addCookie(cookie);
    //    driver.manage().addCookie(new Cookie("Otus4", "value4"));
    //    System.out.println(driver.manage().getCookies().toString()); // не уверен что это даст ожидаемый результат
    //    System.out.println(driver.manage().getCookieNamed("Otus1").toString());
    //    driver.manage().deleteCookieNamed("Otus2");
    //    driver.manage().deleteCookie(cookie);
    //    driver.manage().deleteAllCookies();
    //    boolean isempty = driver.manage().getCookies().isEmpty();
    }

    @AfterTest
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}