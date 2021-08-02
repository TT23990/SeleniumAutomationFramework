package com.fusion.test.regression;


import org.testng.annotations.Factory;

import java.util.LinkedHashMap;


public class WebTestFactory {

    @Factory(dataProvider = "profiles",
            dataProviderClass =com.fusion.utilities.TestDataProvider.class )
    public Object[] createInstances(LinkedHashMap<String, String> user) {

        return new Object[]{new FusionTestSuite(user)};
        }

}

 