package CarParkExitBarrierSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FileWriter
{
    public void writeToFile(String reg, int arriveHour, int arriveMinute)
    {
        TicketReader read = new TicketReader();
        int ammountOfTickets = read.getTicketAmmount();
        String[] ticketDetails = read.getTicketDetails();
        try
        {
            PrintWriter wr = new PrintWriter(new File(
                    "C:\\_development\\workspaces\\epi_tutorials\\AtosSoloProjectOneCOLE\\src\\main\\resources\\ParkingTicket.csv"));
            ammountOfTickets += 1;
            wr.println(ammountOfTickets);
            for (int tickets = 0; tickets < (ammountOfTickets - 1); tickets++)
            {
                wr.println(ticketDetails[tickets]); // reads information and passes to ticket
            }
            Calendar c = new GregorianCalendar();
            int day = c.get(Calendar.DAY_OF_MONTH), month = c.get(Calendar.MONTH), year = c.get(Calendar.YEAR);
            String dateOfTrans = day + "/" + month + "/" + year;
            wr.println(ammountOfTickets + ", " + reg + ", " + dateOfTrans + ", " + arriveHour + ", " + arriveMinute);
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
