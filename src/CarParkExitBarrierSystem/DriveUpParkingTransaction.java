package CarParkExitBarrierSystem;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DriveUpParkingTransaction
{
    public void confirmSelection(Scanner sc, boolean createTicket)
    {
        if (createTicket == true)
        {
            System.out.println("You have selected Drive Up Ticket, is this correct?");
            String opt = sc.next();
            if (opt.equals("yes") || opt.equals("Yes"))
            {
                System.out.println("Successfully Selected Yes");
                DriveUpParkingTransaction TRANS = new DriveUpParkingTransaction();
                TRANS.getTicket();
            }
            if (opt.equals("No") || opt.equals("no"))
            {
                System.out.println("Successfully selected No");
                ParkingTransaction m = new ParkingTransaction();
                m.getOpt();
            }
        }
        else
        {
            System.out.println("You have selected Pay For Ticket, is this correct?");
            String opt = sc.next();
            if (opt.equals("yes") || opt.equals("Yes"))
            {
                System.out.println("Successfully Selected Yes");
                DriveUpParkingTransaction TRANS = new DriveUpParkingTransaction();
                TRANS.payForTicket();
            }
            if (opt.equals("No") || opt.equals("no"))
            {
                System.out.println("Successfully selected No");
                ParkingTransaction m = new ParkingTransaction();
                m.getOpt();
            }
        }
    }

    public void getTicket()
    {
        Scanner sc = new Scanner(System.in);
        Calendar c = new GregorianCalendar();
        int hr = c.get(Calendar.HOUR_OF_DAY), min = c.get(Calendar.MINUTE);
        String UserReg = "";
        System.out.println("Enter Registration Number: ");
        UserReg = sc.nextLine();
        ParkingTicket DUT = new ParkingTicket(UserReg, hr, min);
        // DUT = drive up ticket
        System.out.println(DUT.toString());
        ReadTicket X = new ReadTicket();
        X.readFile(UserReg, false);
        X.writeToFile(UserReg, hr, min, 0, 0, false);
    }

    public void payForTicket()
    {

        Scanner sc = new Scanner(System.in);
        String UserReg = "";
        System.out.println("Enter Registration Number: ");
        UserReg = sc.nextLine();
        ReadTicket PFT = new ReadTicket();
        PFT.readFile(UserReg, true);

        // PFT = pay for ticket
    }
}
