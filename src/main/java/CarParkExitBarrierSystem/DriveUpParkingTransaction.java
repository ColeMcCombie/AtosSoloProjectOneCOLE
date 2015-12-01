package CarParkExitBarrierSystem;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DriveUpParkingTransaction
{
    private String CardNo = "";

    public int expMonth, expYear;

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
        TicketReader read = new TicketReader();
        FileWriter write = new FileWriter();

        read.readFile(UserReg, false, false);
        write.writeToFile(UserReg, hr, min, 0, 0, false);
    }

    public void payForTicket()
    {
        CentralAuthWriter caw = new CentralAuthWriter();
        CardChecker checker = new CardChecker();

        Scanner sc = new Scanner(System.in);
        String UserReg = "";
        System.out.println("Enter Registration Number: ");
        UserReg = sc.nextLine();
        TicketReader PFT = new TicketReader();
        PFT.readFile(UserReg, true, false);
        boolean cardAccepted = false;
        boolean cardDateAccepted = false;
        do
        {
            cardDateAccepted = false;
            System.out.println("Please input Card Number: ");
            CardNo = sc.nextLine();
            if (checker.checkCardDigits(CardNo))
            {
                System.out.println("Card Number accepted! ");
                do
                {
                    if (checker.checkCardExpiry(expMonth, expYear))
                    {
                        cardDateAccepted = true;
                        cardAccepted = true;
                    }
                    else
                    {
                        if (cardDateAccepted == false)
                        {
                            caw.writeToCentralAuth(false, "card expired", CardNo, expMonth, expYear);
                        }
                        else
                            caw.writeToCentralAuth(false, "invalid card number", CardNo, expMonth, expYear);

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
        caw.writeToCentralAuth(true, "n/a", CardNo, expMonth, expYear);

        ParkingTransaction m = new ParkingTransaction();
        m.getOpt();

    }

}
