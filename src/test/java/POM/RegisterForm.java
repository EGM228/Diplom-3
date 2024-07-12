package POM;

import io.qameta.allure.Step;
import org.example.EnvConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterForm {

    private final By enterButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");
    private final By errorSign = By.xpath(".//p[@class='input__error text_type_main-default']");
    private final By nameField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    private final By emailField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    private final By passwordField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input");
    private final By registerBtn = By.xpath(".//button[contains(@class,'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa')]");
    private final WebDriver driver;

    public RegisterForm(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Filling name field in register form")
    public void fillNameFieldReg(String name){
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Filling email field in register form")
    public void fillEmailFieldReg(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("filling password field in register form")
    public void fillPasswordFieldReg(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Clicking register button")
    public void clickRegistrationButton(){
        driver.findElement(registerBtn).click();
    }

    @Step("Checking that error sing appear")
    public void checkErrorSignAppear(){
        assert driver.findElement(errorSign).isDisplayed();
    }

    @Step("Clicking enter button in register form")
    public void clickEnterButton(){
        new WebDriverWait(driver, EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(enterButton));
        driver.findElement(enterButton).click();
    }
}