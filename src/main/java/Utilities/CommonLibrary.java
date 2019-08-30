/**
 * 
 */
package Utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;






/**
 * @author Ankur Rana
 *
 */
public class CommonLibrary {

	/**
	 * Will return WebElement after searching for specified time
	 * 
	 * @author Ankur Rana
	 * @param driver
	 * @param xpath
	 * @param timeOut
	 * @return WebElement
	 */
	public static WebElement findElement(WebDriver driver, String xpath, int timeOut){

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
	
	/**
	 * @author TestVagrant
	 * @param durationInMilliSeconds
	 */
	public static void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
	
	/**
	 * Will return true if successfully clicked, otherwise false
	 * 
	 * @author Ankur Rana
	 * @param driver
	 * @param element
	 * @return boolean
	 */
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
	
	/**
	 * Will return true if successfully passed the value to the text box
	 * 
	 * @author Ankur Rana
	 * @param driver
	 * @param element
	 * @param value
	 * @return boolean
	 */
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
	
	/**
	 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
	 * 
	 * @author Ankur Rana
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static Class[] getClasses(String packageName) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources;
		ArrayList<Class> classes = null;
		String folderPath = null;
		try {
			resources = classLoader.getResources(path);
			List<File> dirs = new ArrayList<File>();
			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();
				dirs.add(new File(resource.getFile()));
			}
			classes = new ArrayList<Class>();
			for (File directory : dirs) {
				try {
					if(directory.toString().contains("%20")){
						folderPath = directory.toString().replace("%20", " ");
					} else {
						folderPath = directory.toString();
					}
					classes.addAll(findClasses(new File(folderPath), packageName));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 * 
	 * @author Ankur Rana
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("rawtypes")
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			System.err.println("Directory not found: "+directory);
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

	/**
	 * Will read the data from the properties file.
	 * 
	 * @author Ankur Rana
	 * @param filePath
	 * @param key
	 * @return String
	 */
	public static String readDataFromPropertyFile(String filePath, String key) {

		String value = "";
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream(new File(filePath)));
			value = prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * Will wait for page to load poperly.
	 * 
	 * @author Ankur Rana
	 * @param driver
	 * @param timeOut
	 */
	public static void waitForPageLoad(WebDriver driver, int timeOut) {

		Wait<WebDriver> wait = new WebDriverWait(driver, timeOut);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				if(isAlertPresent(driver)){
					return true;
				}
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	/**
	 * Will check if alert is present or not
	 * 
	 * @author Ankur Rana
	 * @param driver
	 * @return boolean
	 */
	public static boolean isAlertPresent(WebDriver driver){
		try{
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e){
			return false;
		}
	}

	/**
	 * Will check if element is visible on the page or not.
	 * 
	 * @author Ankur Rana
	 * @param driver
	 * @param element
	 * @param timeout
	 * @return WebElement
	 */
	public static WebElement isDisplayed(WebDriver driver, WebElement element, int timeout) {
		try {
			if (element != null) {
				WebDriverWait wait = new WebDriverWait(driver, timeout);
				WebElement ele = null;
				ele = wait.until(ExpectedConditions.visibilityOf(element));
				if (ele != null) {
					return ele;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Will select on the basis of visible text
	 * 
	 * @author Ankur Rana
	 * @param element
	 * @param textToBeSelected
	 * @return boolean
	 */
	public static boolean selectVisibleTextFromTheDropDown(WebElement element, String textToBeSelected){
		try{
			Select select = new Select(element);
			select.selectByVisibleText(textToBeSelected);
			return true;
		} catch (Exception e){
			return false;
		}
	}
	
	/**
	 * Will switch to the frame on the basis of element
	 * 
	 * @author Ankur Rana
	 * @param driver
	 * @param element
	 * @return boolean
	 */
	public static boolean switchToFrame(WebDriver driver, WebElement element){
		try{
			driver.switchTo().frame(element);
			return true;
		} catch (NoSuchFrameException e){
			return false;
		}
	}


}
