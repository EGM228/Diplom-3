package POM;

import io.qameta.allure.Step;
import org.example.EnvConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private By bunsTab = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");
    private By sauceTab = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    private By fillingTab = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");
    private final By personalAccountButton = By.xpath(".//a[@href='/account']/p");
    private final By loginToAccButton = By.xpath(".//button[contains(@class,'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg')]");
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Opening main page")
    public void open(){
        driver.get(EnvConfig.BASE_URL);
        driver.manage().window().maximize();
    }

    @Step("Clicking login to account button")
    public void clickLoginToAccBtn(){
        new WebDriverWait(driver, EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(loginToAccButton));
        driver.findElement(loginToAccButton).click();
    }

    @Step("Clicking personal account button")
    public void clickPersonalAccBtn(){
        new WebDriverWait(driver, EnvConfig.BASE_DRIVER_WAIT_TIME)
                .until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        driver.findElement(personalAccountButton).click();
        new WebDriverWait(driver, EnvConfig.BASE_DRIVER_WAIT_TIME)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("Modal_modal_overlay__x2ZCr")));
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Waiting for main page to load")
    public void waitForMainPageLoad(){
        new WebDriverWait(driver, EnvConfig.BASE_DRIVER_WAIT_TIME)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("a[class^='BurgerIngredient'"),2));
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assert driver.findElement(loginToAccButton).isDisplayed();
    }

    @Step("Clicking on sauce tab on main page")
    public void clickOnSauceButton() {
        new WebDriverWait(driver,EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(sauceTab));
        driver.findElement(sauceTab).click();
    }

    @Step("Checking that sauce tab opened")
    public void checkSauceTabOpened() {
        new WebDriverWait(driver,EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.attributeContains(sauceTab,"class","current"));
        String newClassName = driver.findElement(sauceTab).getAttribute("class");
        assert newClassName.contains("current");
    }

    @Step("Clicking on filling tab on main page")
    public void clickOnFillingButton() {
        new WebDriverWait(driver,EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(fillingTab));
        driver.findElement(fillingTab).click();
    }

    @Step("Checking that filling tab opened")
    public void checkFillingTabOpened() {
        new WebDriverWait(driver,EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.attributeContains(fillingTab,"class","current"));
        String newClassName = driver.findElement(fillingTab).getAttribute("class");
        assert newClassName.contains("current");
    }

    @Step("Clicking on buns tab on main page")
    public void clickOnBunsButton() {
        new WebDriverWait(driver,EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
        driver.findElement(bunsTab).click();
    }

    @Step("Checking that buns tab opened")
    public void checkBunsTabOpened() {
        new WebDriverWait(driver,EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.attributeContains(bunsTab,"class","current"));
        String newClassName = driver.findElement(bunsTab).getAttribute("class");
        assert newClassName.contains("current");
    }
}