package at.cpo.selenium.tests;

import java.io.File;
import java.nio.file.Paths;
import java.util.Properties;

import com.github.cpo1964.platform.selenium.SeleniumHelper;
import com.github.cpo1964.platform.selenium.SeleniumStrings;

/**
 * The Class ConfigurationHelper.
 */
public class ConfigurationHelper {
	
	/** The selenium helper. */
	private static SeleniumHelper seleniumHelper = new SeleniumHelper();

	/** The test properties. */
	static Properties testProperties = null;
	
	/**
	 * Gets the mandant.
	 *
	 * @return the mandant
	 */
	public static String getMandant() {
		return System.getProperty(SeleniumStrings.MANDANTKEY);
	}
		
	/**
	 * Gets the zone.
	 *
	 * @return the zone
	 */
	public static String getZone() {
		return System.getProperty(SeleniumStrings.ZONEKEY);
	}
		
	/**
	 * Gets the test data path.
	 *
	 * @return the test data path
	 */
	public static String getTestDataPath() {
		String dirMandantZone = "";
		System.out.println("mandant: " + getMandant());
		System.out.println("zone: " + getZone());
		if (getMandant() != null && !getMandant().isEmpty()) {
			dirMandantZone = File.separator + getMandant();
			if (getZone() != null && !getZone().isEmpty()) {
				dirMandantZone = File.separator + getMandant() + "-" + getZone();
			}
		}
		dirMandantZone = Paths.get("").toAbsolutePath().toString() + File.separator +
				SeleniumStrings.TESTDATADIR + dirMandantZone;
		return dirMandantZone;
	}

	/**
	 * Gets the test properties.
	 *
	 * @return the test properties
	 */
	private static Properties getTestProperties() {
		if (testProperties == null) {
			String path = getTestDataPath() + File.separator + "test-platform.properties";
			testProperties = seleniumHelper.getTestPlatformProperties(path);
		}
		return testProperties;
	}
	
	/**
	 * Gets the test platform property.
	 *
	 * @param key the key
	 * @return the test platform property
	 */
	public static String getTestPlatformProperty(String key) {
		return getTestProperties().getProperty(key);
	}
	
}
