package tests;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        // Инициализация драйвера и открытие браузера
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        // Закрытие браузера после тестов
            driver.quit();
    }
}
