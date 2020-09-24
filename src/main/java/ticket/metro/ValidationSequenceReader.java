package ticket.metro;

public class ValidationSequenceReader {

    private static final int START_INDEX_OF_DIRECTION = 2;
    private static final int END_INDEX_OF_DIRECTION = 4;

    private static final int START_INDEX_OF_METRO_STATION = 0;
    private static final int END_INDEX_OF_METRO_STATION = 2;

    private static final int START_INDEX_OF_YEAR = 7;
    private static final int END_INDEX_OF_YEAR = 9;
    private static final int START_INDEX_OF_MONTH = 9;
    private static final int END_INDEX_OF_MONTH = 11;
    private static final int START_INDEX_OF_DAY = 11;
    private static final int END_INDEX_OF_DAY = 13;
    private static final int START_INDEX_OF_HOUR = 13;
    private static final int END_INDEX_OF_HOUR = 15;
    private static final int START_INDEX_OF_MINUTE = 15;
    private static final int END_INDEX_OF_MINUTE = 17;

    public static int getDirectionFromValidationSequence(String validationSequence) {
        return Integer.parseInt(validationSequence.substring(START_INDEX_OF_DIRECTION, END_INDEX_OF_DIRECTION));
    }

    public static String getMetroStationFromValidationSequence(String validationSequence) {
        return validationSequence.substring(START_INDEX_OF_METRO_STATION, END_INDEX_OF_METRO_STATION);
    }

    public static int getHourFromValidationSequence(String validationSequence) {
        return Integer.parseInt(validationSequence.substring(START_INDEX_OF_HOUR, END_INDEX_OF_HOUR));
    }

    public static int getMinuteFromValidationSequence(String validationSequence) {
        return Integer.parseInt(validationSequence.substring(START_INDEX_OF_MINUTE, END_INDEX_OF_MINUTE));
    }

    public static int getDayFromValidationSequence(String validationSequence) {
        return Integer.parseInt(validationSequence.substring(START_INDEX_OF_DAY, END_INDEX_OF_DAY));
    }

    public static int getYearFromValidationSequence(String validationSequence) {
        String yearString = validationSequence.substring(START_INDEX_OF_YEAR, END_INDEX_OF_YEAR);
        return Integer.parseInt(String.valueOf(yearString.charAt(1) + yearString.charAt(0)));
    }

    public static int getMonthFromValidationSequence(String validationSequence) {
        return Integer.parseInt(validationSequence.substring(START_INDEX_OF_MONTH, END_INDEX_OF_MONTH));
    }
}
