package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.UnsupportedEncodingException;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class FormTest {
    private WebDriver driver = null;
    private WebDriverWait wait = null;

    private void setup() {
	System.setProperty("webdriver.chrome.driver", "C://Users/cactus/Desktop/JPA_WEB/chromedriver.exe");
	driver = new ChromeDriver();
    	driver.manage().window().maximize();
	driver.get("http://localhost:5173");

	WebElement loginField = driver.findElement(By.id("login"));
	loginField.sendKeys("q");

    	// Находим поле "Пароль" и оставляем его пустым
    	WebElement passwordField = driver.findElement(By.id("password"));
    	passwordField.sendKeys("q");

    	// Находим кнопку "Войти"
    	WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
    	loginButton.click();

	// Ожидаем загрузки страницы
    	wait = new WebDriverWait(driver, 10);
    	wait.until(ExpectedConditions.urlToBe("http://localhost:5173/main"));

    	// Ожидаем появления элемента add_btn
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add_btn")));
    }
       
    @Test
    public void emptyForm() {
        setup();     

    	// Нажимаем на кнопку "проверить" без заполнения полей
    	WebElement button = driver.findElement(By.id("add_btn"));
    	button.click();

    	wait.until(ExpectedConditions.alertIsPresent());

    	// Получаем текст alert
    	String alertText = driver.switchTo().alert().getText();
           
    	// Проверяем текст alert
    	Assert.assertEquals(alertText, "Invalid data!");

    	// Закрываем alert
    	driver.switchTo().alert().accept();        
  
    	// Закрываем браузер
    	driver.quit();
    }

    @Test
    public void onlyXForm() {
        setup();          	

	WebElement x = driver.findElement(By.id("x-3"));
    	x.click();

    	// Нажимаем на кнопку "проверить" без заполнения полей
    	WebElement button = driver.findElement(By.id("add_btn"));
    	button.click();

    	wait.until(ExpectedConditions.alertIsPresent());

    	// Получаем текст alert
    	String alertText = driver.switchTo().alert().getText();
           
    	// Проверяем текст alert
    	Assert.assertEquals(alertText, "Invalid data!");

    	// Закрываем alert
    	driver.switchTo().alert().accept();        
  
    	// Закрываем браузер
    	driver.quit();
    }

    @Test
    public void onlyRForm() {
        setup();        

	WebElement r = driver.findElement(By.id("r2"));
    	r.click();

    	// Нажимаем на кнопку "проверить" без заполнения полей
    	WebElement button = driver.findElement(By.id("add_btn"));
    	button.click();

    	wait.until(ExpectedConditions.alertIsPresent());

    	// Получаем текст alert
    	String alertText = driver.switchTo().alert().getText();
           
    	// Проверяем текст alert
    	Assert.assertEquals(alertText, "Invalid data!");

    	// Закрываем alert
    	driver.switchTo().alert().accept();        
  
    	// Закрываем браузер
    	driver.quit();
    }

    @Test
    public void onlyYForm() {
        setup();        

	WebElement y = driver.findElement(By.id("y"));
    	y.sendKeys("0");

    	// Нажимаем на кнопку "проверить" без заполнения полей
    	WebElement button = driver.findElement(By.id("add_btn"));
    	button.click();

    	wait.until(ExpectedConditions.alertIsPresent());

    	// Получаем текст alert
    	String alertText = driver.switchTo().alert().getText();
           
    	// Проверяем текст alert
    	Assert.assertEquals(alertText, "Invalid data!");

    	// Закрываем alert
    	driver.switchTo().alert().accept();        
  
    	// Закрываем браузер
    	driver.quit();
    }

    @Test
    public void preYForm() {
        setup();        

	WebElement x = driver.findElement(By.id("x-3"));
    	x.click();

	WebElement y = driver.findElement(By.id("y"));
    	y.sendKeys("-4");

	WebElement r = driver.findElement(By.id("r2"));
    	r.click();

    	// Нажимаем на кнопку "проверить" без заполнения полей
    	WebElement button = driver.findElement(By.id("add_btn"));
    	button.click();

    	wait.until(ExpectedConditions.alertIsPresent());

    	// Получаем текст alert
    	String alertText = driver.switchTo().alert().getText();
           
    	// Проверяем текст alert
    	Assert.assertEquals(alertText, "y must be greater than or equal to -3 (y=-4)");

    	// Закрываем alert
    	driver.switchTo().alert().accept();        
  
    	// Закрываем браузер
    	driver.quit();
    }

    @Test
    public void postYForm() {
        setup();        

	WebElement x = driver.findElement(By.id("x-3"));
    	x.click();

	WebElement y = driver.findElement(By.id("y"));
    	y.sendKeys("4");

	WebElement r = driver.findElement(By.id("r2"));
    	r.click();

    	// Нажимаем на кнопку "проверить" без заполнения полей
    	WebElement button = driver.findElement(By.id("add_btn"));
    	button.click();
    	
    	wait.until(ExpectedConditions.alertIsPresent());

    	// Получаем текст alert
    	String alertText = driver.switchTo().alert().getText();
           
    	// Проверяем текст alert
    	Assert.assertEquals(alertText, "y must be less than or equal to 3 (y=4)");

    	// Закрываем alert
    	driver.switchTo().alert().accept();        
  
    	// Закрываем браузер
    	driver.quit();
    }

    @Test
    public void checkClickByFormWithEmptyR2() {
        setup();  	

	WebElement graph = driver.findElement(By.id("graph"));
	int width = graph.getRect().getWidth();
    	int height = graph.getRect().getHeight();
	int centerX = graph.getRect().getX() + width / 2;
    	int centerY = graph.getRect().getY() + height / 2;
    	Actions actions = new Actions(driver);
    	actions.moveByOffset(centerX, centerY).click().perform();  
    	    	
    	wait.until(ExpectedConditions.alertIsPresent());

    	// Получаем текст alert
    	String alertText = driver.switchTo().alert().getText();
	System.out.println("RESULT = " + alertText + ";");
           
    	// Проверяем текст alert
    	Assert.assertEquals(alertText, "Invalid data!");	

    	// Закрываем alert
    	driver.switchTo().alert().accept();        
  
    	// Закрываем браузер
    	driver.quit();
    }

    @Test
    public void happyForm() {
        setup();        

	WebElement x = driver.findElement(By.id("x-2"));
    	x.click();

	WebElement y = driver.findElement(By.id("y"));
    	y.sendKeys("0");

	WebElement r = driver.findElement(By.id("r3"));
    	r.click();

    	// Нажимаем на кнопку "проверить" без заполнения полей
    	WebElement button = driver.findElement(By.id("add_btn"));
    	button.click();

	try {
	    TimeUnit.SECONDS.sleep(5); // ожидание в 5 секунд
	} catch (InterruptedException e) {
	    Thread.currentThread().interrupt();
	}
    	
    	WebElement firstRow = driver.findElement(By.xpath("//tbody[@id='table-body']/tr[1]"));
	String xValue = firstRow.findElements(By.tagName("td")).get(0).getText();
	String yValue = firstRow.findElements(By.tagName("td")).get(1).getText();
	String rValue = firstRow.findElements(By.tagName("td")).get(2).getText();
	String popalValue = firstRow.findElements(By.tagName("td")).get(3).getText();

	System.out.println("x: " + xValue);
	System.out.println("y: " + yValue);
	System.out.println("r: " + rValue);
	System.out.println("Попал: " + popalValue + ";" + popalValue == "Да");

	Assert.assertEquals(xValue, "-2.000");
	Assert.assertEquals(yValue, "0.000");
	Assert.assertEquals(rValue, "3");
  
    	// Закрываем браузер
    	driver.quit();
    }
}