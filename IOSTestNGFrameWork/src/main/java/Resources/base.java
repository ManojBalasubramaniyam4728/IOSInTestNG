package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.ios.IOSDriver;

public class base {
	public static Logger log = LogManager.getLogger(base.class.getName());

	// global Variables
	public static IOSDriver driver;
	static String path=System.getProperty("user.dir");
	public static String credientialsFilePath = path+"/src/main/java/Resources/credientials.properties";
	public static String ageasProxyTestDataFilePath = path+"/src/main/java/Resources/ageasProxyTestData.properties";
	public static String ageasVpnTestDataFilePath = path+"/src/main/java/Resources/ageasVpnTestData.properties";
	public static String ageasScreenMirroringTestDataFilePath = path+"/src/main/java/Resources/ageasScreenMirroringTestData.properties";
	public static String ageasUnsecuredwifiTestDataFilePath = path+"/src/main/java/Resources/ageasUnsecuredwifiTestData.properties";
	public static String ageasAppInstallationTestDataFilePath = path+"/src/main/java/Resources/ageasAppInstallationTestData.properties";
	public static String ageasEnabledScreenLockTestDataFilePath = path+"/src/main/java/Resources/ageasEnabledScreenLockTestData.properties";
	public static String screenShootFilePath = path+"/Screenshoot/";

//**************************************************************************************************************************************
	// User Defined Method To open Ageas Application

	public static IOSDriver openAgeasApplication() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(credientialsFilePath);
		prop.load(fis);
		String deviceName = prop.getProperty("deviceName");
		String platformName = prop.getProperty("platformName");
		String platformVersion = prop.getProperty("platformVersion");
		String automationName = prop.getProperty("automationName");
		String udid = prop.getProperty("udid");
		String agentPath = prop.getProperty("agentPath");
		String bootstrapPath = prop.getProperty("bootstrapPath");
		String ageasBundleId = prop.getProperty("ageasBundleId");
		String implicitlyWait = prop.getProperty("implicitlyWait");
		Integer implicitlyWaitTime = Integer.parseInt(implicitlyWait);
		String baseUrl = prop.getProperty("url");

		// Creating Capabilities
		DesiredCapabilities cap = new DesiredCapabilities();

		// Setting Capabilities
		cap.setCapability("deviceName", deviceName);
		cap.setCapability("platformName", platformName);
		cap.setCapability("platformVersion", platformVersion);
		cap.setCapability("automationName", automationName);
		cap.setCapability("udid", udid);
		cap.setCapability("agentPath", agentPath);
		cap.setCapability("bootstrapPath", bootstrapPath);
		cap.setCapability("bundleId", ageasBundleId);

		// Url To server
		URL url = new URL(baseUrl);

		// open the app
		driver = new IOSDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWaitTime));
		log.info("Successfully Opened The Application");
		return driver;
	}

//**************************************************************************************************************************************
	// User Defined Method To open Setting Application
	public static IOSDriver openSettingApplication() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(credientialsFilePath);
		prop.load(fis);
		String deviceName = prop.getProperty("deviceName");
		String platformName = prop.getProperty("platformName");
		String platformVersion = prop.getProperty("platformVersion");
		String automationName = prop.getProperty("automationName");
		String udid = prop.getProperty("udid");
		String agentPath = prop.getProperty("agentPath");
		String bootstrapPath = prop.getProperty("bootstrapPath");
		String settingsBundleId = prop.getProperty("settingsBundleId");
		String implicitlyWait = prop.getProperty("implicitlyWait");
		Integer implicitlyWaitTime = Integer.parseInt(implicitlyWait);
		String baseUrl = prop.getProperty("url");

		// Creating Capabilities
		DesiredCapabilities cap = new DesiredCapabilities();

		// Setting Capabilities
		cap.setCapability("deviceName", deviceName);
		cap.setCapability("platformName", platformName);
		cap.setCapability("platformVersion", platformVersion);
		cap.setCapability("automationName", automationName);
		cap.setCapability("udid", udid);
		cap.setCapability("agentPath", agentPath);
		cap.setCapability("bootstrapPath", bootstrapPath);
		cap.setCapability("bundleId", settingsBundleId);

		// Url To server
		URL url = new URL(baseUrl);

		// open the app
		driver = new IOSDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWaitTime));
		log.info("Successfully Opened The Application");
		return driver;
	}

