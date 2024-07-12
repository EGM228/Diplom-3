package POM;

import io.qameta.allure.Step;
import org.example.EnvConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginForm {

    private final By passwordField = By.cssSelector("input[name='Пароль']");
    private final By emailField = By.cssSelector("input[name='name']");
    private final By recoveryPassButton = By.xpath(".//a[@href=\"/forgot-password\"]");
    private final By enterButton = By.xpath(".//button[contains(@class,'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa')]");
    private final By registerButton = By.xpath(".//a[contains(text(),'Зарегистрироваться')]");
    private final WebDriver driver;

    public LoginForm(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Clicking registration button in login form")
    public void clickRegisterBtn(){
        new WebDriverWait(driver, EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(registerButton));
        driver.findElement(registerButton).click();
    }

    @Step("Filling email field in login form")
    public void fillEmailField(String email){
        new WebDriverWait(driver, EnvConfig.BASE_DRIVER_WAIT_TIME);
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Filling password field in login form")
    public void fillPasswordField(String password){
        new WebDriverWait(driver, EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Clicking enter button in login form")
    public void clickEnterButton(){
        new WebDriverWait(driver,EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(enterButton));
        driver.findElement(enterButton).click();
    }

    @Step("Check that login form appear")
    public void checkLoginFormAppear(){
        new WebDriverWait(driver, EnvConfig.BASE_DRIVER_WAIT_TIME)
                .until(ExpectedConditions.elementToBeClickable(recoveryPassButton));
        assert driver.findElement(recoveryPassButton).isDisplayed();
    }

    @Step("Clicking recovery password button in login form")
    public void clickRecoveryPasswordButton(){
        new WebDriverWait(driver,EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(recoveryPassButton));
        driver.findElement(recoveryPassButton).click();
    }
}