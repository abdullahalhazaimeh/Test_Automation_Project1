package web_p;


import static org.testng.Assert.assertEquals;
import java.awt.Desktop.Action;
import java.sql.Driver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.ForwardingIterator;

import dev.failsafe.internal.util.Assert;





public class Test_web_site {

	
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	String site = "https://codenboxautomationlab.com/practice/";
	JavascriptExecutor js = (JavascriptExecutor) driver;	
	Actions action = new Actions(driver);
	
	@BeforeTest
	public void web_site() {
		
		
		driver.manage().window().maximize();
		driver.get(site);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		
	}
		
		

	@Test(priority =1 , description = "radio button" , invocationCount = 1 , enabled = false)
	private void first() throws InterruptedException {
		

		List<WebElement> radio_button = driver.findElements(By.className("radioButton"));
		//radio_button.get(0).click();
		int rand_x = rand.nextInt(radio_button.size());
		//radio_button.get(rand_x).click();
		
		for (int i = 0; i < radio_button.size(); i++) {
			Thread.sleep(1000);
			radio_button.get(i).click();
		}
	}
	
	
	
	@Test(priority =2 , description ="drop down list" , invocationCount =1 , enabled = false)
	private void second() throws InterruptedException {
		
		String [] drop_down_arry = { "US", "CA", "OM", "BR", "AR", "FR", "DE", "IT", "ES" };
		int rand_x = rand.nextInt(drop_down_arry.length);
		
		
		WebElement drop_down = driver.findElement(By.id("autocomplete"));
		drop_down.sendKeys(drop_down_arry[rand_x]);
		Thread.sleep(1000);
		drop_down.sendKeys(Keys.chord(Keys.ARROW_DOWN,Keys.ENTER));
	}
	
	
	
	
	@Test(priority =3 , description ="static down down" , invocationCount =1 , enabled = false)
	private void therd() {
		
		WebElement select  = driver.findElement(By.id("dropdown-class-example"));
		Select sel = new Select(select);
		//sel.selectByIndex(1);
		//sel.selectByValue("option3");
		sel.selectByVisibleText("Appium");
	}
	
	
	@Test(priority =4 , description ="check box" , invocationCount =1 , enabled = false)
	private void forth() {
		

		List<WebElement> check_box = driver.findElements(By.xpath("//input[@type='checkbox']"));
		int rand_x = rand.nextInt(check_box.size());
		//check_box.get(rand_x).click();
		check_box.getFirst().click();
		check_box.getLast().click();
	}
	
	
	
	@Test(priority =5 , description ="this is to move from window to another one", invocationCount =1 , enabled = false)
	private void fifth() throws InterruptedException {
		

		WebElement OpenWindowButton = driver.findElement(By.id("openwindow"));
		OpenWindowButton.click();
		Thread.sleep(2000);
		List<String> windowsHandels = new ArrayList<String>(driver.getWindowHandles());
		// number of opened windows
		System.out.println(windowsHandels.size());
// switch to the other window 
		driver.switchTo().window(windowsHandels.get(1));
		// in the second window
		WebElement ContactButton = driver.findElement(By.id("menu-item-9680"));
		ContactButton.click();

		System.out.println(driver.getTitle() + " hello from the second window");

		driver.close();
		// switch to the first window
		driver.switchTo().window(windowsHandels.get(0));
		System.out.println(driver.getTitle() + " site chrome");
	}
	
	
	
	
	@Test(priority =6 , description = "check moving to another tab", invocationCount =1 , enabled = false)
	private void sexth() throws InterruptedException {
		
	
		
			WebElement OpenTabButton = driver.findElement(By.id("opentab"));

			OpenTabButton.click();

			List<String> windowsHandels = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(windowsHandels.get(1));
			Thread.sleep(2000);
			System.out.println(driver.getTitle());

		}
	
	
	@Test(priority = 7, description = "Alert and confirm", enabled = false)
	public void Switch_To_Alert_Example() throws InterruptedException {
		WebElement nameBox = driver.findElement(By.id("name"));
		nameBox.sendKeys("abedalraheem");
		// WebElement AlertBox = driver.findElement(By.id("alertbtn"));
		// AlertBox.click();

		Thread.sleep(1000);
		// driver.switchTo().alert().accept();
		// driver.switchTo().alert().dismiss();

		WebElement ConfirmBox = driver.findElement(By.id("confirmbtn"));
		ConfirmBox.click();
		//Thread.sleep(1000);
		// driver.switchTo().alert().accept();
		// driver.switchTo().alert().dismiss();

		// System.out.println(driver.switchTo().alert().getText());
	}

	
	
