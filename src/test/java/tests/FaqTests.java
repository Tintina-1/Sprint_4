package tests;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pages.MainPage;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FaqTests extends BaseTest {
        private final int questionIndex;
        private final String expectedAnswer;
        // Конструктор для параметризированного теста
        public FaqTests(int questionIndex, String expectedAnswer) {
            this.questionIndex = questionIndex;
            this.expectedAnswer = expectedAnswer;
        }
        // Параметры для тестов
        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                    {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                    {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                    {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                    {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                    {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                    {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                    {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
            });
        }

        @Test
        public void checkFaqAnswerOpens() {
            // Создаем объект страницы FAQ
            MainPage.FaqPage faqPage = new MainPage.FaqPage(driver);
            faqPage.acceptCookies();
            faqPage.scrollToBottom();

            // Клик по вопросу с заданным индексом
            faqPage.clickFaqQuestion(questionIndex);

            // Проверка, что текст ответа отображается
            Assert.assertTrue("Ответ не отображается!", faqPage.isFaqAnswerVisible(questionIndex));

            // Проверка содержимого ответа
            String actualAnswer = faqPage.getFaqAnswerText(questionIndex);
            Assert.assertEquals("Текст ответа не совпадает!", expectedAnswer, actualAnswer);
        }
    }

