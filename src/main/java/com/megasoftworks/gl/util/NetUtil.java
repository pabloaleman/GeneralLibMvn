package com.megasoftworks.gl.util;

import java.net.InetAddress;

import org.apache.log4j.Logger;

public class NetUtil {
	
	static Logger logger = Logger.getLogger(NetUtil.class);
	
	public static boolean thereIsPing(String address) {
		try {
			logger.debug("Trying to connect to: " + address);
            InetAddress inet = InetAddress.getByName(address);
            return inet.isReachable(5000);
        } catch (Exception e) {
            logger.fatal("Error doing ping to: " + address);
            logger.fatal(e.getMessage());
            return false;
        }
	}

}
