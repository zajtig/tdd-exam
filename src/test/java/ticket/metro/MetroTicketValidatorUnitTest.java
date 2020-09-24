package ticket.metro;

import org.junit.Test;
import ticket.metro.exception.VehicleIsNotMetroException;

public class MetroTicketValidatorUnitTest {

    @Test(expected = VehicleIsNotMetroException.class)
    public void inspectorOnOtherTypeOfVehicleTest() {
        new MetroTicketValidator().validate("5422519041246");
    }



}
