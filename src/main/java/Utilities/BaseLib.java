/**
 * 
 */
package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
	
	@BeforeMethod
	public void browserLauncher(String browserName){
		setDriverPath();
		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\exefiles\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.addArguments("--start-maximized");
			edriver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\exefiles\\geckodriver.exe");
			edriver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("IE Edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") +"\\exefiles\\MicrosoftWebDriver.exe");
			edriver = new EdgeDriver();
		} else {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\exefiles\\chromedriver.exe");
			edriver = new ChromeDriver();
		}
		listeners = new Listeners();
		driver = new EventFiringWebDriver(edriver);
		driver.register(listeners);
	}
	
	@AfterMethod
	public void quitBrowser(){
		driver.quit();
	}
	
	private static void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
}
