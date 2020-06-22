package com.sturgeon.photobook.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String dateFileNameFormat() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyhhmmss"));
    }

}
