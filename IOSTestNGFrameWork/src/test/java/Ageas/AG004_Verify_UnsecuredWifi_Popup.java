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
import PageFactoryRepo.settingLandingScreen;
import PageFactoryRepo.settingWifiScreen;
import Resources.base;

public class AG004_Verify_UnsecuredWifi_Popup extends base{
public static Logger log=LogManager.getLogger(AG004_Verify_UnsecuredWifi_Popup.class.getName());
	
	@BeforeTest
	public void opensettingIPA() throws IOException {
		log.info("************ Opening The Setting Application *****************");
		openSettingApplication();
	}
	
	@Test  
	public void Verify_UnsecuredWifi_Popup() throws IOException, InterruptedException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(ageasUnsecuredwifiTestDataFilePath);
		FileInputStream fis1 = new FileInputStream(credientialsFilePath);
		prop.load(fis);
		prop.load(fis1);
		String settingsScreenVerification=prop.getProperty("Settings_Screen_Verification");
		String caseSensitive=prop.getProperty("ignoreCase");
		boolean ignoreCase=stringToBoolean(caseSensitive);
		String wifiTextVerification = prop.getProperty("Wifi_Text_Verification");
		String unsecuredWifeNotPresent=prop.getProperty("unsecuredWifeNotPresent");
		boolean unsecuredWifeIsNot=stringToBoolean(unsecuredWifeNotPresent);
		String ageasSecurityScreenVerification=prop.getProperty("Ageas_Security_Screen_Verification");
		String unsecuredWiFiPopupName=prop.getProperty("Unsecured_Wi-Fi_Popup_Name");
		String expectedTextPopUp=prop.getProperty("Expected_Text_Pop_Up");
		String unsecuredWiFiIdentifiedPopupVerification=prop.getProperty("Unsecured_Wi-Fi_Identified_Popup_Verification");
		String unsecuredWiFiPopupMessage=prop.getProperty("Unsecured_Wi-Fi_Popup_Message_Verification");
		String unsecuredWifePresent=prop.getProperty("unsecuredWifePresent");
		boolean unsecuredWifeIs=stringToBoolean(unsecuredWifePresent);
		String unsecuredWiFiIdentified=prop.getProperty("Unsecured Wi-Fi Identified");
		
		
		
	 try {
		//Giving Driver To Rrepo (Pagefactory)
		settingLandingScreen  sls=new settingLandingScreen(driver);
	    settingWifiScreen     sws=new settingWifiScreen(driver);
			
		log.info("************ Setting The Unsecured WiFi Network *****************");
		
	    verifyElementIsHavingExpectedText(sls.SettingsText, settingsScreenVerification,ignoreCase);
        tap(sls.WiFiButton, driver);
        verifyElementIsHavingExpectedText(sws.WiFiText, wifiTextVerification, ignoreCase);
        boolean isDisplayed=verifyElementIsDisplayedAreNot(sws.UnsecuredNetwork);
        if (isDisplayed==unsecuredWifeIsNot) {
			tap(sws.MyNetwork, driver);
		}
    	log.info("************ Successfully Oned The Unsceured Wifi *****************");
		
		log.info("************ Opening The Ageas Application *****************");

		openAgeasApplication();
		// Giving Driver To Rrepo (Pagefactory)
		ageasScreen ag = new ageasScreen(driver);

		verifyElementIsHavingExpectedText(ag.WelcomeText, ageasSecurityScreenVerification, ignoreCase);
		ArrayList<String> popUpTextTitle = new ArrayList<>();
		popUpTextTitle = verifyElementIsHavingExpectedTextAndAddThatTexttoList(ag.skipButton, ag.IdentifiedText,expectedTextPopUp);
		String arrayListToString = arrayListToString(popUpTextTitle);
		writeDataToThePropertiesFile(ageasUnsecuredwifiTestDataFilePath, unsecuredWiFiPopupName, arrayListToString);
		boolean unsceuredWifiNetworkIdentifiedTextResult = verifyElementIsHavingExpectedText(ag.UnsecuredWiFiIdentifiedText,unsecuredWiFiIdentifiedPopupVerification, ignoreCase);
		AssertJUnit.assertTrue(unsceuredWifiNetworkIdentifiedTextResult);
		log.info("Unsceured wifi newtwork header is Present in the Ageas "+unsceuredWifiNetworkIdentifiedTextResult);
		boolean unsceuredWifiNetworkIdentifiedMessageTextResult = verifyElementIsHavingExpectedText(ag.VPNNetworkIdentifiedMessageText, unsecuredWiFiPopupMessage, ignoreCase);
		AssertJUnit.assertTrue(unsceuredWifiNetworkIdentifiedMessageTextResult);
		log.info("Unsceured wifi newtwork Boday is Present in the Ageas "+unsceuredWifiNetworkIdentifiedMessageTextResult);
	 }
	 
	 finally {
		log.info("************ Opening The Setting Application *****************");

		openSettingApplication();
		//Giving Driver To Rrepo (Pagefactory)
		settingLandingScreen  sls=new settingLandingScreen(driver);
	    settingWifiScreen     sws=new settingWifiScreen(driver);
        log.info("************ Setting The Secured WiFi Network *****************");
		
	    verifyElementIsHavingExpectedText(sls.SettingsText, settingsScreenVerification,ignoreCase);
        tap(sls.WiFiButton, driver);
        verifyElementIsHavingExpectedText(sws.WiFiText, wifiTextVerification, ignoreCase);
        boolean isDisplayed=verifyElementIsDisplayedAreNot(sws.UnsecuredNetwork);
        if (isDisplayed==unsecuredWifeIs) {
        	tap(sws.MyNetwork, driver);
			log.info("************ Successfully Oned The Sceured Wifi *****************");
		}
		
		log.info("************ Opening The Ageas Application *****************");

		openAgeasApplication();
		
		// Giving Driver To Rrepo (Pagefactory)
		ageasScreen ag = new ageasScreen(driver);
					
	    verifyElementIsHavingExpectedText(ag.WelcomeText, ageasSecurityScreenVerification, ignoreCase);
		ArrayList<String> popUpTextTitle = new ArrayList<>();
	    popUpTextTitle = verifyElementIsHavingExpectedTextAndAddThatTexttoList(ag.skipButton, ag.IdentifiedText,expectedTextPopUp);
		String arrayListToString = arrayListToString(popUpTextTitle);
		log.info(arrayListToString);
		boolean unsecuredWiFiPopupIsNotPresent = arrayListToString.equals(unsecuredWiFiIdentified);
		AssertJUnit.assertFalse(unsecuredWiFiPopupIsNotPresent);
		log.info("Unsceured wifiy is Not Present in the Ageas "+unsecuredWiFiPopupIsNotPresent);
				}
			}

	@AfterTest
		public void closeApplication() {
		log.info("************ Closeing The Application *****************");
		closeTheApplication();
			}
		}
