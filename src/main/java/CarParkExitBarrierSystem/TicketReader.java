package CarParkExitBarrierSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TicketReader
{

    int ammountOfTickets = 0, arriveHour, arriveMinute;

    private String[] ticketDetails;

    public void readFile(String reg, boolean payForTicket)
    {
        ammountOfTickets = 0;
        try
        {
            Scanner r = new Scanner(new File(
                    "C:\\_development\\workspaces\\epi_tutorials\\AtosSoloProjectOneCOLE\\src\\main\\resources\\ParkingTicket.csv"));
            ammountOfTickets = Integer.parseInt(r.nextLine());
            ticketDetails = new String[ammountOfTickets];
            for (int tickets = 0; tickets < ammountOfTickets; tickets++)
            {
                ticketDetails[tickets] = r.nextLine(); // reads information and puts into Array
            }
            r.close();
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException("File was not found");
        }
        // write
        if (payForTicket)
        {
            boolean ticketfound = false;
            for (int tickets = 0; tickets < ticketDetails.length; tickets++)
            {
                String[] ticketSplitter = ticketDetails[tickets].split(", ");
                if (reg.equals(ticketSplitter[1]))
                {
                    System.out.println("Your ticket has been found!");
                    System.out.println("Transaction Number: " + ticketSplitter[0] + ", Registration Number: "
                            + ticketSplitter[1] + ", Date: " + ticketSplitter[2] + ", Arrival Time: "
                            + ticketSplitter[3] + ":" + ticketSplitter[4]);
                    arriveHour = Integer.parseInt(ticketSplitter[3]);
                    arriveMinute = Integer.parseInt(ticketSplitter[4]);
                    ticketfound = true;

                }
            }

            if (ticketfound)
            {
                ParkingTransaction PT = new ParkingTransaction(reg, arriveHour, arriveMinute);

                PT.calcLengthOfStay();
                PT.calculateCost();
                System.out.println("The ammount you are owe is: £" + PT.getCost() + "\n");

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
        return ammountOfTickets;
    }

    public String[] getTicketDetails()
    {
        return ticketDetails;
    }

}
