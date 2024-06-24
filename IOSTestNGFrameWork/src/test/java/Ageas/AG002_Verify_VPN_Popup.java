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
import PageFactoryRepo.settingGeneralScreen;
import PageFactoryRepo.settingLandingScreen;
import PageFactoryRepo.settingVpnAndDeviceManagementScreen;
import PageFactoryRepo.settingVpnScreen;
import Resources.base;

public class AG002_Verify_VPN_Popup extends base {
	public static Logger log = LogManager.getLogger(AG002_Verify_VPN_Popup.class.getName());

	@BeforeTest
	public void opensettingIPA() throws IOException {
		log.info("************ Opening The Setting Application *****************");
		openSettingApplication();
	}

	@Test
	public void verify_VPN_Popup() throws IOException, InterruptedException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(ageasVpnTestDataFilePath);
		FileInputStream fis1 = new FileInputStream(credientialsFilePath);
		prop.load(fis);
		prop.load(fis1);
		String settingsScreenVerification = prop.getProperty("Settings_Screen_Verification");
		String caseSensitive = prop.getProperty("ignoreCase");
		boolean ignoreCase = stringToBoolean(caseSensitive);
		String generalScreenVerification = prop.getProperty("General_Screen_Verification");
		String vpnAndDeviceManagementScreenVerification = prop.getProperty("VPN_And_Device_Management_Verification");
		String notConnectedStatus = prop.getProperty("Not_Connected_Status");
		String vpnScreenVerification = prop.getProperty("Vpn_Screen_Verification");
		String ageasSecurityScreenVerification = prop.getProperty("Ageas_Security_Screen_Verification");
		String expectedTextPop_Up = prop.getProperty("Expected_Text_Pop_Up");
		String vpnNetworkPopupName = prop.getProperty("VPN_Network_Popup_Name");
		String vpnNetworkIdentifiedVerification = prop.getProperty("VPN_Network_Identified_Verification");
		String vpnNetworkPopupMessageVerification = prop.getProperty("VPN_Network_Popup_Message_Verification");
		String Connected_Status = prop.getProperty("Connected_Status");
		String vpnPopupIsNotPresentInAgeas = prop.getProperty("VPN_Popup_Is_Not_Present");

		try {
			// Giving Driver To Rrepo (Pagefactory)
			settingLandingScreen sls = new settingLandingScreen(driver);
			settingGeneralScreen sgs = new settingGeneralScreen(driver);
			settingVpnAndDeviceManagementScreen smis = new settingVpnAndDeviceManagementScreen(driver);
			settingVpnScreen svs = new settingVpnScreen(driver);

			log.info("************ Setting The VPN *****************");

			verifyElementIsHavingExpectedText(sls.SettingsText, settingsScreenVerification, ignoreCase);
			sls.GeneralIcon.click();
			verifyElementIsHavingExpectedText(sgs.GeneralText, generalScreenVerification, ignoreCase);
			sgs.ManagedConfigurationListButton.click();
			verifyElementIsHavingExpectedText(smis.VpnAndDeviceManagementText, vpnAndDeviceManagementScreenVerification,ignoreCase);
			String obtainedText = getTextFromElement(smis.VPNStatusText);
			if (notConnectedStatus.equals(obtainedText)) {
				tap(smis.VPNStatusText, driver);
				verifyElementIsHavingExpectedText(svs.VPNText, vpnScreenVerification, ignoreCase);
				svs.VPNStatusToggleButton.click();
				log.info("************ Successfully Setted The VPN *****************");
			}
			log.info("************ Opening The Ageas Application *****************");

			openAgeasApplication();
			// Giving Driver To Rrepo (Pagefactory)
			ageasScreen ag = new ageasScreen(driver);

			verifyElementIsHavingExpectedText(ag.WelcomeText, ageasSecurityScreenVerification, ignoreCase);
			ArrayList<String> popUpTextTitle = new ArrayList<>();
			popUpTextTitle = verifyElementIsHavingExpectedTextAndAddThatTexttoList(ag.skipButton, ag.IdentifiedText,expectedTextPop_Up);
			String arrayListToString = arrayListToString(popUpTextTitle);
			writeDataToThePropertiesFile(ageasVpnTestDataFilePath, vpnNetworkPopupName, arrayListToString);
			boolean vpnNetworkIdentifiedTextResult = verifyElementIsHavingExpectedText(ag.VPNNetworkIdentifiedText,vpnNetworkIdentifiedVerification, ignoreCase);
			AssertJUnit.assertTrue(vpnNetworkIdentifiedTextResult);
			log.info("Vpn Network header is Present in the Ageas "+vpnNetworkIdentifiedTextResult);
			boolean vpnNetworkIdentifiedMessageTextResult = verifyElementIsHavingExpectedText(ag.VPNNetworkIdentifiedMessageText, vpnNetworkPopupMessageVerification, ignoreCase);
			AssertJUnit.assertTrue(vpnNetworkIdentifiedMessageTextResult);
			log.info("Vpn Network header Boday is Present in the Ageas "+vpnNetworkIdentifiedMessageTextResult);

		}

		finally {
			log.info("************ Opening The Setting Application *****************");

			openSettingApplication();
			// Giving Driver To Rrepo (Pagefactory)
			settingLandingScreen sls = new settingLandingScreen(driver);
			settingGeneralScreen sgs = new settingGeneralScreen(driver);
			settingVpnAndDeviceManagementScreen smis = new settingVpnAndDeviceManagementScreen(driver);
			settingVpnScreen svs = new settingVpnScreen(driver);
			
			log.info("************ Switching Off The VPN *****************");
			verifyElementIsHavingExpectedText(sls.SettingsText, settingsScreenVerification, ignoreCase);
			sls.GeneralIcon.click();
			verifyElementIsHavingExpectedText(sgs.GeneralText, generalScreenVerification, ignoreCase);
			sgs.ManagedConfigurationListButton.click();
			verifyElementIsHavingExpectedText(smis.VpnAndDeviceManagementText, vpnAndDeviceManagementScreenVerification,ignoreCase);
			String obtainedText = getTextFromElement(smis.VPNStatusText);
			if (Connected_Status.equals(obtainedText)) {
				tap(smis.VPNStatusText, driver);
				verifyElementIsHavingExpectedText(svs.VPNText, vpnScreenVerification, ignoreCase);
				svs.VPNStatusToggleButton.click();
				log.info("************ Successfully Turned Off The VPN *****************");
			}

			log.info("************ Opening The Ageas Application *****************");
			
			openAgeasApplication();
			// Giving Driver To Rrepo (Pagefactory)
			ageasScreen ag = new ageasScreen(driver);
			
			verifyElementIsHavingExpectedText(ag.WelcomeText, ageasSecurityScreenVerification, ignoreCase);
			ArrayList<String> popUpTextTitle = new ArrayList<>();
			popUpTextTitle = verifyElementIsHavingExpectedTextAndAddThatTexttoList(ag.skipButton, ag.IdentifiedText,expectedTextPop_Up);
			String arrayListToString = arrayListToString(popUpTextTitle);
			log.info(arrayListToString);
			boolean vpnPopupIsNotPresent = arrayListToString.equals(vpnPopupIsNotPresentInAgeas);
			AssertJUnit.assertFalse(vpnPopupIsNotPresent);
			log.info("Vpn is Not Present in the Ageas "+vpnNetworkPopupMessageVerification);
		}
	}

	@AfterTest
	public void closeApplication() {
		log.info("************ Closeing The Application *****************");
		closeTheApplication();
	}
}
