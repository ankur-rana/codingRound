package Scripts;

import Utilities.BaseLib;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import PageObjects.FlightBookingPage;
import static Utilities.CommonLibrary.*;

public class FlightBookingTest extends BaseLib {



    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        FlightBookingPage fbp = new FlightBookingPage(driver);
        SoftAssert sa= new SoftAssert();
        if(click(driver, fbp.getOneWayRadioButton(30))){
        	Reporter.log("Clicked on one way radio button.");
        	if(sendKeys(driver, fbp.getFromTextBox(30), "Bangalore")){
        		Reporter.log("Passed value to from text box.");
        		if(fbp.selectValueFromTheSuggestions("Bangalore", 30)){
        			if(sendKeys(driver, fbp.getToTextBox(30), "Delhi")){
                		Reporter.log("Passed value to 'To' text box.");
                		if(fbp.selectValueFromTheSuggestions("Delhi", 30)){
                			if(click(driver, fbp.getDepartDatePickerTextBox(30))){
                				if(fbp.selectCurrentDate(30)){
                					Reporter.log("Successfully selected the date from date picker");
                					if(click(driver, fbp.getSearchFlightsButton(30))){
                						Reporter.log("Successfully clicked on search flight button.");
                						if(fbp.getSearchSummaryHeader(30)!=null){
                							String text = fbp.getSearchSummaryHeader(30).getText();
                							sa.assertTrue(text.contains("Bangalore") && text.contains("Delhi"), "Search summary of searched flight is not displaying. Expected: Bangalore To Delhi\tActual:"+text);
                						} else {
                							Reporter.log("Search summary is not displaying.");
                							sa.assertTrue(false, "Search summary is not displaying.");
                						}
                					} else {
                						Reporter.log("Cannot click on search flight button, So cannot continue with the execution.");
                						sa.assertTrue(false, "Cannot click on search flight button, So din't continue with the execution.");
                					}
                				} else {
                					Reporter.log("Not able to select current date, So cannot continue with the execution.");
                					sa.assertTrue(false, "Not able to select current date, So din't continue with the execution.");
                				}
                			} else {
                				Reporter.log("Not able to click on Depart Date picker, So cannot continue with the execution.");
                				sa.assertTrue(false, "Not able to click on Depart Date picker, So din't continue with the execution.");
                			}
                		} else {
                			Reporter.log("Not able to select value from the 'To' suggestions.");
                			sa.assertTrue(false, "Not able to select value from the 'To' suggestions.");
                		}
                	} else {
                		Reporter.log("Cannot enter text in 'To' text box, So cannot continue with the execution.");
                		sa.assertTrue(false, "Cannot enter text in 'To' text box, So din't continue with the execution.");
                	}
        		} else {
        			Reporter.log("Not able to select value from the suggestions.");
        			sa.assertTrue(false, "Not able to select value from the suggestions.");
        		}
        	} else {
        		Reporter.log("Cannot enter text in from text box, So cannot continue with the execution.");
        		sa.assertTrue(false, "Cannot enter text in from text box, So din't continue with the execution.");
        	}
        } else {
        	Reporter.log("Cannot click on One Way radio button, So cannot continue with the execution.");
        	sa.assertTrue(false, "Cannot click on One Way radio button, So din't continue with the execution.");
        }
        sa.assertAll();
    }


    
    
}
