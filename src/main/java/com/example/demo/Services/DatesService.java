package com.example.demo.Services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DatesService {

    //metode til at sammenligne to datoer og returnere en boolean alt efter om parameteret date2 er efter eller før parameteret date1
    public boolean compareDates(String date1, String date2){
        //vi får datoer ind som strings fra html siderne via webrequest så vi parser dem om til localDates
        LocalDate compareDate1 = LocalDate.parse(date1);
        LocalDate compareDate2 = LocalDate.parse(date2);

        //bruger .isAfter metoden til at sammenligne datoerne og returnerer boolean
        return !compareDate1.isAfter(compareDate2);
    }

    //metode til at udregne hvor mange dage der er imellem to datoer
    public long computeDaysBetweenDates(String date1, String date2){
        //vi får datoer ind som strings fra html siderne via webrequest så vi parser dem om til localDates
        LocalDate compareDate1 = LocalDate.parse(date1);
        LocalDate compareDate2 = LocalDate.parse(date2);

        //bruger ChronoUnit.days.between metode til at finde antal dage imellem datoerne og returnere resultatet som long
        return ChronoUnit.DAYS.between(compareDate1, compareDate2);
    }
}
