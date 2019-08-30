/**
 * 
 */
package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static Utilities.CommonLibrary.*;


/**
 * @author Ankur Rana
 *
 */
public class HotelBookingPage extends BasePage {

	/**
	 * @param driver
	 */
	public HotelBookingPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	/**
	 * @return the hotelLink
	 */
	public WebElement getHotelLink(int timeOut) {
		return isDisplayed(driver, hotelLink, timeOut);
	}

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	/**
	 * @return the localityTextBox
	 */
	public WebElement getLocalityTextBox(int timeOut) {
		return isDisplayed(driver, localityTextBox, timeOut);
	}

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchHotelButton;

	/**
	 * @return the searchButton
	 */
	public WebElement getSearchHotelButton(int timeOut) {
		return isDisplayed(driver, searchHotelButton, timeOut);
	}

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	/**
	 * @return the travellerSelection
	 */
	public WebElement getTravellerSelection(int timeOut) {
		return isDisplayed(driver, travellerSelection, timeOut);
	}
	
	@FindBy(xpath="//p[text()='Looking for hotels...']/preceding-sibling::div//div[@class='stripes']")
	private WebElement resultLoadingBar;

	/**
	 * @return the resultLoadingBar
	 */
	public WebElement getResultLoadingBar(int timeOut) {
		return isDisplayed(driver, resultLoadingBar, timeOut);
	}
}
