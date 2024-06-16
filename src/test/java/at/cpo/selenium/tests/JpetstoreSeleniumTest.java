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

import java.io.IOException;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import com.github.cpo1964.platform.selenium.SeleniumHelper;
import com.github.cpo1964.platform.selenium.WebelementType;
import com.github.cpo1964.utils.CommonHelper;

/**
 * Test Login by Selenium.
 */
@RunWith(Parameterized.class)
public class JpetstoreSeleniumTest extends SeleniumHelper {

	/**
	 * The Email.
	 */
	@Parameter()
	public String username;
	/**
	 * The Password.
	 */
	@Parameter(1)
	public String password;
	/**
	 * The localhostUrl.
	 */
	@Parameter(2)
	public String localhostUrl;
	/**
	 * The remoteUrl.
	 */
	@Parameter(3)
	public String remotehostUrl;
	/**
	 * The Skip.
	 */
	@Parameter(4)
	public String skip;
	/**
	 * The runlocal.
	 */
	@Parameter(5)
	public String runlocal;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Parameterized.Parameters // (name = "{index}: {0}")
	public static Collection<?> getData() throws IOException {
		return SeleniumHelper.getTestdata(ConfigurationHelper.getTestDataPath(),
				JpetstoreSeleniumTest.class.getSimpleName());
	}

	/**
	 * Sets up before class.
	 *
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
	}

	/**
	 * Tear down after class.
	 *
	 */
	@AfterClass
	public static void tearDownAfterClass() {
	}

	/**
	 * Sets the up.
	 *
	 */
	@Before
	public void setUp() {
		setIteration(getIteration() + 1);
		reportCreateTest("TestCase #" + getIteration() + " login to Jpetstore - runlocal: " + runlocal);
		if (CommonHelper.isTrue(skip)) {
			reportTestInfo("setUp: Jpetstore test skipped ...");
			return;
		}

		reportTestInfo("Jpetstore started");
		reportTestInfo("<br>Testparameter:<br>" + "username: '" + username + "'<br>" + "password: '" + password
				+ "'<br>" + "localhostUrl: '" + localhostUrl + "'<br>" + "remotehostUrl: '" + remotehostUrl + "'<br>"
				+ "runlocal: '" + runlocal + "'<br>");

		reportCreateStep("setUp TestCase #" + getIteration() + " #");

		launch();
	}

	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
		if (CommonHelper.isTrue(skip)) {
			reportTestInfo("tearDown: Jpetstore test skipped ...<br>");
			return;
		}

		reportCreateStep("tearDown #");
		closeBrowser();

