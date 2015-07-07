package es.macero.cqgame.util;

public final class Utils
{

    private Utils()
    {
    }

    public static String durationTranslator(String sonarDuration)
    {
        String daysPart = "P" + (sonarDuration.contains("d") ? sonarDuration.substring(0, sonarDuration.indexOf('d')) + "D" : "");
        String timePart = (sonarDuration.contains("d") ? sonarDuration.substring(sonarDuration.indexOf('d') + 1) : sonarDuration);
        return daysPart + (timePart.isEmpty() ? "" : "T" + timePart.replaceAll("min", "M").replaceAll("h", "H"));
    }
}
