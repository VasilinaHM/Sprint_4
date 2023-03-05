import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.MainPage;
import static org.junit.Assert.assertEquals;

public class TestMain {
   public WebDriver driver2;
    @Test

    @Before

  public   void setupChromeDriver() throws InterruptedException {
        ChromeDriver().setup();
        driver2 = new ChromeDriver();
        options();
        driver2.get("https://qa-scooter.praktikum-services.ru/");

       testMain();
    }

    void options() {
        driver2.manage().window().maximize();
    }

    public String [][] QuAn = new String[][]{
            {"Сколько это стоит? И как оплатить?","accordion__heading-0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.","accordion__panel-0"},
            {"Хочу сразу несколько самокатов! Так можно?","accordion__heading-1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.","accordion__panel-1"},
            {"Как рассчитывается время аренды?","accordion__heading-2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.","accordion__panel-2"},
            {"Можно ли заказать самокат прямо на сегодня?","accordion__heading-3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.","accordion__panel-3"},
            {"Можно ли продлить заказ или вернуть самокат раньше?","accordion__heading-4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.","accordion__panel-4"},
            {"Вы привозите зарядку вместе с самокатом?","accordion__heading-5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.","accordion__panel-5"},
            {"Можно ли отменить заказ?", "accordion__heading-6","Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.","accordion__panel-6"},
            {"Я жизу за МКАДом, привезёте?", "accordion__heading-7","Да, обязательно. Всем самокатов! И Москве, и Московской области.","accordion__panel-7"}
    };




    public void testMain() throws InterruptedException {

        MainPage objMP;

        {
            objMP = new MainPage(driver2);

        }
       objMP.loadMainPageAccordion();

     for(int i=0;i<QuAn.length; i++)
        {
            assertEquals(QuAn[i][0],  objMP.clickAccordion(QuAn[i][1]));
            assertEquals(QuAn[i][2],  objMP.clickAccordion(QuAn[i][3]));
    //        String xxx = objMP.clickAccordion(QuAn[i][3]);
        }



    }
    @After
    public void teardown() {
        driver2.quit();
    }

}
