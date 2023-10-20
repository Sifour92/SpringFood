package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.util.StringUtils;

import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
    private DateTimeUtil() {
    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

    public static @Nullable LocalDate parseLocalDate(@Nullable String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }

    public static @Nullable LocalTime parseLocalTime(@Nullable String str) {
        return StringUtils.isEmpty(str) ? null : LocalTime.parse(str);
    }


    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDateTime getStartInclusive(LocalDate localDate) {
        return (localDate != null ? localDate : MIN_DATE).atStartOfDay();
    }

    public static LocalDateTime getEndExclusive(LocalDate localDate) {
        return (localDate != null ? localDate.plus(1, ChronoUnit.DAYS) : MAX_DATE).atStartOfDay();
    }

}
