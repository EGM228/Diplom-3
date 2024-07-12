package POM;

import io.qameta.allure.Step;
import org.example.EnvConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccount {

    private final By constructor = By.xpath(".//p[text()='Конструктор']");
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    private final By profileText = By.xpath(".//a[@href='/account/profile']");
    private final WebDriver driver;

    public PersonalAccount(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Checking that personal account form opened")
    public void checkPersonalAccountLoaded(){
        new WebDriverWait(driver, EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(profileText));
        assert driver.findElement(profileText).isDisplayed();
    }

    @Step("Clicking exit button")
    public void clickExitButton() {
        new WebDriverWait(driver,EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.elementToBeClickable(exitButton));
        driver.findElement(exitButton).click();
    }

    @Step("Clicking logo")
    public void clickLogo() {
        new WebDriverWait(driver,EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.elementToBeClickable(logo));
        driver.findElement(logo).click();
    }

    @Step("Clicking constructor button")
    public void clickConstructor() {
        new WebDriverWait(driver,EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.elementToBeClickable(constructor));
        driver.findElement(constructor).click();
    }
}