//**************************************************************************************************************************************
	// User Defined Method To open Gmail Application
	public static IOSDriver openGmailApplication() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(credientialsFilePath);
		prop.load(fis);
		String deviceName = prop.getProperty("deviceName");
		String platformName = prop.getProperty("platformName");
		String platformVersion = prop.getProperty("platformVersion");
		String automationName = prop.getProperty("automationName");
		String udid = prop.getProperty("udid");
		String agentPath = prop.getProperty("agentPath");
		String bootstrapPath = prop.getProperty("bootstrapPath");
		String gmailBundleId = prop.getProperty("gmailBundleId");
		String implicitlyWait = prop.getProperty("implicitlyWait");
		Integer implicitlyWaitTime = Integer.parseInt(implicitlyWait);
		String baseUrl = prop.getProperty("url");

		// Creating Capabilities
		DesiredCapabilities cap = new DesiredCapabilities();

		// Setting Capabilities
		cap.setCapability("deviceName", deviceName);
		cap.setCapability("platformName", platformName);
		cap.setCapability("platformVersion", platformVersion);
		cap.setCapability("automationName", automationName);
		cap.setCapability("udid", udid);
		cap.setCapability("agentPath", agentPath);
		cap.setCapability("bootstrapPath", bootstrapPath);
		cap.setCapability("bundleId", gmailBundleId);

		// Url To server
		URL url = new URL(baseUrl);

		// open the app
		driver = new IOSDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWaitTime));
		log.info("Successfully Opened The Application");
		return driver;
	}

//**************************************************************************************************************************************
	// User Defined Method To Explicit Wait
	public void WaitUntilvisibilityOfElement(WebElement Element) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(credientialsFilePath);
		prop.load(fis);
		String explicitWait = prop.getProperty("explicitWait");
		Integer explicitWaitTime = Integer.parseInt(explicitWait);
		// logic gos here
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
		wait.until(ExpectedConditions.visibilityOf(Element));
		log.info("Successfully Waited Till visibility Of " + " " + Element);
	}

//**************************************************************************************************************************************
	// User Defined Method To Explicit Wait
	public void LongWaitUntilvisibilityOfElement(WebElement Element) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(credientialsFilePath);
		prop.load(fis);
		String explicitLongWait = prop.getProperty("explicitLongWait");
		Integer explicitLongWaitTime = Integer.parseInt(explicitLongWait);
		// logic gos here
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitLongWaitTime));
		wait.until(ExpectedConditions.visibilityOf(Element));
		log.info("Successfully Waited Till visibility Of " + " " + Element);
	}

//**************************************************************************************************************************************
	// User Defined Method To Explicit Wait LocaterValue
	public void WaitUntilvisibilityOfElementLocated(By.ByXPath LocaterValue) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(credientialsFilePath);
		prop.load(fis);
		String explicitWait = prop.getProperty("explicitWait");
		Integer explicitWaitTime = Integer.parseInt(explicitWait);
		// logic gos here
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
		wait.until(ExpectedConditions.visibilityOfElementLocated(LocaterValue));
		log.info("Successfully Waited Till visibility Of " + " " + LocaterValue);
	}

//**************************************************************************************************************************************
	// User Defined Method To Tap on Element
	public void tap(WebElement element, IOSDriver driver) throws IOException {
		Point sourceLocation = element.getLocation();
		Dimension sourceSize = element.getSize();
		int centerX = sourceLocation.getX() + sourceSize.getWidth() / 2;
		int centerY = sourceLocation.getY() + sourceSize.getHeight() / 2;

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, centerY));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(tap));
		log.info("Successfully Taped on " + " " + element);
	}

//**************************************************************************************************************************************
	// User Defined Method For Closing The Application
	public void closeTheApplication() {
		driver.quit();
		driver = null;
		log.info("Successfully Closing The Application");
	}

