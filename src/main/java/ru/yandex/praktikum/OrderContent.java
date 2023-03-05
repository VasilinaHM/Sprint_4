package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderContent {
    private WebDriver driver;
    private final By date = By.xpath("//*/div/div[2]/div[2]/div[1]/div/div/input");//локатор для выбора даты заказа
    private final By rent = By.xpath("//*/div/div[2]/div[2]/div[2]/div/div[1]");//локатор для выбора срока аренды
    private final By color = By.xpath("//*/div/div[2]/div[2]/div[3]");//локатор для выбора цвета
    private final By orderComment = By.xpath("//*/div/div[2]/div[2]/div[4]/input");//локатор для поля комментарий курьеру
    private final By buttonOrder = By.xpath("//*/div/div[2]/div[3]/button[2]");//локатор для кнопки заказа в самом заказе

    private String dateOrder;
    private String comment;
    public void setDateOrder (String dateOrder){
        driver.findElement(date).sendKeys(dateOrder);       // String format = "dd.mm.yyyy"
    }


    public void setOrderComment (String comment){             //комментрай курьеру
        driver.findElement(orderComment).sendKeys(comment);
    }




    public void  SecondInputFormData( )
            throws InterruptedException {
        setDateOrder(dateOrder);
        //setRentOrder();
        //setColor();
        setOrderComment(comment);

    }





}





