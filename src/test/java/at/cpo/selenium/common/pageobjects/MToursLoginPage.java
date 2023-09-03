/*
 * MIT License
 *
 * Copyright (c) 2023 Christian Pöcksteiner
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
package at.cpo.selenium.common.pageobjects;

import com.github.cpo1964.platform.selenium.LocatorHelper;
import com.github.cpo1964.platform.selenium.SeleniumHelper;

/**
 * 
 * The Class SeleniumLoginPage.
 * 
 */

public class MToursLoginPage extends SeleniumHelper {
	
	Class<MToursLoginPage> page = MToursLoginPage.class;

	/**
	 * 
	 * The constant USERNAME.
	 * 
	 */

	public final String USERNAME = LocatorHelper.getEditFieldLocator(page, "UsernameIN");

	/**
	 * 
	 * The constant PASSWORD.
	 * 
	 */

	public final String PASSWORD = LocatorHelper.getEditFieldLocator(page, "PasswordIN");

	/**
	 * 
	 * The constant Login.
	 * 
	 */

	public final String LOGIN = LocatorHelper.getButtonLocator(page, "LoginBT");

	/**
	 * 
	 * The constant LoginOk.
	 * 
	 */

	public final String LOGINOK = LocatorHelper.getTextLocator(page, "LoginOkTXT");

	/**
	 * 
	 * The constant Login.
	 * 
	 */

	public final String NOTICE = LocatorHelper.getButtonLocator(page, "NoticeBT");

	/**
	 * 
	 * The constant HOME.
	 * 
	 */

	public final String HOME = LocatorHelper.getLinkLocator(page, "HomeLN");

	/**
	 * 
	 * The constant HOME.
	 * 
	 */

	public final String FLIGHTS = LocatorHelper.getLinkLocator(page, "FlightsLN");

	/**
	 * 
	 * The constant SIGNININFO.
	 * 
	 */

	public final String SIGNININFO = LocatorHelper.getTextLocator(page, "SigInInfoTXT");

}
