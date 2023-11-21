package test;

import org.junit.Test;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
import org.junit.Before;
import System.PlayGroundSchedule;

import java.beans.Transient;
import java.lang.annotation.Target;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestJUnitPlaygroundScedule {
    PlayGroundSchedule schedule;
  
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final TextFromStandardInputStream systemIn = emptyStandardInputStream();

    @Before
    public void criandoCalendario()
    {
        schedule = new PlayGroundSchedule();
    }

    @Test
    public void testeAdiconandoCalendario()
    {
        systemIn.provideLines("12","22");
        schedule.setschedule();
        assertEquals("12",schedule.getBegin());
        assertEquals("22",schedule.getEnd());
    }
}
