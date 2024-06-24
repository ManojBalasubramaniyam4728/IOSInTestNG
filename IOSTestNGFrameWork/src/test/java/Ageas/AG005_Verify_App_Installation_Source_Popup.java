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
import Resources.base;

public class AG005_Verify_App_Installation_Source_Popup extends base {
	public static Logger log = LogManager.getLogger(AG005_Verify_App_Installation_Source_Popup.class.getName());

	@BeforeTest
	public void opensettingIPA() throws IOException {
		log.info("************ Opening The Ageas Application *****************");
		openAgeasApplication();
	}

	@Test
	public void Verify_App_Installation_Source_Popup() throws IOException, InterruptedException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(ageasAppInstallationTestDataFilePath);
		FileInputStream fis1 = new FileInputStream(credientialsFilePath);
		prop.load(fis);
		prop.load(fis1);
		String ageasSecurityScreenVerification = prop.getProperty("Ageas_Security_Screen_Verification");
		String caseSensitive = prop.getProperty("ignoreCase");
		boolean ignoreCase = stringToBoolean(caseSensitive);
		String expectedTextPopUp = prop.getProperty("Expected_Text_Pop_Up");
		String insecureAppPopupName = prop.getProperty("Insecure_App_Popup_Name");
		String insecureAppInstallationIdentifiedPopupVerification = prop.getProperty("Insecure_App_Installation_Identified_Popup_Verification");
		String insecureAPPPopupMessageVerification = prop.getProperty("Insecure_APP_Popup_Message_Verification");

		ageasScreen ag = new ageasScreen(driver);

		verifyElementIsHavingExpectedText(ag.WelcomeText, ageasSecurityScreenVerification, ignoreCase);
		ArrayList<String> popUpTextTitle = new ArrayList<>();
		popUpTextTitle = verifyElementIsHavingExpectedTextAndAddThatTexttoList(ag.skipButton, ag.IdentifiedText,
				expectedTextPopUp);
		String arrayListToString = arrayListToString(popUpTextTitle);
		writeDataToThePropertiesFile(ageasAppInstallationTestDataFilePath, insecureAppPopupName, arrayListToString);
		boolean insecureAppInstallationIdentifiedIdentifiedTextResult = verifyElementIsHavingExpectedText(ag.insecureAppInstallationIdentifiedText, insecureAppInstallationIdentifiedPopupVerification,ignoreCase);
		AssertJUnit.assertTrue(insecureAppInstallationIdentifiedIdentifiedTextResult);
		log.info("Insecure AppInstallation header is Present in the Ageas "+insecureAppInstallationIdentifiedIdentifiedTextResult);
		boolean insecureAppInstallationIdentifiedMessageTextResult = verifyElementIsHavingExpectedText(ag.InsecureAppInstallationIdentifiedMessageText, insecureAPPPopupMessageVerification, ignoreCase);
		AssertJUnit.assertTrue(insecureAppInstallationIdentifiedMessageTextResult);
		log.info("Insecure AppInstallation Boday is Present in the Ageas "+insecureAppInstallationIdentifiedMessageTextResult);

	}

	@AfterTest
	public void closeApplication() {
		log.info("************ Closeing The Application *****************");
		closeTheApplication();
	}
}
