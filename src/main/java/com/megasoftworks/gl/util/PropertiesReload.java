package com.megasoftworks.gl.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;

public class PropertiesReload extends Properties {
    
    static Logger logger = Logger.getLogger(PropertiesReload.class);

	private static final long serialVersionUID = 1L;

	public Boolean getBoolean(String key) {
            logger.info("Reading property " + key);
		String value = getProperty(key);
		Boolean retorno = null;
		if(value != null) {
			retorno = value.equals("true");
		}
		return retorno;
	}
	
	public Integer getInt(String key) {
		String value = getProperty(key);
		Integer retorno = null;
		if(value != null) {
			retorno = Integer.parseInt(value);
		}
		return retorno;
	}
	
	public List<String> getList(String key, String separator) {
		String value = getProperty(key);
		List<String> retorno = null;
		if(value != null) {
			retorno = new ArrayList<>(Arrays.asList(value.split(separator)));
		}
		return retorno;
	}

}
