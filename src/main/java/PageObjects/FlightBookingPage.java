/**
 * 
 */
package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Ankur Rana
 *
 */
public class FlightBookingPage extends BasePage {

	/**
	 * @param driver
	 */
	public FlightBookingPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

}
