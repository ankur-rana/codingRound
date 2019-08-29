/**
 * 
 */
package Launcher;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import Utilities.BaseLib;
import Utilities.CommonLibrary;

/**
 * @author Ankur Rana
 *
 */
public class Executioner {
	
	public static void testNgXmlSuite(String browser) {

		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		List<XmlClass> classes = new ArrayList<XmlClass>();
		List<String> listenerClasses = new ArrayList<String>();
		Map<String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("browser", browser);
		listenerClasses.add("Utilities.Listeners");
		XmlSuite suite = new XmlSuite();
		suite.setName("CodingRoundSuite");
		suite.setListeners(listenerClasses);
		XmlTest test = new XmlTest(suite);
		test.setName("Test");
		test.setParameters(parameters);
		List<XmlClass> xmlClass = new ArrayList<XmlClass>();
		@SuppressWarnings("rawtypes")
		Class[] classesInScriptPackage = CommonLibrary.getClasses("Scripts");
		for(int i = 0; i < classesInScriptPackage.length; i++){
			System.err.println(classesInScriptPackage[i].toString());
			xmlClass.add(new XmlClass(classesInScriptPackage[i].toString().split("class ")[1]));
			classes.add(xmlClass.get(i));
		}
		test.setXmlClasses(classes);
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.run();

	}

	public static void main(String[] args) {
		testNgXmlSuite(CommonLibrary.readDataFromPropertyFile(BaseLib.credentialsAndEnvironmentSetupFilePath,"BrowserName"));
	}
}
