package com.qa.jen;

import java.io.File;
import java.util.concurrent.TimeUnit;
//import junit.framework.Assert;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyWebDriver {
	
	private WebDriver driver =null;
	
	@BeforeMethod
	public void beformethode(){
		File file =new File("C:/DevTools/IEDriverServer.exe"); //lib\\IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver",file.getAbsolutePath());
		driver = new InternetExplorerDriver();
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.navigate().to("http://www.cars.com");
		
	}
	@Test
	public void test() throws InterruptedException{
		
		
		Select newMakeCombo = findComboBox(By.name("make"));
		newMakeCombo.selectByVisibleText("Audi");
		
		Select newModelCombo = findComboBox(By.name("model"));
		newModelCombo.selectByVisibleText("A6");
		
		Select newPriceCombo = findComboBox(By.name("prMx"));
		newPriceCombo.selectByVisibleText("$19,000");
		
		Select newMillCombo = findComboBox(By.name("rd"));
		newMillCombo.selectByVisibleText("50 Miles");
		
		WebElement zipCode = driver.findElement(By.id("zc2"));
		zipCode.clear();
		zipCode.sendKeys("20853");
		WebElement search = driver.findElement(By.name("submit"));
		search.click();
		Thread.sleep(2000);
		WebElement h1 = driver.findElement(By.xpath("//form/input[@id='ccZip']"));
		
		String h = h1.getText();
		System.out.println(h);
			Assert.assertEquals(h,"");
	}
	public Select findComboBox(By by){
		WebElement selectElement = driver.findElement(by);
		Select selectedCombo = new Select(selectElement);
		return selectedCombo;
	}
	@AfterMethod
	public void aftremethode(){
		driver.close();
		}
	}
