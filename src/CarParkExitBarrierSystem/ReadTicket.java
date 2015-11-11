package CarParkExitBarrierSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ReadTicket
{
    private String Reg;

    private int AmmountOfTickets = 0, ArriveHrs, ArriveMin;

    private boolean isPrePaid;

    private String[] TicketDetails;

    public void readFile(String REG, boolean payForTicket)
    {
        AmmountOfTickets = 0;
        String[] TicketSplitter = new String[5];
        try
        {
            Scanner r = new Scanner(new File("ParkingTicket.csv"));
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
                    ArriveHrs = Integer.parseInt(TicketSplitter[3]);
                    ArriveMin = Integer.parseInt(TicketSplitter[4]);

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

    public void writeToFile(String Reg, int ArriveHrs, int ArriveMin, int ExpiryHr, int ExpiryMin, boolean isPrePaid)
    {
        try
        {
            PrintWriter wr = new PrintWriter(new File("ParkingTicket.csv"));
            AmmountOfTickets += 1;
            wr.println(AmmountOfTickets);
            for (int tickets = 0; tickets < (AmmountOfTickets - 1); tickets++)
            {
                wr.println(TicketDetails[tickets]); // reads information and passes to ticket
            }
            Calendar c = new GregorianCalendar();
            int Day = c.get(Calendar.DAY_OF_MONTH), Month = c.get(Calendar.MONTH), Year = c.get(Calendar.YEAR);
            String DOT = Day + "/" + Month + "/" + Year;
            wr.println(AmmountOfTickets + ", " + Reg + ", " + DOT + ", " + ArriveHrs + ", " + ArriveMin);
            wr.close();
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ParkingTransaction m = new ParkingTransaction();
        m.getOpt();
    }
}
