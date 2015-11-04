package CarParkExitBarrierSystem;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ParkingTransaction
{
    // main method

    Calendar c = new GregorianCalendar();

    public static void main(String[] args)
    {
        System.out.println("heybo1");
    }

    public int getCardExpiryMonth()
    {
        return c.get(Calendar.MONTH);
    }

    public int getCardExpiryYear()
    {
        return c.get(Calendar.YEAR);
    }

}
