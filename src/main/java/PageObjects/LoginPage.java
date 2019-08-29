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
public class LoginPage extends BasePage{

	/**
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		PageFactory.initElements(driver, this);
	}

}
