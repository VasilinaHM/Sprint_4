package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final By orderButton = By.className("Button_Button__ra12g");// локатор для кнопки Заказать вверху страницы
    private final By secondOrderButton = By.xpath("/html/body/div/div/div[1]/div[4]/div[2]/div[5]/button");// локатор для кнопки Заказать внизу страницы
    private final By orderContent = By.className("Order_Content__bmtHS");// локатор для формы заказа
    private final By AccordionQuestion = By.xpath(".//div[@class='Home_FAQ__3uVm4']"); // локатор для аккордиона
    private final By CookiesButtoon = By.className("App_CookieButton__3cvqF");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loadMainPageAccordion() {
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();
        new WebDriverWait(this.driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(AccordionQuestion));
        WebElement element = driver.findElement(AccordionQuestion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public String clickAccordion(String Question) {
        driver.findElement(By.id(Question)).click(); //нажать на элемент аккордиона
        return driver.findElement(By.id(Question)).getText();
    }

    public void clickOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
        WebElement element = driver.findElement(orderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}