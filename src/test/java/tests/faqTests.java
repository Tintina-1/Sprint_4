package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.pages.mainPage;
import ru.yandex.praktikum.pages.mainPage.FaqPage;

public class faqTests {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Инициализация драйвера Chrome
        System.setProperty("webdriver.chrome.driver", "/home/inna/Downloads/Webdriver/bin/chromedriver"); // Correct for Linux
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

    }

    @Test
    public void checkFaqAnswerOpens() {
        // Создаем объект страницы FAQ
        mainPage.FaqPage faqPage = new mainPage.FaqPage(driver);


        faqPage.acceptCookies();
        faqPage.scrollToBottom();

        // Индекс вопроса, который будем проверять (например, 0 для первого вопроса)
        int questionIndex = 0;

        // Клик по первому вопросу
        faqPage.clickFaqQuestion(questionIndex);

        // Проверка, что текст ответа отображается
        Assert.assertTrue("Ответ не отображается!", faqPage.isFaqAnswerVisible(questionIndex));

        // Можно дополнительно проверить содержимое ответа, если оно известно
        String expectedAnswer = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
        String actualAnswer = faqPage.getFaqAnswerText(questionIndex);
        Assert.assertEquals("Текст ответа не совпадает!", expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
            driver.quit();
    }
}
