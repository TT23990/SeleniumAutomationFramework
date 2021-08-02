package com.fusion.utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/*
'Revision History
'#############################################################################
'@rev.On		@rev.No		@rev.By				  @rev.Comments
'8-August-2017	1			AF30637 Rajat Mishra	Reviewed. Uni tested for PACT								
'#############################################################################
*/

/**
 * Class to handle table header provided the table has one. Exception will be thrown if thead tag does not exist in the table.
 * </p>
 * Works only when there is a tag called thead in the table. If tag name is different, changes need to be made to the class.
 * </p>
 * Note: For the user, column index start from 1.
 * </p>
 * Usage: WebTableWithHeader wtClaimsResult = new WebTableWithHeader(HomePage.get().claimsResultTable, "Claims result");
 * </p>
 * </p>
 * 
 * @author  AF37512 Santosh Bukkashetti
 * @since 1-August-2017
 */
public class WebTableWithHeader extends WebTable {
	private WebElement theadTableHeader;

	/**
	 * Parameterized constructor for WebTableWithHeader. Exception will be thrown if thead tag does not exist in the table.
	 * </p>
	 * 
	 * @param argTable
	 *            WebElement of Table (extended from parent WebTable class)
	 * @param strName
	 *            Table name (extended from parent WebTable class)
	 * @author AF37512 Santosh Bukkashetti
	 * @since 8-August-2017
	 */
	public WebTableWithHeader(WebElement argTable, String strName) {
		super(argTable, strName);
		try {
			theadTableHeader = tblTable.findElement(By.tagName("thead"));

		} catch (Exception excException) {
			processExceptions("Exception while executing constructor of WebTableWithHeader", excException);
			throw excException;
		}
	}
	
	/**
	 * Parameterized constructor for WebTableWithHeader. Exception will be thrown if thead tag does not exist in the table.
	 * </p>
	 * 
	 * @param argTable
	 *            WebElement of Table (extended from parent WebTable class)
	 * @param strName
	 *            Table name (extended from parent WebTable class)
	 * @param intNumberOfPages 
	 * 			  Number of Pages in table (extended from parent WebTable class)           
	 * @author AD20221	Algie Watts
	 * @since 24-January-2018
	 */
	public WebTableWithHeader(WebElement argTable, String strName, int intNumberOfPages) {
		super(argTable, strName, intNumberOfPages);
		try {
			theadTableHeader = tblTable.findElement(By.tagName("thead"));

		} catch (Exception excException) {
			processExceptions("Exception while executing constructor of WebTableWithHeader", excException);
			throw excException;
		}
	}

	/**
	 * Fetches the column count of the table. Mostly, number of columns in header and number of columns in the table are same. Returns -1 if tag name for cells in header is not th
	 * </p>
	 * Usage: WebTableWithHeader wtClaimsResult = new WebTableWithHeader(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * int intHeaderColumnCount = wtClaimsResult.getHeaderColumnCount();
	 * </p>
	 * 
	 * @return intColumnCount total column count of header
	 * @author AF34794 Usharani Arunachalam
	 * @since 03-August-2017
	 */
	public int getHeaderColumnCount() {
		int intColumnCount = -1;
		try {
			List<WebElement> lstFindColPath = theadTableHeader.findElements(By.tagName("th"));
			intColumnCount = lstFindColPath.size();
		} catch (Exception excException) {
			processExceptions("Exception occured while fetching column count of header ", excException);
			throw excException;
		}

		return intColumnCount;
	}

	/**
	 * Returns a cell from table header
	 * </p>
	 * Usage: WebTableWithHeader wtClaimsResult = new WebTableWithHeader(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * WebElement celTableHeaderCell = wtClaimsResult.getHeaderCell(1);
	 * </p>
	 * 
	 * @param intColumnNumber
	 *            Column number of the table
	 * @return tableCell WebElement the header cell as WebElement
	 * @author AF33811 Santosh Bukkashetti
	 * @since 7-August-2017
	 */
	public WebElement getHeaderCell(int intColumnNumber) {
		WebElement tableCell = null;
		try {
			WebElement headerRow = theadTableHeader.findElement(By.tagName("tr"));
			List<WebElement> findCellPath = headerRow.findElements(By.tagName("th"));
			tableCell = findCellPath.get(intColumnNumber - 1);
		} catch (Exception excException) {
			processExceptions("Exception occurred while fetching a header cell", excException);
			throw excException;
		}
		return tableCell;

	}

	/**
	 * Fetches column name when column index is passed as the argument. Returns null if column does not exist.
	 * </p>
	 * Usage: WebTableWithHeader wtClaimsResult = new WebTableWithHeader(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * String strHeaderColumnName = wtClaimsResult.getColumnName(1);
	 * </p>
	 * 
	 * @param intColumnNumber
	 *            Index of column. Starting from 1.
	 * @return strColumnName Column name
	 * @author AF34794 Usharani Arunachalam
	 * @since 01-August-2017
	 */
	public String getHeaderColumnName(int intColumnNumber) {
		String strColumnName = "";
		try {

			List<WebElement> lstFindColPath = theadTableHeader.findElements(By.tagName("th"));
			int intColCount = lstFindColPath.size();
			for (int intColumn = 0; intColumn < intColCount; intColumn++) {
				if (intColumn == intColumnNumber - 1) {
					strColumnName = lstFindColPath.get(intColumn).getText();
					break;
				}
			}
		} catch (Exception excException) {
			processExceptions("Exception occured while fetching column name", excException);
			throw excException;
		}

		return strColumnName;

	}

