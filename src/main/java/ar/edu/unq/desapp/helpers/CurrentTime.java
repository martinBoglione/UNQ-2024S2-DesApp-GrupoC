package ar.edu.unq.desapp.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    private CurrentTime() {
        //Empty Constructor
    }

    public static Date getNewDate(){
        return new Date();
    }

    public static String getNewDateString(){
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.format(getNewDate());
    }

    public static SimpleDateFormat getNewDateFormatter(){
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter;
    }
}
