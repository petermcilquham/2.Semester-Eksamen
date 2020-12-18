package com.example.demo.Services;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DatesServiceTest {
    DatesService datesService = new DatesService();

    //tester om date1 er før date2
    @Test
    void compareDates() {
        //vælger to forskellige datoer, date1 før date2
        String date1 = "2020-12-10";
        String date2 = "2020-12-31";

        assertTrue(datesService.compareDates(date1,date2));
    }
}