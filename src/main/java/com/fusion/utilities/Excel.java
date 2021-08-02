package com.fusion.utilities;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Excel {

    public static List<LinkedHashMap<String, String>> excelReadHashMap(String filePath, String sheetName){
        ArrayList<LinkedHashMap<String, String>> actualFeatureData_ArrayList = new ArrayList<>();
        return actualFeatureData_ArrayList;

    }

    public static void main(String[] args) throws FilloException {

        Connection connection=new Fillo().getConnection("data/test_data.xlsx");
        Recordset recordset=connection.executeQuery("Select * from sheet1");

        System.out.println("Row count: "+recordset.getCount());

        int rows=recordset.getCount();
        int columns=recordset.getFieldNames().size();
        Object[][] resultSet = new Object[rows][columns];
        int row = 0;

        while(recordset.next()){
            System.out.println("Details: "+recordset.getField("Details"));

            for (int i = 0; i < columns-1; i++) {

                resultSet[row][i] = recordset.getField(i+1);
            }
            row++;

        }

        recordset.close();
        connection.close();


    }
}
