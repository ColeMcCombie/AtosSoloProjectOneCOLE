
package CarParkExitBarrierSystem;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class OverstayedPrePaidParkingTransaction extends ParkingTicket
{
    private int ExpiryHour, ExpiryMin, ArriveHrs, ArriveMin, timestayed;

    String Reg;

    double cost, discount;

    public OverstayedPrePaidParkingTransaction(String Reg, int ArriveHrs, int ArriveMin, int ExpiryHour, int ExpiryMin)
    {
        super(Reg, ArriveHrs, ArriveMin, ExpiryHour, ExpiryMin);
        this.Reg = Reg;
        this.ArriveHrs = ArriveHrs;
        this.ArriveMin = ArriveMin;
        this.ExpiryHour = ExpiryHour;
        this.ExpiryMin = ExpiryMin;
        calculateCost();
        calculateDiscount();
        System.out.println(discount);
    }

    public OverstayedPrePaidParkingTransaction()
    {
    }

    public boolean isOverstayed()
    {
        Calendar c = new GregorianCalendar();

        if ((ExpiryHour < c.get(Calendar.HOUR_OF_DAY))
                || ((ExpiryHour == c.get(Calendar.HOUR_OF_DAY)) && (ExpiryMin < c.get(Calendar.MINUTE))))
        {
            return true;
        }
        else
            return false;

    }

    private void calculateCost()
    {

        ParkingTransaction trans = new ParkingTransaction(this.Reg, this.ArriveHrs, this.ArriveMin, this.ExpiryHour,
                this.ExpiryMin);
        trans.calcLOS();
        trans.calculateCostPrePaidOver(ArriveHrs, ArriveMin, ExpiryHour, ExpiryMin);
        cost = trans.getCost();

    }

    public double getCost()
    {
        return cost;
    }

    public double calculateDiscount()
    {
        discount = ((getCost()) * 0.90);
        return discount;
    }

}
