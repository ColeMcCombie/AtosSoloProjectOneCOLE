package CarParkExitBarrierSystem;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CardDetailChecker
{

    public boolean checkCardDigits(String CardNo)
    {
        if (CardNo.length() == 16)
        {
            return true;
        }
        else
            return false;
    }

    public boolean checkCardExpiry(int expiryMonth, int expiryYear)
    {
        Scanner sc = new Scanner(System.in);
        Calendar c = new GregorianCalendar();
        System.out.println("Enter Expiry Month: (mm)");
        expiryMonth = sc.nextInt();
        System.out.println("Enter Expiry Year: (YYYY)");
        expiryYear = sc.nextInt();

        if (expiryYear >= c.get(Calendar.YEAR)
                || expiryYear == c.get(Calendar.YEAR) && expiryMonth >= (c.get(Calendar.MONTH) + 1))
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
