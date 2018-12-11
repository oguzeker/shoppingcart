package com.trendyol.tr.shoppingcart.common.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to manage configurations stored in a database. The properties are
 * retrieved from a table containing at least one column for the configuration
 * names, one column for the keys, and one column for the values. It's possible
 * to store several configurations in the same table. The name of the table and
 * the columns is specified in the configuration.properties.
 * 
 * @author Oguz Erhan Eker
 *
 */
public final class ConfigurationManager {

	private static final Log log = LogFactory.getLog(ConfigurationManager.class);
	
	private static PropertiesConfiguration config;
	private static String projectVersion;
	private static String configurationPath = "";
	
	private static final String PROJECT_VERSION = "com.trendyol.tr.shoppingcart.project.version";
	private static final String CONFIG_FILE = "shoppingcart-config.properties";
	private static final String VERSION_FILE = "version.properties";
	
	public static final String FIXED_COST = "com.trendyol.tr.shoppingcart.fixed.cost";

	/**
	 * Utility classes should not have a public constructor.
	 * Added a private constructor to hide the implicit public one.
	 */
	private ConfigurationManager() {
	}
	
	public static Configuration getConfiguration() throws ConfigurationException {
		if (config == null) {
			initConfig();
		}
		return config;
	}
	
	public static synchronized void initConfig() {
		initProjectVersion();
		if (config == null) {
			log.debug("initConfig...");
			try {
				config = new PropertiesConfiguration(CONFIG_FILE);
				configurationPath = config.getFile().getParent();
				config.setReloadingStrategy(new FileChangedReloadingStrategy());
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	}
	
	public static synchronized void initProjectVersion() {
		if (projectVersion == null) {
			try {
				PropertiesConfiguration versionProps = new PropertiesConfiguration(VERSION_FILE);
				log.debug(">> Project version file path: " + versionProps.getBasePath());
				versionProps.setReloadingStrategy(new FileChangedReloadingStrategy());
				projectVersion = versionProps.getString(PROJECT_VERSION);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	}

	public static String getConfigurationPath() {
		return configurationPath;
	}
	
	public static String getProjectVersion() {
		return projectVersion;
	}

}
