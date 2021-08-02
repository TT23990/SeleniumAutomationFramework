package com.fusion.test.regression;

import com.fusion.utilities.CoreSuperHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends CoreSuperHelper {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("****************** before Method ******************");
//        setIgnoreLastTestFailure(true);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("****************** after Method ******************\n" +
                "Closing application after test method");

    }

}
