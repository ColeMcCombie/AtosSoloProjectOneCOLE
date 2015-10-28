package CarParkExitBarrierSystem;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ParkingTicket
{

    Calendar c = new GregorianCalendar();

    private String RegNo;

    private int ArrivalHrs, ArrivalMin;

    private boolean isPrePaid;

    private int ExpiryHour, ExpiryMin;

    private int lengthOfStayHours;

    private int lengthOfStayMinutes;

    //prepaid 
    public ParkingTicket(String RegNo, int ArrivalHrs, int ArrivalMin, int ExpiryHour, int ExpiryMin)
    {
        this.RegNo = RegNo;
        this.ArrivalHrs = ArrivalHrs;
        this.ArrivalMin = ArrivalMin;
        this.isPrePaid = true;
    }

    //driveup parking ticket
    public ParkingTicket(String RegNo, int ArrivalHrs, int ArrivalMin)
    {
        this.RegNo = RegNo;
        this.ArrivalHrs = ArrivalHrs;
        this.ArrivalMin = ArrivalMin;
        this.isPrePaid = false;
    }
 
    //overstayed prepaid
    /*public ParkingTicket(String RegNo, int ArrivalHrs, int ArrivalMin,  )
    {
        
    }*/
    public String getRegNo()
    {
        return RegNo;
    }

    public int getArrivalHrs()
    {
        return ArrivalHrs;
    }

    public int getArrivalMin()
    {
        return ArrivalMin;
    }

    public boolean getPrePaid()
    {
        return isPrePaid;
    }

    public int getTransactionHour()
    {
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public int getTransactionMinutes()
    {
        return c.get(Calendar.MINUTE);
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
}
