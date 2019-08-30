package Scripts;

import Utilities.BaseLib;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import PageObjects.ErrorMessageFile;
import PageObjects.LoginPage;
import static Utilities.CommonLibrary.*;

public class SignInTest extends BaseLib {


    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

    	LoginPage lp = new LoginPage(driver);
    	SoftAssert sa = new SoftAssert();
    	if(click(driver, lp.getYourTripLink(30))){
    		if(click(driver, lp.getSignInButton(30))){
    			if(switchToFrame(driver, lp.getSignInFrame(30))){
    				if(click(driver, lp.getSignInButtonOnCredentialsPopUp(30))){
        				if(lp.getSignInErrorMessage(30)!=null){
        					String text = lp.getSignInErrorMessage(2).getText();
        					if(text.contains(ErrorMessageFile.signInErrorMessage1) && text.contains(ErrorMessageFile.signInErrorMessage1) && text.contains(ErrorMessageFile.signInErrorMessage1)){
        						Reporter.log("Error Message successfully verified.");
        					} else {
        						Reporter.log("Error message is not verified. Expected: "+ErrorMessageFile.signInErrorMessage1+"\n"+ErrorMessageFile.signInErrorMessage1+"\n"+ErrorMessageFile.signInErrorMessage1+"\tActual:"+text);
        						sa.assertTrue(false, "Error message is not verified. Expected: "+ErrorMessageFile.signInErrorMessage1+"\n"+ErrorMessageFile.signInErrorMessage1+"\n"+ErrorMessageFile.signInErrorMessage1+"\tActual:"+text);
        					}
        				} else {
        					Reporter.log("Sign In Error Message is not displaying.");
        					sa.assertTrue(false, "Sign In Error Message is not displaying.");
        				}
        			} else {
        				Reporter.log("Sign in button on credentials pop up cannot be clicked, So cannot continue with the execution.");
        				sa.assertTrue(false, "Sign in button on credentials pop up cannot be clicked, So cannot continue with the execution.");
        			}
    			} else {
    				Reporter.log("Not able to switch on frame, So cannot continue with the execution.");
    				sa.assertTrue(false, "Not able to switch on frame, So din't continue with the execution.");
    			}
    		} else {
    			Reporter.log("Sign in button cannot be clicked, So cannot continue with the execution.");
    			sa.assertTrue(false, "Sign in button cannot be clicked, So cannot continue with the execution.");
    		}
    	} else {
    		Reporter.log("Your trip link cannot be clicked, So cannot continue with the execution.");
    		sa.assertTrue(false,"Your trip link cannot be clicked, So din't continue with the execution.");
    	}
    	sa.assertAll();
    }

}
