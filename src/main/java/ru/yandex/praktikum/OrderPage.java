package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class OrderPage {

    private WebDriver driver;
    private final By name = By.xpath("//*/div/div[2]/div[2]/div[1]/input");//локатор для поля имя
    private final By surname = By.xpath("//*/div/div[2]/div[2]/div[2]/input");//локатор для поля фамилия
    private final By address = By.xpath("//*/div/div[2]/div[2]/div[3]/input");//локатор для поля адрес
    private final By metro = By.className("select-search__value");// локатор для поля метро
    private final By metroInput = By.className("select-search__input");// локатор для выбора станции метро
    private final By phone = By.xpath("//*/div/div[2]/div[2]/div[5]/input");//локатор для поля телефон
    private final By buttonNext = By.xpath("//*/div/div[2]/div[3]/button");//локатор для кнопки Далее


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUserName(String userName) {
        driver.findElement(name).sendKeys(userName);
    }

    public void setSurname(String userSurname) {
        driver.findElement(surname).sendKeys(userSurname);
    }

    public void setUserAddress(String userAddress) {
        driver.findElement(address).sendKeys(userAddress);
    }

    public void selectStationMetro(String stationName) {
        driver.findElement(metro).click();
        driver.findElement(metroInput).sendKeys(stationName, Keys.chord(Keys.DOWN, Keys.ENTER));
    }

    public void setPhone(String userPhone) {
        driver.findElement(phone).sendKeys(userPhone);
    }


    public void clickSignInButton() {
        driver.findElement(buttonNext).click();//найти кнопку далее и нажать
    }


    public void newOrderPage(String userName, String userSurname, String userAddress, String stationName, String
            userPhone) {
        setUserName(userName);
        setSurname(userSurname);
        setUserAddress(userAddress);
        selectStationMetro(stationName);
        setPhone(userPhone);
        clickSignInButton();
    }

}

