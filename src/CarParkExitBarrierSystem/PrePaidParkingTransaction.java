package CarParkExitBarrierSystem;

import java.util.Scanner;

public class PrePaidParkingTransaction extends ParkingTicket
{
    public void confirmSelection(Scanner sc)
    {

        System.out.println("You have selected Pre-paid, is this correct?");

        String opt = sc.next();

        if (opt.equals("yes") || opt.equals("Yes"))
        {
            System.out.println("Successfully Selected Yes");
            // Call Get Ticket method
        }
        if (opt.equals("No") || opt.equals("no"))
        {
            System.out.println("Successfully selected No");
            ParkingTransaction m = new ParkingTransaction();
            m.getOpt();

        }

    }

    public PrePaidParkingTransaction(String RegNo, int ArrivalHrs, int ArrivalMin, int ExpiryHour, int ExpiryMin)
    {
        super(RegNo, ArrivalHrs, ArrivalMin, ExpiryHour, ExpiryMin);
        // TODO Auto-generated constructor stub
    }

}
