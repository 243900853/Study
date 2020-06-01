package com.xiaobi.config;


import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//TestController.test1(Date date) SpringBoot会自动将String类型的时间转换成Date
public class StringConverterDate implements Converter<String, Date> {
    String dateFormat;

    public StringConverterDate(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public Date convert(String s) {
        System.out.println("字符串自动转换成时间");
        LocalDateTime parse = LocalDateTime.parse(s, DateTimeFormatter.ofPattern(dateFormat));
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = parse.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

}
