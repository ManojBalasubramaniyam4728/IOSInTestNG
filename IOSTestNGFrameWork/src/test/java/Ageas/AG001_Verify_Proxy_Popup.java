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
import PageFactoryRepo.settingConfigureProxyScreen;
import PageFactoryRepo.settingLandingScreen;
import PageFactoryRepo.settingMoreInfoScreen;
import PageFactoryRepo.settingWifiScreen;
import Resources.base;

public class AG001_Verify_Proxy_Popup extends base {
public static Logger log=LogManager.getLogger(AG001_Verify_Proxy_Popup.class.getName());
	
	@BeforeTest
	public void opensettingIPA() throws IOException {
		log.info("************ Opening The Setting Application *****************");
		openSettingApplication();
	}
	
	@Test  
	public void verify_proxy_checks() throws IOException, InterruptedException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(ageasProxyTestDataFilePath);
		FileInputStream fis1 = new FileInputStream(credientialsFilePath);
		prop.load(fis);
		prop.load(fis1);
		String settingsScreenVerification=prop.getProperty("Settings_Screen_Verification");
		String wifiTextVerification = prop.getProperty("Wifi_Text_Verification");
		String caseSensitive=prop.getProperty("ignoreCase");
		boolean ignoreCase=stringToBoolean(caseSensitive);
		String configureProxyScreenVerification=prop.getProperty("Configure_Proxy_Screen_Verification");
		String ifProxyInOff=prop.getProperty("ifProxyInOff");
		boolean proxyButtonOf=stringToBoolean(ifProxyInOff);
		String ifProxyInManual=prop.getProperty("ifProxyInManual");
		boolean proxyButtonManual=stringToBoolean(ifProxyInManual);
		String servernumber=prop.getProperty("Server_number");
		String portNumber=prop.getProperty("Port_Number");
		String ageasSecurityScreenVerification=prop.getProperty("Ageas_Security_Screen_Verification");
		String expectedTextPop_Up=prop.getProperty("Expected_Text_Pop_Up");
		String proxyPopupName=prop.getProperty("proxy_Popup_Name");
		String prox_NetworkdentifiedTextVerification=prop.getProperty("Proxy_Network_dentified_Text_Verification");
		String proxyNetworkPopupMessage=prop.getProperty("Proxy_Network_Popup_Message");
		String proxyPopupIsNotPresentInAgeas=prop.getProperty("proxy_Popup_Is_Not_Present");
		
		
	 try {
		//Giving Driver To Rrepo (Pagefactory)
		settingLandingScreen  sls=new settingLandingScreen(driver);
	    settingWifiScreen     sws=new settingWifiScreen(driver);
	    settingMoreInfoScreen smis=new settingMoreInfoScreen(driver);
	    settingConfigureProxyScreen scps=new settingConfigureProxyScreen(driver);
			
		log.info("************ Setting The Proxy *****************");
		
	    verifyElementIsHavingExpectedText(sls.SettingsText, settingsScreenVerification,ignoreCase);
        tap(sls.WiFiButton, driver);
        verifyElementIsHavingExpectedText(sws.WiFiText, wifiTextVerification, ignoreCase);
        tap(sws.MoreInfoIcon, driver);
        boolean isDispalyed=verifyElementIsDisplayedAreNot(smis.OffText);
        if(isDispalyed==proxyButtonOf) {
           smis.ConfigureProxyButton.click();
           log.info("Successfully Clicked On"+" "+smis.ConfigureProxyButton);
           verifyElementIsHavingExpectedText(scps.ConfigureProxyText, configureProxyScreenVerification, ignoreCase);
           tap(scps.ManualButton, driver);
           clearTheTextAndEnterInputIntoTheElement(scps.ServerTextfield, servernumber);
           clearTheTextAndEnterInputIntoTheElement(scps.PortTextfield, portNumber);
           tap(scps.SaveButton, driver);
           log.info("************ Successfully Setted The Proxy *****************");
         }
        log.info("************ Opening The Ageas Application *****************");
        
        openAgeasApplication();
       //Giving Driver To Rrepo (Pagefactory)
        ageasScreen ag=new ageasScreen(driver);
        
        verifyElementIsHavingExpectedText(ag.WelcomeText,ageasSecurityScreenVerification ,ignoreCase);
        ArrayList<String> popUpTextTitle = new ArrayList<>();
        popUpTextTitle=verifyElementIsHavingExpectedTextAndAddThatTexttoList(ag.skipButton, ag.IdentifiedText, expectedTextPop_Up);
        String arrayListToString= arrayListToString(popUpTextTitle);
        writeDataToThePropertiesFile(ageasProxyTestDataFilePath, proxyPopupName, arrayListToString);
        boolean ProxyNetworkIdentifiedTextResult=verifyElementIsHavingExpectedText(ag.ProxyNetworkIdentifiedText,prox_NetworkdentifiedTextVerification, ignoreCase);
        AssertJUnit.assertTrue(ProxyNetworkIdentifiedTextResult);
		log.info("Proxy Network header is Present in the Ageas "+ProxyNetworkIdentifiedTextResult);
        boolean ProxyNetworkIdentifiedMessageTextResult= verifyElementIsHavingExpectedText(ag.ProxyNetworkIdentifiedMessageText,proxyNetworkPopupMessage, ignoreCase);
        AssertJUnit.assertTrue(ProxyNetworkIdentifiedMessageTextResult);
		log.info("Proxy Network header Boday is Present in the Ageas "+ProxyNetworkIdentifiedMessageTextResult);
	    }
	 
