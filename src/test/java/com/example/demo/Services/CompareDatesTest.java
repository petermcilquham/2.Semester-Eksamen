package com.example.demo.Services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatesServiceTest {

    @Test
    void compareDates() {
        //tester om date1 er før date2
        DatesService compareDates = new DatesService();
        String date1 = "2020-12-10";
        String date2 = "2020-12-31";

        assertTrue(compareDates.compareDates(date1,date2));
    }
}