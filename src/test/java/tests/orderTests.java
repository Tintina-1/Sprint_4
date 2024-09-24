package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.praktikum.pages.mainPage;
import ru.yandex.praktikum.pages.orderPage;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class orderTests {

    private WebDriver driver;
    private String name;
    private String surname;
    private String address;
    private String metroStation;
    private String phone;
    private String date;
    private String period;

    public orderTests(String name, String surname, String address, String metroStation, String phone, String date, String period) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.period = period;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "ул. Пушкина, дом 12", "Бульвар Рокоссовского", "+79001234567", "01.10.2024", "двое суток"},
                {"Петр", "Петров", "ул. Ленина, дом 15", "Черкизовская", "+79007654321", "05.10.2024", "трое суток"}
        });
    }

    @Before
    public void setUp() {
        // Инициализация драйвера Chrome
        System.setProperty("webdriver.chrome.driver", "/home/inna/Downloads/Webdriver/bin/chromedriver"); // Correct for Linux
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

    }

    @Test
    public void testOrderFromTopButton() {
        mainPage MainPage = new mainPage(driver);
        orderPage OrderPage = new orderPage(driver);

        // Нажимаем верхнюю кнопку "Заказать"
        MainPage.clickOrderButtonTop();

        // Заполняем форму
        OrderPage.fillOrderForm(name, surname, address, metroStation, phone, date, period);

        // Проверяем успешность заказа
        Assert.assertTrue(MainPage.isOrderConfirmationDisplayed());
    }

    @Test
    public void testOrderFromBottomButton() {
        mainPage MainPage = new mainPage(driver);
        orderPage OrderPage = new orderPage(driver);

        // Нажимаем нижнюю кнопку "Заказать"
        MainPage.clickOrderButtonBottom();

        // Заполняем форму
        OrderPage.fillOrderForm(name, surname, address, metroStation, phone, date, period);

        // Проверяем успешность заказа
        Assert.assertTrue(MainPage.isOrderConfirmationDisplayed());
    }

    @After
    public void teardown() {
            driver.quit();
    }
}

