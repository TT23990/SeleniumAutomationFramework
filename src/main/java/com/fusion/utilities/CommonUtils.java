package com.fusion.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    public static String convertDateFormat(String value) throws ParseException {

        Date date = new SimpleDateFormat("yyyyMMdd").parse(value);
        return new SimpleDateFormat("M/d/yyyy").format(date);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String date = simpleDateFormat.format(new Date());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

//        return simpleDateFormat.parse(value).toString();
    }
}
