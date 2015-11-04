package CarParkExitBarrierSystem;

public enum Charge
{

    // processes total value

    WDUpTo1(
            4.70),
    WDUpTo2(
            7.40),
    WDUpTo4(
            10.30),
    WDUpTo6(
            14.80),
    WDUpTo9(
            17.80),
    WDUpTo12(
            20.20),
    WDUpTo24(
            23.70),
    WEUpTo2(
            4.40),
    WEUpTo6(
            7.40),
    WEUpTo24(
            12.00);

    private double charge;

    private Charge(double charge)
    {
        this.charge = charge;
    }

    public double getCharge()
    {
        return charge;

    }

}
