import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private WebDriver driver;

    public void initDriver(){
        if("firefox".equals(System.getProperty("browser"))){ ///Program Files/Mozilla Firefox/firefox.exe
            initFirefox();
        }
        else{
            initChrome();
        }
    }

    private void initFirefox(){
        WebDriverManager.firefoxdriver().setup();
        var opts = new FirefoxOptions().configureFromEnv();
        driver = new FirefoxDriver(opts);
    }

    private void initChrome(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public WebDriver getDriver(){
        return driver;
    }
}