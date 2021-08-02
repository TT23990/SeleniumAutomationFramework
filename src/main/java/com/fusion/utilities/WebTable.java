package com.fusion.utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.anthem.ataf.logging.AnthemLogger;
import com.anthem.selenium.SuperHelper;

/**
 * Class to handle all table related methods. This class does not contains methods specific to header i.e., thead tag.<br>
 * Works only when there is a tag called tbody in the table. If tag name is different, changes need to be made to the class.
 * </p>
 * Note: For the user, row and column index start from 1.
 * </p>
 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result");
 * </p>
 * </p>
 * 
 * @author AF37512 Santosh Bukkashetti
 * @since 1-August-2017
 */
public class WebTable extends SuperHelper {

	protected WebElement tblTable;
	private WebElement tbdyTableBody;
	private String strTableName;
	protected AnthemLogger logger = AnthemLogger.getLogger(WebTable.class.getName());
	private int intNumberOfPagesInTable;

	/**
	 * Parameterized Constructor for WebTable
	 * </p>
	 * 
	 * @param argTable
	 *            WebElement of Table
	 * @autor AF34794 Usharani Arunachalam
	 * @since 8-August-2017
	 */
	public WebTable(WebElement argTable, String strName) {
		try {
			tblTable = argTable;
			tbdyTableBody = tblTable.findElement(By.tagName("tbody"));
			strTableName = strName;
			intNumberOfPagesInTable = getNumberOfTablePages();
		} catch (Exception excException) {
			processExceptions("Exception while executing WebTable constructor", excException);
			throw excException;
		}
	}

	public WebTable(WebElement argTable, String strName, int intNumberOfPages) {
		try {
			tblTable = argTable;
			tbdyTableBody = tblTable.findElement(By.tagName("tbody"));
			strTableName = strName;
			intNumberOfPagesInTable = intNumberOfPages;
		} catch (Exception excException) {
			processExceptions("Exception while executing WebTable constructor", excException);
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
	 * Gets the row count of a table
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * int intRowsCount = wtClaimsResult.getRowsCount();
	 * </p>
	 * 
	 * @return intRowsCount number of rows in the table
	 * @author AF34794 Usharani Arunachalam
	 * @since 3-August-2017
	 */
	public int getRowsCount() {

		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			int intRowsCount = findRowPath.size();
			return intRowsCount;
		} catch (Exception excException) {
			processExceptions("Exception occured while fetching row count of table " + strTableName, excException);
			throw excException;
		}
	}

	/**
	 * Gets the Column count of first row of a table
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * int intColCount = wtClaimsResult.getColumnCount();
	 * </p>
	 * 
	 * @return intColCount number of column in first row
	 * @author AF34794 Usharani Arunachalam
	 * @since 3-August-2017
	 */
	public int getColumnCount() {

		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			List<WebElement> findColPath = findRowPath.get(0).findElements(By.tagName("*"));
			int intColCount = findColPath.size();
			return intColCount;
		} catch (Exception excException) {
			processExceptions("Exception occured while fetching column count of table", excException);
			throw excException;
		}

	}

