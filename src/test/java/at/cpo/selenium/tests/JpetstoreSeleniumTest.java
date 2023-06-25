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
import org.openqa.selenium.By;

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

		setupDriver();
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
		clickByXpath("//a[contains(@href, 'signonForm')]");

		inputByXpath("//input[@name='username']", WebelementType.EDITFIELD.name(), username);
		inputByXpath("//input[@name='password']", WebelementType.EDITFIELD.name(), password);
		clickByXpath("//input[@name='signon']");

		reportStepPassScreenshot();
		wait(1);
	}

	/**
	 * Test step 03.
	 *
	 * @param msg the msg
	 */
	private void testStep03(String msg) {
		reportCreateStep(msg);

		clickByXpath("//a[contains(text(),'My Account')]");
		inputByXpath("//select[@name='account.languagePreference']", WebelementType.LISTBOX.name(), "japanese");
		inputByXpath("//select[@name='account.languagePreference']", WebelementType.LISTBOX.name(), "english");
		clickByXpath("//form[@action='/actions/Account.action']");
		clickByXpath("//select[@name='account.favouriteCategoryId']");
		inputByXpath("//select[@name='account.favouriteCategoryId']", WebelementType.LISTBOX.name(), "DOGS");
		inputByXpath("(.//*[normalize-space(text()) and normalize-space(.)='Enable MyBanner'])[1]/preceding::td[1]", 
				WebelementType.CHECKBOX.name(), "ON");

		reportStepPassScreenshot();
	}

	private void testStep04(String msg) {
		reportCreateStep(msg);
		wait(2);

		clickByXpath("//a[contains(@href, '/actions/Account.action?signoff=')]");
		ok = waitUntilBy(By.xpath("//a[contains(@href, '/actions/Account.action?signoff=')]"), NotFound, 3, true);
		validate(ok, "SignOut link is NOT visible");
		ok = waitUntilBy(By.xpath("//a[contains(@href, 'signonForm')]"), Displayed, 3, true);
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
				startUpUrl = ConfigurationHelper.getTestProperties().getProperty(localhostUrl);
			} else {
				startUpUrl = ConfigurationHelper.getTestProperties().getProperty(remotehostUrl);
			}
			reportStepInfo("startUpUrl: " + startUpUrl);
			driverGet(startUpUrl);
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
		clickByXpath("//a[contains(@href, 'newAccountForm')]");
		inputByXpath("//input[@name='username']", WebelementType.EDITFIELD.name(), "j2ee");
		inputByXpath("//input[@name='password']", WebelementType.EDITFIELD.name(), "j2ee");
		inputByXpath("//input[@name='repeatedPassword']", WebelementType.EDITFIELD.name(), "Test");
		inputByXpath("//input[@name='firstName']", WebelementType.EDITFIELD.name(), "Cpo");
		inputByXpath("//input[@name='lastName']", WebelementType.EDITFIELD.name(), "Cpo");
		inputByXpath("//input[@name='email']", WebelementType.EDITFIELD.name(), "cpo1964@aon.at");
		inputByXpath("//input[@name='phone']", WebelementType.EDITFIELD.name(), "12345");
		inputByXpath("//input[@name='address1']", WebelementType.EDITFIELD.name(), "cpo 1");
		inputByXpath("//input[@name='address2']", WebelementType.EDITFIELD.name(), "cpo 2");
		inputByXpath("//input[@name='city']", WebelementType.EDITFIELD.name(), "Cpo");
		inputByXpath("//input[@name='state']", WebelementType.EDITFIELD.name(), "Cpostate");
		inputByXpath("//input[@name='zip']", WebelementType.EDITFIELD.name(), "1111");
		inputByXpath("//input[@name='country']", WebelementType.EDITFIELD.name(), "Cpocountry");
		clickByXpath("//select[@name='languagePreference']");
		inputByXpath("//select[@name='languagePreference']", WebelementType.LISTBOX.name(), "German");
		clickByXpath("//option[@value='german']");
		clickByXpath("//input[@name='listOption']");
		clickByXpath("//input[@name='bannerOption']");
		clickByXpath("//div[@id='CenterForm']/form/div/button");
	}

}
