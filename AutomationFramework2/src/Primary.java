/*import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;*/
//import org.openqa.selenium.chrome.ChromeDriver;


public class Primary {

	public static void main(String[] args) throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver", "F:\\softwares\\selenium\\chromedriver.exe");
	//	System.setProperty("webdriver.gecko.driver", "E:\\softwares\\selenium\\geckodriver.exe");
		
		Helper ob = new Helper();
		System.out.println("Just fooling around for git");
		System.out.println("jhgfhg");
		//just a comment
		//ob.login_to_net("pk_sur","789");
		//ob.google_search("boro baal");
		//ob.dropdown(); //exlpixit ,fluent,implicit implemented here 
		//ob.table();
		//ob.uploadFiles();
		//ob.fluentwait();
		//ob.switchwindow();
		//ob.alert();
		//ob.iframe();
		//ob.dagdrop(); //not working properly
		ob.hover();
		
		
	
	}
	
}


