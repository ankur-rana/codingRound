/**
 * 
 */
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import Utilities.CommonEnums.TabName;
import static Utilities.CommonLibrary.*;

/**
 * @author Ankur Rana
 *
 */
public abstract class BasePage {

	WebDriver driver = null;
	/**
	 * 
	 */
	public BasePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public static boolean clickOnTabs(WebDriver driver, TabName tabName){
		WebElement element = FindElement(driver, "//a[text()='"+tabName.toString()+"']", 30);
		if(click(driver, element)){
			return true;
		} 
		return false;
	}
	
}
