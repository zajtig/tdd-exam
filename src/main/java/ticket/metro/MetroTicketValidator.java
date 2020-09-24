package ticket.metro;

import ticket.metro.exception.OppositeDirectionOnTheSameLineException;
import ticket.metro.exception.VehicleIsNotMetroException;

import static ticket.metro.ValidationSequenceReader.getDirectionFromValidationSequence;
import static ticket.metro.ValidationSequenceReader.getMetroStationFromValidationSequence;

public class MetroTicketValidator {

    private static final String METRO_SIGNATURE = "xxx";

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

    private boolean isEven(int number) {
        return number % 2 == 0;
    }
}
