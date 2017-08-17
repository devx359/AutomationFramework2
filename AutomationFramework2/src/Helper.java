import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.sun.jmx.snmp.Timestamp;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

/**
 * 
 */

/**
 * @author debo
 *
 */
public class Helper {
	public WebDriver driver = new ChromeDriver();
	//	public ChromeDriver driver= new ChromeDriver();
	//public WebDriver driver = new FirefoxDriver();
	

	public void login_to_net (String usern ,String pass)
	{
		
		
		
		driver.get("http://10.254.254.47/0/up/");
			
		driver.findElement(By.id("username")).sendKeys(usern);
		driver.findElement(By.id("password")).sendKeys(pass);
		
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/form/div/button")).click();
		System.out.println("after clicking submit");
	}
	
	public void google_search(String search)
	{
		driver.navigate().to("http://www.google.co.in");
		driver.findElement(By.xpath("//input[@id='lst-ib']")).sendKeys(search);
		driver.findElement(By.xpath("//input[@value='Google Search']")).click();
		driver.findElement(By.xpath("//button[@name='btnG']")).click();
	}
	
	//explicit and implicit wait
	public void dropdown() throws InterruptedException
	{
		driver.manage().window().maximize();
		/*implicit wait-----
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);*/
		
		driver.get("http://toolsqa.wpengine.com/automation-practice-form");
		/*Explicit wait ----
		WebDriverWait wait = new WebDriverWait(driver,10); 
		WebElement continent = wait.until(ExpectedConditions.elementToBeClickable(By.id("continents")));
*/		
		//fluent wait -------
		 Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(30, TimeUnit.SECONDS)
			       .pollingEvery(1, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);
		 

			   WebElement continent = wait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
			       return driver.findElement(By.id("continents"));
			     }
			   });
			 
		
	//	driver.get("http://toolsqa.wpengine.com/automation-practice-form");
	//	WebElement continent= driver.findElement(By.id("continents"));
		Select ob= new Select(continent);
		ob.selectByVisibleText("Antartica");
		Thread.sleep(2000);
		WebElement commands = driver.findElement(By.id("selenium_commands"));
		Select select2 = new Select(commands);
		select2.selectByIndex(3);
		Thread.sleep(2000);
		select2.selectByVisibleText("Switch Commands");
		Thread.sleep(2000);
		select2.deselectByIndex(3);
		select2.selectByIndex(1);
		
		driver.close();
		
	}
	
	public void table() //Find Taiwan and get corresponding values of cells of same row
	{
		driver.get("http://toolsqa.wpengine.com/automation-practice-table/");
		int i,j =1;
		for(i=1;i<=4;i++)
		{
		WebElement el = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr["+i+"]/td["+j+"]"));
		String text = el.getText();
			if(text.equalsIgnoreCase("Taiwan"))
			{
				System.out.print(text+" : ");
				for(j=2;j<=5;j++)
				{
					
					WebElement el2 = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr["+i+"]/td["+j+"]"));
					System.out.print(el2.getText()+" - ");
				}
				driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr["+i+"]/td["+j+"]")).click();
			}
		}
		
	}	
	
	
	
	public void uploadFiles()
	{
		driver.get("http://demo.guru99.com/selenium/upload/");
		driver.findElement(By.xpath("//input[@id='uploadfile_0']")).sendKeys("D:\\upload.txt");
		driver.findElement(By.xpath("//input[@id='terms']")).click();
		driver.findElement(By.id("submitbutton")).click();
		screenshot(driver,"D://screenshots//screenshot");
	}
	
	public void screenshot(WebDriver driver , String filenamewithpath)
	{
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			TakesScreenshot scrshot = (TakesScreenshot)driver; //convert driver to screenshot obj
			File srcfile = scrshot.getScreenshotAs(OutputType.FILE); //get screenshot and save to a FILE type
			File destfile = new File(filenamewithpath+"_"+timestamp.getDate().toString().replace(" ","_").replace(":","")+".png"); //create new file in desktop
			//copy source to destination file
			try {
				FileUtils.copyFile(srcfile,destfile);
			} catch (IOException e) {				
				e.printStackTrace();
			}
			
	}
	
	public void fluentwait()
	{
		driver.get("http://toolsqa.com/automation-practice-switch-windows/");
		Wait<WebDriver> wait2 = new FluentWait<WebDriver>(driver)
			       .withTimeout(45, TimeUnit.SECONDS)
			       .pollingEvery(1, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);
		
		//Expected conditions
		/*WebElement invisblebutton=wait2.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@id='invisibility']"))));
		System.out.println("Invisible element gettext : "+invisblebutton.getText());
		*/
		
			   WebElement changecolor = wait2.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
			    	WebElement ccolor= driver.findElement(By.xpath("//button[@id='colorVar']"));//.getAttribute("color");
			    	String colors = ccolor.getCssValue("color");
			    	System.out.println("The color of the button is " + colors);
			    	if(colors.equals("rgba(255, 0, 0, 1)"))
			    	{
			    		System.out.println("Text color is red");
			    		return driver.findElement(By.xpath("//button[@id='colorVar']"));
			    		//return true;
			    	}
			    	else
			    	{
			    		
			    		return null;
			    	}
			     }
			   });
			   //Wait for timer to show buzz buzz
			 /*  Wait<WebDriver> wait2 = new FluentWait<WebDriver>(driver)
				       .withTimeout(45, TimeUnit.SECONDS)
				       .pollingEvery(1, TimeUnit.SECONDS)
				       .ignoring(NoSuchElementException.class);*/
			   WebElement buzz = wait2.until(new Function<WebDriver,WebElement>(){
				   public WebElement apply(WebDriver driver) {
					   
					 WebElement buzz=   driver.findElement(By.xpath("//span [text()='Buzz  Buzz'][@class='timer']"));
					 if(buzz!=null)
					 {
						 System.out.println("Element  found ");
						 return buzz;
					 }
					 else
					 {
						 System.out.println("Element not found !!!");
						 return null;
					 }
				   }
				   
			   });
				   System.out.println(buzz.getText().toString());
				  

				
			   
			   driver.close();
	}
	
	public void switchwindow() throws InterruptedException
	{
		driver.manage().window().maximize();
		driver.get("http://toolsqa.wpengine.com/automation-practice-switch-windows");
		String windowhandle = driver.getWindowHandle();
		System.out.println(windowhandle);
		
		driver.findElement(By.xpath("//button[text()='New Message Window']")).click();
		Set<String> windows =driver.getWindowHandles();
		System.out.println(windows);
		
		//advanced for loop to retrieve values from Set
		for(String handle : driver.getWindowHandles())
		{
			System.out.println(handle);
			driver.switchTo().window(handle);
		}
		Thread.sleep(1000);
		
		driver.close(); //closing the popup
		
		driver.switchTo().window(windowhandle); //switching to earlier window
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='alert']")).click();
		
		Alert alert = driver.switchTo().alert();
		//alert.accept();
		Thread.sleep(1000);
		alert.accept();
		Thread.sleep(1000);
		driver.close();
			
		
	
		
	}
	
	public void alert() throws InterruptedException
	{
		driver.manage().window().maximize();
		driver.get("http://toolsqa.wpengine.com/handling-alerts-using-selenium-webdriver/");
		
		WebElement  ele = driver.findElement(By.xpath("//button[text()='Confirm Pop up']"));
		//giving element not visible so had to use this
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", ele);
		
		
		Alert alert= driver.switchTo().alert();
		Thread.sleep(1000);
		alert.dismiss();
		
		jse.executeScript("alert('Hello javascript');");
		alert=driver.switchTo().alert();
		Thread.sleep(1000);
		alert.accept();
		
		WebElement ele2=driver.findElement(By.xpath("//button[text()='Prompt Pop up']"));
		jse.executeScript("arguments[0].click()", ele2);
		alert=driver.switchTo().alert();		
		alert.sendKeys("Yes");
		Thread.sleep(2000);
		System.out.println(alert.getText());
		alert.accept();
		
		
		Thread.sleep(1000);
		
		
		driver.close();
		}
	
	
	
	
	
	public void iframe() throws InterruptedException
	{
		driver.get("http://toolsqa.wpengine.com/iframe-practice-page/");
		driver.switchTo().frame("iframe2");
		driver.findElement(By.xpath("//img[@alt='pattern-14']")).click();
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		driver.close();
	}
	
	
	public void dagdrop() throws InterruptedException
	{
		
		driver.get("http://www.dhtmlx.com/docs/products/dhtmlxTree/index.shtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		WebElement from = driver.findElement(By.xpath("//*[@id=\"treebox2\"]/div/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[6]/td[2]/table/tbody/tr/td[4]/span"));
		WebElement to = driver.findElement(By.xpath("//*[@id=\"treebox1\"]/div/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/table/tbody/tr[1]/td[4]/span"));
		Actions action = new Actions(driver);
		Thread.sleep(4000);
		Action ob=action.clickAndHold(from).moveToElement(to).release(to).build();
		ob.perform();
		Thread.sleep(4000);
		
		//driver.close();
	}
	
	public void hover()
	{
		driver.get("http://store.demoqa.com/products-page/product-category/");
		driver.manage().window().maximize();
		
		WebElement prod = driver.findElement(By.xpath("//a[text()='Product Category']"));
		WebElement iphones = driver.findElement(By.xpath("//a[text()='iPods']"));
		Actions actions = new Actions(driver);
		actions.clickAndHold(prod).moveToElement(iphones).click().build().perform();
		
		
		
		
		
		
		
		
		
		
		
	}
}
