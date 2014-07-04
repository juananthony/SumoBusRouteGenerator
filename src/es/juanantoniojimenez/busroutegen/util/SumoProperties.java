package es.juanantoniojimenez.busroutegen.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class to retrieve properties from properties file.
 * @author juananthony
 *
 */
public class SumoProperties {
	
	/**
	 * Returns a property mapped with a given key.
	 * 
	 * @param key property's key
	 * @return Value from properties file
	 */
	public static String get(String key) {
		Properties prop = new Properties();
		InputStream input = null;
		
		String retValue = "";
		try{
			prop.load(SumoProperties.class.getClassLoader().getResourceAsStream("config.properties"));
			retValue = prop.getProperty(key);
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return retValue;
	}
}
