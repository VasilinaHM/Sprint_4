package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
@RunWith(Parameterized.class)
public class TestOrder {
    private WebDriver driver;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String orderDate;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    public TestOrder(String firstName, String lastName, String address, String metroStation, String phone, String orderDate, String rentalPeriod, String color, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.orderDate = orderDate;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

    }

    @Parameterized.Parameters
    public static String [][] orderData() {
        return new String[][]{
                {"Катя", "Ежова", "Вавилова 22", "Планерная", "89998887766", "09.03.2023", "двое суток", "black", "рандомный коммент"},
                {"Егор", "Самсонов", "Мира 122", "Ростокино", "89993332211", "25.04.2023", "трое суток", "black", "оставить у двери"}
        };
    }

    @Test
    public void orderScooterFirstButton() {
        MainPage pressButton = new MainPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Button_Button__ra12g")));

        //нажать кнопку Заказать верхняя
        pressButton.clickOrderButton();
        //подождать пока появится страница
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Content__bmtHS")));

        OrderPage objOrderPage = new OrderPage(driver);
        // заполнили 1 форму заказа
        objOrderPage.newOrderPage(firstName, lastName, address, metroStation, phone);

        // подождать пока появиться страница Про аренду
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Header__BZXOb")));

        SecondOrderContent objSecondOrderContent = new SecondOrderContent(driver);
        objSecondOrderContent.finishOrderPage(orderDate, rentalPeriod, color, comment);
        //подождать когда кнопка заказать станет кликабельной
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*/div/div[2]/div[3]/button[2]")));
        //нажать кнопку закать
        driver.findElement(By.xpath("//*/div/div[2]/div[3]/button[2]")).click();

        //подождать когда откроется окно с подтверждением заказа
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Modal__YZ-d3")));

        //найти кнопку Да и кликнуть
        driver.findElement(By.xpath("//*/div/div[2]/div[5]/div[2]/button[2]")).click();

        //подождать когда откроется окно с заказ оформлен
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_ModalHeader__3FDaJ")));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}

