package com.fusion.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.anthem.ataf.logging.AnthemLogger;
import com.anthem.selenium.SuperHelper;

/*
'Revision History
'#############################################################################
'@rev.On	@rev.No		@rev.By				  @rev.Comments
'#############################################################################
*/

/**
 * Class to handle all date picker related methods. 
 * </p>
 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result");
 * </p>
 * </p>
 * 
 * @author AD20221 Algie Watts
 * @since 18 June 2020
 */
public class DatePickerEnhancedUtils extends SuperHelper {
	
	/**
	 * Updates RESULT_STATUS flag and logger when there is an exception
	 * </p>
	 * 
	 * @param strStep-
	 *            Step at which exception occurred
	 * @param excException
	 *            Exception occurred
	 * 
	 * @author AF34794 Usharani Arunachalam
	 * @since 27-July-2017
	 */
	protected static void processExceptions(String strStep, Exception excException) {
		setLastTestPassed(false);
		AnthemLogger logger = AnthemLogger.getLogger(WebTable.class.getName());
		logger.error(strStep);
		excException.printStackTrace();
		log(ERROR, strStep, "Exception: " + excException.getLocalizedMessage(), true);

	}
	
	/**
	 * selects the current date from the date picker window
	 * </p>
	 * 
	 * @author AD20221 Algie Watts
	 * @throws Exception 
	 * @since 18 June 2020
	 */
	public static void selectCurrentDate() throws Exception{
		SimpleDateFormat forMonth = new SimpleDateFormat ("MMM");
		SimpleDateFormat forYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat forDay = new SimpleDateFormat("d");
		
		try {
			Date date = new Date();
			
			String strMonth = forMonth.format(date);
			String strYear = forYear.format(date);
			String strDay = forDay.format(date);

			goToMonthYear(strMonth.toUpperCase(), strYear);
			selectDayFromCalendar(strDay);
			
		} catch (Exception excException) {
			processExceptions("Exception while executing selectSpecificDate function", excException);
			throw excException;
		}
	}

	/**
	 * selects a specific date from the date picker window based on date string given
	 * </p>
	 * @param strDate - date to be selected passed as a string (ex. 1/1/2018 or 10/10/2018).
	 * @param strDateFormat - the format that the date is in in the string (ex. MM/d/yyyy)
	 * 				
	 * @author AD20221 Algie Watts
	 * @throws Exception 
	 * @since 18 June 2020
	 */
	public static void selectSpecificDate(String strDate, String strDateFormat) throws Exception{
		SimpleDateFormat forMonth = new SimpleDateFormat("MMM");
		SimpleDateFormat forYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat forDay = new SimpleDateFormat("d");
		
		try {
			Date date = new SimpleDateFormat(strDateFormat).parse(strDate);
			
			String strMonth = forMonth.format(date);
			String strYear = forYear.format(date);
			String strDay = forDay.format(date);
			
			goToMonthYear(strMonth.toUpperCase(), strYear);
			selectDayFromCalendar(strDay);
		} catch (Exception excException) {
			processExceptions("Exception while executing selectSpecificDate function", excException);
			throw excException;
		}
	}
	
	/**
	 * selects a specific date from the date picker window based on date given as month, day, and year
	 * </p>
	 * @param strMonth - Month to search for
	 * @param strDay - Day to search for
	 * @param strYear - Year to search for
	 * 
	 * @author AD20221 Algie Watts
	 * @throws Exception 
	 * @since 18 June 2020
	 */
	public static void selectSpecificDate(String strMonth, String strDay, String strYear) throws Exception {
		
		SimpleDateFormat forMonth = new SimpleDateFormat("MMM");
		try {
			String strFullDate = strMonth + " " + strDay + ", " + strYear;
			Date date = new SimpleDateFormat("MMMM d, yyyy").parse(strFullDate);
			String strMonthAbrev = forMonth.format(date);
			
			goToMonthYear(strMonthAbrev.toUpperCase(), strYear);
			selectDayFromCalendar(strDay);
		} catch (Exception excException) {
			processExceptions("Exception while executing selectSpecificDate function", excException);
			throw excException;
		}
	}
	
