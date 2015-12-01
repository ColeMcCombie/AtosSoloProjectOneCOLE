package CarParkExitBarrierSystem;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ParkingTicket
{
    // class for processing ticket

    Calendar c = new GregorianCalendar();

    private String reg;

    private int arriveHour, arriveMinute;

    private int expiryHour, expiryMinute;

    private int lengthOfStayHours;

    private int lengthOfStayMinutes;

    // prepaid
    public ParkingTicket(String reg, int arriveHour, int arriveMinute, int expiryHour, int expiryMinute)
    {
        this.reg = reg;
        this.arriveHour = arriveHour;
        this.arriveMinute = arriveMinute;
        this.expiryHour = expiryHour;
        this.expiryMinute = expiryMinute;
    }

    // driveup parking ticket
    public ParkingTicket(String reg, int arriveHour, int arriveMinute)
    {
        this.reg = reg;
        this.arriveHour = arriveHour;
        this.arriveMinute = arriveMinute;
    }

    public ParkingTicket()
    {

    }

    @Override
    public String toString()
    {
        return "Registration Number: " + reg + ", Arrival Time: " + arriveHour + ":" + arriveMinute;

    }

    public int getTransactionHour()
    {
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public int getTransactionMinute()
    {
        return c.get(Calendar.MINUTE);
    }

    public int getExpiryHour()
    {
        return expiryHour;
    }

    public int getExpiryMinute()
    {
        return expiryMinute;
    }

    public int getLengthOfStayHours()
    {
        return lengthOfStayHours;
    }

    public int getLengthOfStayMinutes()
    {
        return lengthOfStayMinutes;
    }

}
