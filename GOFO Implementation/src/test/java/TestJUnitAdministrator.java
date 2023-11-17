package System;

import org.junit.Test;
import org.junit.Before;

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
        String senha = adm.getPasswrod();

        Assert.assertEquals("admin@gmail.com",email);
        Assert.assertEquals("123",senha);
    }

    //@Test
    //public void teste
}
