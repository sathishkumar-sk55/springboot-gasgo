package com.frost.gasgo.ordermanager.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversion {

    public static Date convertStringToDate(String dateString){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = sdf.parse(dateString);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
