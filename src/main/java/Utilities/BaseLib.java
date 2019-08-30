/**
 * 
 */
package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.sun.javafx.PlatformUtil;

/**
 * @author Ankur Rana
 *
 */
@SuppressWarnings("restriction")
public class BaseLib {

	private WebDriver edriver = null;
	public static EventFiringWebDriver driver = null;
	private Listeners listeners = null;
	public static String credentialsAndEnvironmentSetupFilePath = System.getProperty("user.dir")+"\\CredentialsAndEnvironmentSetup.properties";
	private static String BaseURL = CommonLibrary.readDataFromPropertyFile(credentialsAndEnvironmentSetupFilePath, "BaseURL");
	
	@Parameters(value = "browser")
	@BeforeMethod
	public void browserLauncher(String browserName){
		if (browserName.equalsIgnoreCase("Chrome")) {
			setDriverPath();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-notifications");
			edriver = new ChromeDriver(options);

		} else {
			setDriverPath();
			edriver = new ChromeDriver();
		}
		// Can add conditions for other browers also
		listeners = new Listeners();
		driver = new EventFiringWebDriver(edriver);
		driver.register(listeners);
		driver.get(BaseURL);
	}
	
	@AfterMethod
	public void quitBrowser(){
		driver.quit();
	}
	
	private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driverFiles/chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driverFiles/chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driverFiles/chromedriver_linux");
        }
    }
}
