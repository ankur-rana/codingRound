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
public class LoginPage extends BasePage{

	/**
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="SignIn")
	private WebElement signInButton;

	/**
	 * @return the signInButton
	 */
	public WebElement getSignInButton(int timeOut) {
		return isDisplayed(driver, signInButton, timeOut);
	}
	
	@FindBy(id="signInButton")
	private WebElement signInButtonOnCredentialsPopUp;

	/**
	 * @return the signInButtonOnCredentialsPopUp
	 */
	public WebElement getSignInButtonOnCredentialsPopUp(int timeOut) {
		return isDisplayed(driver, signInButtonOnCredentialsPopUp, timeOut);
	}
	
	@FindBy(id="errors1")
	private WebElement signInErrorMessage;

	/**
	 * @return the signInErrorMessage
	 */
	public WebElement getSignInErrorMessage(int timeOut) {
		return isDisplayed(driver, signInErrorMessage, timeOut);
	}
	
	@FindBy(id="modal_window")
	private WebElement signInFrame;

	/**
	 * @return the signInFrame
	 */
	public WebElement getSignInFrame(int timeOut) {
		return isDisplayed(driver, signInFrame, timeOut);
	} 
	 

}
