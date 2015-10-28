package CarParkExitBarrierSystem;

public class OverstayedPrePaidParkingTransaction extends ParkingTicket
{

    public OverstayedPrePaidParkingTransaction(String RegNo, int ArrivalHrs, int ArrivalMin, int ExpiryHour,
            int ExpiryMin)
    {
        super(RegNo, ArrivalHrs, ArrivalMin, ExpiryHour, ExpiryMin);

    }

}
