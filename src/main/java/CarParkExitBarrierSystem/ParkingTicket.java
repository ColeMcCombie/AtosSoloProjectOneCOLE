package CarParkExitBarrierSystem;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ParkingTicket
{
    // class for processing ticket

    Calendar c = new GregorianCalendar();

    private String RegNo;

    private int ArrivalHrs, ArrivalMin;

    private boolean isPrePaid;

    private int ExpiryHour, ExpiryMin;

    private int lengthOfStayHours;

    private int lengthOfStayMinutes;

    private int transactionHour, transactionMinute;
    // double cost;

    // prepaid
    public ParkingTicket(String RegNo, int ArrivalHrs, int ArrivalMin, int ExpiryHour, int ExpiryMin)
    {
        this.RegNo = RegNo;
        this.ArrivalHrs = ArrivalHrs;
        this.ArrivalMin = ArrivalMin;
        this.isPrePaid = true;
    }

    // driveup parking ticket
    public ParkingTicket(String RegNo, int ArrivalHrs, int ArrivalMin)
    {
        this.RegNo = RegNo;
        this.ArrivalHrs = ArrivalHrs;
        this.ArrivalMin = ArrivalMin;
        this.isPrePaid = false;
    }

    public ParkingTicket()
    {

    }

    public String toString()
    {
        return "Registration Number: " + RegNo + ", Arrival Time: " + ArrivalHrs + ":" + ArrivalMin;

    }

    public int getTransactionHour()
    {
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public void setTransactionHour(int transactionHour)
    {
        this.transactionHour = transactionHour;
    }

    public int getTransactionMinute()
    {
        return c.get(Calendar.MINUTE);
    }

    void setTransactionMinute(int transactionMinute)
    {
        this.transactionMinute = transactionMinute;
    }

    public int getExpiryHour()
    {
        return ExpiryHour;
    }

    public int getExpiryMinute()
    {
        return ExpiryMin;
    }

    public int getLengthOfStayHours()
    {
        return lengthOfStayHours;
    }

    public int getLengthOfStayMinutes()
    {
        return lengthOfStayMinutes;
    }

    public void setLengthOfStayMinutes(int lengthOfStayMinutes)
    {
        this.lengthOfStayMinutes = lengthOfStayMinutes;
    }

    public void setLengthOfStayHours(int lengthOfStayHours)
    {
        this.lengthOfStayHours = lengthOfStayHours;
    }

    public int workOutTheDay(Calendar currentDate)
    {
        return currentDate.get(Calendar.DAY_OF_WEEK);
    }

    public boolean isItAWeekend(Calendar currentDate)
    {
        int dayCalculation = workOutTheDay(currentDate);
        if (dayCalculation == Calendar.SUNDAY || dayCalculation == Calendar.SATURDAY)
        {
            return true;

        }

        else
        {
            return false;
        }

    }

}
