package at.cpo.selenium.tests;

import java.io.File;
import java.nio.file.Paths;
import java.util.Properties;

import com.github.cpo1964.platform.selenium.SeleniumHelper;
import com.github.cpo1964.platform.selenium.SeleniumStrings;

public class ConfigurationHelper {
	
	private static SeleniumHelper sh = new SeleniumHelper();

	public static String getMandant() {
		return System.getProperty(SeleniumStrings.MANDANTKEY);
	}
		
	public static String getZone() {
		return System.getProperty(SeleniumStrings.ZONEKEY);
	}
		
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

	public static Properties getTestProperties() {
		String path = getTestDataPath() + File.separator + "test-platform.properties";
		Properties props = sh.getTestPlatformProperties(path);
		return props;
	}
	
	public static String getStartUrl(String key) {
		return getTestProperties().getProperty(key);
	}
	
}
