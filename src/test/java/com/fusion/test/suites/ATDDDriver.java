package com.fusion.test.suites;

import com.anthem.ataf.arch.atdd.ATDDRunner;
import com.anthem.selenium.constants.EnvConstants;
import com.anthem.selenium.utility.EnvHelper;

import java.io.File;

/**
 * 
 * This file executes all the scripts from features folder and all its
 * sub-folders.
 * 
 * ##### Do not modify this file. #####
 * 
 * @author Touseef.Tamboli
 */

public class ATDDDriver {

	public static void main(String[] args) {
		boolean specificFeatures = false;
		if (!EnvHelper.getValue(EnvConstants.atddExecFeaturesNames).equalsIgnoreCase("NO")) {
			specificFeatures = true;
		}
		ATDDRunner.execute("./",specificFeatures);
	}
	
	private static String getTestScripstPath() {
		File classpathRoot = new File(ATDDDriver.class.getResource("").getPath());
		return classpathRoot.getPath();
	}

}
