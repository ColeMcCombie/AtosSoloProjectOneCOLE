package CarParkExitBarrierSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FileWriter
{
    public void writeToFile(String Reg, int ArriveHrs, int ArriveMin)
    {
        TicketReader read = new TicketReader();
        int AmmountOfTickets = read.getTicketAmmount();
        String[] TicketDetails = read.getTicketDetails();
        try
        {
            PrintWriter wr = new PrintWriter(new File(
                    "C:\\_development\\workspaces\\epi_tutorials\\AtosSoloProjectOneCOLE\\src\\main\\resources\\ParkingTicket.csv"));
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

            throw new RuntimeException(e);

        }
        ParkingTransaction m = new ParkingTransaction();
        m.getOpt();
    }

}
