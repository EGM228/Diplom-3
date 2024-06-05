import POM.LoginForm;
import POM.MainPage;
import POM.PersonalAccount;
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

public class PersonalAccountTest {

    private final UserClient client = new UserClient();
    private final DriverFactory driverFactory = new DriverFactory();
    private String accessToken;

    @Before
    public void startUp(){
        driverFactory.initDriver();
    }

    @DisplayName("Testing that user can enter personal account")
    @Test
    public void entrancePersonalAccount() throws InterruptedException {
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

        main.clickPersonalAccBtn();

        PersonalAccount persona = new PersonalAccount(driver);
        persona.checkPersonalAccountLoaded();

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        System.out.println(accessToken);
    }

    @DisplayName("Testing that user can go to the constructor from personal account by clicking logo")
    @Test
    public void goingToConstructorFromCabByLogoTest(){
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
        main.clickPersonalAccBtn();

        PersonalAccount persona = new PersonalAccount(driver);
        persona.clickLogo();

        main.waitForMainPageLoad();

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        System.out.println(accessToken);
    }

    @DisplayName("Testing that user can go to the constructor from personal account by clicking constructor button")
    @Test
    public void goingToConstructorFromCabByConstructorButtonTest(){
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
        main.clickPersonalAccBtn();

        PersonalAccount persona = new PersonalAccount(driver);
        persona.clickConstructor();

        main.waitForMainPageLoad();

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        System.out.println(accessToken);
    }

    @DisplayName("Testing exiting from account")
    @Test
    public void exitFromPersonalAccountTest(){
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

        main.clickPersonalAccBtn();
        PersonalAccount persona = new PersonalAccount(driver);
        persona.clickExitButton();

        login.checkLoginFormAppear();
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