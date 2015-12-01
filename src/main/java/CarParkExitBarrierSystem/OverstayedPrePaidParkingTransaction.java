
package CarParkExitBarrierSystem;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class OverstayedPrePaidParkingTransaction extends ParkingTicket
{
    private int expiryHour, expiryMinute, arriveHour, arriveMinute;

    String reg;

    double cost, discount;

    public OverstayedPrePaidParkingTransaction(String reg, int arriveHour, int arriveMinute, int expiryHour,
            int expiryMinute)
    {
        super(reg, arriveHour, arriveMinute, expiryHour, expiryMinute);
        this.reg = reg;
        this.arriveHour = arriveHour;
        this.arriveMinute = arriveMinute;
        this.expiryHour = expiryHour;
        this.expiryMinute = expiryMinute;

    }

    public void createTransaction()
    {
        calculateCost();
        calculateDiscount();
        System.out.println(discount);
    }

    public OverstayedPrePaidParkingTransaction()
    {
        // Parking transaction requires access to isOverstayed method before passing into constructor
    }

    public boolean isOverstayed()
    {
        Calendar c = new GregorianCalendar();

        if ((expiryHour < c.get(Calendar.HOUR_OF_DAY))
                || ((expiryHour == c.get(Calendar.HOUR_OF_DAY)) && (expiryMinute < c.get(Calendar.MINUTE))))
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
