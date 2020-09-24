package ticket.metro;

import ticket.metro.exception.VehicleIsNotMetroException;

public class MetroTicketValidator {

    private static final String METRO_SIGNATURE = "xxx";

    public void validate(String passengerTicket, String inspectorState) {
        checkMetroSignature(inspectorState);
        new TimeExpiredValidator().validate(passengerTicket, inspectorState);
    }

    private void checkMetroSignature(String inspectorState) {
        if (!inspectorState.contains(METRO_SIGNATURE)) {
            throw new VehicleIsNotMetroException();
        }
    }
}