	 finally {
		log.info("************ Opening The Setting Application *****************");
		
		openSettingApplication();
		//Giving Driver To Rrepo (Pagefactory)
		settingLandingScreen  sls=new settingLandingScreen(driver);
		settingWifiScreen     sws=new settingWifiScreen(driver);
	    settingMoreInfoScreen smis=new settingMoreInfoScreen(driver);
	    settingConfigureProxyScreen scps=new settingConfigureProxyScreen(driver);
	    
        log.info("************ Switching Off The Proxy *****************");
	    verifyElementIsHavingExpectedText(sls.SettingsText, settingsScreenVerification,ignoreCase);
        tap(sls.WiFiButton, driver);
        verifyElementIsHavingExpectedText(sws.WiFiText, wifiTextVerification, ignoreCase);
        tap(sws.MoreInfoIcon, driver);
        boolean isDispalyed=verifyElementIsDisplayedAreNot(smis.ManualText);
        if(isDispalyed==proxyButtonManual) {
            smis.ConfigureProxyButton.click();
            log.info("Successfully Clicked On"+" "+smis.ConfigureProxyButton);
            tap(smis.ConfigureProxyButton, driver);
            verifyElementIsHavingExpectedText(scps.ConfigureProxyText, configureProxyScreenVerification, ignoreCase);
            tap(scps.OffButton, driver);
            tap(scps.SaveButton, driver);
           log.info("************ Successfully Turned Off The Proxy *****************");
        }
        log.info("************ Opening The Ageas Application *****************");
        
        openAgeasApplication();
        //Giving Driver To Rrepo (Pagefactory)
        ageasScreen ag=new ageasScreen(driver);
        
        verifyElementIsHavingExpectedText(ag.WelcomeText,ageasSecurityScreenVerification ,ignoreCase);
        ArrayList<String> popUpTextTitle = new ArrayList<>();
        popUpTextTitle=verifyElementIsHavingExpectedTextAndAddThatTexttoList(ag.skipButton, ag.IdentifiedText, expectedTextPop_Up);
        String arrayListToString= arrayListToString(popUpTextTitle);
        log.info(arrayListToString);
        boolean proxyPopupIsNotPresent=arrayListToString.equals(proxyPopupIsNotPresentInAgeas);
        AssertJUnit.assertFalse(proxyPopupIsNotPresent);
        log.info("Proxy is Not Present in the Ageas "+proxyPopupIsNotPresent);
		    }
		}
	
	@AfterTest
	public void  closeApplication() {
		log.info("************ Closeing The Application *****************");
		closeTheApplication();
	}
}