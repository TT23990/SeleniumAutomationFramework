package com.fusion.utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.anthem.ataf.logging.AnthemLogger;

/**
 * Class to handle all table related methods for BlueLINK. <br>
 * </p>
 * Note: For the user, row and column index start from 1.
 * </p>
 * 
 * @author AD20221 Algie Watts
 * @since 1-August-2017
 */

public class MatTable extends CoreSuperHelper {

	protected WebElement tblTable;
	private String strTableName;
	protected AnthemLogger logger = AnthemLogger.getLogger(WebTable.class.getName());;

	/**
	 * Parameterized Constructor for WebTable
	 * </p>
	 * 
	 * @param argTable
	 *            WebElement of Table
	 * @param strName
	 *            Table name
	 * 
	 * @autor AD20221 Algie Watts
	 * @since 9/14/2017
	 */
	public MatTable(WebElement argTable, String strName) {
		try {
			tblTable = argTable;
			strTableName = strName;
		} catch (Exception excException) {
			processExceptions("Exception while executing BlueLINKTable constructor", excException);
			throw excException;
		}
	}

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
	protected void processExceptions(String strStep, Exception excException) {
		setLastTestPassed(false);
		logger.error(strStep);
		excException.printStackTrace();
		log(ERROR, strStep, "Exception: " + excException.getLocalizedMessage(), true);

	}

	/**
	 * Function to the search through a table in the PACT application to see if a value exists in a certain column
	 * 
	 * @author AD20221 Algie Watts
	 * 
	 * @param argTable
	 *            the table object
	 * @param intSearchColumn
	 *            index of the column to search
	 * @param strSearchValue
	 *            value to search for in the index column
	 * @param blnExpectedResult
	 *            should the value should be found
	 * 
	 * @return boolean if search value was found or not based on the expected result
	 */
	public int findValueInTable(String strSearchColumn, String strSearchValue, boolean blnExpectedResult) throws Exception {

		try {
			int intRowNumber = -1;

			String strMatColumn = getMatColumn(strSearchColumn);
			
			do {
				intRowNumber = getRowWithCellText(strSearchValue, strMatColumn);

				if (intRowNumber > 0) {
					// Pass or Fail based on whether the value was supposed to be in the table
					if (blnExpectedResult) {
						log(PASS, "Find Value in table", "Pass: Passed Compare - search value \"" + strSearchValue + "\"; Found At Row: " + intRowNumber + " in table: " + strTableName, false);
						return intRowNumber;
					} else {
						log(FAIL, "Find Value in table", "Fail: Search value \"" + strSearchValue + "\"; Found at Row: " + intRowNumber + ". Value was NOT expected to be in table: " + strTableName, false);
						setLastTestPassed(false);
						return intRowNumber;
					}
				}
			
				WebElement nextPage = getWebDriver().findElement(By.xpath("//button[contains(@class,'mat-paginator-navigation-next')]"));
				if (nextPage.isEnabled()) {
					nextPage.click();
					seWaitForPageLoad();
					nextPage=null;
				} else {
					break;
				}
					
			} while(true);

			if (!blnExpectedResult) {
				// Pass if we have reached the last record on the last com.fusion.pageobjects.page and the expected result was false
				log(PASS, "Find Value in table", "Pass: Search value \"" + strSearchValue + "\" NOT found in table: " + strTableName + " as expected", true);

				return intRowNumber;
			} else {
				// Fail if we have reached the last record on the last com.fusion.pageobjects.page and the expected result was true
				log(FAIL, "Find Value in table", "Fail: Search value \"" + strSearchValue + "\" NOT found in table: " + strTableName + " in column " + strSearchColumn, true);
				setLastTestPassed(false);
				return intRowNumber;
			}

		} catch (Exception excException) {
			log(FAIL, "Default function findValueInTableInquiries", "Exception while executing findValueInTableInquiries function", false);
			setLastTestPassed(false);
			throw excException;
		}
	}

