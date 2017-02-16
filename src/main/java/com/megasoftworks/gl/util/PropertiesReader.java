package com.megasoftworks.gl.util;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class PropertiesReader {
	static Logger logger = Logger.getLogger(PropertiesReader.class);
	public static PropertiesReload propertiesReader(String path) {
		PropertiesReload properties = null;
		try {
			 properties = new PropertiesReload();
			 InputStream input = new FileInputStream(path);
			 properties.load(input);
		} catch(Exception e) {
			logger.error("Problema leyendo el archivo de propiedades " + path);
		}
		
		return properties;
	}

}
