package CarParkExitBarrierSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CentralAuthWriter
{
    public void writeToCentralAuth(boolean isPass, String Reason, String CardNo, int expMonth, int expYear)
    {
        String Passed;

        if (isPass)
        {
            Passed = "Pass";
        }
        else
            Passed = "Fail";

        int AmmountOfTrans = 0;
        String[] CSVdetails;
        String[] TicketSplitter = new String[5];
        try
        {
            Scanner r = new Scanner(new File(
                    "C:\\_development\\workspaces\\epi_tutorials\\AtosSoloProjectOneCOLE\\src\\main\\resources\\CentralAuthorisation.csv"));
            AmmountOfTrans = Integer.parseInt(r.nextLine());
            CSVdetails = new String[AmmountOfTrans];
            for (int tickets = 0; tickets < AmmountOfTrans; tickets++)
            {
                CSVdetails[tickets] = r.nextLine(); // reads information and puts into Array
            }
            r.close();
            PrintWriter wr = new PrintWriter(new File(
                    "C:\\_development\\workspaces\\epi_tutorials\\AtosSoloProjectOneCOLE\\src\\main\\resources\\CentralAuthorisation.csv"));
            AmmountOfTrans += 1;
            wr.println(AmmountOfTrans);
            for (int tickets = 0; tickets < (AmmountOfTrans - 1); tickets++)
            {
                wr.println(CSVdetails[tickets]); // reads information and passes to ticket
            }
            Calendar c = new GregorianCalendar();
            int Day = c.get(Calendar.DAY_OF_MONTH), Month = c.get(Calendar.MONTH), Year = c.get(Calendar.YEAR);
            String DOT = Day + "/" + Month + "/" + Year;
            wr.println("Transaction Number: " + AmmountOfTrans + ", Transaction Type: D, Card Number: " + CardNo
                    + ", Expiry Date: " + expMonth + "/" + expYear + ", Date Of Transaction: " + DOT
                    + ", request outcome:" + Passed + ", + reason: " + Reason);
            wr.close();

        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
            // e.printStackTrace();
            // throw runtimeexception
        }
    }
}