	/**
	 * Function to get the mat-column- value of column based on the column name passed
	 * 
	 * @author AD20221 Algie Watts
	 * 
	 * @param strColumnName
	 *            the physical column name to find
	 * 
	 * @return mat-column value in the class field of the column
	 */
	public String getMatColumn(String strColumnName) {
		String strMatColumn = null;

		try {
			// Get the number of rows in the current displayed table com.fusion.pageobjects.page
			List<WebElement> listColumns = tblTable.findElements(By.xpath(".//mat-header-row/mat-header-cell"));
						
			for(WebElement column : listColumns) {
				try {
					if(column.getText().trim().contains(strColumnName)) {
						String strClass = column.getAttribute("class");
						String[] strClassArray = strClass.split(" ");
						
						for(String strValue : strClassArray) {
							if (strValue.contains("mat-column-")) {
								strMatColumn = strValue;
								break;
							}
						}
						break;
					}
				} catch (NullPointerException e) {
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Default function getNumberOfRows", "Exception while executing getMatColumn function", false);
			setLastTestPassed(false);
		}
		return strMatColumn;
	}
	
	/**
	 * Function to get the number of rows on a com.fusion.pageobjects.page in a table in the BlueLINK application
	 * 
	 * @author AD20221 Algie Watts
	 * 
	 * @param strTableType
	 *            the ID of the table
	 * 
	 * @return integer value of the number of rows in the current com.fusion.pageobjects.page in the table
	 */
	public int getNumberOfRows() {
		int intNumberOfRows = 0;

		try {
			// Get the number of rows in the current displayed table com.fusion.pageobjects.page
			List<WebElement> listRows = tblTable.findElements(By.xpath(".//mat-row"));
			intNumberOfRows = listRows.size();
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Default function getNumberOfRows", "Exception while executing getNumberOfRows function", false);
			setLastTestPassed(false);
		}
		return intNumberOfRows;
	}

	/**
	 * Function to get the row number from a table in the BlueLINK application that contains certain search value
	 * 
	 * @author AD20221 Algie Watts
	 * 
	 * @param strTableType
	 *            the ID of the table
	 * @param strSearchValue
	 *            the value to search for in the table
	 * @param intSearchColumn
	 *            the column index to perform the search in the table
	 * @param intNumberOfRows
	 *            the number of rows in the current com.fusion.pageobjects.page of the table
	 * 
	 * @return integer value of the number of rows in the current com.fusion.pageobjects.page in the table
	 * @throws Exception
	 */
	public int getRowWithCellText(String strSearchValue, String strMatColumn) throws Exception {
		int intRowNumber = -1;
		int intRowCounter = 1;
		String strCellValue = "";

		int intNumberOfRows = getNumberOfRows();
		
		while (intRowCounter <= intNumberOfRows) {

			strCellValue = null;
			strCellValue = getTableCellValue(intRowCounter, strMatColumn);
			
			// Verify if the search value is the found in the current row.
			if (strCellValue.equalsIgnoreCase(strSearchValue)) {
				intRowNumber = intRowCounter;
				break;
			}
			intRowCounter++;
		}

		return intRowNumber;
	}

	/**
	 * Function to find a value in a table in the DataHub application mat-table and click on a link in the same row
	 * 
	 * @author AD20221 Algie Watts
	 * 
	 * @param strSearchValue
	 *            the value to search for in the table
	 * @param strSearchColumn
	 *            the column name to perform the search in the table
	 * @param strClickColumn
	 *            the column name to perform the click in the table
	 * @param strClickText
	 *            the text of the link to click
	 * 
	 * @return boolean true or false depending on if the click was successful
	 */
	public boolean findValueInTableClick(String strSearchColumn, String strSearchValue, String strClickColumn, String strClickText) throws Exception {

		try {

			int intRowNumber = -1;

			String strMatColumn = getMatColumn(strClickColumn);
			
			do {
				intRowNumber = getRowWithCellText(strSearchValue, strMatColumn);
	
				if (intRowNumber > 0) {
					clickLinkInTableCell(intRowNumber, strMatColumn, strClickText);
					return true;
				}
	
				WebElement nextPage = getWebDriver().findElement(By.xpath("//button[contains(@class,'mat-paginator-navigation-next')]"));
				if (nextPage.isEnabled()) {
					nextPage.click();
					seWaitForPageLoad();
					nextPage=null;
				} else {
					break;
				}
			} while(true);
			
			// Fail if we have reached the last record on the last com.fusion.pageobjects.page and the expected result was true
			log(FAIL, "Find Value in table", "Fail: Search value \"" + strSearchValue + "\" NOT found in the table in column " + strSearchColumn, true);
			setLastTestPassed(false);
			return false;

		} catch (Exception excException) {
			processExceptions("Exception while executing findValueInTableClick function", excException);
			throw excException;
		}
	}

	/**
	 * Function to click on a link in a table cell based on the text, and row and column mat-column
	 *  
	 * @author AD20221 Algie Watts
	 * 
	 * @param intRowNumber
	 *            the row index to perform the click in the table
	 * @param strMatColumn
	 *            the mat-column of the column to perform the click in the table
	 * @param strClickText
	 *            the text of the link to click
	 *
	 */
	public void clickLinkInTableCell(int intRowNumber, String strMatColumn, String strClickText) {

		try {
			WebElement clickLink = tblTable.findElement(By.xpath(".//mat-row[" + intRowNumber + "]/mat-cell[contains(@class,'" + strMatColumn + "')]/a[text() = '" + strClickText + "']"));
			seScrollIntoView(clickLink, "Go To " + strClickText + " Link");
			seClick(clickLink, strClickText + " Link");
		} catch (Exception excException) {
			processExceptions("Exception while executing clickLinkInTableCell function", excException);
			throw excException;
		}
	}

	/**
	 * Function to click on a link in a table cell based on the text, and row index and column name
	 * 
	 * @author AD20221 Algie Watts
	 * 
	 * @param intRowNumber
	 *            the row index to perform the click in the table
	 * @param strColumnName
	 *            the column Name to perform the click in the table
	 * @param strClickText
	 *            the text of the link to click
	 *
	 */
	public void clickInTableCell(int intRowNumber, String strColumnName, String strClickText) {

		try {
			
			String strMatColumn = getMatColumn(strColumnName);
			clickLinkInTableCell(intRowNumber, strMatColumn, strClickText);
		} catch (Exception excException) {
			processExceptions("Exception while executing clickLinkInTableCell function", excException);
			throw excException;
		}
	}

	/**
	 * Function to retrieve the value in a specific cell in a table in the BlueLINK application
	 * 
	 * @author AD20221 Algie Watts
	 * 
	 * @param intRow
	 *            the integer value of the row of the cell to be extracted
	 * @param strMatColumn
	 *            the mat-column value of the column of the cell to be extracted
	 * 
	 * @return String value of the cell
	 * @throws Exception
	 */
	public String getTableCellValue(int intRow, String strMatColumn) throws Exception {

		String strCellValue = null;
		WebElement cellValue = null;

		try {
			Thread.sleep(250);
			// Get the cell value from the table
			cellValue = tblTable.findElement(By.xpath(".//mat-row[" + intRow + "]/mat-cell[contains(@class,'" + strMatColumn + "')]"));
			strCellValue = cellValue.getText().trim();

		} catch (Exception excException) {
			processExceptions("Exception while executing getColumnValues function", excException);
			throw excException;
		}

		return strCellValue;
	}

	/**
	 * Function to retrieve the value in a specific cell in a table in the Data Hub application based on a value found in a different column
	 * 
	 * @author AD20221 Algie Watts
	 * 
	 * @param strSearchColumn
	 *            the name of the search column
	 * @param strSearchValue
	 *            the string value to search for in the search column
	 * @param strGetValueColumn
	 *            the column to pull the value from after the search value is found
	 * 
	 * @return String value of the cell
	 * @throws Exception
	 */
	public String findValueInTableGetOtherCellValue(String strSearchColumn, String strSearchValue, String strGetValueColumn) throws Exception {

		String strCellValue = null;
		int intRowNumber = -1;
		
		String strSearchMatColumn = getMatColumn(strSearchColumn);
		String strGetMatColumn = getMatColumn(strGetValueColumn);

		try {
			
			do {
				intRowNumber = getRowWithCellText(strSearchValue, strSearchMatColumn);
	
				if (intRowNumber > 0) {
					strCellValue = getTableCellValue(intRowNumber, strGetMatColumn);
					break;
				}
				
				WebElement nextPage = getWebDriver().findElement(By.xpath("//button[contains(@class,'mat-paginator-navigation-next')]"));
				if (nextPage.isEnabled()) {
					nextPage.click();
					seWaitForPageLoad();
					nextPage=null;
				} else {
					break;
				}
			} while(true);
			
			// Fail if we have reached the last record on the last com.fusion.pageobjects.page and the search value was not found
			log(FAIL, "Find Value in table", "Fail: Search value \"" + strSearchValue + "\" NOT found in the table in column " + strSearchColumn, true);
			setLastTestPassed(false);
		} catch (Exception excException) {
			processExceptions("Exception while executing getColumnValues function", excException);
			throw excException;
		}
		return strCellValue;
	}

	/**
	 * Function to verify the value in a specific cell in a table in the Data Hub application based on a value found in a different column
	 * 
	 * @author AD20221 Algie Watts
	 * 
	 * @param strSearchColumn
	 *            the namee of the search column
	 * @param strSearchValue
	 *            the string value to search for in the search column
	 * @param intGetValueColumn
	 *            the column to pull the value from after the search value is found
	 * @param strVerifyValue
	 * 			  The value to verify           
	 * 
	 * @return true or false based on the result
	 * @throws Exception
	 */
	public boolean findValueInTableVerifyOtherCellValue(String strSearchColumn, String strSearchValue, String strGetValueColumn, String strVerifyValue) throws Exception {
		boolean returnValue = false;

		try {
			String strCellValue = findValueInTableGetOtherCellValue(strSearchColumn, strSearchValue, strGetValueColumn);

			if (strCellValue.equalsIgnoreCase(strVerifyValue)) {
				returnValue = true;
				log(PASS, "Find Value In Table Verify Other Table Cell", "PASS: Value in column [" + strGetValueColumn + "] for Row Containing Serarch Value [" + strSearchValue + "] Contains passed value [" + strVerifyValue + "]", true);
			} else {
				log(FAIL, "Find Value In Table Verify Other Table Cell","FAIL: Value in column [" + strGetValueColumn + "] for Row Containing Serarch Value [" + strSearchValue + "] Contains invalid value.  Expected [" + strVerifyValue + "], Actual Value [" + strCellValue + "]", true);
				setLastTestPassed(false);
			}
		} catch (Exception excException) {
			processExceptions("Exception while executing getColumnValues function", excException);
			throw excException;
		}
		return returnValue;
	}

}
