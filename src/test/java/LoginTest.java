import POM.LoginForm;
import POM.MainPage;
import POM.RecoveryPasswordForm;
import POM.RegisterForm;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.User;
import org.example.UserClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;

public class LoginTest {

    private final UserClient client = new UserClient();
    private final DriverFactory driverFactory = new DriverFactory();
    private String accessToken;

    @Before
    public void startUp(){
        driverFactory.initDriver();
    }

    @DisplayName("Testing login from enter to account button on main page")
    @Test
    public void loginFromEnterToAccountButtonTest(){
        var user = User.random();
        ValidatableResponse createResponse = client.createUser(user);
        client.checkCreatedSuccessfully(createResponse);

        WebDriver driver = driverFactory.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        main.clickLoginToAccBtn();

        LoginForm login = new LoginForm(driver);
        login.fillEmailField(user.getEmail());
        login.fillPasswordField(user.getPassword());
        login.clickEnterButton();

        main.waitForMainPageLoad();

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        System.out.println(accessToken);
    }

    @DisplayName("Testing login from personal account button on main page")
    @Test
    public void loginFromPersonalAccountButtonTest(){
        var user = User.random();
        ValidatableResponse createResponse = client.createUser(user);
        client.checkCreatedSuccessfully(createResponse);

        WebDriver driver = driverFactory.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        main.clickPersonalAccBtn();

        LoginForm login = new LoginForm(driver);
        login.fillEmailField(user.getEmail());
        login.fillPasswordField(user.getPassword());
        login.clickEnterButton();

        main.waitForMainPageLoad();

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        System.out.println(accessToken);
    }

    @DisplayName("Testing login from registration form")
    @Test
    public void loginFromRegistrationFormTest(){
        var user = User.random();
        ValidatableResponse createResponse = client.createUser(user);
        client.checkCreatedSuccessfully(createResponse);

        WebDriver driver = driverFactory.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        main.clickLoginToAccBtn();

        LoginForm login = new LoginForm(driver);
        login.clickRegisterBtn();

        RegisterForm register = new RegisterForm(driver);
        register.clickEnterButton();

        login.fillEmailField(user.getEmail());
        login.fillPasswordField(user.getPassword());
        login.clickEnterButton();

        main.waitForMainPageLoad();

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        System.out.println(accessToken);
    }

    @DisplayName("Testing login from password recovery form")
    @Test
    public void loginFromPasswordRecoveryFormTest(){
        var user = User.random();
        ValidatableResponse createResponse = client.createUser(user);
        client.checkCreatedSuccessfully(createResponse);

        WebDriver driver = driverFactory.getDriver();
        MainPage main = new MainPage(driver);
        main.open();
        main.clickLoginToAccBtn();

        LoginForm login = new LoginForm(driver);
        login.clickRecoveryPasswordButton();

        RecoveryPasswordForm recovery = new RecoveryPasswordForm(driver);
        recovery.clickEnterButton();

        login.fillEmailField(user.getEmail());
        login.fillPasswordField(user.getPassword());
        login.clickEnterButton();

        main.waitForMainPageLoad();

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        System.out.println(accessToken);
    }

    @After
    public void tearDown(){
        client.deleteUser(accessToken);
    }

    @After
    public void tearDown2(){
        driverFactory.getDriver().quit();
    }
}