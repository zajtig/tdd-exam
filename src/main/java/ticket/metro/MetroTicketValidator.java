package ticket.metro;

import ticket.metro.exception.TimeExpiredException;
import ticket.metro.exception.VehicleIsNotMetroException;

public class MetroTicketValidator {

    private static final String METRO_SIGNATURE = "xxx";
    private static final int START_INDEX_OF_YEAR = 7;
    private static final int END_INDEX_OF_YEAR = 9;
    private static final int START_INDEX_OF_MONTH = 9;
    private static final int END_INDEX_OF_MONTH = 11;

    public void validate(String passengerTicket, String inspectorState) {
        checkMetroSignature(inspectorState);
        checkTime(passengerTicket, inspectorState);
    }

    private void checkMetroSignature(String inspectorState) {
        if (!inspectorState.contains(METRO_SIGNATURE)) {
            throw new VehicleIsNotMetroException();
        }
    }

    private void checkTime(String passengerTicket, String inspectorState) {
        checkYear(passengerTicket, inspectorState);
        checkMonth(passengerTicket, inspectorState);
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

}
