package ticket.metro;

import org.junit.Test;
import ticket.metro.exception.OppositeDirectionOnTheSameLineException;
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
                "0643xxx8112181305",
                "0845xxx9112181426");
    }

    @Test(expected = TimeExpiredException.class)
    public void timeExpiredMonthTest() {
        new MetroTicketValidator().validate(
                "0643xxx9111181305",
                "0845xxx9112181426");
    }

    @Test(expected = TimeExpiredException.class)
    public void timeExpiredDayTest() {
        new MetroTicketValidator().validate(
                "0643xxx9111171305",
                "0845xxx9111181426");
    }

    @Test(expected = TimeExpiredException.class)
    public void timeExpiredMinuteTest1() {
        new MetroTicketValidator().validate(
                "0643xxx9111181305",
                "0845xxx9111181426");
    }

    @Test
    public void timeExpiredMinuteTest2() {
        new MetroTicketValidator().validate(
                "0643xxx9111181305",
                "0845xxx9111181424");
    }

    @Test
    public void timeExpiredMinuteTest3() {
        new MetroTicketValidator().validate(
                "0643xxx9111181305",
                "0845xxx9111181310");
    }

    @Test(expected = OppositeDirectionOnTheSameLineException.class)
    public void directionOnTheSameLineTest() {
        new MetroTicketValidator().validate(
                "0643xxx9111181305",
                "0644xxx9111181340");
    }

}