	@Test(priority = 8, description = " play with the data of the column " , enabled = false)

	public void Web_Table_Example() {

		WebElement TheTable = driver.findElement(By.id("product"));

		List<WebElement> theDataInsideTheTable = TheTable.findElements(By.tagName("tr"));

		for (int i = 1; i < theDataInsideTheTable.size(); i++) {
			
			int totalTdInTheRow = theDataInsideTheTable.get(i).findElements(By.tagName("td")).size();

			System.out.println(
					theDataInsideTheTable.get(i).findElements(By.tagName("td")).get(totalTdInTheRow - 1).getText());
		}

	}
	
	
	@Test(priority = 9 , description ="hard assert and soft assert and java script excutor" , enabled =  false)
	public void Element_Displayed_Example() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert myAssertion = new SoftAssert();

		// softassert if one test failed it will continue to the rest of the code
		// hardassert once failed it will stop all the execution

		js.executeScript("window.scrollTo(0,1500)");

		WebElement HideButton = driver.findElement(By.id("hide-textbox"));
		WebElement ShowButton = driver.findElement(By.id("show-textbox"));
		
		HideButton.click();
		
		WebElement theTEXXXXXTINPUT = driver.findElement(By.id("displayed-text"));
		// soft assert
//		myAssertion.assertEquals(theTEXXXXXTINPUT.isDisplayed(), true);
//		Thread.sleep(4000);
//		System.out.println(theTEXXXXXTINPUT.isDisplayed());
//		HideButton.click();
//		System.out.println(driver.getTitle());
		
		
		//hard assert
		System.out.println(theTEXXXXXTINPUT.isDisplayed());
		assertEquals(theTEXXXXXTINPUT.isDisplayed(), true);
		ShowButton.click();
		System.out.println(driver.getTitle());
		
		
		
