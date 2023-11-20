package test;

import org.junit.Test;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
import org.junit.Before;
import System.Administrator;
import System.Player;
import System.Playground;

import java.beans.Transient;
import java.lang.annotation.Target;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestJUnitAdministrator
{
    Administrator adm;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final TextFromStandardInputStream systemIn = emptyStandardInputStream();

    @Before
    public void testeCriandoAdm()
    {
        adm = new Administrator();
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
    public void testeAprovarPlayground()
    {
        systemIn.provideLines("yes");
        adm.approvePlayground();
    }

    @Test
    public void testeMostraPlayground()
    {
        adm.displayAllPlaygrounds();
    }

    @Test
    public void testeBuscarPlaygroundPorNome()
    {
        adm.searchByName("Campo SBC");
    }

    @Test
    public void testeBuscarPlaygroundPorLugar()
    {
        adm.searchByLocation("SP");
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
