package Ageas;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageFactoryRepo.ageasScreen;
import PageFactoryRepo.gmailLandingScreen;
import PageFactoryRepo.gmailMeetScreen;
import PageFactoryRepo.gmailYoureTheOnlyOneHereScreen;
import Resources.base;

public class AG003_Verify_Screen_Mirroring_Popup extends base {
	public static Logger log = LogManager.getLogger(AG003_Verify_Screen_Mirroring_Popup.class.getName());

	@BeforeTest
	public void opensettingIPA() throws IOException {
		log.info("************ Opening The Gmail Application *****************");
		openGmailApplication();
	}

	@Test
	public void verify_Screen_Mirroring_Popup() throws IOException, InterruptedException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(ageasScreenMirroringTestDataFilePath);
		FileInputStream fis1 = new FileInputStream(credientialsFilePath);
		prop.load(fis);
		prop.load(fis1);
		String gmailScreenVerification = prop.getProperty("Gmail_Screen_Verification");
		String caseSensitive = prop.getProperty("ignoreCase");
		boolean ignoreCase = stringToBoolean(caseSensitive);
		String meetScreenVerification = prop.getProperty("Meet_Screen_Verification");
		String startAnInstantMeetingOptionVerification = prop.getProperty("Start_An_Instant_Meeting_Option_Verification");
		String shareScreenOptionVerification = prop.getProperty("Share_Screen_Option_Verification");
		String presentToEveryoneScreenVerification = prop.getProperty("Present_To_Everyone_Screen_Verification");
		String startBroadcastScreenVerification = prop.getProperty("Start_Broadcast_Screen_Verification");
		String vysourTextPresentAreNot = prop.getProperty("vysourTextPresentAreNot");
		boolean vysourTextIsOrNot=stringToBoolean(vysourTextPresentAreNot);
		String ageasSecurityScreenVerification = prop.getProperty("Ageas_Security_Screen_Verification");
		String expectedTextPop_Up = prop.getProperty("expectedTextPop_Up");
		String screenMirroringPopupName = prop.getProperty("Screen_Mirroring_Popup_Name");
		String screenMirroringDetectedPopupVerification = prop.getProperty("Screen_Mirroring_Detected_Popup_Verification");
		String screenMirroringPopupMessageVerification = prop.getProperty("Screen_Mirroring_Popup_Message_Verification");
		String screenBroadcastPresntOrNot = prop.getProperty("screenBroadcastPresntOrNot");
		boolean screenBroadcastIsOrNot=stringToBoolean(screenBroadcastPresntOrNot);
		String screenMirroringPopupIsNotPresent = prop.getProperty("Screen_Mirroring_Popup_Is_Not_Present");

		
		try {
			// Giving Driver To Rrepo (Pagefactory)
			gmailLandingScreen gls=new gmailLandingScreen(driver);
			gmailMeetScreen gms=new gmailMeetScreen(driver);
			gmailYoureTheOnlyOneHereScreen gytooh=new gmailYoureTheOnlyOneHereScreen(driver);

			log.info("************ Setting The VPN *****************");
			verifyElementIsHavingExpectedText(gls.MettButton, gmailScreenVerification, ignoreCase);
			tap(gls.MettButton, driver);
			verifyElementIsHavingExpectedText(gms.NewMettingButton, meetScreenVerification, ignoreCase);
			tap(gms.NewMettingButton, driver);
			verifyElementIsHavingExpectedText(gms.InstantMeetingButton, startAnInstantMeetingOptionVerification, ignoreCase);
			tap(gms.InstantMeetingButton, driver);
			tap(gytooh.OptionsButton, driver);
			verifyElementIsHavingExpectedText(gytooh.ShareScreenButton, shareScreenOptionVerification, ignoreCase);
			tap(gytooh.ShareScreenButton, driver);
			verifyElementIsHavingExpectedText(gytooh.PresentToEveryoneText, presentToEveryoneScreenVerification, ignoreCase);
			tap(gytooh.ContinueButton, driver);
			verifyElementIsHavingExpectedText(gytooh.StattBroadcastButton, startBroadcastScreenVerification, ignoreCase);
			tap(gytooh.StattBroadcastButton, driver);
			Thread.sleep(5000);
			boolean isdisplayed=verifyElementIsDisplayedAreNot(gytooh.vysourPopupText);
			if (isdisplayed==vysourTextIsOrNot) {
				tap(gytooh.OkButton, driver);
			}
		    pressHomeButton();
		    WaitUntilvisibilityOfElement(gytooh.MeetingLobbyButton);
		    pressHomeButton();
			log.info("************ Successfully Shearing The Screen*****************");
	
		    log.info("************ Opening The Ageas Application *****************");

		    openAgeasApplication();
		    // Giving Driver To Rrepo (Pagefactory)
		    ageasScreen ag = new ageasScreen(driver);

		    verifyElementIsHavingExpectedText(ag.WelcomeText, ageasSecurityScreenVerification, ignoreCase);
		    boolean brodacastScrrenText=verifyElementIsDisplayedAreNot(ag.ScreenBroadcastingText);
		    if (brodacastScrrenText==screenBroadcastIsOrNot) {
				tap(ag.OkButton, driver);
			}
		    ArrayList<String> popUpTextTitle = new ArrayList<>();
		    popUpTextTitle = verifyElementIsHavingExpectedTextAndAddThatTexttoList(ag.skipButton, ag.IdentifiedText,expectedTextPop_Up);
		    String arrayListToString = arrayListToString(popUpTextTitle);
		    writeDataToThePropertiesFile(ageasScreenMirroringTestDataFilePath, screenMirroringPopupName, arrayListToString);
		    boolean screenMirroringIdentifiedTextResult = verifyElementIsHavingExpectedText(ag.ScreenMirroringIdentifiedText,screenMirroringDetectedPopupVerification, ignoreCase);
		    AssertJUnit.assertTrue(screenMirroringIdentifiedTextResult);
			log.info("Screen Mirroring header is Present in the Ageas "+screenMirroringIdentifiedTextResult);
		    boolean screenMirroringIdentifiedMessageTextResult = verifyElementIsHavingExpectedText(ag.ScreenMirroringIdentifiedMessageText, screenMirroringPopupMessageVerification, ignoreCase);
		    AssertJUnit.assertTrue(screenMirroringIdentifiedMessageTextResult);
			log.info("Screen Mirroring Boday is Present in the Ageas "+screenMirroringIdentifiedMessageTextResult);
	      } 
		
		finally {
             log.info("************ Opening The Ageas Application *****************");
			
			openAgeasApplication();
			// Giving Driver To Rrepo (Pagefactory)
			ageasScreen ag = new ageasScreen(driver);
			
			verifyElementIsHavingExpectedText(ag.WelcomeText, ageasSecurityScreenVerification, ignoreCase);
			ArrayList<String> popUpTextTitle = new ArrayList<>();
			popUpTextTitle = verifyElementIsHavingExpectedTextAndAddThatTexttoList(ag.skipButton, ag.IdentifiedText,expectedTextPop_Up);
			String arrayListToString = arrayListToString(popUpTextTitle);
			log.info(arrayListToString);
			boolean screenMirroringIsNotPresent = arrayListToString.equals(screenMirroringPopupIsNotPresent);
			AssertJUnit.assertFalse(screenMirroringIsNotPresent);
			log.info("Screen Mirroring is Not Present in the Ageas "+screenMirroringIsNotPresent);
		}
	}

	@AfterTest
	public void closeApplication() {
		log.info("************ Closeing The Application *****************");
		closeTheApplication();
	}
}

