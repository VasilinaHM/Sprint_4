package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SecondOrderContent {
    private WebDriver driver;
    private final By OrderdateL = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div/div/input");//локатор для выбора даты заказа
    private final By rent = By.className("Dropdown-placeholder");//локатор для выбора срока аренды
    private final By orderComment = By.xpath("//*/div/div[2]/div[2]/div[4]/input");//локатор для поля комментарий курьеру
    private final By buttonOrder = By.xpath("//*/div/div[2]/div[3]/button[2]");//локатор для кнопки заказа в самом заказе

    public SecondOrderContent(WebDriver driver) {
        this.driver = driver;
    }

    private void setDataOrder(String OrderData) {
        driver.findElement(OrderdateL).sendKeys(OrderData, Keys.chord(Keys.ENTER));
    }

    private void setRent(String OrderRent) {
        driver.findElement(rent).click();
        List<WebElement> allOptions = driver.findElements(By.className("Dropdown-option"));
        for (WebElement el : allOptions) {
            if (el.getText().contains(OrderRent)) {
                Actions action = new Actions(driver);
                action.contextClick(el).perform();
                break;

            }
        }
    }

    private void setColor(String orderColor) {
        WebElement element = driver.findElement(By.id(orderColor));
        element.click();
    }

    private void setComment(String orderComment) {
        WebElement element = driver.findElement(this.orderComment);
        element.sendKeys(orderComment);
    }

    public void clickInButtonFinal() {
        driver.findElement(buttonOrder).click();//найти кнопку далее и нажать

        //подождать когда откроется окно с подтверждением заказа
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Modal__YZ-d3")));

        //найти кнопку Да и кликнуть
        driver.findElement(By.xpath("//*/div/div[2]/div[5]/div[2]/button[2]")).click();

        //подождать когда откроется окно Заказ оформлен
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_ModalHeader__3FDaJ")));
    }

    public void finishOrderPage(String orderData, String orderRent, String orderColor, String orderComment) {
        setDataOrder(orderData);
        setRent(orderRent);
        setColor(orderColor);
        setComment(orderComment);
        clickInButtonFinal();
    }
}
