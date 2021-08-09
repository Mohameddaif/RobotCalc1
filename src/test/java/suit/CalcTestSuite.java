package suit;

import org.testng.annotations.Test;

public class CalcTestSuite {

	WebDriver driver;
	String url;

	@BeforeClass
	public void OpenCalculator() {

		String ChromeDriver_URL = "C:\\Users\\engda\\Desktop\\chromedriver_win32\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", ChromeDriver_URL);
		driver = new ChromeDriver();

		url = "https://www.online-calculator.com/full-screen-calculator/";
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 1)
	public void TestDivision() throws Exception {

		// division 6 / 3 = 2

		Robot robot = new Robot();

		// Mouse click on number 6
		// Mouse click on number 6
		robot.mouseMove(630, 360); // move mouse point to specific location
		robot.delay(1500); // delay is to make code wait for mentioned milliseconds before executing next
							// // step
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click
		robot.delay(1500);

		// Mouse click on Div button
		robot.mouseMove(700, 300); // move mouse point to specific location
		robot.delay(1500); // delay is to make code wait for mentioned milliseconds before executing next
							// // step
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click
		robot.delay(1500);

		// Mouse click on number 3
		robot.mouseMove(630, 430); // move mouse point to specific location
		robot.delay(1500); // delay is to make code wait for mentioned milliseconds before executing next
							// // step
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click
		robot.delay(1500);

		// Mouse click on =
		robot.mouseMove(760, 500); // move mouse point to specific location
		robot.delay(1500); // delay is to make code wait for mentioned milliseconds before executing next
							// // step
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click
		robot.delay(1500);

		String x = ReturnResult(driver, "SubtractResult");
		int finalResult = Integer.parseInt(x);

		Thread.sleep(10000);

		// Mouse click on C button
		robot.mouseMove(760, 232); // move mouse point to specific location
		robot.delay(1500); // delay is to make code wait for mentioned milliseconds before executing next
							// step
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click
		robot.delay(1500);

		Assert.assertEquals(finalResult, 2);
	}

	@Test(priority = 2)
	public void TestSubtract() throws Exception {

		// Subtract 7 - 3 = 4

		Robot robot = new Robot();

		// Mouse click on number 7
		robot.mouseMove(500, 300); // move mouse point to specific location
		robot.delay(1500); // delay is to make code wait for mentioned milliseconds before executing next
							// // step
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click
		robot.delay(1500);

		// Mouse click on subtract button
		robot.mouseMove(700, 430); // move mouse point to specific location
		robot.delay(1500); // delay is to make code wait for mentioned milliseconds before executing next
							// // step
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click
		robot.delay(1500);

		// Mouse click on number 3
		robot.mouseMove(630, 430); // move mouse point to specific location
		robot.delay(1500); // delay is to make code wait for mentioned milliseconds before executing next
							// // step
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click
		robot.delay(1500);

		// Mouse click on =
		robot.mouseMove(760, 500); // move mouse point to specific location
		robot.delay(1500); // delay is to make code wait for mentioned milliseconds before executing next
							// // step
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click
		robot.delay(1500);

		String x = ReturnResult(driver, "SubtractResult");
		int finalResult = Integer.parseInt(x);

		Thread.sleep(10000);
		// Mouse click on C button
		robot.mouseMove(760, 232); // move mouse point to specific location
		robot.delay(1500); // delay is to make code wait for mentioned milliseconds before executing next
							// step
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click
		robot.delay(1500);

		Assert.assertEquals(finalResult, 4);

	}

	@Test(priority = 3)
	public void TestCE() throws Exception {

		Robot robot = new Robot();

		// Mouse click on C button
		robot.mouseMove(760, 232); // move mouse point to specific location
		robot.delay(1500); // delay is to make code wait for mentioned milliseconds before executing next
							// step
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click
		robot.delay(1500);

		String x = ReturnResult(driver, "CEResult");
		int finalResult = Integer.parseInt(x);

		Thread.sleep(10000);

		Assert.assertEquals(finalResult, 0);

	}

	public String ReturnResult(WebDriver driver, String fileName) throws IOException {
		WebElement ele = driver.findElement(By.xpath("/html/body/div[2]"));

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(screenshot);

		// Get the location of element on the page
		Point point = ele.getLocation();

		// Get width and height of the element
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();

		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenshot = fullImg.getSubimage(462, 0, eleWidth, 150);
		ImageIO.write(eleScreenshot, "png", screenshot);

		// Copy the element screenshot to disk
		File screenshotLocation = new File(
				"C:\\Users\\engda\\eclipse-workspace\\calculator\\Screenshots\\" + fileName + ".png");
		FileUtils.copyFile(screenshot, screenshotLocation);

		Ocr.setUp(); // one time setup
		Ocr ocr = new Ocr(); // create a new OCR engine
		ocr.startEngine("eng", Ocr.SPEED_FASTEST); // Enish

		File fff = new File("C:\\Users\\engda\\eclipse-workspace\\calculator\\Screenshots\\h123.png");
		String result = ocr.recognize(new File[] { screenshotLocation }, Ocr.RECOGNIZE_TYPE_ALL,
				Ocr.OUTPUT_FORMAT_PLAINTEXT);

		System.out.println(screenshotLocation.getAbsolutePath());
		System.out.println("Result: " + result);
		// ocr more images here ...
		ocr.stopEngine();

		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(result);
		while (m.find()) {
			result = m.group();
			System.out.println(m.group());
		}

		return result;

	}

	@AfterClass
	public void stopDriver() {
		driver.close();
	}

}
