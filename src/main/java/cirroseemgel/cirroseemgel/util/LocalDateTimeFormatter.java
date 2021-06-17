package cirroseemgel.cirroseemgel.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatter {

    public static String format(LocalDateTime data) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String formattedData = data.format(formatter);

        return formattedData;
    }
}
