package ticket.metro;

import ticket.metro.exception.TimeExpiredException;

import static ticket.metro.ValidationSequenceReader.*;

public class TimeExpiredValidator {

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

    private void checkMonth(String passengerTicket, String inspectorState) {
        if (getMonthFromValidationSequence(passengerTicket)
                != getMonthFromValidationSequence(inspectorState)) {
            throw new TimeExpiredException();
        }
    }

    private void checkDay(String passengerTicket, String inspectorState) {
        if (getDayFromValidationSequence(passengerTicket)
                != getDayFromValidationSequence(inspectorState)) {
            throw new TimeExpiredException();
        }
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
}
