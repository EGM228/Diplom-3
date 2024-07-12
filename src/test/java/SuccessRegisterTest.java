import POM.LoginForm;
import POM.MainPage;
import POM.RegisterForm;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.UserClient;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;

@RunWith(Parameterized.class)
public class SuccessRegisterTest {

    private final UserClient client = new UserClient();
    private final DriverFactory driverFactory = new DriverFactory();
    private final String name;
    private final String email;
    private final String password;
    private String accessToken;

    public SuccessRegisterTest(String password, String name, String email) {
        this.password = password;
        this.name = name;
        this.email = email;
    }

    @Before
    public void startUp(){
        driverFactory.initDriver();
    }

    @Parameterized.Parameters
    public static Object[][] dataForTests() {
        return new Object[][] {
                { "123456", RandomStringUtils.randomAlphabetic(7), RandomStringUtils.randomAlphabetic(7)+"@yandex.ru"},
                { "1234567", RandomStringUtils.randomAlphabetic(7), RandomStringUtils.randomAlphabetic(7)+"@yandex.ru"},
        };
    }

    @DisplayName("Testing that user can register when password is more than 5 figures")
    @Test
    public void successRegisterPassMoreThen5Test() {
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

        login.checkLoginFormAppear(); // end of test then prepare to delete user

        login.fillEmailField(email);
        login.fillPasswordField(password);
        login.clickEnterButton();

        main.waitForMainPageLoad();

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        System.out.println(accessToken);
    }

    @After
    public void tearDown2(){
        client.deleteUser(accessToken);
    }

    @After
    public void tearDown(){
        driverFactory.getDriver().quit();
    }
}