package Scripts;
import com.sun.javafx.PlatformUtil;

import PageObjects.BasePage;
import PageObjects.HotelBookingPage;
import Utilities.BaseLib;
import Utilities.CommonEnums.TabName;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static Utilities.CommonLibrary.*;

public class HotelBookingTest extends BaseLib{


    @Test
    public void shouldBeAbleToSearchForHotels() {
    	HotelBookingPage hbp = new HotelBookingPage(driver);
    	SoftAssert sa = new SoftAssert();
        if(BasePage.clickOnTabs(driver, TabName.Hotels)){
        	Reporter.log("Successfully clicked on Hotel Tab.");
        	if(sendKeys(driver, hbp.getLocalityTextBox(30), "Indiranagar, Bangalore")){
        		Reporter.log("Successfully passed value to locality text box.");
        		if(hbp.selectValueFromTheSuggestions("Bangalore", 30)){
        			if(selectVisibleTextFromTheDropDown(hbp.getTravellerSelection(30), "1 room, 2 adults")){
            			Reporter.log("Successfully selected 1 room, 2 adults");
            			if(click(driver, hbp.getSearchHotelButton(30))){
            				Reporter.log("Successfully clicked on search Hotel button.");
    						if(hbp.getSearchSummaryHeader(30)!=null){
    							String text = hbp.getSearchSummaryHeader(30).getText();
    							sa.assertTrue(text.contains("Bangalore"), "Search summary of searched Hotels is not displaying. Expected: Bangalore\tActual:"+text);
    						} else {
    							Reporter.log("Search summary is not displaying.");
    							sa.assertTrue(false, "Search summary is not displaying.");
    						}
            			} else {
            				Reporter.log("Cannot click on search button, So cannot continue with the execution.");
            				sa.assertTrue(false, "Cannot click on search button, So din't continue with the execution.");
            			}
            		} else {
            			Reporter.log("1 room, 2 adults value is not selected, So cannot continue with the execution.");
            			sa.assertTrue(false, "1 room, 2 adults value is not selected, So din't continue with the execution.");
            		}
        		} else {
        			Reporter.log("Not able to select value from the suggestions.");
        			sa.assertTrue(false, "Not able to select value from the suggestions.");
        		}
        	} else {
        		Reporter.log("Cannot pass value to locality text box, So cannot continue with the execution.");
        		sa.assertTrue(false, "Cannot pass value to locality text box, So din't continue with the execution.");
        	}
        } else {
        	Reporter.log("Cannot click on hotels tab, So cannot continue with the execution.");
        	sa.assertTrue(false, "Cannot click on hotels tab, So din't continue with the execution.");
        }
        sa.assertAll();
    }


}
