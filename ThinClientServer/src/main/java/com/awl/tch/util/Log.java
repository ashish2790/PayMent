package com.awl.tch.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {

	private static final LogManager logManager = LogManager.getLogManager();

	public static final Logger logger=Logger.getLogger("appLogger");

	static{

		try {

			logManager.readConfiguration(new FileInputStream("./logging.properties"));

		} catch (IOException exception) {

			logger.log(Level.SEVERE, "Error in loading configuration",exception);

		}

	}


}
