import POM.LoginForm;
import POM.MainPage;
import POM.RegisterForm;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class NotSuccessRegisterTest {

    private final DriverFactory driverFactory = new DriverFactory();
    private String name = RandomStringUtils.randomAlphabetic(7);
    private String email = RandomStringUtils.randomAlphabetic(7)+"@yandex.ru";
    private String password = "12345";

    @Before
    public void startUp(){
        driverFactory.initDriver();
    }

    @DisplayName("Testing that user can not register when password is less than 6 figures")
    @Test
    public void notSuccessRegisterPassLessThen6Test(){
        WebDriver driver = driverFactory.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        main.clickLoginToAccBtn();

        LoginForm login = new LoginForm(driver);
        login.clickRegisterBtn();

        RegisterForm register = new RegisterForm(driver);
        register.fillEmailFieldReg(email);
        register.fillPasswordFieldReg(password);
        register.fillNameFieldReg(name);
        register.clickRegistrationButton();
        register.checkErrorSignAppear();
    }

    @After
    public void tearDown(){
        driverFactory.getDriver().quit();
    }
}