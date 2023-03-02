/*
 * MIT License
 *
 * Copyright (c) 2023 Christian Pöcksteinera
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package at.cpo.selenium.tests;

import com.github.cpo1964.platform.selenium.SeleniumHelper;

/**
 * Test Login by Selenium.
 * 
 * ein JUnit Test Aufrufreihenfolge: 
 * 1) die Methoden annotiert mit @Parameterized.Parameters - zb getData() 
 * 2) die Methode annotiert mit @BeforeClass - zb setUpBeforeClass() 
 * 3) die Methoden annotiert mit @Before - zb setUp() 
 * 4) die Methoden annotiert mit @Test - zb doSeleniumTest() 
 * 5) die Methoden annotiert mit @After - zb tearDown() 
 * 6) die Methode annotiert mit @AfterClass - zb tearDownAfterClass()
 */
public class JpetstoreSeleniumJunit5Test extends SeleniumHelper {

//	/**
//	 * Sets up before class.
//	 *
//	 */
//	@BeforeAll
//	public static void setUpBeforeClass() {
//	}
//
//	/**
//	 * Sets the up.
//	 *
//	 * @throws IOException          Signals that an I/O exception has occurred.
//	 * @throws InterruptedException the interrupted exception
//	 */
//	@BeforeEach
//	public void setUp() throws IOException, InterruptedException {
//	}
//
//	/**
//	 * Tear down.
//	 */
//	@AfterEach
//	public void tearDown() {
//		reportCreateStep("tearDown #");
//		closeBrowser();
//		reportEndTest("Jpetstore finished" + System.lineSeparator());
//	}
//
//	/**
//	 * Tear down after class.
//	 *
//	 */
//	@AfterAll
//	public static void tearDownAfterClass() {
//	}
//
//	/**
//	 * Gets the data.
//	 *
//	 * @return the data
//	 * @throws IOException Signals that an I/O exception has occurred.
//	 * @throws ConfigurationException 
//	 */
//	static Collection<?> getData() throws IOException, ConfigurationException {
//		commonSetup(PLATFORM_SELENIUM);
//		return getTestdata(JpetstoreSeleniumTest.class.getSimpleName());
//	}
//
//	/**
//	 * Smoke test.
//	 *
//	 * @throws InterruptedException the interrupted exception
//	 * @throws IOException
//	 */
//	@ParameterizedTest
//	@MethodSource("getData")
//	void doSeleniumTest(String username, String password, String localhostUrl, String remotehostUrl, String skip,
//			String runlocal) throws InterruptedException, IOException {
//		setupDriver();
//
//		setIteration(getIteration() + 1);
//		if (isTrue(skip)) {
//			return;
//		}
//
//		reportCreateTest("TestCase #" + getIteration() + " login to Jpetstore - runlocal: " + runlocal);
//		reportTestInfo("Jpetstore started");
//		reportTestInfo("<br>Testparameter:<br>" +
//				"username: '" + username + "'<br>" + 
//				"password: '" + password + "'<br>" + 
//				"localhostUrl: '" + localhostUrl + "'<br>" +
//				"remotehostUrl: '" + remotehostUrl + "'<br>" +
//				"runlocal: '" + runlocal + "'<br>");
//
//		reportCreateStep("setUp TestCase #" + getIteration() + " #");
//
//		if (localhostUrl.startsWith("jpetstore")) {
//			testStep01("Step #1 - start Jpetstore", runlocal, localhostUrl, remotehostUrl);
//			testStep02("Step #2 - Login to Jpetstore", username, password);
//			testStep03("Step #3 - Logout of Jpetstore");
//		}
//	}
//
//	/**
//	 * Test step start app.
//	 *
//	 * @param msg the msg
//	 */
//	private void testStep01(String msg, String runlocal, String localhostUrl, String remoteUrl) {
//		reportCreateStep(msg);
//		ok = navigateToStartJpetstorePage(runlocal, localhostUrl, remoteUrl);
//		Assert.assertTrue(validate(ok, "Jpetstore app started"));
//		ok = existsByXpath("//a[contains(@href, '/account/signonForm')]", true);
//		Assert.assertTrue(validate(ok, "signonForm is visible"));
//		reportStepPassScreenshot();
//	}
//
//	/**
//	 * test Step 02.
//	 *
//	 * @param msg the msg
//	 */
//	private void testStep02(String msg, String username, String password) {
//		reportCreateStep(msg);
//		clickByXpath("//a[contains(@href, '/account/signonForm')]");
//		inputByXpath("//input[@name='username']", EDITFIELD, username);
//		inputByXpath("//input[@name='password']", EDITFIELD, password);
//		clickByXpath("//div[@id='Signon']/form/div/div/button");
//		ok = existsByXpath("//a[contains(@href, '/account/signoff')]", true);
//		Assert.assertTrue(validate(ok, "signonOff link is visible"));
//		reportStepPassScreenshot();
//	}
//
//	/**
//	 * Test step 03.
//	 *
//	 * @param msg the msg
//	 */
//	private void testStep03(String msg) {
//		reportCreateStep(msg);
//		clickByXpath("//a[contains(@href, '/account/signoff')]");
//		ok = existsByXpath("//a[contains(text(),'Sign Up')]", true);
//		Assert.assertTrue(validate(ok, "Sign Up link is visible"));
//		reportStepPassScreenshot();
//	}
//
//	/**
//	 * Navigate to start jpetstore page.
//	 *
//	 * @return true, if successful
//	 */
//	private boolean navigateToStartJpetstorePage(String runlocal, String localhostUrl, String remoteUrl) {
//		try {
//			if (isTrue(runlocal)) {
//				driverGet(testPlatformPropertiesGet(localhostUrl));
//			} else {
//				driverGet(testPlatformPropertiesGet(remoteUrl));
//			}
//			reportTestPass("JPetstore started");
//		} catch (Exception e1) {
//			reportTestFail("JPetstore is down");
//			return false;
//		}
//		return true;
//	}
//
//	/**
//	 * Test signin test case.
//	 *
//	 * @throws Exception the exception
//	 */
//	public void testSigninTestCase() throws Exception {
//		clickByXpath("//a[contains(@href, '/account/newAccountForm')]");
//		inputByXpath("//input[@name='username']", EDITFIELD, "j2ee");
//		inputByXpath("//input[@name='password']", EDITFIELD, "j2ee");
//		inputByXpath("//input[@name='repeatedPassword']", EDITFIELD, "Test");
//		inputByXpath("//input[@name='firstName']", EDITFIELD, "Cpo");
//		inputByXpath("//input[@name='lastName']", EDITFIELD, "Cpo");
//		inputByXpath("//input[@name='email']", EDITFIELD, "cpo1964@aon.at");
//		inputByXpath("//input[@name='phone']", EDITFIELD, "12345");
//		inputByXpath("//input[@name='address1']", EDITFIELD, "cpo 1");
//		inputByXpath("//input[@name='address2']", EDITFIELD, "cpo 2");
//		inputByXpath("//input[@name='city']", EDITFIELD, "Cpo");
//		inputByXpath("//input[@name='state']", EDITFIELD, "Cpostate");
//		inputByXpath("//input[@name='zip']", EDITFIELD, "1111");
//		inputByXpath("//input[@name='country']", EDITFIELD, "Cpocountry");
//		clickByXpath("//select[@name='languagePreference']");
//		inputByXpath("//select[@name='languagePreference']", LISTBOX, "German");
//		clickByXpath("//option[@value='german']");
//		clickByXpath("//input[@name='listOption']");
//		clickByXpath("//input[@name='bannerOption']");
//		clickByXpath("//div[@id='CenterForm']/form/div/button");
//	}

}
