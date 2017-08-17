import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class Grid {
	public static WebDriver driver;
	 
	public static void main(String[]  args) throws MalformedURLException, InterruptedException{
 
		//System.setProperty("webdriver.gecko.driver", "E:\\softwares\\selenium\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "E:\\softwares\\selenium\\chromedriver.exe");
 		String Node = "http://172.16.161.138:4444/wd/hub";
 		
 		DesiredCapabilities cap = DesiredCapabilities.chrome();
 		cap.setPlatform(Platform.WINDOWS);
 		
 
 		driver = new RemoteWebDriver(new URL(Node), cap);
 
 		driver.get("http://google.co.in");
 		driver.quit();
 	}

}

