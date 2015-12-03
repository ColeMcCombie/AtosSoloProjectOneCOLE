package CarParkExitBarrierSystem;

public class RegistrationChecker
{

    public boolean checkRegistrationNumber(String reg)
    {
        if (reg.matches("[0-9a-zA-Z]+") && reg.length() == 7)
        {
            return true;
        }
        else
            return false;
    }

}
