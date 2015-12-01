package CarParkExitBarrierSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CentralAuthWriter
{
    public void writeToCentralAuth(boolean isPass, String reason, String cardNo, int expiryMonth, int expiryYear)
    {
        String passed;

        if (isPass)
        {
            passed = "Pass";
        }
        else
            passed = "Fail";

        int ammountOfTrans = 0;
        String[] CSVdetails;

        try
        {
            Scanner r = new Scanner(new File(
                    "C:\\_development\\workspaces\\epi_tutorials\\AtosSoloProjectOneCOLE\\src\\main\\resources\\CentralAuthorisation.csv"));
            ammountOfTrans = Integer.parseInt(r.nextLine());
            CSVdetails = new String[ammountOfTrans];
            for (int tickets = 0; tickets < ammountOfTrans; tickets++)
            {
                CSVdetails[tickets] = r.nextLine(); // reads information and puts into Array
            }
            r.close();
            PrintWriter wr = new PrintWriter(new File(
                    "C:\\_development\\workspaces\\epi_tutorials\\AtosSoloProjectOneCOLE\\src\\main\\resources\\CentralAuthorisation.csv"));
            ammountOfTrans += 1;
            wr.println(ammountOfTrans);
            for (int tickets = 0; tickets < (ammountOfTrans - 1); tickets++)
            {
                wr.println(CSVdetails[tickets]); // reads information and passes to ticket
            }
            Calendar c = new GregorianCalendar();
            int day = c.get(Calendar.DAY_OF_MONTH), Month = c.get(Calendar.MONTH), Year = c.get(Calendar.YEAR);
            String dateOfTrans = day + "/" + Month + "/" + Year;
            wr.println("Transaction Number: " + ammountOfTrans + ", Transaction Type: D, Card Number: " + cardNo
                    + ", Expiry Date: " + expiryMonth + "/" + expiryYear + ", Date Of Transaction: " + dateOfTrans
                    + ", request outcome:" + passed + ", + reason: " + reason);
            wr.close();

        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);

        }
    }
}
