package CarParkExitBarrierSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrePaidParkingTransaction
{
    private int AmmountOfTickets = 0, ArriveHrs, ArriveMin;

    String Reg, CardNo;

    // private boolean isPrePaid;

    private String[] TicketDetails;

    public static void confirmSelection(Scanner sc)
    {

        System.out.println("You have selected Pre-paid, is this correct?");

        String opt = sc.next();

        if (opt.equals("yes") || opt.equals("Yes"))
        {
            System.out.println("Successfully Selected Yes");
            PrePaidParkingTransaction PrePaid = new PrePaidParkingTransaction();
            PrePaid.readPrePaidTicket("", false);
        }
        if (opt.equals("No") || opt.equals("no"))
        {
            System.out.println("Successfully selected No");
            ParkingTransaction m = new ParkingTransaction();
            m.getOpt();

        }

    }

    public void readPrePaidTicket(String REG, boolean ticketFound)
    {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Registration Number: ");
        Reg = sc.nextLine();
        System.out.println("Enter Card Number: ");
        CardNo = sc.nextLine();

        AmmountOfTickets = 0;
        String[] PPTicketSplitter = new String[9];
        try
        {
            Scanner r = new Scanner(new File("PrePaidTicket.csv"));
            AmmountOfTickets = Integer.parseInt(r.nextLine());
            TicketDetails = new String[AmmountOfTickets];
            for (int tickets = 0; tickets < AmmountOfTickets; tickets++)
            {

                TicketDetails[tickets] = r.nextLine(); // reads information and puts into Array
                PPTicketSplitter = TicketDetails[tickets].split(", ");
                System.out.println(PPTicketSplitter[1]);
                System.out.println(PPTicketSplitter[2]);
                if (Reg.equals(PPTicketSplitter[1]) && CardNo.equals(PPTicketSplitter[2]))
                {
                    ticketFound = true;
                }

            }
            r.close();
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // write
        if (ticketFound)
        {

            System.out.println("Your ticket has been found!");
            System.out.println("Transaction Number: " + PPTicketSplitter[0] + ", Registration Number: "
                    + PPTicketSplitter[1] + ", CardNumber: " + PPTicketSplitter[2] + ", Arrival Time: "
                    + PPTicketSplitter[3] + ":" + PPTicketSplitter[4] + ", ExpiryTime: " + PPTicketSplitter[5] + ":"
                    + PPTicketSplitter[6] + ", DateofTicketPurchasO: " + PPTicketSplitter[7] + "/"
                    + PPTicketSplitter[8] + "/" + PPTicketSplitter[9] + "\n");
            ticketFound = true;
            ArriveHrs = Integer.parseInt(PPTicketSplitter[3]);
            ArriveMin = Integer.parseInt(PPTicketSplitter[4]);
        }
        else
        {
            System.out.println("Ticket Not Found, Please Start again");
            ParkingTransaction m = new ParkingTransaction();
            m.getOpt();
        }
        /*
         * if (ticketFound)
         * {
         * ParkingTransaction PT = new ParkingTransaction(Reg, ArriveHrs, ArriveMin);
         * PT.calcLOS();
         * PT.calculateCost();
         * System.out.println(PT.getCost());
         * 
         * }
         * else if (ticketFound == false)
         * {
         * System.out.println("Ticket Not Found.");
         * ParkingTransaction m = new ParkingTransaction();
         * m.getOpt();
         * }
         */

    }

}

/*
 * public PrePaidParkingTransaction(String RegNo, int ArrivalHrs, int ArrivalMin, int ExpiryHour, int ExpiryMin)
 * {
 * super(RegNo, ArrivalHrs, ArrivalMin, ExpiryHour, ExpiryMin);
 * // TODO Auto-generated constructor stub
 * }
 */

