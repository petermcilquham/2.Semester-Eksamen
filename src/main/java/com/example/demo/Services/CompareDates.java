package com.example.demo.Services;

import java.time.LocalDate;

public class CompareDates {
    public boolean compareDates(String date1, String date2){

        LocalDate compareDate1 = LocalDate.parse(date1);
        LocalDate compareDate2 = LocalDate.parse(date2);

        if (compareDate1.isAfter(compareDate2)) {
            return false;
        } else {
            return true;
        }
    }
}
