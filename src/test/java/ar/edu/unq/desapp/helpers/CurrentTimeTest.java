package ar.edu.unq.desapp.helpers;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
class CurrentTimeTest {

    @Test
    void currentTimeIsNotEmptyString() {
        assertNotNull(CurrentTime.getNewDateString());
        assertInstanceOf(String.class, CurrentTime.getNewDateString());
        assertFalse(CurrentTime.getNewDateString().isEmpty());
    }

    @Test
    void timestampAreCorrectlyFormattedAccordingToPattern() {
        Date timestampDate = new GregorianCalendar(2020, Calendar.OCTOBER, 31).getTime();
        assertEquals("31/10/2020 00:00:00", CurrentTime.getNewDateFormatter().format(timestampDate));
    }

    @Test
    void formatterPatternIsDDMMYYYYHHMMSS() {
        assertEquals(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), CurrentTime.getNewDateFormatter());
    }
}