package ticket.metro;

import ticket.metro.exception.TimeExpiredException;

public class TimeExpiredValidator {

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
    public static final int TIME_THRESHOLD_IN_MINUTES = 80;


    public void validate(String passengerTicket, String inspectorState) {
        checkYear(passengerTicket, inspectorState);
        checkMonth(passengerTicket, inspectorState);
        checkDay(passengerTicket, inspectorState);
        checkMinute(passengerTicket, inspectorState);
    }

    private void checkYear(String passengerTicket, String inspectorState) {
        if (getYearFromValidationSequence(passengerTicket)
                != getYearFromValidationSequence(inspectorState)) {
            throw new TimeExpiredException();
        }
    }

    private int getYearFromValidationSequence(String validationSequence) {
        String yearString = validationSequence.substring(START_INDEX_OF_YEAR, END_INDEX_OF_YEAR);
        return Integer.parseInt(String.valueOf(yearString.charAt(1) + yearString.charAt(0)));
    }

    private void checkMonth(String passengerTicket, String inspectorState) {
        if (getMonthFromValidationSequence(passengerTicket)
                != getMonthFromValidationSequence(inspectorState)) {
            throw new TimeExpiredException();
        }
    }

    private int getMonthFromValidationSequence(String validationSequence) {
        return Integer.parseInt(validationSequence.substring(START_INDEX_OF_MONTH, END_INDEX_OF_MONTH));
    }

    private void checkDay(String passengerTicket, String inspectorState) {
        if (getDayFromValidationSequence(passengerTicket)
                != getDayFromValidationSequence(inspectorState)) {
            throw new TimeExpiredException();
        }
    }

    private int getDayFromValidationSequence(String validationSequence) {
        return Integer.parseInt(validationSequence.substring(START_INDEX_OF_DAY, END_INDEX_OF_DAY));
    }

    private void checkMinute(String passengerTicket, String inspectorState) {
        int inspectorTimeInMinutes = calculateInMinutes(getHourFromValidationSequence(inspectorState),
                getMinuteFromValidationSequence(inspectorState));
        int passengerTimeInMinutes = calculateInMinutes(getHourFromValidationSequence(passengerTicket),
                getMinuteFromValidationSequence(passengerTicket));
        if (moreMinutesPassedThanTheThreshold(inspectorTimeInMinutes, passengerTimeInMinutes)) {
            throw new TimeExpiredException();
        }
    }

    private boolean moreMinutesPassedThanTheThreshold(int inspectorTimeInMinutes, int passengerTimeInMinutes) {
        return Math.abs(inspectorTimeInMinutes - passengerTimeInMinutes) > TIME_THRESHOLD_IN_MINUTES;
    }

    private int calculateInMinutes(int hour, int minute) {
        return hour * 60 + minute;
    }

    private int getHourFromValidationSequence(String validationSequence) {
        return Integer.parseInt(validationSequence.substring(START_INDEX_OF_HOUR, END_INDEX_OF_HOUR));
    }

    private int getMinuteFromValidationSequence(String validationSequence) {
        return Integer.parseInt(validationSequence.substring(START_INDEX_OF_MINUTE, END_INDEX_OF_MINUTE));
    }
}
