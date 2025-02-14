package pac.qa.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ExampleAct {

	public static void main(String[] args) {
		
		/* set property for chrome driver */
		System.setProperty("webdriver.chrome.driver", "D:\\JarFile\\chrome\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		
		/* Navigate to Main URL and verify Login Achro text displayed */
		String urlTitel=driver.getTitle();
		System.out.println(" Main WebPage Title "+urlTitel);
		WebElement loginAchorText=driver.findElement(By.xpath("//span[text()='Login']"));
		
		if(loginAchorText.isDisplayed())
		{
			/* if home page displayed then go to electronics Tab and verify Laptop and Desktop items displayed or not */
		   Actions act=new Actions(driver);
			WebElement eleElectronics=driver.findElement(By.xpath("//div//span[text()='Electronics']"));
			act.moveToElement(eleElectronics).perform();
			
			List<WebElement>lst=driver.findElements(By.tagName("a"));
			int numTags=lst.size();
			System.out.println("number of all links available in MainPage "+numTags);
			
			WebElement eleLapAndDestop=driver.findElement(By.xpath("//a[contains(text(),'Laptop and Desktop')]"));
			act.moveToElement(eleLapAndDestop).click().perform();
			
			/* check if only Laptops displayed */
			
			List<WebElement>lstCategories=driver.findElements(By.xpath("//div[@class='esFpML']//a"));
			
			   int catSize=lstCategories.size();
			   System.out.println("size of left panel categories "+catSize);
			   String expectedFilter="Laptops";
			 
			   for(int i=0;i<lstCategories.size();i++)
			   {
				   WebElement e=lstCategories.get(i);
				   System.out.println("filters for laptop and desktop are displayed"+e.getText());
				   if(i==1)
				   {
		           if(e.getText().equals(expectedFilter))
		           {
		        	   System.out.println("Expected filter is "+e.getText());
		           }
		           else
		           {
		        	   System.out.println("User is on another item Page ");
		           }
		           break;
				   }
			   }
			   /* then Brand tab select HP Laptop and count the numbers of items and list only hp laptops name in console */
			   
			   driver.findElement(By.xpath("//div[text()='Brand']")).click();
			   Actions act2=new Actions(driver);
			   WebElement checkHp=driver.findElement(By.xpath("//div[normalize-space(text())='HP']/preceding-sibling::input"));
			       act2.moveToElement(checkHp).click().perform();
			   List<WebElement>expectedItems=driver.findElements(By.xpath("//div[@id='container']//div[1][contains(text(),'HP')]"));
			 
			   int itemCount=expectedItems.size();
			   System.out.println(" all the available items on HP"+itemCount);
			   String regExp="(?i)^hp*";
			   for(WebElement e:expectedItems)
			   {
				   System.out.println("all the available item on HP:"+e.getText());
				   if(e.getText().matches(regExp))
						   {
					        System.out.println("Side panel filters are working fine");
						   }
			   }
				   
			   }
		else
		{
			System.out.println("User is still on Home Page ");
		}
		
		driver.close();
		driver.quit();
	}


}
