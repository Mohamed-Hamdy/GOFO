package test;

import org.junit.Test;
import org.junit.Rule;
import org.junit.Before;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import System.Administrator;

import java.beans.Transient;
import java.lang.annotation.Target;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestJUnitAdministrator
{
    Administrator adm;
    Playground p1;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final TextFromStandardInputStream systemIn = emptyStandardInputStream();

    @Before
    public void testeCriandoAdmEPlayground()
    {
        adm = new Administrator();
        p1 = new Playground();

        p1.setName("CampoSP");
        p1.setOwner("Ricardo");
        p1.setCancellationPeriod(10);
    }

    @Test
    public void testeAdicionaRequestPlayground()
    {     
        adm.playgroundRequests(p1);

        systemIn.provideLines("yes");
        adm.approvePlayground();
    }

    @Test
    public void testePesquisaPorNome()
    {   
        systemIn.provideLines("yes");
        adm.approvePlayground();

        adm.searchByName("CampoSP");
    }

    @Test
    public void testePesquisaPorLugar()
    {   
        systemIn.provideLines("yes");
        adm.approvePlayground();

        systemIn.provideLines("SP");
        p1.setLocation();

        adm.searchByLocation("SP");
    }


    @Test
    public void testeRetornoLoginSenhaDefault()
    {   
        String email = adm.getEmail();
        String senha = adm.getPassword();

        assertEquals("admin@gmail.com",email);
        assertEquals("123",senha);
    }

    @Test
    public void testeDeletarPlaygroun()
    {
        adm.playgroundRequests(p1);

        systemIn.provideLines("yes");
        adm.approvePlayground();
        
        adm.deletePlayground("CampoSP");
    }

    @Test
    public void testeAdicionarReclamações()
    {
        adm.addComplaints("Esse código é muito estranho.");
        adm.addComplaints("Muito estranho mesmo.");
        adm.showComplaints();
        assertEquals("The complaints list is: \n1Esse código é muito estranho.\n2Muito estranho mesmo.", systemOutRule.getLog().trim());
    }
}
