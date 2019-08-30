/**
 * 
 */
package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
	
	@FindBy(className="searchSummary")
	private WebElement searchSummaryHeader;

	/**
	 * @return the searchSummaryHeader
	 */
	public WebElement getSearchSummaryHeader(int timeOut) {
		return isDisplayed(driver, searchSummaryHeader, timeOut);
	}
	
	@FindBy(id="userAccountLink")
	private WebElement yourTripLink;

	/**
	 * @return the yourTripLink
	 */
	public WebElement getYourTripLink(int timeOut) {
		return isDisplayed(driver, yourTripLink, timeOut);
	}
	
	public static boolean clickOnTabs(WebDriver driver, TabName tabName){
		WebElement element = findElement(driver, "//a[text()='"+tabName.toString()+"']", 30);
		if(click(driver, element)){
			return true;
		} 
		return false;
	}
	
	public boolean selectValueFromTheSuggestions(String value, int timeOut){
		if(click(driver, findElement(driver, "//li[@class='list']/a[contains(text(),'"+value+"')]", timeOut))){
			return true;
		} else {
			return false;
		}
	}
	
}
