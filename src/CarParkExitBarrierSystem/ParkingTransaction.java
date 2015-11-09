package CarParkExitBarrierSystem;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ParkingTransaction
{
    // main method
    static Scanner sc = new Scanner(System.in);

    static Calendar c = new GregorianCalendar();

    DriveUpParkingTransaction DriveUp = new DriveUpParkingTransaction();

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

    /*
     * public int getCardExpiryMonth()
     * {
     * return c.get(Calendar.MONTH);
     * }
     * 
     * public int getCardExpiryYear()
     * {
     * return c.get(Calendar.YEAR);
     * }
     */

}
