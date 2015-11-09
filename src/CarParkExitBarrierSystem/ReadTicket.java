package CarParkExitBarrierSystem;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ReadTicket
{

    public void copyFile(String Reg, int ArriveHrs, int ArriveMin, int ExpiryHr, int ExpiryMin, boolean isPrePaid)
    {
        int AmmountOfTickets;
        String[] TicketDetails;

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

            System.out.println(TicketDetails[0]);
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
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // write
        System.out.println("Ticket Successfully Read!");
    }

}
