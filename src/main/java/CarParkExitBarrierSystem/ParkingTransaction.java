package CarParkExitBarrierSystem;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ParkingTransaction
{
    static Scanner sc = new Scanner(System.in);

    int totalMins = 1;

    DriveUpParkingTransaction DriveUp = new DriveUpParkingTransaction();

    ParkingTicket pt;

    PrePaidParkingTransaction PrePaid = new PrePaidParkingTransaction();

    OverstayedPrePaidParkingTransaction OverStay = new OverstayedPrePaidParkingTransaction();

    public static void main(String[] args)
    {

        System.out.println("Welcome to carpark ticket system!");
        ParkingTransaction m = new ParkingTransaction();
        // Calling getOpt Method.
        m.getOpt();

    }

    private Calendar c = new GregorianCalendar();

    private String reg, cardNo;

    private int arriveHour;

    private int arriveMinute;

    private int lengthOfStayHours;

    private int lengthOfStayMinutes;

    int expiryHour;

    int expiryMinute;

    double cost;

    int transactionNumber;

    public void getOpt()
    {

        System.out.println("Please pick one of the following options:");
        System.out.println("1. Pre-paid Ticket");
        System.out.println("2. DriveUp Ticket");
        System.out.println("3. Pay For Stay (Drive Up)");
        System.out.println("4. End Program");

        String opt = sc.next();

        if ("1".equals(opt))
        {
            // call Pre-paid Ticket method
            System.out.println("Pre-paid Ticket");
            PrePaid.confirmSelection(sc);
        }
        else if ("2".equals(opt))
        {
            // Call Drive up Ticket method
            System.out.println("Drive Up Ticket");
            DriveUp.confirmSelection(sc, true);

        }
        else if ("3".equals(opt))
        {
            System.out.println("Pay For Stay (DriveUp)");
            DriveUp.confirmSelection(sc, false);
        }

        else if ("4".equals(opt))
        {
            System.out.println("Have" + " a good day!!");

        }
        else
        {
            System.out.println("You have made an invalid selection. Please try again.\n");
            getOpt();
        }

    }

    public ParkingTransaction()
    {

    }

    public ParkingTransaction(String Reg, int ArriveHrs, int ArriveMin, int ExpiryHrs, int ExpiryMin)
    {

        this.reg = Reg;
        this.arriveHour = ArriveHrs;
        this.arriveMinute = ArriveMin;
        this.expiryHour = ExpiryHrs;
        this.expiryMinute = ExpiryMin;

    }

    public ParkingTransaction(String Reg, int ArriveHrs, int ArriveMin)
    {
        this.reg = Reg;
        this.arriveHour = ArriveHrs;
        this.arriveMinute = ArriveMin;
    }

    public void calcLengthOfStay()
    {

        lengthOfStayHours = (c.get(Calendar.HOUR_OF_DAY)) - arriveHour;

        lengthOfStayMinutes = (c.get(Calendar.MINUTE)) - arriveMinute;

        if (lengthOfStayMinutes < 0)
        {
            // fixes bug with -ve time
            lengthOfStayMinutes += 60;
            lengthOfStayHours--;
        }
        if (lengthOfStayHours < 0 || lengthOfStayHours == 0 && lengthOfStayMinutes < 1)
            lengthOfStayHours += 24;

    }

    public void calculateCost()
    {

        if (checkWeekDay())
        {
            weekdayCalc();
        }
        else
        {
            weekendCalc();
        }
    }

    public void calculateCostPrePaidOver(int ExpiryHour, int ExpiryMin)
    {
        int ChargeableHour = c.get(Calendar.HOUR_OF_DAY) - (ExpiryHour);

        int ChargeableMinute = c.get(Calendar.MINUTE) - (ExpiryMin);

        if (ChargeableMinute < 0)
        {
            // fixes bug with -ve time
            ChargeableMinute += 60;
            ChargeableHour--;
        }
        if (ChargeableHour < 0 || ChargeableHour == 0 && ChargeableMinute < 1)
            ChargeableHour += 24;

        totalMins = ChargeableMinute + (ChargeableHour * 60);
        if (checkWeekDay())
        {
            weekdayGetCharge();
        }
        else
            weekendGetCharge();
    }

    public double getCost()
    {
        return cost;
    }

    public boolean checkWeekDay()
    {
        c = new GregorianCalendar();

        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday >= 2 && weekday <= 6)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void weekdayCalc()
    {

        totalMins = (lengthOfStayHours * 60) + lengthOfStayMinutes;
        weekdayGetCharge();
    }

    private void weekdayGetCharge()
    {

        if (totalMins <= 60)
            cost = Charge.WDUpTo1.getCharge();
        else if (totalMins <= 120)
            cost = Charge.WDUpTo2.getCharge();
        else if (totalMins <= 240)
            cost = Charge.WDUpTo4.getCharge();
        else if (totalMins <= 360)
            cost = Charge.WDUpTo6.getCharge();
        else if (totalMins <= 540)
            cost = Charge.WDUpTo9.getCharge();
        else if (totalMins <= 720)
            cost = Charge.WDUpTo12.getCharge();
        else
            cost = Charge.WDUpTo24.getCharge();
    }

    private void weekendGetCharge()
    {
        if (totalMins <= 60)
            cost = Charge.WEUpTo2.getCharge();
        else if (totalMins <= 360)
            cost = Charge.WEUpTo6.getCharge();
        else
            cost = Charge.WEUpTo24.getCharge();
    }

    public void weekendCalc()
    {
        totalMins = (lengthOfStayHours * 60) + lengthOfStayMinutes;
        weekendGetCharge();

    }

}
