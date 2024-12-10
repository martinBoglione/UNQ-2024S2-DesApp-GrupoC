package ar.edu.unq.desapp.helpers;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
class CurrentTimeTest {

    @Test
    void currentTimeIsNotEmptyString() {
        assertNotNull(CurrentTime.getNewDateString());
        assertInstanceOf(String.class, CurrentTime.getNewDateString());
        assertTrue(CurrentTime.getNewDateString().length() > 0);
    }

    @Test
    void millisecondsAreCorrectlyFormattedToTimestamp() {
        Date timestampDate = new Date(Long.parseLong("1333444555000"));
        assertEquals("03/04/2012 06:15:55", CurrentTime.getNewDateFormatter().format(timestampDate));
    }

    @Test
    void formatterPatternIsDDMMYYYYHHMMSS() {
        assertEquals(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), CurrentTime.getNewDateFormatter());
    }
}