		//myAssertion.assertAll();
		
	}
	
	@Test(priority = 10, description = "dropDown Dynamic", enabled = false)
	public void Dynamic_Dropdown_Example() throws InterruptedException {

		// generate random String in a static way because i dont need my test to include
		// any other data
		String[] countryCodes = { "US", "CA", "OM", "BR", "AR", "FR", "DE", "IT", "ES", "AM" };

		// random index based on the length of the above array

		int randomIndex = rand.nextInt(countryCodes.length);

// webelement to the input field ( country)  
		WebElement DynamicListInput = driver.findElement(By.id("autocomplete"));

		// send an random item from my array to the webelement (inputfield)
		DynamicListInput.sendKeys(countryCodes[randomIndex]);

		Thread.sleep(1000);

		// it will press an arrow down + enter to select the first item from the list
		
		DynamicListInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
// i need to capture or take the country name that selenum already selected 
		String DataInsideMyInput = (String) js.executeScript("return arguments[0].value", DynamicListInput);
		
		// the country name for example United Arab Emirates contains capital letters
		// and small ones
		// so what i did i make all the letters in small ( for example France , and
		// South Africa look at the 'fr and Fr'
		String updateDataInMyInput = DataInsideMyInput.toUpperCase();
		System.out.println(updateDataInMyInput);
	
//		hard assert
		boolean ActualValue = updateDataInMyInput.contains(countryCodes[randomIndex].toUpperCase());
		boolean ExpectedResult = true;
		assertEquals(ActualValue, ExpectedResult);

	}
	
	@Test(priority = 11, description = "check The Both Buttons disable , enable", enabled = false)
	public void Enabled_Disabled_Example() throws InterruptedException {
		WebElement DisabledButton = driver.findElement(By.id("disabled-button"));
		WebElement EnabledButton = driver.findElement(By.id("enabled-button"));

		DisabledButton.click();

		WebElement enabled_example_input = driver.findElement(By.id("enabled-example-input"));

		boolean ActualResult = enabled_example_input.isEnabled();

		boolean ExpectedResult = false;

		assertEquals(ActualResult, ExpectedResult);

		Thread.sleep(1000);

		EnabledButton.click();
		boolean ActualResult2 = enabled_example_input.isEnabled();
		enabled_example_input.sendKeys("123");
		boolean ExpectedResult2 = true;
		assertEquals(ActualResult2, ExpectedResult2);
		
	}
	

	@Test(priority = 12, description = "check the hover to certain element", enabled = false)
	public void Mouse_Hover_Example() throws InterruptedException {

		js.executeScript("window.scrollTo(0,1800)");
		Thread.sleep(2000);

		WebElement MouseHoverElement = driver.findElement(By.id("mousehover"));

		action.moveToElement(MouseHoverElement).perform();
		

		Thread.sleep(1000);
		// driver.findElement(By.linkText("Top")).click();
		driver.findElement(By.partialLinkText("Relo")).click();

	}
	
	@Test(priority = 13, description = "open calendar in a new tab", enabled = false)
	public void Calendar_Example() throws InterruptedException {
		// this is not the eaistes way
		js.executeScript("window.scrollTo(0,1900)");

		// WebElement Calendar2 = driver.findElement(By.linkText("Booking Calendar"));
		WebElement Calendar2 = driver.findElement(By.partialLinkText("Booking"));

		Calendar2.click();
		Thread.sleep(1000);

		List<String> windowsHandels = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowsHandels.get(1));

		System.out.println(driver.getTitle());
		List<WebElement> totalAvailbleDates = driver.findElements(By.className("date_available"));
		//totalAvailbleDates.size();
		
		driver.findElements(By.className("date_available")).size();
		driver.findElements(By.className("date_available")).get(0).click();
		driver.findElements(By.className("date_available")).get(totalAvailbleDates.size()-1).click();
		
		//System.out.println((totalAvailbleDates.size()-1));
	}
	
	@Test(priority = 14, description = "switch to frame inside the main page", enabled = false)
	public void iFrame_Example() {

		WebElement TheFrame = driver.findElement(By.id("courses-iframe"));
		// by index
		driver.switchTo().frame(0);
		// by id
		driver.switchTo().frame("courses-iframe");
		// by webelemment
		driver.switchTo().frame(TheFrame);

		String theText = driver.findElement(By.xpath("//*[@id=\"ct_text_editor-be8c5ad\"]/div/div/p")).getText();

		System.out.println(theText);

	}

	@Test(priority = 15, description = "download the file inside the main page", enabled = false)
	public void Download_file_to_test() {
		

		//WebElement thefile = driver.findElement(By.className("wp-block-button__link"));
		//WebElement thefile = driver.findElement(By.cssSelector(".wp-block-button__link"));
		//WebElement thefile = driver.findElement(By.xpath("//*[@id=\"post-501\"]/div/div[8]/fieldset/div/a"));
		WebElement thefile = driver.findElement(By.linkText("Download Apk files"));
		thefile.click();
		
	}


	
	@Test(priority = 16, enabled = true)

	public void CheckTheTitle() {
		 
		String expected = "Automation Practice - CodenBox AutomationLab";
		
		String actual = driver.getTitle();
		
		assertEquals(expected, actual);
	}

	@AfterTest()
	public void after() {}
		

		
}
	

	

	
	
	
	
	
	
	
	
	
	
	



