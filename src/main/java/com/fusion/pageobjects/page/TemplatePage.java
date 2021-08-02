package com.fusion.pageobjects.page;


import org.openqa.selenium.support.PageFactory;

import com.anthem.selenium.SuperHelper;

/*
'Revision History
'#############################################################################
'@rev.On	@rev.No		@rev.By				  @rev.Comments
'										
'#############################################################################
*/

/**
 * <Add description here>
 * 
 * @author <Author name>
 * @since <Creation date>
 *
 */
public class TemplatePage extends SuperHelper {

	private static TemplatePage thisIsTestObj;

	// So that there only one object accesses this class at any moment
	public synchronized static TemplatePage get() {
		thisIsTestObj = PageFactory.initElements(getWebDriver(), TemplatePage.class);
		return thisIsTestObj;
	}

	//Add objects here...
}
