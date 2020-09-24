package ticket.metro;

import org.junit.Assert;
import org.junit.Test;

public class MetroTicketValidatorUnitTest {

    @Test
    public void createTest() {
        Assert.assertNotNull(new MetroTicketValidator());
    }

}
