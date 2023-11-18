package test;

import org.junit.Test;
import org.junit.Before;
import UI.SystemUI;
import System.Administrator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestJUnitAdministrator
{
    Administrator adm;
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
        adm.approvePlayground();
    }
}
