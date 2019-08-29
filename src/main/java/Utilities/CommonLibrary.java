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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;





/**
 * @author Ankur Rana
 *
 */
public class CommonLibrary {

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
	
	public static void waitFor(int durationInMilliSeconds) {
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
	
	 /**
		 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
		 *
		 * @param packageName The base package
		 * @return The classes
		 * @throws ClassNotFoundException
		 * @throws IOException
		 */
		@SuppressWarnings("rawtypes")
		public static Class[] getClasses(String packageName) {
		    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		    System.err.println("inside getclasses");
		    assert classLoader != null;
		    System.err.println("inside getclasses");
		    String path = packageName.replace('.', '/');
		    Enumeration<URL> resources;
		    ArrayList<Class> classes = null;
		    String folderPath = null;
			try {
				System.err.println(path);
				resources = classLoader.getResources(path);
				List<File> dirs = new ArrayList<File>();
				while (resources.hasMoreElements()) {
					System.err.println("inside has more");
					URL resource = resources.nextElement();
					dirs.add(new File(resource.getFile()));
				}
				classes = new ArrayList<Class>();
				for (File directory : dirs) {
					try {
						System.err.println(directory.toString());
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
		
		public static String readDataFromPropertyFile(String filePath, String key) {

			String value = "";
			Properties prop = new Properties();

			try {
				prop.load(new FileInputStream(new File(filePath)));
				value = prop.getProperty(key);
			} catch (Exception e) {
				System.out.println("property file not found");
				e.printStackTrace();
			}
			return value;
		}
}
