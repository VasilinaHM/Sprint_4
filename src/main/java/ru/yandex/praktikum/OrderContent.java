package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrderContent {
    private WebDriver driver;
    private By OrderdateL = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div/div/input");//локатор для выбора даты заказа
    private By rent = By.className("Dropdown-placeholder");//локатор для выбора срока аренды
    private By rented = By.className("Dropdown-placeholder is-selected");//локатор для выбора срока аренды

    private By colorBlack = By.xpath("//*[@id=\"black\"]");//локатор для выбора черного цвета
    private By colorGray = By.xpath("//*[@id=\"black\"]");//локатор для выбора серого цвета
    private By orderСomment = By.xpath("//*/div/div[2]/div[2]/div[4]/input");//локатор для поля комментарий курьеру
    private By buttonOrder = By.xpath("//*/div/div[2]/div[3]/button[2]");//локатор для кнопки заказа в самом заказе


    public  OrderContent(WebDriver driver)
    {
        this.driver =driver;
    }


    private void SetDataOrder(String OrderData)
    {
        driver.findElement(OrderdateL).sendKeys(OrderData, Keys.chord(Keys.ENTER));

    }

    private void SetRent(String OrderRentk )
    {

        driver.findElement(rent).click();
        List<WebElement> allOptions = driver.findElements(By.className("Dropdown-option"));
        for (WebElement el : allOptions)
        {

            if (el.getText().contains(OrderRentk))
            {

                Actions action=new Actions(driver);
                action.contextClick(el).perform();
                break;

            }
       }
    }

    private void SetColor(String OrderColor)
    {
        WebElement element = driver.findElement(By.id(OrderColor));
        element.click();

    }

    private void SetComment(String OrderComment)
    {
        WebElement element = driver.findElement(orderСomment);
        element.sendKeys(OrderComment);

    }
    public void clickInButtonFinal () {
        driver.findElement(buttonOrder).click();//найти кнопку далее и нажать

        //подождать когда откроется окно с подтверждением заказа
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Modal__YZ-d3")));

//найти кнопку Да и кликнуть

        driver.findElement(By.xpath("//*/div/div[2]/div[5]/div[2]/button[2]")).click();

//подождать когда откроется окно с номером заказа
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("")));
    }

    public void InputStep2(String OrderData, String OrderRent, String OrderColor, String OrderComment)
    {
        SetDataOrder(OrderData);
        SetRent(OrderRent);
        SetColor(OrderColor);
        SetComment(OrderComment);
        clickInButtonFinal();
    }
}
