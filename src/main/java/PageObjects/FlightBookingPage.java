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
public class FlightBookingPage extends BasePage {

	/**
	 * @param driver
	 */
	public FlightBookingPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='DepartDate']")
	private WebElement departDatePickerTextBox;

	/**
	 * @return the departDatePicker
	 */
	public WebElement getDepartDatePickerTextBox(int timeOut) {
		return isDisplayed(driver, departDatePickerTextBox, timeOut);
	}
	
	@FindBy(xpath="//a[@class='nextMonth ']")
	private WebElement nextMonthArrow;
	
	/**
	 * @return the nextMonthArrow
	 */
	public WebElement getNextMonthArrow(int timeOut) {
		return isDisplayed(driver, nextMonthArrow, timeOut);
	}
	
	@FindBy(xpath="//a[@class='nextMonth disabled']")
	private WebElement nextMonthDisableButton;

	/**
	 * @return the nextMonthDisableButton
	 */
	public WebElement getNextMonthDisableButton(int timeOut) {
		return isDisplayed(driver, nextMonthDisableButton, timeOut);
	}
	
	@FindBy(xpath="//a[@class='ui-state-default ui-state-highlight ui-state-active ']")
	private WebElement currentDateInDatePicker;

	/**
	 * @return the currentDateInDatePicker
	 */
	public WebElement getCurrentDateInDatePicker(int timeOut) {
		return isDisplayed(driver, currentDateInDatePicker, timeOut);
	}
	
	@FindBy(id="OneWay")
	private WebElement oneWayRadioButton;

	/**
	 * @return the oneWayRadioButton
	 */
	public WebElement getOneWayRadioButton(int timeOut) {
		return isDisplayed(driver, oneWayRadioButton, timeOut);
	}
	
	@FindBy(id="FromTag")
	private WebElement fromTextBox;

	/**
	 * @return the fromTextBox
	 */
	public WebElement getFromTextBox(int timeOut) {
		return isDisplayed(driver, fromTextBox, timeOut);
	}
	
	@FindBy(id="ToTag")
	private WebElement toTextBox;

	/**
	 * @return the toTextBox
	 */
	public WebElement getToTextBox(int timeOut) {
		return isDisplayed(driver, toTextBox, timeOut);
	}
	
	@FindBy(id="SearchBtn")
	private WebElement searchFlightsButton;

	/**
	 * @return the searchButton
	 */
	public WebElement getSearchFlightsButton(int timeOut) {
		return isDisplayed(driver, searchFlightsButton, timeOut);
	}
	
	public boolean pickDate(int day, String month, int year){
		String xpath = "//span[text()='"+month+"']/following-sibling::span[text()='"+year+"']/../../following-sibling::table//a[text()='"+day+"']";
		boolean flag = false;
		WebElement ele = findElement(driver, xpath, 30);
		if(ele!=null){
			if(click(driver, ele)){
				return true;
			} else {
				return false;
			}
		} 
		if(click(driver, getNextMonthArrow(2))){
			flag = pickDate(day, month, year);
		}
		return flag;
	}
	
	public boolean selectCurrentDate(int timeOut){
		if(click(driver, getCurrentDateInDatePicker(timeOut))){
			return true;
		} else {
			return false;
		}
	}
	
}
