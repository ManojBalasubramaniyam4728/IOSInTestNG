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
import PageFactoryRepo.settingFaceIdAndPasscodeScreen;
import PageFactoryRepo.settingLandingScreen;
import Resources.base;

public class AG006_Enabled_Device_Screen_Lock_Popup extends base{
public static Logger log=LogManager.getLogger(AG006_Enabled_Device_Screen_Lock_Popup.class.getName());
	
	@BeforeTest
	public void opensettingIPA() throws IOException {
		log.info("************ Opening The Setting Application *****************");
		openSettingApplication();
	}
	
	@Test  
	public void Verify_UnsecuredWifi_Popup() throws IOException, InterruptedException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(ageasEnabledScreenLockTestDataFilePath);
		FileInputStream fis1 = new FileInputStream(credientialsFilePath);
		prop.load(fis);
		prop.load(fis1);
		String settingsScreenVerification=prop.getProperty("Settings_Screen_Verification");
		String caseSensitive=prop.getProperty("ignoreCase");
		boolean ignoreCase=stringToBoolean(caseSensitive);
		String enterPasscodeText=prop.getProperty("EnterPasscodeText");
		boolean enterPasscodeTextIs=stringToBoolean(enterPasscodeText);
		String enterPasscodeTextVerification = prop.getProperty("Enter_Passcode_Text_Verification");
		String passcode=prop.getProperty("Passcode");
		String faceIDAndPasscodeScree_Verification=prop.getProperty("Face_ID_And_Passcode_Screen_Verification");
		String turnOffPasscodeScreenVerification=prop.getProperty("Turn_Off_Passcode_Screen_Verification");
		String ageasSecurityScreenVerification=prop.getProperty("Ageas_Security_Screen_Verification");
		String expectedTextPopUp=prop.getProperty("Expected_Text_Pop_Up");
		String deviceLockPopupName=prop.getProperty("Device_Lock_Popup_Name");
		String deviceLockDisabledIdentifiedPopupVerification=prop.getProperty("Device_Lock_Disabled_Identified_Popup_Verification");
		String deviceLockPopupMessageVerification=prop.getProperty("Device_Lock_Popup_Message_Verification");
			
		
		//Giving Driver To Rrepo (Pagefactory)
		settingLandingScreen  sls=new settingLandingScreen(driver);
	    settingFaceIdAndPasscodeScreen   sfisps=new settingFaceIdAndPasscodeScreen(driver);
			
		log.info("************ Setting The Password Free Device *****************");
		
	    verifyElementIsHavingExpectedText(sls.SettingsText, settingsScreenVerification,ignoreCase);
	    sls.FaceIDAndPasscodeIcon.click();
	    boolean isDispalyed=verifyElementIsDisplayedAreNot(sfisps.EnterPasscodeText);
	    if(isDispalyed==enterPasscodeTextIs) {
	    	verifyElementIsHavingExpectedText(sfisps.EnterPasscodeText, enterPasscodeTextVerification, ignoreCase);
	    	tap(sfisps.PasscodeTextfileds, driver);
	    	enterInputIntoTheElement(sfisps.PasscodeTextfileds, passcode);
	    	verifyElementIsHavingExpectedText(sfisps.FaceAndIDPasscodeText, faceIDAndPasscodeScree_Verification, ignoreCase);
	    	tap(sfisps.PasscodeTextfileds, driver);
	    	verifyElementIsHavingExpectedText(sfisps.TurnPasscodeOffText, turnOffPasscodeScreenVerification, ignoreCase);
	    	enterInputIntoTheElement(sfisps.PasscodeTextfileds, passcode);
	    	verifyElementIsHavingExpectedText(sfisps.FaceAndIDPasscodeText, faceIDAndPasscodeScree_Verification, ignoreCase);
	    	
	    	log.info("************ Successfully Setted Password Free Device *****************");
	             }
	    log.info("************ Opening The Ageas Application *****************");

		openAgeasApplication();
		// Giving Driver To Rrepo (Pagefactory)
		ageasScreen ag = new ageasScreen(driver);

		verifyElementIsHavingExpectedText(ag.WelcomeText, ageasSecurityScreenVerification, ignoreCase);
		ArrayList<String> popUpTextTitle = new ArrayList<>();
		popUpTextTitle = verifyElementIsHavingExpectedTextAndAddThatTexttoList(ag.skipButton, ag.IdentifiedText,expectedTextPopUp);
		String arrayListToString = arrayListToString(popUpTextTitle);
		writeDataToThePropertiesFile(ageasEnabledScreenLockTestDataFilePath, deviceLockPopupName, arrayListToString);
		boolean deviceLockDisabledIdentifiedTextResult = verifyElementIsHavingExpectedText(ag.DeviceLockDisabledIdentifiedText,deviceLockDisabledIdentifiedPopupVerification, ignoreCase);
		AssertJUnit.assertTrue(deviceLockDisabledIdentifiedTextResult);
		log.info("device Lock Disabled header is Present in the Ageas "+deviceLockDisabledIdentifiedTextResult);
		boolean deviceLockDisabledIdentifiedMessageTextResult = verifyElementIsHavingExpectedText(ag.DeviceLockDisabledIdentifiedMessageText, deviceLockPopupMessageVerification, ignoreCase);
		AssertJUnit.assertTrue(deviceLockDisabledIdentifiedMessageTextResult);
		log.info("device Lock Disabled Boday is Present in the Ageas "+deviceLockDisabledIdentifiedMessageTextResult);
		 }

	
	@AfterTest
	public void closeApplication() {
		log.info("************ Closeing The Application *****************");
		closeTheApplication();
	}
}
