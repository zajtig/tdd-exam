package ticket.metro;

import ticket.metro.exception.VehicleIsNotMetroException;

public class MetroTicketValidator {
    public void validate(String inspectorState) {
        if (!inspectorState.contains("xxx")) {
            throw new VehicleIsNotMetroException();
        }
    }
}
