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

        p1.setName("CampoSP");
        p1.setOwner("Ricardo");
        p1.setCancellationPeriod(10);

        bal = new eWallet();
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
        assertTrue(owner.existPlayground("CampoSP"));
    }

    @Test
    public void testeAtualizaPlaygroundNome()
    {
        owner.addPlayground(p1);

        systemIn.provideLines("1","NovoCampoSP");
        owner.updatePlaygroundName("CampoSP");
        
        assertTrue(owner.existPlayground("NovoCampoSP"));
    }

    @Test
    public void testeAtualizaPlaygroundLocation()
    {
        owner.addPlayground(p1);

        systemIn.provideLines("2","SBC");
        owner.updatePlaygroundName("CampoSP");
        
        assertEquals("SBC",p1.getLocation());
    }

    @Test
    public void testeAtualizaPlaygroundPrice()
    {
        owner.addPlayground(p1);

        systemIn.provideLines("3","70");
        owner.updatePlaygroundName("CampoSP");
        
        assertEquals(70,p1.getPrice());
    }

    @Test
    public void testeAtualizaPlaygroundStatus()
    {
        owner.addPlayground(p1);

        systemIn.provideLines("4","not available");
        owner.updatePlaygroundName("CampoSP");
        
        assertEquals("not available",p1.getStatus());
    }

    @Test
    public void testeAtualizaPlaygroundStatusInvalido()
    {
        owner.addPlayground(p1);

        systemIn.provideLines("4","1notavailable","not available");
        owner.updatePlaygroundName("CampoSP");
        
        assertEquals("not available",p1.getStatus());
    }

    @Test
    public void testeAtualizaPlaygroundBookTime()
    {
        owner.addPlayground(p1);

        systemIn.provideLines("5","10","22");
        owner.updatePlaygroundName("CampoSP");
        
        assertEquals(10,p1.getSlotsBegin());
        assertEquals(22,p1.getSlotsEnd());
    }

    @Test
    public void testeAdicionaBalance()
    {
        owner.setBalance(bal);
        assertEquals(1000,owner.getMyBalance());
    }

    @Test
    public void testeListaPlayground()
    {
        owner.addPlayground(p1);
        owner.getListofPlayground();
        assertEquals("CampoSP",systemOutRule.getLog().trim());
    }

    @Test
    public void testeAdiconaMensagem()
    {
        owner.addRecieveMsg("Olha playground");
        owner.displayRecieveMsg();

        assertEquals("Olha playground",systemOutRule.getLog().trim());
    }

    @Test
    public void testePagamento()
    {
        owner.addPlayground(p1);
        
        systemIn.provideLines("50");
        p1.setPrice();
        
        owner.payMoney("CampoSP", 3);
        assertEquals(850,owner.getMyBalance());
    }
}
