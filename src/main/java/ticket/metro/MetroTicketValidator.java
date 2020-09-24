package ticket.metro;

import ticket.metro.exception.OppositeDirectionOnTheSameLineException;
import ticket.metro.exception.VehicleIsNotMetroException;

public class MetroTicketValidator {

    private static final String METRO_SIGNATURE = "xxx";
    private static final int START_INDEX_OF_METRO_STATION = 0;
    private static final int END_INDEX_OF_METRO_STATION = 2;
    private static final int START_INDEX_OF_DIRECTION = 2;
    private static final int END_INDEX_OF_DIRECTION = 4;

    public void validate(String passengerTicket, String inspectorState) {
        checkMetroSignature(inspectorState);
        new TimeExpiredValidator().validate(passengerTicket, inspectorState);
        checkDirection(passengerTicket, inspectorState);
    }

    private void checkMetroSignature(String inspectorState) {
        if (!inspectorState.contains(METRO_SIGNATURE)) {
            throw new VehicleIsNotMetroException();
        }
    }

    private void checkDirection(String passengerTicket, String inspectorState) {
        if (getMetroStationFromValidationSequence(passengerTicket)
                .equals(getMetroStationFromValidationSequence(inspectorState))) {
            if (isEven(getDirectionFromValidationSequence(passengerTicket))
                    != isEven(getDirectionFromValidationSequence(inspectorState))
            ) {
                throw new OppositeDirectionOnTheSameLineException();
            }
        }
    }

    private String getMetroStationFromValidationSequence(String validationSequence) {
        return validationSequence.substring(START_INDEX_OF_METRO_STATION, END_INDEX_OF_METRO_STATION);
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }

    private int getDirectionFromValidationSequence(String validationSequence) {
        return Integer.parseInt(validationSequence.substring(START_INDEX_OF_DIRECTION, END_INDEX_OF_DIRECTION));
    }
}
