package CarParkExitBarrierSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrePaidParkingTransaction
{
    private int AmmountOfTickets = 0, ArriveHrs, ArriveMin, ExpiryHour, ExpiryMin;

    String Reg, CardNo;

    // private boolean isPrePaid;

    public String[] TicketDetails;

    public String[] PPTicketSplitter = new String[9];

    public void confirmSelection(Scanner sc)
    {

        System.out.println("You have selected Pre-paid, is this correct?");

        String opt = sc.next();

        if (opt.equals("yes") || opt.equals("Yes"))
        {
            System.out.println("Successfully Selected Yes");
            PrePaidParkingTransaction PrePaid = new PrePaidParkingTransaction();
            PrePaid.readPrePaidTicket(false);
        }
        if (opt.equals("No") || opt.equals("no"))
        {
            System.out.println("Successfully selected No");
            ParkingTransaction m = new ParkingTransaction();
            m.getOpt();

        }

    }

    public void readPrePaidTicket(boolean ticketFound)
    {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Registration Number: ");
        Reg = sc.nextLine();
        System.out.println("Enter Card Number: ");
        CardNo = sc.nextLine();

        AmmountOfTickets = 0;
        try
        {
            Scanner r = new Scanner(new File(
                    "C:\\_development\\workspaces\\epi_tutorials\\AtosSoloProjectOneCOLE\\src\\main\\resources\\PrePaidTicket.csv"));
            AmmountOfTickets = Integer.parseInt(r.nextLine());
            TicketDetails = new String[AmmountOfTickets];
            for (int tickets = 0; tickets < AmmountOfTickets; tickets++)
            {

                TicketDetails[tickets] = r.nextLine(); // reads information and puts into Array
                PPTicketSplitter = TicketDetails[tickets].split(", ");
                if (Reg.equals(PPTicketSplitter[1]) && CardNo.equals(PPTicketSplitter[2]))
                {
                    ticketFound = true;
                    break;
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
                    + PPTicketSplitter[6] + ", DateofTicketPurchasO: " + PPTicketSplitter[7] + "/" + PPTicketSplitter[8]
                    + "/" + PPTicketSplitter[9] + "\n");
            ticketFound = true;
            ArriveHrs = Integer.parseInt(PPTicketSplitter[3]);
            ArriveMin = Integer.parseInt(PPTicketSplitter[4]);
            ExpiryHour = Integer.parseInt(PPTicketSplitter[5]);
            ExpiryMin = Integer.parseInt(PPTicketSplitter[6]);
            ParkingTransaction PT = new ParkingTransaction(Reg, ArriveHrs, ArriveMin);

            PT.calcLOS();
            PT.calculateCost();
            System.out.println(PT.getCost());

        }
        else
        {
            System.out.println("Ticket Not Found, Please Start again");
            ParkingTransaction m = new ParkingTransaction();
            m.getOpt();
        }

    }

    public String getReg()
    {
        return Reg;
    }

    public int getArrivalHour()
    {
        return ArriveHrs;
    }

    public int getArrivalMinute()
    {
        return ArriveMin;
    }

    public int getExpiryHour()
    {
        return ExpiryHour;
    }

    public int getExpiryMinute()
    {
        return ExpiryMin;
    }

}
