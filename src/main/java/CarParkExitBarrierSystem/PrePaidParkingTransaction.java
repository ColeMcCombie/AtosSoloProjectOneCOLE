package CarParkExitBarrierSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrePaidParkingTransaction
{
    private int ammountOfTickets = 0, arriveHour, arriveMinute, expiryHour, expiryMinute;

    String reg, cardNo;

    boolean ticketFound = false;

    String[] ticketDetails;

    String[] PPTicketSplitter = new String[9];

    public void confirmSelection(Scanner sc)
    {

        System.out.println("You have selected Pre-paid, is this correct?");

        String opt = sc.next();

        if ("yes".equalsIgnoreCase(opt))
        {
            System.out.println("Successfully Selected Yes");
            readPrePaidTicket();
            arriveHour = getArrivalHour();
            arriveMinute = getArrivalMinute();
            expiryHour = getExpiryHour();
            expiryMinute = getExpiryMinute();
            cardNo = getCardNo();
            reg = getReg();

            OverstayedPrePaidParkingTransaction OverStay = new OverstayedPrePaidParkingTransaction();

            if (OverStay.isOverstayed())
            {
                OverstayedPrePaidParkingTransaction overStayed = new OverstayedPrePaidParkingTransaction(reg,
                        arriveHour, arriveMinute, expiryHour, expiryMinute, cardNo);
                overStayed.createTransaction();
            }
            else
                System.out.println("No further charges are due, thank you for using the car park exit barrier system.");
        }
        else if ("no".equalsIgnoreCase(opt))
        {
            System.out.println("Successfully selected No");
            ParkingTransaction m = new ParkingTransaction();
            m.getOpt();

        }
        else
        {
            System.out.println("Invalid selection.");
            ParkingTransaction m = new ParkingTransaction();
            m.getOpt();

        }

    }

    public void readPrePaidTicket()
    {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Registration Number: ");
        reg = sc.nextLine();
        System.out.println("Enter Card Number: ");
        cardNo = sc.nextLine();

        ammountOfTickets = 0;
        try
        {
            Scanner r = new Scanner(new File(
                    "C:\\_development\\workspaces\\epi_tutorials\\AtosSoloProjectOneCOLE\\src\\main\\resources\\PrePaidTicket.csv"));
            ammountOfTickets = Integer.parseInt(r.nextLine());
            ticketDetails = new String[ammountOfTickets];
            for (int tickets = 0; tickets < ammountOfTickets; tickets++)
            {

                ticketDetails[tickets] = r.nextLine(); // reads information and puts into Array
                PPTicketSplitter = ticketDetails[tickets].split(", ");
                if (reg.equals(PPTicketSplitter[1]) && cardNo.equals(PPTicketSplitter[2]))
                {
                    ticketFound = true;
                    break;
                }

            }
            r.close();
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException("File was not found");
        }
        // write
        if (ticketFound)
        {
            System.out.println("Your ticket has been found!");
            System.out.println("Transaction Number: " + PPTicketSplitter[0] + ", Registration Number: "
                    + PPTicketSplitter[1] + ", CardNumber: " + PPTicketSplitter[2] + ", Arrival Time: "
                    + PPTicketSplitter[3] + ":" + PPTicketSplitter[4] + ", ExpiryTime: " + PPTicketSplitter[5] + ":"
                    + PPTicketSplitter[6] + ", DateofTicketPurchasO: " + PPTicketSplitter[7] + "/" + PPTicketSplitter[8]
                    + "/" + PPTicketSplitter[9] + "\n");
            arriveHour = Integer.parseInt(PPTicketSplitter[3]);
            arriveMinute = Integer.parseInt(PPTicketSplitter[4]);
            expiryHour = Integer.parseInt(PPTicketSplitter[5]);
            expiryMinute = Integer.parseInt(PPTicketSplitter[6]);
            ParkingTransaction PT = new ParkingTransaction(reg, arriveHour, arriveMinute);

            PT.calcLengthOfStay();
            PT.calculateCost();

        }
        else
        {
            System.out.println("Ticket Not Found, Please Start again");
            ParkingTransaction m = new ParkingTransaction();
            m.getOpt();
        }

    }

    public String getReg()
    {
        return reg;
    }

    public int getArrivalHour()
    {
        return arriveHour;
    }

    public int getArrivalMinute()
    {
        return arriveMinute;
    }

    public int getExpiryHour()
    {
        return expiryHour;
    }

    public int getExpiryMinute()
    {
        return expiryMinute;
    }

    public String getCardNo()
    {
        return cardNo;
    }

}
