package CarParkExitBarrierSystem;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ParkingTransaction
{
    // main method
    static Scanner sc = new Scanner(System.in);

    static Calendar c = new GregorianCalendar();

    private String UserReg;

    private int ArriveHrs, ArriveMin, LOShrs, LOSmin;

    private double cost;

    DriveUpParkingTransaction DriveUp = new DriveUpParkingTransaction();

    public ParkingTransaction()
    {

    }

    public ParkingTransaction(String UserReg, int ArriveHrs, int ArriveMin)
    {
        this.UserReg = UserReg;
        this.ArriveHrs = ArriveHrs;
        this.ArriveMin = ArriveMin;
    }

    public static void main(String[] args)
    {

        ParkingTransaction m = new ParkingTransaction();
        // Calling getOpt Method.
        m.getOpt();

    }

    public void getOpt()
    {

        System.out.println("Welcome to carpark ticket system!");
        System.out.println("Please pick one of the following options:");
        System.out.println("1. Pre-paid Ticket");
        System.out.println("2. DriveUp Ticket");
        System.out.println("3. Pay For Stay");

        String opt = sc.next();

        if (opt.equals("1"))
        {
            // call Pre-paid Ticket method
            System.out.println("Pre-paid Ticket");
        }

        if (opt.equals("2"))
        {
            // Call Drive up Ticket method
            System.out.println("Drive Up Ticket");
            DriveUp.confirmSelection(sc, true);

        }
        if (opt.equals("3"))
        {
            System.out.println("Pay For Stay");
            DriveUp.confirmSelection(sc, false);
        }
    }

    public static void print(String Text)
    {
        System.out.println(Text);
    }

    public int getCardExpiryMonth()
    {
        return c.get(Calendar.MONTH);
    }

    public int getCardExpiryYear()
    {
        return c.get(Calendar.YEAR);
    }

    public void calcLOS()
    {
        LOShrs = (c.get(Calendar.HOUR_OF_DAY) - ArriveHrs);
        LOSmin = (c.get(Calendar.MINUTE) - ArriveMin);

        if (LOSmin < 0)
        {
            // fixes bug with -ve time
            LOSmin += 60;
            LOShrs--;
        }
        if (LOShrs < 0 || LOShrs == 0 && LOSmin < 1)
            LOShrs += 24;
    }

    public void calculateCost()
    {
        if (checkWeekDay())
        {
            // calculate cost for weekday
        }
        else
        {
            // calculate cost for weekend
        }
    }

    public boolean checkWeekDay()
    {
        c = new GregorianCalendar();

        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday >= c.get(Calendar.MONDAY) && weekday <= c.get(Calendar.FRIDAY))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void weekdayCalc()
    {
        int totalMins = ((LOShrs * 60) + LOSmin);
        if (totalMins <= 60)
            cost = Charge.WDUpTo1.getCharge();
        else if (totalMins <= 120)
            cost = Charge.WDUpTo2.getCharge();

    }

    public void weekendCalc()
    {

    }

}
