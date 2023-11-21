package test;

import org.junit.Test;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
import org.junit.Before;

import System.Playground;

import java.beans.Transient;
import java.lang.annotation.Target;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestJUnitPlayground {
    Playground playground;
  
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final TextFromStandardInputStream systemIn = emptyStandardInputStream();

    @Before
    public void criandoPlayground()
    {
        playground = new Playground();
        playground.setName("Campo SBC");
        playground.setOwner("Ricardo");
        playground.setCancellationPeriod(10);
    
    }

    @Test
    public void testeRecuperaDadosPlayground()
    {
        assertEquals("Campo SBC",playground.getName());
        assertEquals("Ricardo",playground.getOwner());
        assertEquals(10,playground.getCancellationPeriod());
    }

    @Test
    public void testeAdicionandoLocation()
    {
        systemIn.provideLines("SP");
        playground.setLocation();
        assertEquals("SP",playground.getLocation());
    }

    @Test
    public void testeAdicionandoPrice()
    {
        systemIn.provideLines("50");
        playground.setPrice();
        assertEquals(50,playground.getPrice());
    }

    @Test
    public void testeAdicionandoStatusDisponivelEAlugandoECancelando()
    {
        systemIn.provideLines("available");
        playground.setStatus();
        assertEquals("available", playground.getStatus());

        playground.bookingTheSlot("Ricardo", "12:00", "20/10/2023");
        playground.cancelBooking("20/10/2023", "12:00");
    }

    @Test
    public void testeAdicionandoStatusIndisponivelETentandoAlugar()
    {
        systemIn.provideLines("not available");
        playground.setStatus();
        assertEquals("not available", playground.getStatus());

        playground.bookingTheSlot("Ricardo", "12:00", "20/10/2023");
        assertEquals("This playground isn't available yet", systemOutRule.getLog().trim());
    }
}
