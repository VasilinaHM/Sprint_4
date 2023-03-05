import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.MainPage;
import ru.yandex.praktikum.OrderPage;

public class TestOrder {
   public WebDriver driver2;
    @Test

    @Before

  public   void setupChromeDriver() throws InterruptedException {
        ChromeDriver().setup();
        driver2 = new ChromeDriver();
        options();
        driver2.get("https://qa-scooter.praktikum-services.ru/");

       testOrder();
    }

    void options() {
        driver2.manage().window().maximize();
    }



    public String [] UserData = new String[]{
            "Кириллов",
            "Максим",
            "Бориса Пастернака 17 кв 152",
            "Планерная",
            "+79165446662"
    };

    public void testOrder() throws InterruptedException {

        MainPage objMP;

        {
            objMP = new MainPage(driver2);

        }


       new WebDriverWait(driver2, 5)
              .until(ExpectedConditions.visibilityOfElementLocated(By.className("Button_Button__ra12g")));

        objMP.clickOrderButton();
        OrderPage FirstOrderPage = new OrderPage (driver2);
        FirstOrderPage.InputFormData(UserData[0],UserData[1],UserData[2],UserData[3],UserData[4]);

        // подождать пока появиться страница Про аренду
        new WebDriverWait(driver2, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Header__BZXOb")));

        OrderContent SecondOrderPage = new OrderContent (driver2); //создали обьект класса OrderContent
//        SecondOrderPage.SecondInputFormData(UserData[0],UserData[1],UserData[2],UserData[3],UserData[4]);

        //подождать когда кнопка заказать станет кликабельной
        new WebDriverWait(driver2, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*/div/div[2]/div[3]/button[2]")));

        //подождать когда откроется окно с подтверждением заказа
        new WebDriverWait(driver2, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Modal__YZ-d3")));

        //найти кнопку Да и кликнуть

        driver2.findElement(By.xpath("//*/div/div[2]/div[5]/div[2]/button[2]")).click();

        //подождать когда откроется окно с номером заказа
        new WebDriverWait(driver2, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("")));

    }
    @After
    public void teardown() {
        driver2.quit();
    }

}
