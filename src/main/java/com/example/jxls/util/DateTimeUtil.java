package com.example.jxls.util;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


/**
 *=UTILITY CLASS FOR DATE TIME
*/
public class DateTimeUtil {

    //--RETURNS NOW DATE: for example: "2021-12-16"
    //private LocalDate getCurrentDate() {
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    //--RETURNS LocalDate from String: for example: "2021-12-16"
    public LocalDate getDateFromString(String stringDate) {
        return LocalDate.parse(stringDate);
    }

    //--RETURNS LIST OF 2 DATES - NOW & NOW+1d: for example: ["2021-12-16", "2021-12-17"]
    public List<LocalDate> getCurrentDatePlus() {

        List<LocalDate> dates = new ArrayList<>();

        LocalDate nowDate = LocalDate.now();
        LocalDate nowDatePlus1Day = nowDate.plusDays(1);

        dates.add(nowDate);
        dates.add(nowDatePlus1Day);

        return dates;
    }

}
