package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import CarParkExitBarrierSystem.ParkingTicket;

public class TestParkingTicket
{

    ParkingTicket ticket = new ParkingTicket("AA22BLD", 10, 05);

    @Test
    public void testGetRegNo()
    {
        System.out.println(ticket.getRegNo());
        assertEquals("AA22BLD", ticket.getRegNo());
    }

    public void testGetArrivalHrs()
    {
        System.out.println(ticket.getArrivalHrs());
        assertEquals(10, ticket.getArrivalHrs());
    }

    public void testGetArrivalMins()
    {
        System.out.println(ticket.getArrivalMin());
        assertEquals(05, ticket.getArrivalMin());
    }

    public void testGetPrePaid()
    {
        System.out.println(ticket.getPrePaid());
        assertEquals(false, ticket.getPrePaid());
    }

}
