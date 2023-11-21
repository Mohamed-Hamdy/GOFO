package test;

import org.junit.Test;
import org.junit.Rule;
import org.junit.Before;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import System.PlaygroundOwner;
import System.Playground;
import System.eWallet;

import java.beans.Transient;
import java.lang.annotation.Target;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;


public class TestJUnitPlaygroundOwner {
    PlaygroundOwner owner;
    Playground p1;
    eWallet bal;
  
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final TextFromStandardInputStream systemIn = emptyStandardInputStream();

    @Before
    public void criandoDonoEPlaygrounds()
    {
        owner = new PlaygroundOwner();
        
        owner.setFName("Ricardo");
        owner.setLName("Silva");
        owner.setID(1);
        owner.setPassword("1234");
        owner.setEmail("ricardo@email.com");
        owner.setPhone(40028922);
        owner.setLocation("SP");
        owner.setRule("Regra");

        p1 = new Playground();

        p1.setName("Campo SP");
        p1.setOwner("Ricardo");
        p1.setCancellationPeriod(10);
        
        systemIn.provideLines("available");
        p1.setStatus();

        systemIn.provideLines("SP");
        p1.setLocation();

        systemIn.provideLines("50");
        p1.setPrice();

        bal.setBalance(1000);
    }

    @Test
    public void testeVerificaDadosOwner()
    {
        assertEquals("Ricardo Silva",owner.getFullName());
        assertEquals(1,owner.getID());
        assertEquals("1234",owner.getPassword());
        assertEquals("ricardo@email.com",owner.getEmail());
        assertEquals(40028922,owner.getPhone());
        assertEquals("Regra",owner.getRule());
    }

    @Test
    public void testeAdicionaPlayground()
    {
        owner.addPlayground(p1);
        assertTrue(owner.existPlayground("Campo SP"));
    }

    @Test
    public void testeAdicionaBalance()
    {
        owner.setBalance(bal);
        assertEquals(1000,owner.getMyBalance());
    }

    // @Test
    // public void testeListaPlayground()
    // {
    //     owner.getListofPlayground();
    //     assertEquals("Campo SP",systemOutRule.getLog().trim());
    // }

    // @Test
    // public void testeAdiconaMensagem()
    // {
    //     owner.addRecieveMsg("Olha playground");
    //     owner.displayRecieveMsg();

    //     assertEquals("Olha playground",systemOutRule.getLog().trim());
    // }

    // @Test
    // public void testePagamento()
    // {
    //     owner.payMoney("Campo SP", 3);
    //     assertEquals(850,owner.getMyBalance());
    // }
}
