package es.macero.cqgame.domain.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class IssueDateFormatter {
    private static DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

    private IssueDateFormatter() {
    }

    public static LocalDate format(String s) {
        return LocalDate.parse(s, f);
    }

}
