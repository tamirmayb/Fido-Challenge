package utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

@WebListener
public class SitePropertiesInitializeListner implements ServletContextListener {
	final static String PROPERTIES_FILE_NAME = "sites.properties";
	final static Logger logger = Logger.getLogger(SitePropertiesInitializeListner.class);

	@Override
	public final void contextInitialized(final ServletContextEvent sce) {
		//fill sites properties map when tomcat starts
		logger.info("Starting FIDO tomcat");
		FidoApp.getFidoAppInstance().setSitesPropertiesMap(initSiteProperties());
	}

	@Override
	public final void contextDestroyed(final ServletContextEvent sce) {

	}

	private Map<String, String> initSiteProperties() {
		Map<String, String> propertiesMap = new HashMap<String, String>();
		InputStream inputStream;

		Properties properties = new Properties();
		String propFileName = PROPERTIES_FILE_NAME;
		logger.info("Starting initSiteProperties");
		
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				//fill properties 
				properties.load(inputStream);
			} else {
				logger.error("property file '" + propFileName + "' not found in the classpath");
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//fill properties map
		Enumeration<?> e = properties.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			propertiesMap.put(key, properties.getProperty(key));
		}
		logger.info("Properties map ready");
		return propertiesMap;

	}
}