	/**
	 * clicks on the Month/Year button and then selects the year and then the month
	 * </p>
	 * @param strMonth - Month to search for in for mat of MMM in all caps (ex. JAN)
	 * @param strYear - Year to search for
	 * 
	 * @author AD20221 Algie Watts
	 * @throws Exception 
	 * @since 18 June 2020
	 */
	public static void goToMonthYear (String strMonth, String strYear) throws Exception{
		
		try {
			WebElement monthYearButton = getWebDriver().findElement(By.xpath("//mat-datepicker-content//button[@aria-label='Choose month and year']"));
			CoreSuperHelper.seWaitForClickable(monthYearButton, "Month and Year Button");
			monthYearButton.click();
			seWaitForPageLoad();
			
			do {
				int intyearVerification = verifyYearInGroup(strYear);
				
				if (intyearVerification < 0) {
					getWebDriver().findElement(By.xpath("//button[@aria-label='Previous 20 years']")).click();
					Thread.sleep(500);
				} else if (intyearVerification > 0) {
					getWebDriver().findElement(By.xpath("//button[@aria-label='Next 20 years']")).click();
					Thread.sleep(500);
				} else {
					break;
				}
			} while (true);
				
			WebElement selectYear = getWebDriver().findElement(By.xpath("//mat-datepicker-content//div[contains(@class,'mat-calendar-body-cell-content') and text()='" + strYear + "']"));
			CoreSuperHelper.seWaitForClickable(selectYear, "Year Selection");
			selectYear.click();
			seWaitForPageLoad();
			log(PASS,"Select Day From Date Picker Calendar",strYear + " selected from the calendar.",false);
			
			WebElement selectMonth= getWebDriver().findElement(By.xpath("//mat-datepicker-content//div[contains(@class,'mat-calendar-body-cell-content') and text()='" + strMonth + "']"));
			CoreSuperHelper.seWaitForClickable(selectMonth, "Month Selection");
			selectMonth.click();
			seWaitForPageLoad();
			log(PASS,"Select Day From Date Picker Calendar",strMonth + " selected from the calendar.",false);
			
		} catch (Exception excException) {
			processExceptions("Exception while executing goToMonthYear Function", excException);
			throw excException;
		}
	}
	
	/**
	 * Verifies that the year passed is in the year group 
	 * </p>
	 * @param strYear - Year value to verify
	 * @author AD20221 Algie Watts
	 * @since 18 June 2020
	 */
	public static int verifyYearInGroup (String strYear) {
		
		int intReturnValue = 0;
		try {
			String strDateRange = getWebDriver().findElement(By.xpath("//mat-datepicker-content//button[@aria-label='Choose date']/span")).getText().trim();
			String[] strDateArray = strDateRange.split(" � ");
			
			if (strYear.compareTo(strDateArray[0].trim()) < 0) {
				intReturnValue = -1; 
			} else if (strYear.compareTo(strDateArray[1].trim()) > 0) {
				intReturnValue = 1; 
			}
		} catch (Exception excException) {
			processExceptions("Exception while executing verifyYearInGroup Function", excException);
			throw excException;
		}
		return intReturnValue;
	}
	
	/**
	 * Clicks on the day of the month in the calendar 
	 * </p>
	 * @param strDay - Day value to search the table for and click on
	 * @author AD20221 Algie Watts
	 * @since 18 June 2020
	 */
	public static void selectDayFromCalendar (String strDay) {
		
		try {
			WebElement datePickerDay = getWebDriver().findElement(By.xpath("//mat-datepicker-content//div[contains(@class,'mat-calendar-body-cell-content') and text()='" + strDay + "']"));
			CoreSuperHelper.seWaitForClickable(datePickerDay, "Date Picker Day");
			datePickerDay.click();
			log(PASS,"Select Day From Date Picker Calendar",strDay + " selected from the calendar.",false);
		} catch (Exception excException) {
			processExceptions("Exception while executing selectDayFromCalendar Function", excException);
			throw excException;
		}
		
	}

}
