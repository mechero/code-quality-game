package es.macero.cqgame.domain.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class IssueDateFormatter {
    private static DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

    private IssueDateFormatter() {
    }

    public static LocalDate format(final String s) {
        return LocalDate.parse(s, f);
    }

    public static String toIssueDate(final LocalDate date) {
        final OffsetDateTime dateTime = date.atTime(0, 0, 0).atOffset(ZoneOffset.UTC);
        return f.format(dateTime);
    }

}
