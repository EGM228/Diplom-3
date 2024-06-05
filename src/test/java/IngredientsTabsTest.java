import POM.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class IngredientsTabsTest {

    private final DriverFactory driverFactory = new DriverFactory();

    @Before
    public void startUp(){
        driverFactory.initDriver();
    }

    @DisplayName("Testing that sauce tab can be open")
    @Test
    public void openingSauceTabTest(){
        WebDriver driver = driverFactory.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        main.clickOnSauceButton();
        main.checkSauceTabOpened();
    }

    @DisplayName("Testing that filling tab can be open")
    @Test
    public void openingFillingTabTest(){
        WebDriver driver = driverFactory.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        main.clickOnFillingButton();
        main.checkFillingTabOpened();
    }

    @DisplayName("Testing that buns tab can be open")
    @Test
    public void openingBunsTabTest(){
        WebDriver driver = driverFactory.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        main.clickOnSauceButton();
        main.clickOnBunsButton();
        main.checkBunsTabOpened();
    }

    @After
    public void tearDown(){
        driverFactory.getDriver().quit();
    }
}