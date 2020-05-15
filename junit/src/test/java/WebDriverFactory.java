import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public static final WebDriver DEFAULT = new ChromeDriver();

    public WebDriver createDriver(String webDriverName, String options){
        if (webDriverName.equals(Browser.CHROME)){
            return new ChromeDriver();
        } else if (webDriverName.equals(Browser.FIREFOX)){
            return new FirefoxDriver();
        } else if (webDriverName.equals(Browser.EDGE)){
            return new EdgeDriver();
        } else return DEFAULT;
    }
}
