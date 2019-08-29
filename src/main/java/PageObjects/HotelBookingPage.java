/**
 * 
 */
package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import Scripts.HotelBookingTest;

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

}
