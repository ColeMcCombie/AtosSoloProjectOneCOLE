package CarParkExitBarrierSystem;

import java.util.Calendar;

public class TimeTracker
{
    public static Calendar getCurrentTime(int dayOfWeek)
    {
        Calendar tranTime = Calendar.getInstance();
        tranTime.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        System.out.println("Tran Time is " + tranTime.get(Calendar.YEAR) + tranTime.get(Calendar.MONTH)
                + tranTime.get(Calendar.DAY_OF_MONTH) + tranTime.get(Calendar.HOUR_OF_DAY)
                + tranTime.get(Calendar.MINUTE));
        return tranTime;
    }
}
