package com.example.demo.Services;

import java.time.LocalDate;

public class CompareDates {
    public boolean compareDates(String Date1, String Date2){

        LocalDate compareDate1 = LocalDate.parse(Date1);
        LocalDate compareDate2 = LocalDate.parse(Date2);

        if (compareDate1.isAfter(compareDate2)) {
            return false;
        } else {
            return true;
        }
    }
}
