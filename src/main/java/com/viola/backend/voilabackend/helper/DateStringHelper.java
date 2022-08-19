package com.viola.backend.voilabackend.helper;

import java.util.Date;

public class DateStringHelper {
    public String dateDifferenceInWords(Date d1, Date d2) {
        long difference_In_Time
        = d2.getTime() - d1.getTime();

        // Calucalte time difference in
        // seconds, minutes, hours, years,
        // and days
        long difference_In_Seconds
            = (difference_In_Time
            / 1000)
            % 60;

        long difference_In_Minutes
            = (difference_In_Time
            / (1000 * 60))
            % 60;

        long difference_In_Hours
            = (difference_In_Time
            / (1000 * 60 * 60))
            % 24;

        long difference_In_Years
            = (difference_In_Time
            / (1000l * 60 * 60 * 24 * 365));

        long difference_In_Days
            = (difference_In_Time
            / (1000 * 60 * 60 * 24))
            % 365;

        // Print the date difference in
        // years, in days, in hours, in
        // minutes, and in seconds

        System.out.print(
            "Difference "
            + "between two dates is: ");

        System.out.println(
            difference_In_Years
            + " years, "
            + difference_In_Days
            + " days, "
            + difference_In_Hours
            + " hours, "
            + difference_In_Minutes
            + " minutes, "
            + difference_In_Seconds
            + " seconds");
        if (difference_In_Years > 0) {
            return "(" + difference_In_Years + " yıl önce)";
        }
        if (difference_In_Days > 0) {
            return "(" + difference_In_Days + " gün önce)";
        }
        if (difference_In_Hours > 0) {
            return "(" + difference_In_Hours + " saat önce)";
        }
        if (difference_In_Minutes > 0) {
            return "(" + difference_In_Minutes + " dakika önce)";
        }
        return "(" + difference_In_Seconds + " saniye önce)";
    }
}