//**************************************************************************************************************************************
	// User Defined Method To Swipe To The Element
	public void swipeToTheElement(WebElement element, int maximumCount, IOSDriver driver) throws IOException {
		// Taking mobile X and y center of screen
		int centerScreenX = driver.manage().window().getSize().getWidth() / 2;
		int centerScreenY = driver.manage().window().getSize().getHeight() / 2;

		// Frome center of the screen swiping 30% upword
		int endY = (int) (driver.manage().window().getSize().getHeight() * 0.02);

		int maxCount = maximumCount;
		int count = 0;
		for (int i = count; i < maxCount; i++) {
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence swipe = new Sequence(finger, 1);

			try {
				if (element.isDisplayed()) {
					break;
				}
			} catch (NoSuchElementException e) {
				swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerScreenX,
						centerScreenY));
				swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(),
						centerScreenX, endY));
				swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				driver.perform(Arrays.asList(swipe));
			}
			if (count >= maxCount) {
				break;
			}
		}
		log.info("Successfull Swipe To The Elemennt");
	}

//**************************************************************************************************************************************
	// User Defined Method For Taking Screnshot On Failed Steps
	public void getScreenShot(String result) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(screenShootFilePath + result + "screenshot.png"));
		log.info("Successfull Captcher the Screenshoot");
	}

//**************************************************************************************************************************************
	// Verify Element Is having Expected Text
	public boolean verifyElementIsHavingExpectedText(WebElement element, String expectedText, boolean ignoreCase) {
		// Get the text from the element
		String actualText = element.getText();
		// Perform text verification (case insensitive)
		boolean isTextMatching;

		if (ignoreCase == true) {
			isTextMatching = actualText.equalsIgnoreCase(expectedText);
		} else {
			isTextMatching = actualText.equals(expectedText);
		}

		// Display the result
		if (isTextMatching == true) {
			log.info("The " + element + " is having the " + expectedText + " text");
		} else {
			log.info("The " + element + " is not having the " + expectedText + " text");
		}

		return isTextMatching;
	}

//**************************************************************************************************************************************
	// Verify Element Is Contains Expected Text
	public boolean verifyElementIsContainsExpectedText(WebElement element, String expectedText, boolean ignoreCase) {
		// Get the text from the element
		String actualText = element.getText();
		// Perform text verification (case insensitive)
		boolean isTextMatching;

		if (ignoreCase == true) {
			isTextMatching = actualText.toLowerCase().contains(expectedText);
		} else {
			isTextMatching = actualText.contains(expectedText);
		}

		// Display the result
		if (isTextMatching == true) {
			log.info("The " + element + " is contains the " + expectedText + " text");
		} else {
			log.info("The " + element + " is not contains the " + expectedText + " text");
		}
		return isTextMatching;

	}

//**************************************************************************************************************************************
	// Verify Attribute Of Element Is having Expected Text
	public boolean verifyAttributOfElementIsHavingExpectedText(WebElement element, String attributName,
			String expectedText, boolean ignoreCase) {
		// Get the text from the element
		String actualText = element.getAttribute(attributName);
		// Perform text verification (case insensitive)
		boolean isTextMatching;

		if (ignoreCase == true) {
			isTextMatching = actualText.equalsIgnoreCase(expectedText);
		} else {
			isTextMatching = actualText.equals(expectedText);
		}

		// Display the result
		if (isTextMatching == true) {
			log.info("The " + attributName + " of " + element + " is having the " + expectedText + " text");
		} else {
			log.info("The " + attributName + " of " + element + " is not having the " + expectedText + " text");
		}
		return isTextMatching;

	}

//**************************************************************************************************************************************
	// Verify Attribute Of Element Is Contains Expected Text
	public boolean verifyAttributOfElementIsContainsExpectedText(WebElement element, String attributName,
			String expectedText, boolean ignoreCase) {
		// Get the text from the element
		String actualText = element.getAttribute(attributName);
		// Perform text verification (case insensitive)
		boolean isTextMatching;

		if (ignoreCase == true) {
			isTextMatching = actualText.toLowerCase().contains(expectedText);
		} else {
			isTextMatching = actualText.contains(expectedText);
		}

		// Display the result
		if (isTextMatching == true) {
			log.info("The " + attributName + " of " + element + " is contains the " + expectedText + " text");
		} else {
			log.info("The " + attributName + " of " + element + " is not contains the " + expectedText + " text");
		}
		return isTextMatching;

	}

//**************************************************************************************************************************************
	// Verify Element is displayed are Not
	public boolean verifyElementIsDisplayedAreNot(WebElement element) {
		boolean isDisplayed;
		try {
			isDisplayed = element.isDisplayed();
			isDisplayed = true;
			log.info(element + " " + "Is Displayed In The Screen");

		} catch (NoSuchElementException e) {
			isDisplayed = false;
			log.info(element + " " + "Is Not Displayed In The Screen");
		}
		return isDisplayed;
	}

