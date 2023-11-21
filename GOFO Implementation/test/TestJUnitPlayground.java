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
    public void testeAdicionandoStatusInvalido()
    {
        systemIn.provideLines("nope");
        playground.setLocation();
        assertEquals("invalid input please enter ( available or not available)", systemOutRule.getLog().trim());
        systemIn.provideLines("available");
        assertEquals("available", playground.getStatus());
    }
}
