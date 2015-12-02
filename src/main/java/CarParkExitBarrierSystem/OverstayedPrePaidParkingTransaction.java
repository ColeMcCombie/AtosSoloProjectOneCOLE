
package CarParkExitBarrierSystem;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class OverstayedPrePaidParkingTransaction extends ParkingTicket
{
    private int expiryHour, expiryMinute, arriveHour, arriveMinute;

    int expiryMonth, expiryYear;

    String reg, cardNo;

    double cost, discount;

    public OverstayedPrePaidParkingTransaction(String reg, int arriveHour, int arriveMinute, int expiryHour,
            int expiryMinute, String cardNo)
    {
        super(reg, arriveHour, arriveMinute, expiryHour, expiryMinute);
        this.reg = reg;
        this.arriveHour = arriveHour;
        this.arriveMinute = arriveMinute;
        this.expiryHour = expiryHour;
        this.expiryMinute = expiryMinute;
        this.cardNo = cardNo;

    }

    public void createTransaction()
    {
        calculateCost();
        calculateDiscount();
        System.out.println(
                "You have overstayed for the ammount you had prepaid, the ammount you are due is £" + discount);
        payForTicket(reg, "O", expiryMonth, expiryYear);
    }

    public void payForTicket(String reg, String transType, int expiryMonth, int expiryYear)
    {
        CardDetailChecker checker = new CardDetailChecker();
        CentralAuthWriter caw = new CentralAuthWriter();

        Scanner sc = new Scanner(System.in);

        boolean cardAccepted = false;
        do
        {

            boolean cardDateAccepted = false;
            System.out.println("Please input Card Number: ");
            cardNo = sc.nextLine();
            if (checker.checkCardDigits(cardNo))
            {
                System.out.println("Card Number accepted! ");
                do
                {
                    if (checker.checkCardExpiry())
                    {
                        cardDateAccepted = true;
                        cardAccepted = true;
                    }
                    else
                    {
                        if (cardDateAccepted == false)
                        {

                            caw.writeToCentralAuth(false, "card expired", "O", cardNo, expiryMonth, expiryYear);
                        }
                        else
                        {

                            caw.writeToCentralAuth(false, "invalid card number", "O", cardNo, expiryMonth, expiryYear);
                        }
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

        caw.writeToCentralAuth(true, "n/a", transType, cardNo, checker.getExpiryMonth(), checker.getExpiryYear());

    }

    public OverstayedPrePaidParkingTransaction()
    {
        // Parking transaction requires access to isOverstayed method before passing into constructor
    }

    public boolean isOverstayed()
    {
        Calendar c = new GregorianCalendar();

        if ((expiryHour > c.get(Calendar.HOUR_OF_DAY))
                || ((expiryHour == c.get(Calendar.HOUR_OF_DAY)) && (expiryMinute > c.get(Calendar.MINUTE))))
        {
            return true;
        }
        else
            return false;

    }

    private void calculateCost()
    {

        ParkingTransaction trans = new ParkingTransaction(this.reg, this.arriveHour, this.arriveMinute, this.expiryHour,
                this.expiryMinute);
        trans.calcLengthOfStay();
        trans.calculateCostPrePaidOver(expiryHour, expiryMinute);
        cost = trans.getCost();

    }

    public double getCost()
    {
        return cost;
    }

    public double calculateDiscount()
    {
        discount = (getCost()) * 0.90;
        return discount;
    }

}
