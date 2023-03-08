package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestQuestions {

    private static WebDriver driver;
    private static MainPage mainPage;

    private final String question;
    private final String ancherQuestion;
    private final String answer;
    private final String ancherAnswer;

    public TestQuestions(String question, String ancherQuestion, String answer, String ancherAnswer) {
        this.question = question;
        this.ancherQuestion = ancherQuestion;
        this.answer = answer;
        this.ancherAnswer = ancherAnswer;
    }

    @Parameterized.BeforeParam
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        mainPage.loadMainPageAccordion();
    }

    @Parameterized.Parameters
    public static String [][] getQuestionAndAnswers() {
        return new String[][]{
                {"Сколько это стоит? И как оплатить?", "accordion__heading-0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "accordion__panel-0"},
                {"Хочу сразу несколько самокатов! Так можно?", "accordion__heading-1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "accordion__panel-1"},
                {"Как рассчитывается время аренды?", "accordion__heading-2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "accordion__panel-2"},
                {"Можно ли заказать самокат прямо на сегодня?", "accordion__heading-3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "accordion__panel-3"},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "accordion__heading-4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "accordion__panel-4"},
                {"Вы привозите зарядку вместе с самокатом?", "accordion__heading-5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "accordion__panel-5"},
                {"Можно ли отменить заказ?", "accordion__heading-6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "accordion__panel-6"},
                {"Я жизу за МКАДом, привезёте?", "accordion__heading-7", "Да, обязательно. Всем самокатов! И Москве, и Московской области.", "accordion__panel-7"}
        };
    }

    @Test
    public void testQA(){
        assertEquals(question, mainPage.clickAccordion(ancherQuestion));
        assertEquals(answer, mainPage.clickAccordion(ancherAnswer));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
