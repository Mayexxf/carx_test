package com.example.carx_test.services.parsers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Parsing {

    String parse;


    public ZonedDateTime parsToZoneDateTime(String parse){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(parse, dateFormatter);
        return localDate.atStartOfDay(ZoneId.systemDefault());
    }
}
