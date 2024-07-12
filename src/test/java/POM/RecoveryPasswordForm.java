package POM;

import io.qameta.allure.Step;
import org.example.EnvConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecoveryPasswordForm {

    private final By enterButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");
    private final WebDriver driver;

    public RecoveryPasswordForm(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Clicking enter button in password recovery form")
    public void clickEnterButton(){
        new WebDriverWait(driver, EnvConfig.BASE_DRIVER_WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(enterButton));
        driver.findElement(enterButton).click();
    }
}