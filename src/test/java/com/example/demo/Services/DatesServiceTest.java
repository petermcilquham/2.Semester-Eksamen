package com.example.demo.Services;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DatesServiceTest {
    DatesService datesService = new DatesService();

    @Test
    void compareDates() {
        //tester om date1 er før date2
        String date1 = "2020-12-10";
        String date2 = "2020-12-31";

        assertTrue(datesService.compareDates(date1,date2));
    }

    @Test
    void computeHoursPerDay() {
        String date1 = "2020-12-10";
        String date2 = "2020-12-31";
        LocalDate compareDate1 = LocalDate.parse(date1);
        LocalDate compareDate2 = LocalDate.parse(date2);
    }
}