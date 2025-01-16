package tests;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.OrderPage;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class OrderTests extends BaseTest {

    private String name;
    private String surname;
    private String address;
    private String metroStation;
    private String phone;
    private String date;
    private String period;

    public OrderTests(String name, String surname, String address, String metroStation, String phone, String date, String period) {
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

    @Test
    public void testOrderFromTopButton() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        // Нажимаем верхнюю кнопку "Заказать"
        mainPage.clickOrderButtonTop();

        // Заполняем форму
        orderPage.fillOrderForm(name, surname, address, metroStation, phone, date, period);

        // Проверяем успешность заказа
        Assert.assertTrue(mainPage.isOrderConfirmationDisplayed());
        // Вызов тестируемого метода getSuccessMessage()
        String successMessage = (orderPage.getSuccessMessage());

        // Проверка, что возвращаемый текст совпадает с ожидаемым
        String expectedText = "Заказ оформлен";
        Assert.assertTrue("Сообщение должно содержать 'Заказ оформлен'", successMessage.contains("Заказ оформлен"));
    }

    @Test
    public void testOrderFromBottomButton() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        // Нажимаем нижнюю кнопку "Заказать"
        mainPage.clickOrderButtonBottom();

        // Заполняем форму
        orderPage.fillOrderForm(name, surname, address, metroStation, phone, date, period);

        // Проверяем успешность заказа
        Assert.assertTrue(mainPage.isOrderConfirmationDisplayed());
        // Вызов тестируемого метода getSuccessMessage()
        String successMessage = (orderPage.getSuccessMessage());

        // Проверка, что возвращаемый текст совпадает с ожидаемым
        String expectedText = "Заказ оформлен";
        Assert.assertTrue("Сообщение должно содержать 'Заказ оформлен'", successMessage.contains("Заказ оформлен"));
    }
}

