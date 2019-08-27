/**
 * 
 */
package Utilities;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;




/**
 * @author Ankur Rana
 *
 */
public class CommonLibrary {

	public static WebElement FindElement(WebDriver driver, String xpath, int timeOut){

		try {
			int timeout = 0;
			WebElement ele = null;
			while (true) {
				try {
					ele = driver.findElement(By.xpath(xpath));
					break;
				} catch (Exception e) {
					waitFor(250);
					timeout++;
					if (timeout > timeOut * 4) {
						ele = driver.findElement(By.xpath(xpath));
						break;
					}
				}
			}
			return ele;
		} catch (Exception e) {
			return null;
		}
	}
	
	private static void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
	
	public static boolean click(WebDriver driver, WebElement element) {
		try {
			if (element != null) {
				element.click();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} 
	}
	
	public static boolean sendKeys(WebDriver driver, WebElement element, String value) {
		try {
			if (element != null) {
				try{
					element.clear();
				} catch (Exception e){
				}
				element.sendKeys(value);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
