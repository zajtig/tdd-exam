package ticket.metro;

import ticket.metro.exception.OppositeDirectionOnTheSameLineException;
import ticket.metro.exception.VehicleIsNotMetroException;

import java.util.HashSet;
import java.util.Set;

import static ticket.metro.ValidationSequenceReader.getDirectionFromValidationSequence;
import static ticket.metro.ValidationSequenceReader.getMetroStationFromValidationSequence;

public class MetroTicketValidator {

    private static final String METRO_SIGNATURE = "xxx";

    private static final Set<String> M3_STATIONS = new HashSet<>();

    {
        M3_STATIONS.add("06");
    }

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
        if (isOnTheSameLine(passengerTicket, inspectorState)) {
            if (isTheSameDirection(passengerTicket, inspectorState)) {
                throw new OppositeDirectionOnTheSameLineException();
            }
        }
    }

    private boolean isTheSameDirection(String passengerTicket, String inspectorState) {
        return isEven(getDirectionFromValidationSequence(passengerTicket))
                != isEven(getDirectionFromValidationSequence(inspectorState));
    }

    private boolean isOnTheSameLine(String passengerTicket, String inspectorState) {
        return isM3Station(getMetroStationFromValidationSequence(passengerTicket))
                == isM3Station(getMetroStationFromValidationSequence(inspectorState));
    }

    private boolean isM3Station(String station) {
        return M3_STATIONS.contains(station);
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }
}
