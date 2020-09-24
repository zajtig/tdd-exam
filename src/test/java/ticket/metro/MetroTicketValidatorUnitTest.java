package ticket.metro;

import org.junit.Test;
import ticket.metro.exception.TimeExpiredException;
import ticket.metro.exception.VehicleIsNotMetroException;

public class MetroTicketValidatorUnitTest {

    @Test(expected = VehicleIsNotMetroException.class)
    public void inspectorOnOtherTypeOfVehicleTest() {
        new MetroTicketValidator().validate(null, "5422519041246");
    }

    @Test(expected = TimeExpiredException.class)
    public void timeExpiredYearTest() {
        new MetroTicketValidator().validate(
                "0643xxx811281305",
                "0845xxx911281426");
    }

    @Test(expected = TimeExpiredException.class)
    public void timeExpiredMonthTest() {
        new MetroTicketValidator().validate(
                "0643xxx911181305",
                "0845xxx911281426");
    }


}