//**************************************************************************************************************************************
	// Enter Input Into The Element
	public void enterInputIntoTheElement(WebElement element, String input) {
		element.sendKeys(input);
		log.info("Successfully Entered" + " " + input + " " + "Into The" + " " + element);
	}

//**************************************************************************************************************************************
	// Clear The Text And Enter The Input Into The Element
	public void clearTheTextAndEnterInputIntoTheElement(WebElement element, String input) {
		element.clear();
		element.sendKeys(input);
		log.info("Successfully Cleared The Text And Entered" + " " + input + " " + "Into The " + " " + element);
	}

//**************************************************************************************************************************************
	// Verify Element is having Expected Text And Add That Text to List
	public ArrayList<String> verifyElementIsHavingExpectedTextAndAddThatTexttoList(WebElement staticElement,
			WebElement textElement, String expectedText) {
		ArrayList<String> popUpTextTitle = new ArrayList<>();
		try {
			while (staticElement.isDisplayed()) {
				String actualText = textElement.getText();
				popUpTextTitle.add(actualText);
				Boolean result = actualText.contains(expectedText);
				if (result == true) {
					break;
				} else if (result == false) {
					staticElement.click();
				}
			}

			log.info("actualText" + " " + "Contains" + " " + expectedText);
		} catch (NoSuchElementException e) {
			log.info(staticElement + "or" + " " + textElement + " " + "Is Not There In The Application Screen");
		} catch (Exception e) {
			log.info("actualText" + " " + "Does not contains" + " " + expectedText + e);

		}
		return popUpTextTitle;

	}

//**************************************************************************************************************************************
	// Write data to the Properties File
	public void writeDataToThePropertiesFile(String propertiesFilePath, String keyData, String valueData) {
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;

		try {
			// Load the existing properties file
			fileInputStream = new FileInputStream(propertiesFilePath);
			properties.load(fileInputStream);

			// Adding new data or update the old data by giving key and value
			properties.setProperty(keyData, valueData);

			// Store the data to the properties file
			fileOutputStream = new FileOutputStream(propertiesFilePath);
			properties.store(fileOutputStream, null);

			log.info("Data are written into the Properties file");

		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

//**************************************************************************************************************************************    
	// Convert ArrayList To String
	public String arrayListToString(ArrayList<String> arrayList) {
		// Convert ArrayList to String using toString() method
		String arrayListAsString = arrayList.toString();
		log.info("Successfully converyed Array List To String And The String Value Is " + arrayListAsString);
		return arrayListAsString;
	}

//**************************************************************************************************************************************  
	// Convert String to Boolean
	public boolean stringToBoolean(String Value) {
		boolean booleanValue = Boolean.parseBoolean(Value);
		log.info("Successfully converted String To Boolean And The Boolean Valve is " + booleanValue);
		return booleanValue;
	}

//**************************************************************************************************************************************  
	// User Defined Method To Tap On Center Of The Screen
	public void tapOnCenterOfTheScreen(IOSDriver driver) throws IOException {
		// Taking mobile X and y center of screen
		int centerScreenX = driver.manage().window().getSize().getWidth() / 2;
		int centerScreenY = driver.manage().window().getSize().getHeight() / 2;

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);

		swipe.addAction(
				finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerScreenX, centerScreenY));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));

		log.info("Successfull Taped On Center Of The Screen");
	}

//**************************************************************************************************************************************          			
	// User Defined Method To Tap X And Y Coordinates
	public void tapOnXAndYCoordinates(IOSDriver driver, int xCoordinate, int yCoordinate) throws IOException {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), xCoordinate,
				yCoordinate));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(tap));
		log.info("Successfully Taped on " + xCoordinate + " And " + yCoordinate);
	}

//**************************************************************************************************************************************          			
	// Get Text From Element
	public String getTextFromElement(WebElement element) throws IOException {
		String obtainedText = element.getText();
		return obtainedText;
	}
	
//**************************************************************************************************************************************          			
	// Get Text From Element
		public void pressHomeButton() throws IOException {
			driver.executeScript("mobile: pressButton", ImmutableMap.of("name", "home"));
		}
//**************************************************************************************************************************************          			
		
}
