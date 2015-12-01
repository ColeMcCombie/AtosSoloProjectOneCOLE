package CarParkExitBarrierSystem;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CardDetailChecker
{

    public boolean checkCardDigits(String cardNo)
    {
        if (cardNo.length() == 16)
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
        int expiryMonth = sc.nextInt();
        System.out.println("Enter Expiry Year: (YYYY)");
        int expiryYear = sc.nextInt();

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
