package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedirectTest {
       
    @Test
    public void checkUrlIndex() {
        System.setProperty("webdriver.chrome.driver", "C://Users/cactus/Desktop/JPA_WEB/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:5173/main");        	
                
	String currentUrl = driver.getCurrentUrl();
	Assert.assertEquals(currentUrl, "http://localhost:5173/");
	
        // Закрываем браузер
        driver.quit();
    }

    @Test
    public void checkUrlMain() {
        System.setProperty("webdriver.chrome.driver", "C://Users/cactus/Desktop/JPA_WEB/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:5173/");        

        WebElement loginField = driver.findElement(By.id("login"));
    	loginField.sendKeys("1");

    	// Находим поле "Пароль" и оставляем его пустым
    	WebElement passwordField = driver.findElement(By.id("password"));
    	passwordField.sendKeys("1");

    	// Находим кнопку "Войти"
    	WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

	WebDriverWait wait = new WebDriverWait(driver, 10); 
        wait.until(ExpectedConditions.urlToBe("http://localhost:5173/main"));

	// Ожидаем появления элемента add_btn
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add_btn")));

	driver.get("http://localhost:5173/");    
	
        String currentUrl = driver.getCurrentUrl();        
	Assert.assertEquals(currentUrl, "http://localhost:5173/main");
	
        // Закрываем браузер
        driver.quit();
    }

    @Test
    public void logoutCheckUrl() {
        System.setProperty("webdriver.chrome.driver", "C://Users/cactus/Desktop/JPA_WEB/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:5173/");        

        WebElement loginField = driver.findElement(By.id("login"));
    	loginField.sendKeys("1");

    	// Находим поле "Пароль" и оставляем его пустым
    	WebElement passwordField = driver.findElement(By.id("password"));
    	passwordField.sendKeys("1");

    	// Находим кнопку "Войти"
    	WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

	WebDriverWait wait = new WebDriverWait(driver, 10); 
        wait.until(ExpectedConditions.urlToBe("http://localhost:5173/main"));

	// Ожидаем появления элемента add_btn
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logout")));

	WebElement logout = driver.findElement(By.id("logout"));
    	logout.click();	
	
        String currentUrl = driver.getCurrentUrl();        
	Assert.assertEquals(currentUrl, "http://localhost:5173/");
	
        // Закрываем браузер
        driver.quit();
    }
}