package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class orderPage {

    // Поля формы заказа

    //Поле для ввода имени
    private By nameField = By.xpath("//input[@placeholder='* Имя']");

    //Поле для ввода фамилии
    private By surnameField = By.xpath("//input[@placeholder='* Фамилия']");

    //Поле для ввода адреса
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    //Поле для выбора станции метро
    private By metroStationField = By.xpath("//input[@placeholder='* Станция метро']");

    //Поле для ввода номера телефона
    private By phoneNumberField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка "Далее" на первом шаге формы
    private By nextButton = By.xpath("//button[text()='Далее']");

    // Выбор даты для аренды
    private By dateField = By.cssSelector("#root > div > div.Order_Content__bmtHS > div.Order_Form__17u6u > div.Order_MixedDatePicker__3qiay > div.react-datepicker-wrapper > div > input");

    // Метод для выбора срока аренды
    private By rentalPeriodDropdown = By.className("Dropdown-placeholder");

    // Кнопка подтверждения заказа
    private By orderButton = By.cssSelector("#root > div > div.Order_Content__bmtHS > div.Order_Buttons__1xGrp > button:nth-child(2)");

    // Кнопка подтверждения согласия
    private By confirmButton = By.xpath(" //*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button[2]");

    // Сообщение об успешном заказе
    private By orderSuccessMessage = By.className("Order_ModalHeader__3FDaJ");

    // Конструктор
    private WebDriver driver;

    public orderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы для заполнения формы

    // Метод для заполнения поля Имя
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    // Метод для заполнения поля Фамилия
    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    // Метод для заполнения поля Адрес
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    // Метод для выбора станции метро
    public void setMetroStation(String metroStation) {
        // Введите название станции метро
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement metroInputReady = wait.until(ExpectedConditions.elementToBeClickable(metroStationField));
        metroInputReady.sendKeys(metroStation);

        // Ожидание появления списка вариантов и выбор нужного элемента
        By stationOptions = By.className("select-search__select");
        wait.until(ExpectedConditions.visibilityOfElementLocated(stationOptions));
        boolean stationFound = false;

        while (!stationFound) {
            // Получение всех элементов в выпадающем списке
            for (WebElement station : driver.findElements(stationOptions)) {
                // Получение названия станции
                String stationName = station.getText();

                // Если нужная станция найдена, кликаем по ней
                if (stationName.contains(metroStation)) {
                    station.click();
                    stationFound = true;
                    break; // Выход из цикла после выбора станции
                }
            }

            if (!stationFound) {
                // Если станция не найдена, прокручиваем список, отправляя ARROW_DOWN на поле ввода
                metroInputReady.sendKeys(Keys.ARROW_DOWN);
            }
        }

    }

    // Метод для заполнения телефона
    public void setPhoneNumber(String phone) {
        driver.findElement(phoneNumberField).sendKeys(phone);
    }

    // Клик по кнопке "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Метод для выбора даты аренды
    public void setDate(String date) {
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(By.cssSelector("body")).click();
    }

    // Метод для выбора длительности аренды
    public void setRentalPeriod(String period) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement periodInputReady = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodDropdown));
        periodInputReady.click();

        By rentalPeriodOption = By.xpath("//div[contains(text(), '" + period + "')]");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodOption));
        option.click();
        driver.findElement(By.cssSelector("body")).click();

    }

    // Клик по кнопке "Заказать"
    public void clickOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement orderButtonReady = wait.until(ExpectedConditions.elementToBeClickable(orderButton));
        orderButtonReady.click();

    }

    // Клик по кнопке "Да", подтверждение заказа
    public void confirmOrder() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement confirmationModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Modal__YZ-d3")));
        WebElement confirmYesButton = confirmationModal.findElement(confirmButton);
        confirmYesButton.click();

    }

    // Получение сообщения об успешном заказе
    public String getSuccessMessage() {
        return driver.findElement(orderSuccessMessage).getText();
    }



    // Заполнение всех полей формы заказа
    public void fillOrderForm(String name, String surname, String address, String metroStation, String phone, String date, String period) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetroStation(metroStation);
        setPhoneNumber(phone);
        clickNextButton();
        setDate(date);
        setRentalPeriod(period);
        clickOrderButton();
        confirmOrder();
        getSuccessMessage();
    }
}
