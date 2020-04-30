package io.github.nicoladileo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {
	private static Logger logger = LoggerFactory.getLogger(Utility.class);
	private static String APPLICATIONPATH = "/application.properties";
	private static String DATASOURCEPATH = "/datasource.properties";	
	
	public static Properties caricaApplicationProperties() throws IOException {
		return caricaProperties(APPLICATIONPATH);
	}
	
	public static Properties caricaDatasourceProperties() throws IOException {
		return caricaProperties(DATASOURCEPATH);
	}
	
	private static Properties caricaProperties(String path) throws IOException {
		String propertiesPath = "";
		if (path != null && !path.trim().equals(""))
			propertiesPath = path;
		else
			propertiesPath = APPLICATIONPATH;
		Properties props = new Properties();
		InputStream stream = null;
		try {
			stream = Utility.class.getResourceAsStream(propertiesPath);
			if (stream!=null){
				props.load(stream);
			}
			else
				logger.error("Not valid properties file");				
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
		
		return props;
	}
}
