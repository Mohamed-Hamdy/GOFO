package test;

import org.junit.Test;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
import org.junit.Before;
import System.eWallet;

import java.beans.Transient;
import java.lang.annotation.Target;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class TestJUnitEWallet {
    eWallet carteira;
  
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final TextFromStandardInputStream systemIn = emptyStandardInputStream();

    @Before
    public void criandoCarteira()
    {
        carteira = new eWallet();
    }

    @Test
    public void testeAdicionandoBalancePorConsole()
    {
        systemIn.provideLines("2000");
        carteira.setBalance();
        assertEquals(2000,carteira.getBalance());
    }
    @Test
    public void testeAdicionandoBalancePorMetodo()
    {
        carteira.setBalance(4000);
        assertEquals(4000,carteira.getBalance());
    }
    @Test
    public void testeRetiraBalance()
    {
        carteira.setBalance(4000);
        carteira.withdraw(1000);
        assertEquals(3000,carteira.getBalance());
    }
    public void testedepositaBalance()
    {
        carteira.setBalance(4000);
        carteira.deposit(1000);
        assertEquals(5000,carteira.getBalance());
    }   
}
