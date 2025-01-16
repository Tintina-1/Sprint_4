package ru.yandex.praktikum.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage {

    // Локаторы для кнопок "Заказать"
    private By orderButtonTop = By.className("Button_Button__ra12g");
    private By orderButtonBottom = By.xpath("//button[text()='Заказать' and contains(@class,'Button_Middle')]");

    // Локатор для всплывающего окна с подтверждением заказа
    private By orderConfirmationPopup = By.className("Order_ModalHeader__3FDaJ");

    private WebDriver driver;
    private WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    // Нажать на кнопку "Заказать" вверху экрана
    public void clickOrderButtonTop() {
        WebElement topOrderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(orderButtonTop));
        topOrderButton.click();
    }

    // Прокрутить до элемента и нажать на кнопку "Заказать" внизу экрана
    public void clickOrderButtonBottom() {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("App_CookieButton__3cvqF")));
            cookieButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка принятия куков не найдена или уже принята.");
        }

        // Прокручиваем страницу до кнопки и кликаем по ней
        WebElement orderButton = driver.findElement(orderButtonBottom);
        scrollToElement(orderButton);
        orderButton.click();
    }

    // Проверка всплывающего окна с подтверждением заказа
    public boolean isOrderConfirmationDisplayed() {
        return driver.findElement(orderConfirmationPopup).isDisplayed();
    }

    // Метод для прокрутки до элемента
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.elementToBeClickable(element)); // Ожидание, пока элемент станет кликабельным
    }

    public static class FaqPage {
        private WebDriver driver;

        private By faqQuestions = By.className("accordion__button");
        private By faqAnswers = By.className("accordion__panel");

        public FaqPage(WebDriver driver) {
            this.driver = driver;
        }

        // Получение всех вопросов
        public List<WebElement> getFaqQuestions() {
            return driver.findElements(faqQuestions);
        }

        // Получение всех ответов
        public List<WebElement> getFaqAnswers() {
            return driver.findElements(faqAnswers);
        }

        // Клик на вопрос по индексу
        public void clickFaqQuestion(int index) {
            getFaqQuestions().get(index).click();
        }

        // Получение текста ответа по индексу
        public String getFaqAnswerText(int index) {
            return getFaqAnswers().get(index).getText();
        }

        // Проверка видимости ответа
        public boolean isFaqAnswerVisible(int index) {
            return getFaqAnswers().get(index).isDisplayed();
        }

        // Нажатие на кнопку принятия куки
        public void acceptCookies() {
            By acceptCookiesButton = By.id("rcc-confirm-button");
            driver.findElement(acceptCookiesButton).click();
        }

        // Скроллинг до конца страницы
        public void scrollToBottom() {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        }
    }
}
