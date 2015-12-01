package CarParkExitBarrierSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TicketReader
{
    private String Reg;

    public int AmmountOfTickets = 0, ArriveHrs, ArriveMin;

    private boolean isPrePaid;

    private String[] TicketDetails;

    public void readFile(String REG, boolean payForTicket, boolean isPrePaid)
    {
        AmmountOfTickets = 0;
        String[] TicketSplitter = new String[5];
        try
        {
            Scanner r = new Scanner(new File(
                    "C:\\_development\\workspaces\\epi_tutorials\\AtosSoloProjectOneCOLE\\src\\main\\resources\\ParkingTicket.csv"));
            AmmountOfTickets = Integer.parseInt(r.nextLine());
            TicketDetails = new String[AmmountOfTickets];
            for (int tickets = 0; tickets < AmmountOfTickets; tickets++)
            {
                TicketDetails[tickets] = r.nextLine(); // reads information and puts into Array
            }
            r.close();
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // write
        if (payForTicket)
        {
            boolean ticketfound = false;
            for (int tickets = 0; tickets < TicketDetails.length; tickets++)
            {
                TicketSplitter = TicketDetails[tickets].split(", ");
                if (REG.equals(TicketSplitter[1]))
                {
                    System.out.println("Your ticket has been found!");
                    System.out.println("Transaction Number: " + TicketSplitter[0] + ", Registration Number: "
                            + TicketSplitter[1] + ", Date: " + TicketSplitter[2] + ", Arrival Time: "
                            + TicketSplitter[3] + ":" + TicketSplitter[4]);
                    ticketfound = true;
                }
            }

            if (ticketfound)
            {
                ParkingTransaction PT = new ParkingTransaction(Reg, ArriveHrs, ArriveMin);
                PT.calcLOS();
                PT.calculateCost();
                System.out.println(PT.getCost());

            }
            else if (ticketfound == false)
            {
                System.out.println("Ticket Not Found.");
                ParkingTransaction m = new ParkingTransaction();
                m.getOpt();
            }

        }

    }

    public int getTicketAmmount()
    {
        return AmmountOfTickets;
    }

    public String[] getTicketDetails()
    {
        return TicketDetails;
    }

}