	/**
	 * Gets the Column count of first row of a table if it is the header row using th
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * int intColCount = wtClaimsResult.getColumnCount();
	 * </p>
	 * 
	 * @return intColCount number of column in first row
	 * @author AF34794 Usharani Arunachalam
	 * @since 3-August-2017
	 */
	public int getColumnCountTh() {

		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			List<WebElement> findColPath = findRowPath.get(0).findElements(By.tagName("th"));
			int intColCount = findColPath.size();
			return intColCount;
		} catch (Exception excException) {
			processExceptions("Exception occured while fetching column count of table", excException);
			throw excException;
		}

	}

	/**
	 * Gets the Column count of first row of a table if it is the header row using th
	 * </p>
	 * 
	 * @param strHeaderName
	 *            Text value of the header name to search for
	 * @return intColumnIndex number of column in first row
	 * @author AD20221 Algie Watts
	 * @since 30-January-2018
	 */
	public int getColumnIndexFromHeaderName(String strHeaderName) {
		int intColumnIndex = -1;

		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			List<WebElement> findColPath = findRowPath.get(0).findElements(By.tagName("div"));
			int intColCount = findColPath.size();

			for (int column = 0; column < intColCount; column++) {
				String strCellText = findColPath.get(column).getText();
				if (strCellText.equals(strHeaderName)) {
					intColumnIndex = column;
					break;
				}
			} // column loop ends
			return intColumnIndex;
		} catch (Exception excException) {
			processExceptions("Exception occured while fetching column count of table", excException);
			throw excException;
		}

	}

	/**
	 * Gets Column count of a particular row of a table
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * int intColCount = wtClaimsResult.getColumnCountOfARow(2);
	 * </p>
	 * 
	 * @param intRowNumber
	 *            Row number starting from 1
	 * @return intColCount Column count in a Row having intRowNumber in Table
	 * @author AF34794 Usharani Arunachalam
	 * @since 1-August-2017
	 */
	public int getColumnCountOfARow(int intRowNumber) {

		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));

			// List<WebElement> findColPath = findRowPath.get(intRowNumber - 1).findElements(By.tagName("td"));
			List<WebElement> findColPath = findRowPath.get(intRowNumber).findElements(By.tagName("td"));
			int intColCount = findColPath.size();
			return intColCount;

		} catch (Exception excException) {
			processExceptions("Exception occured while getting column count of a row", excException);
			throw excException;
		}

	}

	/**
	 * Gets the cell text value in the given row and column
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * String strCellText = wtClaimsResult.getCellData(2, 3);
	 * </p>
	 * 
	 * @param intRowNumber
	 *            Row number starting from 1 eg-1,2,etc
	 * @param intColumnNumber
	 *            Column number starting from 1 of Row having intRowNumber eg-1,2,etc
	 * @return strCellText-text value of the cell eg- WebTable wb = new
	 * @author AF34794 Usharani Arunachalam
	 * @since 1-August-2017
	 */
	public String getCellData(int intRowNumber, int intColumnNumber) {

		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			// List<WebElement> findColumnPath = findRowPath.get(intRowNumber - 1).findElements(By.tagName("td"));
			// String strCellText = findColumnPath.get(intColumnNumber - 1).getText();
			List<WebElement> findColumnPath = findRowPath.get(intRowNumber).findElements(By.tagName("td"));
			String strCellText = findColumnPath.get(intColumnNumber).getText();

			return strCellText;
		} catch (Exception excException) {
			processExceptions("Exception occured in getting cell text value of given Row and column", excException);
			throw excException;
		}

	}

	/**
	 * Gets the row number with a specific text
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * int intRowNumber = wtClaimsResult.getRowWithCellText("17046PVLSDIQCA");
	 * </p>
	 * 
	 * @param strText
	 *            String data to search in the Table
	 * @return intRowNumber Row number of strText
	 * @author AF34794 Usharani Arunachalam
	 * @since 3-August-2017
	 */

	public int getRowWithCellText(String strText) {
		int intRowNumber = -1;
		boolean flag = false;
		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			int rowsCount = findRowPath.size();
			for (int row = 0; row < rowsCount; row++) {
				List<WebElement> findColumnPath = findRowPath.get(row).findElements(By.tagName("td"));
				int columnCount = findColumnPath.size();
				for (int column = 0; column < columnCount; column++) {
					String strCellText = findColumnPath.get(column).getText();
					if (strCellText.equals(strText)) {
						// intRowNumber = row + 1;
						intRowNumber = row;
						flag = true;
						break;
					}
				} // column loop ends
				if (flag == true)
					break;
				else
					continue;
			} // row loop ends

		} catch (Exception excException) {
			processExceptions("Exception occured while getting Row number with a specific text", excException);
			throw excException;
		}
		return intRowNumber;
	}

	/**
	 * Searches the table for specific text and clicks on the table cell
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * int intRowNumber = wtClaimsResult.findValueInTableClickInTableCell("17046PVLSDIQCA");
	 * </p>
	 * 
	 * @param strText
	 *            String data to search in the Table
	 * @author AD20221 Algie Watts
	 * @since 23-January-2018
	 */
	public boolean findValueInTableClickInTableCell(String strText) {
		boolean flag = false;
		try {
			int intCurrentPage = 1;

			do {
				List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
				int rowsCount = findRowPath.size();
				for (int row = 0; row < rowsCount; row++) {
					List<WebElement> findColumnPath = findRowPath.get(row).findElements(By.tagName("td"));
					int columnCount = findColumnPath.size();
					for (int column = 0; column < columnCount; column++) {
						String strCellText = findColumnPath.get(column).getText();
						if (strCellText.equals(strText)) {
							clickInTableCell(row, column);
							flag = true;
							break;
						}
					} // column loop ends
					if (flag == true)
						break;
					else
						continue;
				} // row loop ends

				if (flag == true) {
					break;
				} else {
					intCurrentPage++;
					if (intCurrentPage <= intNumberOfPagesInTable) {
						selectNextPage(intCurrentPage);
					} else {
						log(FAIL, "Find Value In Table Click In Table Cell", "Fail: search value: " + strText + " not found in table.", false);
						setLastTestPassed(false);
						break;
					}
				}
			} while (intCurrentPage <= intNumberOfPagesInTable);

		} catch (Exception excException) {
			processExceptions("Exception occured during findValueInTableClickInTableCell function", excException);
			throw excException;
		}

		return flag;
	}

	/**
	 * Searches the table for specific text in a specific column and clicks on the table cell
	 * </p>
	 * 
	 * @param strText
	 *            String data to search in the Table
	 * @param intColumnNumber
	 *            Column number to search through starting at 0
	 * @author AD20221 Algie Watts
	 * @since 30-January-2018
	 */
	public boolean findValueInTableColumnClickInTableCell(int IntColumnNumber, String strText) {
		boolean flag = false;
		try {
			int intCurrentPage = 1;

			do {
				List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
				int rowsCount = findRowPath.size();
				for (int row = 0; row < rowsCount; row++) {
					List<WebElement> findColumnPath = findRowPath.get(row).findElements(By.tagName("td"));
					String strCellText = findColumnPath.get(IntColumnNumber).getText();
					if (strCellText.equals(strText)) {
						clickInTableCell(row, IntColumnNumber);
						flag = true;
						break;
					}
				} // row loop ends

				if (flag == true) {
					break;
				} else {
					intCurrentPage++;
					if (intCurrentPage <= intNumberOfPagesInTable) {
						selectNextPage(intCurrentPage);
					} else {
						log(FAIL, "Find Value In Table Click In Table Cell", "Fail: search value: " + strText + " not found in table.", false);
						setLastTestPassed(false);
						break;
					}
				}
			} while (intCurrentPage <= intNumberOfPagesInTable);

		} catch (Exception excException) {
			processExceptions("Exception occured during findValueInTableClickInTableCell function", excException);
			throw excException;
		}
		return flag;

	}

	/**
	 * Searches the table for specific text in a specific column and clicks on the table cell in a different column
	 * </p>
	 * 
	 * @param strText
	 *            String data to search in the Table
	 * @param intColumnNumber
	 *            Column number to search through starting at 0
	 * @param intClickColumn
	 *            Column to click in starting at 0
	 * @author AD20221 Algie Watts
	 * @since 30-January-2018
	 */
	public boolean findValueInTableColumnClickInOtherTableCell(int intColumnNumber, String strText, int intClickColumn) {
		boolean blnReturnFlag = false;
		try {
			int intCurrentPage = 1;

			do {
				int intRow = getRowWithCellTextInColumn(0, intColumnNumber, strText);

				if (intRow >= 0) {
					clickInTableCell(intRow, intClickColumn);
					blnReturnFlag = true;
					break;
				}
				intCurrentPage++;
				if (intCurrentPage <= intNumberOfPagesInTable) {
					selectNextPage(intCurrentPage);
				} else {
					log(FAIL, "Find Value In Table Click In Table Cell", "Fail: search value: " + strText + " not found in table.", false);
					setLastTestPassed(false);
				}

			} while (intCurrentPage <= intNumberOfPagesInTable);

		} catch (Exception excException) {
			processExceptions("Exception occured during findValueInTableClickInTableCell function", excException);
			throw excException;
		}

		return blnReturnFlag;
	}

	/**
	 * Searches the table for specific text in a specific column and clicks on the Link(<a>) in table cell in a different column
	 * 
	 * 
	 * </p>
	 * 
	 * @param strText
	 *            String data to search in the Table
	 * @param intColumnNumber
	 *            Column number to search through starting at 0
	 * @param intClickColumn
	 *            Column to click in starting at 0
	 * @author AD20221 Algie Watts
	 * @since 02-August-2018
	 */
	public boolean findValueInTableColumnClickLinkInOtherTableCell(int intColumnNumber, String strText, int intClickColumn) {
		boolean blnReturnFlag = false;
		try {
			int intCurrentPage = 1;

			do {
				int intRow = getRowWithCellTextInColumn(0, intColumnNumber, strText);

				if (intRow >= 0) {
					intRow++;
					intClickColumn++;
					WebElement clickLink = tbdyTableBody.findElement(By.xpath("//tr[" + intRow + "]//td[" + intClickColumn + "]/a"));
					seClick(clickLink, "Link in Row " + intRow + ", Column " + intClickColumn);
					blnReturnFlag = true;
					break;
				}
				intCurrentPage++;
				if (intCurrentPage <= intNumberOfPagesInTable) {
					selectNextPage(intCurrentPage);
				} else {
					log(FAIL, "Find Value In Table Click In Table Cell", "Fail: search value: " + strText + " not found in table.", false);
					setLastTestPassed(false);
				}

			} while (intCurrentPage <= intNumberOfPagesInTable);

		} catch (Exception excException) {
			processExceptions("Exception occured during findValueInTableClickInTableCell function", excException);
			throw excException;
		}

		return blnReturnFlag;
	}

	/**
	 * Searches the table for two different values in two different columns and clicks on a table cell in the same row
	 * </p>
	 * 
	 * @param strFirstText
	 *            String data to search in the Table
	 * @param intFirstColumnNumber
	 *            Column number to search through starting at 0
	 * @param strSecondText
	 *            String data to search in the Table
	 * @param intSecondColumnNumber
	 *            Column number to search through starting at 0
	 * @param intClickColumn
	 *            Column to click in starting at 0
	 * @author AD20221 Algie Watts
	 * @since 30-January-2018
	 */
	public boolean findValuesInTableColumnsClickInOtherTableCell(int intFirstColumnNumber, String strFirstText, int intSecondColumnNumber, String strSecondText, int intClickColumn) {
		boolean blnReturnFlag = false;

		try {
			int intCurrentPage = 1;

			do {
				int intRow = getRowWithCellTextInTwoColumns(0, intFirstColumnNumber, strFirstText, intSecondColumnNumber, strSecondText);
				if (intRow >= 0) {
					clickInTableCell(intRow, intClickColumn);
					blnReturnFlag = true;
					break;
				}
				intCurrentPage++;
				if (intCurrentPage <= intNumberOfPagesInTable) {
					selectNextPage(intCurrentPage);
				} else {
					log(FAIL, "Find Value In Table Click In Table Cell", "Fail: Row with search values: " + strFirstText + " and " + strSecondText + " not found in table.", false);
					setLastTestPassed(false);
				}
			} while (intCurrentPage <= intNumberOfPagesInTable);

		} catch (Exception excException) {
			processExceptions("Exception occured during findValueInTableClickInTableCell function", excException);
			throw excException;
		}
		return blnReturnFlag;
	}

	/**
	 * Searches the table for specific text in a specific column and verifies the value in a different table cell in the same row
	 * </p>
	 * 
	 * @param strText
	 *            String data to search in the Table
	 * @param intColumnNumber
	 *            Column number to search through starting at 0
	 * @param intVerifyColumn
	 *            Column to verify in starting at 0
	 * @param strVerifyText
	 *            String value to compare to cell value
	 * @param blnScreenShot
	 *            Whether or not to take a screenshot on a pass
	 * @author AD20221 Algie Watts
	 * @since 30-January-2018
	 */
	public boolean findValueInTableColumnVerifyOtherTableCell(int intColumnNumber, String strText, int intVerifyColumn, String strVerifyText, boolean blnScreenShot) {
		boolean blnReturnValue = false;
		try {
			int intCurrentPage = 1;
			String strCellData = null;

			do {
				int intRow = getRowWithCellTextInColumn(0, intColumnNumber, strText);

				if (intRow >= 0) {
					strCellData = getCellData(intRow, intVerifyColumn);
					break;
				}
				intCurrentPage++;
				if (intCurrentPage <= intNumberOfPagesInTable) {
					selectNextPage(intCurrentPage);
				} else {
					log(FAIL, "Find Value In Table Verify Other Table Cell", "Fail: search value: " + strText + " not found in table.", false);
					setLastTestPassed(false);
				}

			} while (intCurrentPage <= intNumberOfPagesInTable);

			if (strCellData.equals(strVerifyText)) {
				blnReturnValue = true;
				log(PASS, "Find Value In Table Verify Other Table Cell", "Cell Data [" + strCellData + "] matches expected value [" + strVerifyText + "]", blnScreenShot);
			} else {
				blnReturnValue = false;
				log(FAIL, "Find Value In Table Verify Other Table Cell", "Cell Data [" + strCellData + "] does not match expected value [" + strVerifyText + "]", true);
				setLastTestPassed(false);
			}

		} catch (Exception excException) {
			processExceptions("Exception occured during findValueInTableClickInTableCell function", excException);
			throw excException;
		}

		return blnReturnValue;
	}

	/**
	 * Searches the table for specific text in a specific column and verifies the value in a different table cell in the same row contains a string
	 * </p>
	 * 
	 * @param strText
	 *            String data to search in the Table
	 * @param intColumnNumber
	 *            Column number to search through starting at 0
	 * @param intVerifyColumn
	 *            Column to verify in starting at 0
	 * @param strVerifyText
	 *            String value to compare to cell value
	 * @param blnScreenShot
	 *            Whether or not to take a screenshot on a pass
	 * @author AD20221 Algie Watts
	 * @since 30-July-2018
	 */
	public boolean findValueInTableColumnVerifyOtherTableCellContains(int intColumnNumber, String strText, int intVerifyColumn, String strVerifyText, boolean blnScreenShot) {
		boolean blnReturnValue = false;
		try {
			int intCurrentPage = 1;
			String strCellData = null;

			do {
				int intRow = getRowWithCellTextInColumn(0, intColumnNumber, strText);

				if (intRow >= 0) {
					strCellData = getCellData(intRow, intVerifyColumn);
					break;
				}
				intCurrentPage++;
				if (intCurrentPage <= intNumberOfPagesInTable) {
					selectNextPage(intCurrentPage);
				} else {
					log(FAIL, "Find Value In Table Verify Other Table Cell", "Fail: search value: " + strText + " not found in table.", false);
					setLastTestPassed(false);
				}

			} while (intCurrentPage <= intNumberOfPagesInTable);

			if (strCellData.contains(strVerifyText)) {
				blnReturnValue = true;
				log(PASS, "Find Value In Table Verify Other Table Cell Contains Verification Value", "Cell Data [" + strCellData + "] contains expected value [" + strVerifyText + "]", blnScreenShot);
			} else {
				blnReturnValue = false;
				log(FAIL, "Find Value In Table Verify Other Table Cell Contains Verification Value", "Cell Data [" + strCellData + "] does not contain expected value [" + strVerifyText + "]", true);
				setLastTestPassed(false);
			}

		} catch (Exception excException) {
			processExceptions("Exception occured during findValueInTableColumnVerifyOtherTableCellContains function", excException);
			throw excException;
		}

		return blnReturnValue;
	}

	/**
	 * Searches the table for specific text in a specific column and verifies the value in a different table cell in the same row
	 * </p>
	 * 
	 * @param strFirstText
	 *            String data to search in the Table
	 * @param intFirstColumnNumber
	 *            Column number to search through starting at 0
	 * @param strSecondText
	 *            String data to search in the Table
	 * @param intSecondColumnNumber
	 *            Column number to search through starting at 0
	 * @param intVerifyColumn
	 *            Column to verify in starting at 0
	 * @param strVerifyText
	 *            String value to compare to cell value
	 * @param blnScreenShot
	 *            Whether or not to take a screen shot on a pass
	 * @author AD20221 Algie Watts
	 * @since 30-January-2018
	 */
	public boolean findValuesInTableColumnsVerifyOtherTableCell(int intFirstColumnNumber, String strFirstText, int intSecondColumnNumber, String strSecondText, int intVerifyColumn,
			String strVerifyText, boolean blnScreenShot) {
		boolean blnReturnValue = false;
		try {
			int intCurrentPage = 1;
			String strCellData = null;

			do {
				int intRow = getRowWithCellTextInTwoColumns(0, intFirstColumnNumber, strFirstText, intSecondColumnNumber, strSecondText);

				if (intRow >= 0) {
					strCellData = getCellData(intRow, intVerifyColumn);
					break;
				}
				intCurrentPage++;
				if (intCurrentPage <= intNumberOfPagesInTable) {
					selectNextPage(intCurrentPage);
				} else {
					log(FAIL, "Find Values In Table Verify Other Table Cell", "Fail: a Row with both search values [" + strFirstText + " and " + strSecondText + "] not found in table.", false);
					setLastTestPassed(false);
				}

			} while (intCurrentPage <= intNumberOfPagesInTable);

			if (strCellData.equals(strVerifyText)) {
				blnReturnValue = true;
				log(PASS, "Find Value In Table Verify Other Table Cell", "Cell Data [" + strCellData + "] matches expected value [" + strVerifyText + "]", blnScreenShot);
			} else {
				blnReturnValue = false;
				log(FAIL, "Find Value In Table Verify Other Table Cell", "Cell Data [" + strCellData + "] does not match expected value [" + strVerifyText + "]", true);
				setLastTestPassed(false);
			}

		} catch (Exception excException) {
			processExceptions("Exception occured during findValueInTableClickInTableCell function", excException);
			throw excException;
		}

		return blnReturnValue;
	}

	/**
	 * Clicks in the table cell by given row and column
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * String strCellText = wtClaimsResult.getCellData(2, 3);
	 * </p>
	 * 
	 * @param intRowNumber
	 *            Row number starting from 1 eg-1,2,etc
	 * @param intColumnNumber
	 *            Column number starting from 1 of Row having intRowNumber eg-1,2,etc
	 * @return strCellText-text value of the cell eg- WebTable wb = new
	 * @author AD20221 Algie Watts
	 * @since 23-January-2018
	 */
	public void clickInTableCell(int intRowNumber, int intColumnNumber) {

		try {
			WebElement cellValue = getCell(intRowNumber, intColumnNumber + 1);
			seClick(cellValue, "Value in Cell in Row " + intRowNumber + " and Column " + (intColumnNumber));
		} catch (Exception excException) {
			processExceptions("Exception occured in getting cell text value of given Row and column", excException);
			throw excException;
		}

	}

	/**
	 * Gets all row numbers with a specific text
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * int[] arrIntRowNumbers = wtClaimsResult.getAllRowsWithCellText("17046PVLSDIQCA");
	 * </p>
	 * 
	 * @param strText
	 *            String data to search in the Table
	 * @return intRowNumber All rows with text strText
	 * @author AF30637 Rajat Mishra
	 * @since 3-August-2017
	 */

	public int[] getAllRowsWithCellText(String strText) {
		HashMap<Integer, String> mapValue = new HashMap<Integer, String>();
		int[] intAllRowIndex = null;
		int index = 0;
		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			int rowsCount = findRowPath.size();
			for (int row = 0; row < rowsCount; row++) {
				List<WebElement> findColumnPath = findRowPath.get(row).findElements(By.tagName("td"));
				int columnCount = findColumnPath.size();
				for (int column = 0; column < columnCount; column++) {
					String strCellText = findColumnPath.get(column).getText();
					if (strCellText.equals(strText)) {
						// mapValue.put((row + 1), getCellData(row + 1, 2));
						mapValue.put((row), getCellData(row, 2));
						break;
					}
				} // column loop ends

			} // row loop ends
			intAllRowIndex = new int[mapValue.size()];
			for (Map.Entry<Integer, String> entry : mapValue.entrySet()) {
				do {
					intAllRowIndex[index] = entry.getKey();
					index++;
				} while (index > mapValue.size());
			}

		} catch (Exception excException) {
			processExceptions("Exception occured while getting Row number with a specific text", excException);
			throw excException;
		}
		return intAllRowIndex;
	}

	/**
	 * Gets the row number with a specific text present in specific column
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * int intRowNumbers = wtClaimsResult.getRowWithCellTextInColumn(5, 3, "17046PVLSDIQCA");
	 * </p>
	 * 
	 * @param intStartFromThisRow
	 *            Function will start looking for strText from this row. Saves some time.
	 * @param intColumnNumber
	 *            Column number starting from 1 in a Row eg-1,2,etc
	 * @param strText
	 *            String data to be searched in the a specific column of Table
	 * @return int Row number
	 * @author AF34794 Usharani Arunachalam
	 * @since 3-August-2017
	 */

	public int getRowWithCellTextInColumn(int intStartFromThisRow, int intColumnNumber, String strText) {
		int intRowNumber = -1;
		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			int rowsCount = findRowPath.size();
			// for (int row = intStartFromThisRow - 1; row < rowsCount; row++) {
			for (int row = intStartFromThisRow; row < rowsCount; row++) {
				List<WebElement> findColumnPath = findRowPath.get(row).findElements(By.tagName("td"));
				// String strCellText = findColumnPath.get(intColumnNumber - 1).getText();
				String strCellText = findColumnPath.get(intColumnNumber).getText();
				if (strCellText.equals(strText)) {
					// intRowNumber = row + 1;
					intRowNumber = row;
					log(PASS, "Get Row With Cell Text In Column", "Search Value [" + strText + "] found in row " + intRowNumber, false);
					break;
				}
			}

		} catch (Exception excException) {
			processExceptions("Exception occured while getting Row number with a specific text present in specific column", excException);
			throw excException;
		}
		return intRowNumber;
	}

	/**
	 * Gets the row number with a specific text present in two different columns in the same row
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * int intRowNumbers = wtClaimsResult.getRowWithCellTextInColumn(5, 3, "17046PVLSDIQCA");
	 * </p>
	 * 
	 * @param intStartFromThisRow
	 *            Function will start looking for strText from this row. Saves some time.
	 * @param intFirstColumnNumber
	 *            Column number starting from 0 in a Row eg-0,1,2,etc
	 * @param strFirstText
	 *            String data to be searched in the a first column of Table
	 * @param intSecondColumnNumber
	 *            Column number starting from 0 in a Row eg-0,1,2,etc
	 * @param strSecondText
	 *            String data to be searched in the second column of Table
	 * @return int Row number
	 * @author AD20221 Algie Watts
	 * @since 30-January-2018
	 */

	public int getRowWithCellTextInTwoColumns(int intStartFromThisRow, int intFirstColumnNumber, String strFirstText, int intSecondColumnNumber, String strSecondText) {
		int intRowNumber = -1;
		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			int rowsCount = findRowPath.size();
			// for (int row = intStartFromThisRow - 1; row < rowsCount; row++) {
			for (int row = intStartFromThisRow; row < rowsCount; row++) {
				List<WebElement> findColumnPath = findRowPath.get(row).findElements(By.tagName("td"));
				// String strCellText = findColumnPath.get(intColumnNumber - 1).getText();
				String strCellText = findColumnPath.get(intFirstColumnNumber).getText();
				if (strCellText.equals(strFirstText)) {
					String strSecondCellText = findColumnPath.get(intSecondColumnNumber).getText();
					if (strSecondCellText.equals(strSecondText)) {
						// intRowNumber = row + 1;
						intRowNumber = row;
						log(PASS, "Get Row With Cell Text In Two Columns", "Search Values [" + strFirstText + " and " + strSecondText + "] found in row " + intRowNumber, false);
						break;
					}
				}
			}

		} catch (Exception excException) {
			processExceptions("Exception occured while getting Row number with a specific text present in specific column", excException);
			throw excException;
		}
		return intRowNumber;
	}

	/**
	 * Gets all row numbers with a specific text present in specific column
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * int[] arrIntRowNumbers = wtClaimsResult.getAllRowsWithCellTextInColumn(5, 3, "17046PVLSDIQCA");
	 * </p>
	 * 
	 * @param intStartFromThisRow
	 *            Function will start looking for strText from this row. Saves some time.
	 * @param intColumnNumber
	 *            Column number starting from 1 in a Row eg-1,2,etc
	 * @param strText-String
	 *            data to be searched in the a specific column of Table
	 * @return int Row number
	 * @author AF34794 Usharani Arunachalam
	 * @since 3-August-2017
	 */

	public int[] getAllRowsWithCellTextInColumn(int intStartFromThisRow, int intColumnNumber, String strText) {
		HashMap<Integer, String> mapValue = new HashMap<Integer, String>();
		int[] intAllRowIndex = null;
		int index = 0;
		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			int rowsCount = findRowPath.size();
			// for (int row = intStartFromThisRow - 1; row < rowsCount; row++) {
			for (int row = intStartFromThisRow; row < rowsCount; row++) {
				List<WebElement> findColumnPath = findRowPath.get(row).findElements(By.tagName("td"));
				// String strCellText = findColumnPath.get(intColumnNumber - 1).getText();
				String strCellText = findColumnPath.get(intColumnNumber).getText();

				if (strCellText.equals(strText)) {

					// mapValue.put(row, findColumnPath.get(intColumnNumber - 1).getText());
					mapValue.put(row, findColumnPath.get(intColumnNumber).getText());
					continue;
				}
			}
			intAllRowIndex = new int[mapValue.size()];
			for (Map.Entry<Integer, String> entry : mapValue.entrySet()) {
				do {
					intAllRowIndex[index] = entry.getKey() + 1;
					index++;
				} while (index > mapValue.size());
			}
		} catch (Exception excException) {
			processExceptions("Exception occured while getting Row number with a specific text present in specific column", excException);
			throw excException;
		}

		return intAllRowIndex;
	}

	/**
	 * Gets the list of WebElements in a table using xpath with a specific row and column
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * List<WebElement> childItems = wtClaimsResult.getChildItemsByXPath(5, 3, ".//img[@class='up-arrow']");
	 * </p>
	 * 
	 * @param intColumnNumber-Column
	 *            number starting from 1 in a Row eg-1,2,etc
	 * @param intRowNumber-Row
	 *            number starting from 1 in Table eg-1,2,etc
	 * @param strItemsXPATH-xpath
	 *            of element inside table cell
	 * @return List<WebElement> - childItems with a specific row and column
	 * @author Santosh Bukkashetti
	 * @since 3-August-2017
	 */
	public List<WebElement> getChildItemsByXPath(int intRowNumber, int intColumnNumber, String strItemsXPATH) {
		List<WebElement> childItems = null;
		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			// List<WebElement> findColumnPath = findRowPath.get(intRowNumber - 1).findElements(By.tagName("td"));
			// WebElement tableCell = findColumnPath.get(intColumnNumber - 1);
			List<WebElement> findColumnPath = findRowPath.get(intRowNumber).findElements(By.tagName("td"));
			WebElement tableCell = findColumnPath.get(intColumnNumber);
			childItems = tableCell.findElements(By.xpath(strItemsXPATH));

		} catch (Exception excException) {
			processExceptions("Exception occured while capturing webelement in a cell using xpath", excException);
			throw excException;
		}
		return childItems;
	}

	/**
	 * Gets the list of WebElement in a table using TagName with a specific row and column
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * List<WebElement> childItems = wtClaimsResult.getChildItemsByTagName(5, 3, "span");
	 * </p>
	 * 
	 * @param intColumnNumber-Column
	 *            number starting from 1 in a Row eg-1,2,etc
	 * @param intRowNumber-Row
	 *            number starting from 1 in Table eg-1,2,etc
	 * @param strTagName-tagName
	 *            of element inside table cell
	 * @return List<WebElement> - childItems with a specific row and column
	 * @author Santosh Bukkashetti
	 * @since 3-August-2017
	 */
	public List<WebElement> getChildItemsByTagName(int intRowNumber, int intColumnNumber, String strTagName) {
		List<WebElement> childItems = null;
		try {
			List<WebElement> rwTableRows = tbdyTableBody.findElements(By.tagName("tr"));
			// List<WebElement> tableCells = rwTableRows.get(intRowNumber - 1).findElements(By.tagName("td"));
			//
			// WebElement tableCell = tableCells.get(intColumnNumber - 1);

			List<WebElement> tableCells = rwTableRows.get(intRowNumber).findElements(By.tagName("td"));

			WebElement tableCell = tableCells.get(intColumnNumber);
			childItems = tableCell.findElements(By.tagName(strTagName));
		} catch (Exception excException) {
			processExceptions("Exception occured while capturing webelement in a cell using TagName", excException);
			throw excException;
		}
		return childItems;

	}

	/**
	 * Returns all cells values
	 * </p>
	 * Please Note- This method may take time depending on the table size. More Data More Time eg-1,2,etc
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * String[][] arrAllCellValues = wtClaimsResult.getAllCellsValues();
	 * </p>
	 * 
	 * @return String[][] arrAllCellValues- contains Table details in row column format
	 * @author AF30637 Rajat Mishra
	 * @since 3-August-2017
	 * 
	 */

	public String[][] getAllCellsValues() {
		String[][] arrAllCellValues = null;
		int index = 0;
		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));

			arrAllCellValues = new String[findRowPath.size()][];
			for (int rowsCount = 0; rowsCount < findRowPath.size(); rowsCount++) {
				List<WebElement> findColPath = findRowPath.get(rowsCount).findElements(By.tagName("td"));
				arrAllCellValues[index] = new String[findColPath.size()];
				for (int columnCount = 0; columnCount < findColPath.size(); columnCount++) {
					arrAllCellValues[rowsCount][columnCount] = findColPath.get(columnCount).getText();

				}
				index++;
			}

		} catch (Exception excException) {
			processExceptions("Exception occured while getting all cell values of Table", excException);
			throw excException;
		}

		return arrAllCellValues;

	}

	/**
	 * Returns values from all rows with specific column number
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * String[] arrAllCellValuesOfAColumn = wtClaimsResult.getAllCellValuesOfAColumn(7);
	 * </p>
	 * 
	 * @param intColumnNumber
	 *            Column number from which vlaues need to be fetched
	 * @return String[] arrAllCellValuesOfAColumn column values from all rows with specific column number
	 * @author Gautam Kumar
	 * @since 8-August-2017
	 */

	public String[] getAllCellValuesOfAColumn(int intColumnNumber) {
		String[] arrAllCellValuesOfAColumn = null;
		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			arrAllCellValuesOfAColumn = new String[findRowPath.size()];
			WebElement cell;
			for (int rowsCount = 0; rowsCount < findRowPath.size(); rowsCount++) {
				cell = findRowPath.get(rowsCount).findElement(By.xpath("./td[" + intColumnNumber + "]"));
				arrAllCellValuesOfAColumn[rowsCount] = cell.getText();

			}

		} catch (Exception excException) {
			processExceptions("Exception occured while fetching values from a specific column number", excException);
			throw excException;
		}
		return arrAllCellValuesOfAColumn;
	}

	/**
	 * Rreturns the cell in table with specific row and column
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * WebElement findCell = wtClaimsResult.getCell(2, 7);
	 * </p>
	 * 
	 * @param intRowNumber
	 *            Row number starting from 0
	 * @param intColumnNumber
	 *            Column number starting from 0
	 * @return Table cell as WebElement
	 * @author AF30637 Rajat Mishra
	 * @since 8-August-2017
	 */

	public WebElement getCell(int intRowNumber, int intColumnNumber) {
		WebElement findCell = null;
		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			// findCell = findRowPath.get(intRowNumber - 1).findElement(By.xpath("./td[" + intColumnNumber + "]"));
			findCell = findRowPath.get(intRowNumber).findElement(By.xpath("./td[" + intColumnNumber + "]"));
		}

		catch (Exception excException) {
			processExceptions("Exception occured while fetching a cell with specific row and column", excException);
			throw excException;

		}

		return findCell;
	}

	/**
	 * Rreturns the header cell in table with specific row and column
	 * </p>
	 * 
	 * @param intRowNumber
	 *            Row number starting from 0
	 * @param intColumnNumber
	 *            Column number starting from 0
	 * @return Table cell as WebElement
	 * @author AD20221 Algie Watts
	 * @since 6-February-2018
	 */

	public WebElement getHeaderCell(int intRowNumber, int intColumnNumber) {
		WebElement findCell = null;
		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			// findCell = findRowPath.get(intRowNumber - 1).findElement(By.xpath("./td[" + intColumnNumber + "]"));
			findCell = findRowPath.get(intRowNumber).findElement(By.xpath("./th[" + intColumnNumber + "]"));
		}

		catch (Exception excException) {
			processExceptions("Exception occured while fetching a cell with specific row and column", excException);
			throw excException;

		}

		return findCell;
	}

	public static String webTableGetElementTextwithTagName(WebElement TableTestObject, String strFieldName, String strRowNumber, String strColumnNumber, String strTagName) {

		Integer intRowNumber = Integer.valueOf(strRowNumber);
		Integer intColumnNumber = Integer.valueOf(strColumnNumber);

		WebTable DocumentSelect = new WebTable(TableTestObject, strFieldName);
		String strText = DocumentSelect.getChildItemsByTagName(intRowNumber, intColumnNumber, strTagName).get(0).getText();
		DocumentSelect = null;
		return strText;

	}

	public static void webTableClickWithTagName(WebElement TableTestObject, String strFieldName, String strRowNumber, String strColumnNumber, String strTagName) {

		Integer RowNumber = Integer.valueOf(strRowNumber);
		Integer ColumnNumber = Integer.valueOf(strColumnNumber);

		WebTable DocumentSelect = new WebTable(TableTestObject, strFieldName);
		DocumentSelect.getChildItemsByTagName(RowNumber, ColumnNumber, strTagName).get(0).click();
		DocumentSelect = null;
	}

	/**
	 * Returns all cells values of a row
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * String[] arrAllCellValuesOfARow = wtClaimsResult.getAllCellsValuesOfARow(3);
	 * </p>
	 * 
	 * @return String[] arrAllCellValuesOfARow- contains Table details in row format
	 * @author AF30637 Santosh Bukkashetti
	 * @since 18-September-2017
	 * 
	 */

	public String[] getAllCellsValuesOfARow(int intRow) {
		String[] arrAllCellValuesOfARow = null;
		@SuppressWarnings("unused")
		int index = 0;
		try {
			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));

			// List<WebElement> findColPath = findRowPath.get(intRow - 1).findElements(By.tagName("td"));
			List<WebElement> findColPath = findRowPath.get(intRow).findElements(By.tagName("td"));

			arrAllCellValuesOfARow = new String[findColPath.size()];

			for (int columnCount = 0; columnCount < findColPath.size(); columnCount++) {
				arrAllCellValuesOfARow[columnCount] = findColPath.get(columnCount).getText();
			}

		} catch (Exception excException) {
			processExceptions("Exception occured while getting all cell values of a row", excException);
			throw excException;
		}

		// System.out.println(arrAllCellValuesOfARow);
		return arrAllCellValuesOfARow;

	}

	/**
	 * Returns all cells values of a Column
	 * </p>
	 * Usage: WebTable wtClaimsResult = new WebTable(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * String[] arrAllCellValuesOfARow = wtClaimsResult.getAllCellsValuesOfARow(3);
	 * </p>
	 * 
	 * @return String[] arrAllCellValuesOfColumn- contains Table details in Column format
	 * @author AF30637 Santosh Bukkashetti
	 * @since 25-September-2017
	 * 
	 */

	public String[] getAllValuesInColumn(int intColumnNumber) {
		String[] arrAllCellValuesOfColumn = null;

		try {

			List<WebElement> findRowPath = tbdyTableBody.findElements(By.tagName("tr"));
			int rowsCount = findRowPath.size();

			arrAllCellValuesOfColumn = new String[findRowPath.size() - 2];

			List<WebElement> findColumnPath;
			String strCellText = "";
			for (int row = 1; row < rowsCount - 2; row++) {
				findColumnPath = findRowPath.get(row).findElements(By.tagName("td"));
				strCellText = findColumnPath.get(intColumnNumber - 1).getText();
				arrAllCellValuesOfColumn[row - 1] = strCellText;

			}

		} catch (Exception excException) {
			processExceptions("Exception occured while getting values in specific column", excException);
			throw excException;
		}

		return arrAllCellValuesOfColumn;
	}

	/**
	 * Returns the number of pages in the table
	 * </p>
	 * 
	 * @return int intNumberOfPages - the number of pages in the table
	 * @author AD20221 Algie Watts
	 * @since 23-January-2018
	 * 
	 */
	public int getNumberOfTablePages() {

		int intNumberOfPages = 1;

		try {
			List<WebElement> lstNumberOfPages = getWebDriver().findElements(By.xpath("//div[@class='dataTables_paginate paging_full_numbers']/span/a"));

			if (lstNumberOfPages.size() > 1) {
				intNumberOfPages = lstNumberOfPages.size();
			}

		} catch (Exception excException) {
			processExceptions("Exception occured while getting number of pages", excException);
			throw excException;
		}

		return intNumberOfPages;
	}

	/**
	 * changes to the next com.fusion.pageobjects.page in the table
	 * </p>
	 * 
	 * @return int intPageNumber - the com.fusion.pageobjects.page number to change to in the table
	 * @author AD20221 Algie Watts
	 * @since 25-January-2018
	 * 
	 */
	public void selectNextPage(int intPageNumber) {
		String strPageNumber = String.valueOf(intPageNumber);

		try {
			WebElement nextPage = getWebDriver().findElement(By.xpath("//div[@class='dataTables_paginate paging_full_numbers']//a[text()='" + strPageNumber + "']"));
			nextPage.click();
		} catch (Exception excException) {
			processExceptions("Exception occured while getting number of pages", excException);
			throw excException;
		}

	}

	/**
	 * changes to the next com.fusion.pageobjects.page in the table
	 * </p>
	 * 
	 * @return int intPageNumber - the com.fusion.pageobjects.page number to change to in the table
	 * @author AD20221 Algie Watts
	 * @since 25-January-2018
	 * 
	 */
	public void resetTableToPage1() {

		try {
			WebElement firstPage = getWebDriver().findElement(By.xpath("//div[@class='dataTables_paginate paging_full_numbers']//a[text()='1']"));
			firstPage.click();
		} catch (Exception excException) {
			processExceptions("Exception occured while getting number of pages", excException);
			throw excException;
		}

	}

	/**
	 * Function to verify the sort order of a column in a table in the BlueLINK application
	 * 
	 * @author AD20221 Algie Watts
	 * 
	 * @param intSortColumn
	 *            the integer value of the column where the sort is to be checked starts with 0
	 * @param strSortOrder
	 *            the order that the column is expected to be sorted should use "asc" or "desc".
	 * @param strSortType
	 *            the type of sort being String, Numeric, or Date
	 * @param strDateFormat
	 *            the formatter string for the type of date in the column (ex. "M/d/yyyy", "yyyy-M-d", "yyyy-MM-dd HH:mm:ss") Set to "" or null if not a date
	 * 
	 * @return boolean True or false based on whether or not the column is sorted as expected
	 */
	public boolean verifyColumnSortOrder(int intSortColumn, String strSortOrder, String strSortType, String strDateFormat) throws Exception {
		boolean blnResult = false;

		String[] arrColumnValues = getColumnValues(intSortColumn);
		String strBeforeSort = "";
		String strAfterSort = "";

		strBeforeSort = Arrays.toString(arrColumnValues);

		try {
			switch (strSortType) {
			case "String":
			case "string":
			case "Str":
			case "str":

				strBeforeSort = Arrays.toString(arrColumnValues);

				switch (strSortOrder) {
				case "Ascending":
				case "ascending":
				case "Asc":
				case "asc":
				case "ASC":
					Arrays.sort(arrColumnValues, String.CASE_INSENSITIVE_ORDER);
					break;
				case "Descending":
				case "descending":
				case "Desc":
				case "desc":
				case "DESC":
					Arrays.sort(arrColumnValues, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
					break;
				default:
					log(FAIL, "Verify Column Sort Order by String value", "Fail: Invalid sort order specified, please specify sort order as: asc or desc", false);
					setLastTestPassed(false);
					return false;
				}

				strAfterSort = Arrays.toString(arrColumnValues);
				break;

			case "Numeric":
			case "numeric":
			case "Num":
			case "num":

				Integer[] intColumnValues = new Integer[arrColumnValues.length];

				for (int i = 0; i < arrColumnValues.length; i++) {
					intColumnValues[i] = Integer.valueOf(arrColumnValues[i]);
				}

				strBeforeSort = Arrays.toString(intColumnValues);

				switch (strSortOrder) {
				case "Ascending":
				case "ascending":
				case "Asc":
				case "asc":
				case "ASC":
					Arrays.sort(intColumnValues);
					break;
				case "Descending":
				case "descending":
				case "Desc":
				case "desc":
				case "DESC":
					Arrays.sort(intColumnValues, Collections.reverseOrder());
					break;
				default:
					log(FAIL, "Verify Column Sort Order by Numeric value", "Fail: Invalid sort order specified, please specify sort order as: asc or desc", false);
					setLastTestPassed(false);
					return false;
				}

				strAfterSort = Arrays.toString(intColumnValues);
				break;

			case "Date":
			case "date":

				SimpleDateFormat formatter = new SimpleDateFormat(strDateFormat);

				Date[] dateColumnValues = new Date[arrColumnValues.length];

				for (int i = 0; i < arrColumnValues.length; i++) {
					dateColumnValues[i] = formatter.parse(arrColumnValues[i]);
				}

				strBeforeSort = Arrays.toString(dateColumnValues);

				switch (strSortOrder) {
				case "Ascending":
				case "ascending":
				case "Asc":
				case "asc":
				case "ASC":
					Arrays.sort(dateColumnValues);
					break;
				case "Descending":
				case "descending":
				case "Desc":
				case "desc":
				case "DESC":
					Arrays.sort(dateColumnValues, Collections.reverseOrder());
					break;
				default:
					log(FAIL, "Verify Column Sort Order by Date value", "Fail: Invalid sort order specified, please specify sort order as: asc or desc", false);
					setLastTestPassed(false);
					return false;
				}

				strAfterSort = Arrays.toString(dateColumnValues);
				break;

			default:
				log(FAIL, "Verify Column Sort Order", "Fail: Invalid sort type specified, please specify sort type as: String, Numeric, or Date", false);
				setLastTestPassed(false);
				return false;

			}

			if (strAfterSort.equalsIgnoreCase(strBeforeSort)) {
				log(PASS, "Verify Column Sort Order", "Pass: table correctly sorted in " + strSortOrder + " order based on column: " + intSortColumn + " and sorted by: " + strSortType
						+ ".  Expected: " + strAfterSort + " , Actual: " + strBeforeSort, true);
				blnResult = true;
			} else {
				log(FAIL, "Verify Column Sort Order", "Fail: table NOT sorted in " + strSortOrder + " order based on column: " + intSortColumn + " and sorted by: " + strSortType + ".  Expected: "
						+ strAfterSort + " , Actual: " + strBeforeSort, true);
				setLastTestPassed(false);
				blnResult = false;
			}
		} catch (Exception excException) {
			processExceptions("Exception while executing verifyColumnSortOrder function", excException);
			throw excException;
		}

		return blnResult;

	}

	/**
	 * Function to get an array of all of the values in a column in a table in the BlueLINK application
	 * 
	 * @author AD20221 Algie Watts
	 * 
	 * @param intSortColumn
	 *            the integer value of the column where the sort is to be checked Starts at 0
	 * 
	 * @return String[] a String array of the values from the column in the order that are in the table.
	 */
	public String[] getColumnValues(int intSortColumn) throws Exception {
		int intRowCounter;
		String strCellValue = "";
		String strAlteredCellValue = "";
		int intPageNum = 1;
		List<String> arrSortList = new ArrayList<String>();
		int intNumberOfRows;
		String[] arrColumnValues = null;

		try {

			do {

				intNumberOfRows = getRowsCount();
				intRowCounter = 0;

				while (intRowCounter < intNumberOfRows) {

					// Get the cell value from the table
					strCellValue = getCell(intRowCounter, intSortColumn).getText();

					strAlteredCellValue = strCellValue.replace(",", "^");

					arrSortList.add(strAlteredCellValue);

					intRowCounter++;
				}

				intPageNum++;

				if (intPageNum <= intNumberOfPagesInTable) {

					selectNextPage(intPageNum);
				}

			} while (intPageNum <= intNumberOfPagesInTable);

			arrColumnValues = arrSortList.toArray(new String[arrSortList.size()]);

		} catch (Exception excException) {
			processExceptions("Exception while executing getColumnValues function", excException);
			throw excException;
		}
		return arrColumnValues;
	}

	/**
	 * Function to check the text value of the "aria-sort" attribute of the column header to check for sort order in a web table
	 * </p>
	 * Usage: WebTable.checkHeaderSortOrder(SettingsPage.get().settingsTableStateHeaderVerify, "State", "ascending");</br>
	 * Creating an object of type WebTable is not required for this function.
	 * </p>
	 * 
	 * @param element
	 *            the header to check the attribute of
	 * @param strHeaderName
	 *            String value of the header name. DO NOT USE CHARACTERS NOT ALLOWED IN FILE NAMES!
	 * @param strSortOrder
	 *            "ascending" or "descending" based on value to compare
	 * @return boolean containing the result of the compare
	 * 
	 * @author AD20221 Algie Watts
	 * @since 12-February-2018
	 */
	public static boolean checkHeaderSortOrder(WebElement element, String strHeaderText, String strSortOrder) {
		boolean blnReturnValue = false;

		try {
			String strAriaSort = element.getAttribute("aria-sort");

			if (strAriaSort.equalsIgnoreCase(strSortOrder)) {
				log(PASS, "Check Sort Order of Column " + strHeaderText, "PASS: " + strHeaderText + " column sorted in " + strSortOrder + " as expected.", true);
				blnReturnValue = true;
			} else {
				log(FAIL, "Check Sort Order of Column " + strHeaderText, "FAIL: " + strHeaderText + " column EXPECTED sort order [" + strSortOrder + "], ACTUAL sort order [" + strAriaSort + "]",
						true);
				blnReturnValue = false;
				setLastTestPassed(false);
			}
		} catch (NumberFormatException e) {
			log(FAIL, "Check Sort Order of Column " + strHeaderText, "Exception while executing checkHeaderSortOrder", false);
			e.printStackTrace();
		}

		return blnReturnValue;
	}

	/**
	 * Function to search a column in a RTSR table for a certain value and then verify that the value is not found
	 * 
	 * @author AD20221 Algie Watts
	 * 
	 * @param strSearchColumnName
	 *            search column name
	 * @param strSearchValue
	 *            search value
	 * @param blnScreenShot
	 *            whether or not to take a screen shot
	 * 
	 * @return boolean true or false based on whether the value was not found
	 * @throws Exception
	 */
	public boolean verifyDataNotInTable(int intColumnIndex, String strSearchValue, boolean blnScreenShot) {
		boolean blnReturnValue = true;
		int intNumberOfRows;
		String strSearchCellValue;
		boolean blnFlag = false;
		int intPageNumber = 1;

		try {

			do {
				intNumberOfRows = getRowsCount();

				for (int i = 0; i < intNumberOfRows; i++) {
					strSearchCellValue = getCell(i, intColumnIndex + 1).getText();

					if (strSearchCellValue.equalsIgnoreCase(strSearchValue)) {
						blnReturnValue = false;
						setLastTestPassed(false);
						blnFlag = true;
						log(FAIL, "Verify Data Not In Table", "FAIL: Found [" + strSearchValue + "] in row " + i + ".", blnScreenShot);
						break;
					} // IF (SearchValue found)

				} // FOR (Cycle through rows in current table com.fusion.pageobjects.page)

				if (intNumberOfPagesInTable > 1 && blnFlag == false) {
					intPageNumber++;
					selectNextPage(intPageNumber);
				} else {
					break;
				}
			} while (true);

			if (blnFlag == false) {
				log(PASS, "Verify Data Not In Table", "PASS: Could not find search value [" + strSearchValue + "] in column [ " + intColumnIndex + "].", blnScreenShot);
			}
		} catch (Exception excException) {
			processExceptions("Exception while executing verifyDataNotInTable function", excException);
			throw excException;
		}
		return blnReturnValue;
	}

}