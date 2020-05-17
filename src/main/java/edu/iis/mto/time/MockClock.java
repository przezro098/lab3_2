package edu.iis.mto.time;

import org.joda.time.DateTime;

public class MockClock implements Clock {

    private DateTime time = new DateTime();

    @Override
    public DateTime now()
    {
        return time;
    }

    public void setTime(DateTime time)
    {
        this.time = time;
    }


}
