package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class AuthFormTest {
    
    @Test
    public void emptyAll() {
        System.setProperty("webdriver.chrome.driver", "C://Users/cactus/Desktop/JPA_WEB/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:5173");

        WebElement loginField = driver.findElement(By.id("login"));
    	loginField.clear();

    	// Находим поле "Пароль" и оставляем его пустым
    	WebElement passwordField = driver.findElement(By.id("password"));
    	passwordField.clear();

    	// Находим кнопку "Войти"
    	WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

    	// Проверяем, что кнопка "Войти" не доступна для нажатия
    	assert loginButton.isEnabled() == false;

        // Закрываем браузер
        driver.quit();
    }

    @Test
    public void emptyPass() {
        System.setProperty("webdriver.chrome.driver", "C://Users/cactus/Desktop/JPA_WEB/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:5173");        

        WebElement loginField = driver.findElement(By.id("login"));
    	loginField.sendKeys("1");

    	// Находим поле "Пароль" и оставляем его пустым
    	WebElement passwordField = driver.findElement(By.id("password"));
    	passwordField.clear();

    	// Находим кнопку "Войти"
    	WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

    	// Проверяем, что кнопка "Войти" не доступна для нажатия
    	assert loginButton.isEnabled() == false;

        // Закрываем браузер
        driver.quit();
    }

    @Test
    public void emptyLogin() {
        System.setProperty("webdriver.chrome.driver", "C://Users/cactus/Desktop/JPA_WEB/chromedriver.exe");
        WebDriver driver = new ChromeDriver();       
        driver.get("http://localhost:5173");        

        WebElement loginField = driver.findElement(By.id("login"));
    	loginField.clear();
    	
    	WebElement passwordField = driver.findElement(By.id("password"));
    	passwordField.sendKeys("1");

    	// Находим кнопку "Войти"
    	WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

    	// Проверяем, что кнопка "Войти" не доступна для нажатия
    	assert loginButton.isEnabled() == false;

        // Закрываем браузер
        driver.quit();
    }

    @Test
    public void notAllEmpty() {
        System.setProperty("webdriver.chrome.driver", "C://Users/cactus/Desktop/JPA_WEB/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:5173");        

        WebElement loginField = driver.findElement(By.id("login"));
    	loginField.sendKeys("1");

    	// Находим поле "Пароль" и оставляем его пустым
    	WebElement passwordField = driver.findElement(By.id("password"));
    	passwordField.sendKeys("1");

    	// Находим кнопку "Войти"
    	WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

    	// Проверяем, что кнопка "Войти" не доступна для нажатия
    	assert loginButton.isEnabled() == true;

        // Закрываем браузер
        driver.quit();
    }
}