	/**
	 * Fetches column index when column Name is passed as the argument. Returns -1 if column does not exist.
	 * </p>
	 * Usage: WebTableWithHeader wtClaimsResult = new WebTableWithHeader(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * int intColumnIndexOfIDNumber = wtClaimsResult.getColumnIndex("ID Number");
	 * </p>
	 * 
	 * @param strColumnName
	 *            a column name
	 * @return intColumnNumber column index of strColumnName
	 * @author AF34794 Usharani Arunachalam
	 * @since 01-August-2017
	 */
	public int getHeaderColumnIndex(String strColumnName) {
		int intColumnNumber = -1;
		try {
			List<WebElement> findColumnPath = theadTableHeader.findElements(By.tagName("th"));
			for (int intColumn = 0; intColumn < findColumnPath.size(); intColumn++) {
				{
					String strCellText = findColumnPath.get(intColumn).getText().trim();
					if (strCellText.equalsIgnoreCase(strColumnName)) {
						intColumnNumber = intColumn + 1;
						break;
					}
				}
			}
		} catch (Exception excException) {
			processExceptions("Exception occured while fetching Column Number", excException);
			throw excException;
		}
		return intColumnNumber;
	}

	/**
	 * Fetches all webelements available in a cell of table header. Fetches webelements using tag name
	 * </p>
	 * Usage: WebTableWithHeader wtClaimsResult = new WebTableWithHeader(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * List<WebElement> lstDownArrowsInIDNumberColumn = wtClaimsResult.getHeaderChildItemsByTagName(3, "span");
	 * </p>
	 * 
	 * @param intColumnNumber
	 *            Column number from which child items are to be fetched
	 * 
	 * @param strTagName
	 *            html tag name like span, img etc.
	 * 
	 * @return List<WebElement> - childItems available in a specific intColumnNumber
	 * @author AF37512 Santosh Bukkashetti
	 * @since 04-August-2017
	 */

	public List<WebElement> getHeaderChildItemsByTagName(int intColumnNumber, String strTagName) {
		List<WebElement> childItems = null;
		try {
			WebElement findRowPath = theadTableHeader.findElement(By.tagName("tr"));
			List<WebElement> findCellPath = findRowPath.findElements(By.tagName("th"));
			WebElement tableCell = findCellPath.get(intColumnNumber - 1);
			childItems = tableCell.findElements(By.tagName(strTagName));
		} catch (Exception excException) {
			processExceptions("Exception occured while capturing webelement in a cell using TagName", excException);
			throw excException;
		}
		return childItems;

	}

	/**
	 * Fetches all webelements available in a cell of table header. Fetches webelements using xpath
	 * </p>
	 * Usage: WebTableWithHeader wtClaimsResult = new WebTableWithHeader(HomePage.get().claimsResultTable, "Claims result"); </br>
	 * List<WebElement> lstDownArrowsInIDNumberColumn = wtClaimsResult.getHeaderChildItemsByXPath(3, ".//span[@class='downArrow']");
	 * </p>
	 * 
	 * @param intColumnNumber
	 *            Column number from which child items are to be fetched
	 * 
	 * @param strItemsXPATH
	 *            xpath for child items in a cell.
	 * 
	 * @return List<WebElement> - childItems available in a specific intColumnNumber
	 * @author AF37512 Santosh Bukkashetti
	 * @since 04-August-2017
	 */
	public List<WebElement> getHeaderChildItemsByXPath(int intColumnNumber, String strItemsXPATH) {
		List<WebElement> childItems = null;
		try {
			WebElement headerRow = theadTableHeader.findElement(By.tagName("tr"));
			List<WebElement> findCellPath = headerRow.findElements(By.tagName("th"));
			WebElement tableCell = findCellPath.get(intColumnNumber - 1);
			childItems = tableCell.findElements(By.xpath(strItemsXPATH));

		} catch (Exception excException) {
			processExceptions("Exception occurred while fetching child items of a cell by xpath", excException);
			throw excException;
		}
		return childItems;
	}

	public int getHeaderColumnIndexWithTd(String strColumnName) {
		int intColumnNumber = -1;
		try {
			List<WebElement> findColumnPath = theadTableHeader.findElements(By.tagName("td"));
			for (int intColumn = 0; intColumn < findColumnPath.size(); intColumn++) {
				{
					String strCellText = findColumnPath.get(intColumn).getText().trim();
					if (strCellText.equalsIgnoreCase(strColumnName)) {
						intColumnNumber = intColumn + 1;
						break;
					}
				}
			}
		} catch (Exception excException) {
			processExceptions("Exception occured while fetching Column Number", excException);
			throw excException;
		}
		return intColumnNumber;
	}
	
}