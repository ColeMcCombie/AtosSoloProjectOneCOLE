package CarParkExitBarrierSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DriveUpParkingTransaction
{
    private String CardNo = "";

    private int expMonth, expYear;

    public void confirmSelection(Scanner sc, boolean createTicket)
    {
        if (createTicket == true)
        {
            System.out.println("You have selected Drive Up Ticket, is this correct?");
            String opt = sc.next();
            if (opt.equals("yes") || opt.equals("Yes"))
            {
                System.out.println("Successfully Selected Yes");
                DriveUpParkingTransaction TRANS = new DriveUpParkingTransaction();
                TRANS.getTicket();
            }
            if (opt.equals("No") || opt.equals("no"))
            {
                System.out.println("Successfully selected No");
                ParkingTransaction m = new ParkingTransaction();
                m.getOpt();
            }
        }
        else
        {
            System.out.println("You have selected Pay For Ticket, is this correct?");
            String opt = sc.next();
            if (opt.equals("yes") || opt.equals("Yes"))
            {
                System.out.println("Successfully Selected Yes");
                DriveUpParkingTransaction TRANS = new DriveUpParkingTransaction();
                TRANS.payForTicket();
            }
            if (opt.equals("No") || opt.equals("no"))
            {
                System.out.println("Successfully selected No");
                ParkingTransaction m = new ParkingTransaction();
                m.getOpt();
            }
        }
    }

    public void writeToCentralAuth(boolean isPass, String Reason)
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
            Scanner r = new Scanner(new File("CentralAuthorisation.csv"));
            AmmountOfTrans = Integer.parseInt(r.nextLine());
            CSVdetails = new String[AmmountOfTrans];
            for (int tickets = 0; tickets < AmmountOfTrans; tickets++)
            {
                CSVdetails[tickets] = r.nextLine(); // reads information and puts into Array
            }
            r.close();
            PrintWriter wr = new PrintWriter(new File("CentralAuthorisation.csv"));
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void getTicket()
    {
        Scanner sc = new Scanner(System.in);
        Calendar c = new GregorianCalendar();
        int hr = c.get(Calendar.HOUR_OF_DAY), min = c.get(Calendar.MINUTE);
        String UserReg = "";
        System.out.println("Enter Registration Number: ");
        UserReg = sc.nextLine();
        ParkingTicket DUT = new ParkingTicket(UserReg, hr, min);
        // DUT = drive up ticket
        System.out.println(DUT.toString());
        ReadTicket X = new ReadTicket();
        X.readFile(UserReg, false);
        X.writeToFile(UserReg, hr, min, 0, 0, false);
    }

    public void payForTicket()
    {

        Scanner sc = new Scanner(System.in);
        String UserReg = "";
        System.out.println("Enter Registration Number: ");
        UserReg = sc.nextLine();
        ReadTicket PFT = new ReadTicket();
        PFT.readFile(UserReg, true);

        boolean cardAccepted = false;
        boolean cardDateAccepted = false;

        do
        {
            cardDateAccepted = false;
            System.out.println("Please input Card Number: ");
            CardNo = sc.nextLine();
            if (checkCardDigits(CardNo))
            {
                System.out.println("Card Number accepted! ");
                do
                {
                    if (checkCardExpiry())
                    {
                        cardDateAccepted = true;
                        cardAccepted = true;
                    }
                    else
                    {
                        if (cardDateAccepted == false)
                        {
                            writeToCentralAuth(false, "card expired");
                        }
                        else
                            writeToCentralAuth(false, "invalid card number");

                        cardDateAccepted = true;
                    }

                }
                while (cardDateAccepted == false);

            }
            else
                System.out.println("Please try again.");
        }
        while (cardAccepted == false);
        System.out.println("Card has been accepted and processed! Payment Complete!\n");
        writeToCentralAuth(true, "n/a");

        ParkingTransaction m = new ParkingTransaction();
        m.getOpt();

    }

    public boolean checkCardDigits(String CardNo)
    {
        if (CardNo.length() == 16)
        {
            return true;
        }
        else
            return false;
    }

    public boolean checkCardExpiry()
    {
        Scanner sc = new Scanner(System.in);
        Calendar c = new GregorianCalendar();

        System.out.println("Enter Expiry Month: (mm)");
        expMonth = sc.nextInt();
        System.out.println("Enter Expiry Year: (YYYY)");
        expYear = sc.nextInt();

        if (expYear >= c.get(Calendar.YEAR) || expYear == c.get(Calendar.YEAR)
                && expMonth >= (c.get(Calendar.MONTH) + 1))
        {
            return true;
        }
        else
        {
            System.out.println("Card Expired! Try another.");
            return false;
        }
    }

}
