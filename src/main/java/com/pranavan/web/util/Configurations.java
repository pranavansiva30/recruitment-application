package com.pranavan.web.util;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class Configurations {
	private final Logger log = Logger.getLogger(getClass().getName().toString());

	private Properties properties = null;
	private static Configurations instance = null;

	/** 
	 * Private constructor 
	 */
	private Configurations() {
		this.properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(Constants.PATH_CONFFILE));
		} catch (IOException ex) {
			log.severe("Error at loading property file " + ex.getMessage());
		}
	}

	/** 
	 * Creates the instance is synchronized to avoid multithreads problems 
	 */
	private synchronized static void createInstance() {
		if (instance == null) {
			instance = new Configurations();
		}
	}

	/**
	 * Get the properties instance. Uses singleton pattern 
	 * ultimately guarantee the creation of only once instance
	 */
	public static Configurations getInstance() {		
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	/** 
	 * Get a property of the property file 
	 */
	public String getProperty(String key) {
		String result = null;
		if (key != null && !key.trim().isEmpty()) {
			result = this.properties.getProperty(key);
		}
		return result;
	}

	/**
	 * Override the clone method to ensure the "unique instance" requirement of
	 * this class
	 */
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