		reportEndTest("Jpetstore finished" + System.lineSeparator());
	}

	/**
	 * Smoke test.
	 *
	 */
	@Test
	public void doSeleniumTest() {
		if (CommonHelper.isTrue(skip)) {
			reportTestInfo("doSeleniumTest: Jpetstore test skipped ...");
			return;
		}

		if (localhostUrl.startsWith("jpetstore")) {
			doTestJpetstore();
		}
		Assert.assertTrue(isPassed());
	}

	/**
	 * Do test jpetstore.
	 */
	private void doTestJpetstore() {
		testStep01("Step #1 - start Jpetstore");
		testStep02("Step #2 - Login to Jpetstore");
		testStep03("Step #3 - My Account");
		testStep04("Step #4 - Logout of Jpetstore");
	}

	/**
	 * Test step start app.
	 *
	 * @param msg the msg
	 */
	private void testStep01(String msg) {
		reportCreateStep(msg);
		validate(navigateToStartJpetstorePage(), "Jpetstore app started");
		reportStepPassScreenshot();
	}

	/**
	 * test Step 02.
	 *
	 * @param msg the msg
	 */
	private void testStep02(String msg) {
		reportCreateStep(msg);
		click("//a[contains(@href, 'signonForm')]");

		input("//input[@name='username']", WebelementType.EDITFIELD, username);
		input("//input[@name='password']", WebelementType.EDITFIELD, password);
		click("//input[@name='signon']");

		reportStepPassScreenshot();
	}

	/**
	 * Test step 03.
	 *
	 * @param msg the msg
	 */
	private void testStep03(String msg) {
		reportCreateStep(msg);

		click("//a[contains(text(),'My Account')]");
		input("//select[@name='account.languagePreference']", WebelementType.LISTBOX, "japanese");
		input("//select[@name='account.languagePreference']", WebelementType.LISTBOX, "english");
		click("//form[@action='/actions/Account.action']");
		click("//select[@name='account.favouriteCategoryId']");
		input("//select[@name='account.favouriteCategoryId']", WebelementType.LISTBOX, "DOGS");
		input("(.//*[normalize-space(text()) and normalize-space(.)='Enable MyBanner'])[1]/preceding::td[1]", 
				WebelementType.CHECKBOX, "ON");

		reportStepPassScreenshot();
	}

	/**
	 * Test step 04.
	 *
	 * @param msg the msg
	 */
	private void testStep04(String msg) {
		reportCreateStep(msg);

		click("//a[contains(@href, '/actions/Account.action?signoff=')]");
		ok = waitOn("//a[contains(@href, '/actions/Account.action?signoff=')]", NotFound, 3, true);
		validate(ok, "SignOut link is NOT visible");
		ok = waitOn("//a[contains(@href, 'signonForm')]", Displayed, 3, true);
		validate(ok, "SignIn link is visible");

		reportStepPassScreenshot();
	}

	/**
	 * Navigate to start jpetstore page.
	 *
	 * @return true, if successful
	 */
	private boolean navigateToStartJpetstorePage() {
		String startUpUrl;
		try {
			if (CommonHelper.isTrue(runlocal)) {
				startUpUrl = ConfigurationHelper.getTestPlatformProperty(localhostUrl);
			} else {
				startUpUrl = ConfigurationHelper.getTestPlatformProperty(remotehostUrl);
			}
			reportStepInfo("startUpUrl: " + startUpUrl);
			navigateTo(startUpUrl);
			reportTestPass("JPetstore started");
		} catch (Exception e1) {
			reportTestFail("JPetstore is down");
			return false;
		}
		return true;
	}

	/**
	 * Test signin test case.
	 *
	 */
	public void doTestSigninTestCase() {
		click("//a[contains(@href, 'newAccountForm')]");
		input("//input[@name='username']", WebelementType.EDITFIELD, "j2ee");
		input("//input[@name='password']", WebelementType.EDITFIELD, "j2ee");
		input("//input[@name='repeatedPassword']", WebelementType.EDITFIELD, "Test");
		input("//input[@name='firstName']", WebelementType.EDITFIELD, "Cpo");
		input("//input[@name='lastName']", WebelementType.EDITFIELD, "Cpo");
		input("//input[@name='email']", WebelementType.EDITFIELD, "cpo1964@aon.at");
		input("//input[@name='phone']", WebelementType.EDITFIELD, "12345");
		input("//input[@name='address1']", WebelementType.EDITFIELD, "cpo 1");
		input("//input[@name='address2']", WebelementType.EDITFIELD, "cpo 2");
		input("//input[@name='city']", WebelementType.EDITFIELD, "Cpo");
		input("//input[@name='state']", WebelementType.EDITFIELD, "Cpostate");
		input("//input[@name='zip']", WebelementType.EDITFIELD, "1111");
		input("//input[@name='country']", WebelementType.EDITFIELD, "Cpocountry");
		click("//select[@name='languagePreference']");
		input("//select[@name='languagePreference']", WebelementType.LISTBOX, "German");
		click("//option[@value='german']");
		click("//input[@name='listOption']");
		click("//input[@name='bannerOption']");
		click("//div[@id='CenterForm']/form/div/button");
	}

}
