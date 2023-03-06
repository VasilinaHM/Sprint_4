package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;
    private final By name = By.xpath("//*/div/div[2]/div[2]/div[1]/input");//локатор для поля имя
    private final By surname = By.xpath("//*/div/div[2]/div[2]/div[2]/input");//локатор для поля фамилия
    private final By address = By.xpath("//*/div/div[2]/div[2]/div[3]/input");//локатор для поля адрес
    private final By metro = By.className("select-search");// локатор для поля метро
    private final By selectedMetroStation = By.className("select-search__input");
    private final By currentStMetro = By.className("select-search__select");
    private final By phone = By.xpath("//*/div/div[2]/div[2]/div[5]/input");//локатор для поля телефон
    private final By buttonNext = By.xpath("//*/div/div[2]/div[3]/button");//локатор для кнопки Далее
    private String userName;
    private String userSurname;
    private String userAddress;
    private String stationName;
    private String userPhone;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

        public void setUserName(String userName){
            driver.findElement(name).sendKeys(userName);
        }
        public void setSurname (String userSurname){
            driver.findElement(surname).sendKeys(userSurname);
        }
        public void setUserAddress (String userAddress){
            driver.findElement(address).sendKeys(userAddress);
        }

        public void selectStationMetro (String stationName) throws InterruptedException {

            WebElement element = driver.findElement(metro);

            element.click();

            new WebDriverWait(driver, 2)
                    .until(ExpectedConditions.visibilityOfElementLocated(metro));

            new WebDriverWait(driver, 2)
                    .until(ExpectedConditions.visibilityOfElementLocated(selectedMetroStation));
            WebElement element2 = driver.findElement(selectedMetroStation);
            element2.sendKeys(stationName);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element2);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",element2);

            WebElement element3 = driver.findElement(currentStMetro);
            element3.click();

        }

        public void setPhone (String userPhone){
            driver.findElement(phone).sendKeys(userPhone);
        }


        public void clickSignInButton () {
            driver.findElement(buttonNext).click();//найти кнопку далее и нажать
        }

        public void  InputFormData (  String userName, String userSurname,String userAddress,String stationName, String UserPhone ) throws InterruptedException { setUserName(userName);
            setSurname(userSurname);
            setUserAddress(userAddress);
            setPhone(UserPhone);
            selectStationMetro(stationName);
            clickSignInButton();

        }



}