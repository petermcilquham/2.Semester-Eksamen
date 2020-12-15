package com.example.demo.Services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DatesService {
    public boolean compareDates(String date1, String date2){

        LocalDate compareDate1 = LocalDate.parse(date1);
        LocalDate compareDate2 = LocalDate.parse(date2);

        if (compareDate1.isAfter(compareDate2)) {
            return false;
        } else {
            return true;
        }
    }

    public long computeHoursPerDay(String date1, String date2){
        LocalDate compareDate1 = LocalDate.parse(date1);
        LocalDate compareDate2 = LocalDate.parse(date2);

        return ChronoUnit.DAYS.between(compareDate1, compareDate2);
    }
}
