package test;

import org.junit.Test;
import org.junit.Before;
import System.userProfile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestJUnitUserProfile
{
    userProfile player;
    @Before
    public void testeCriandoPlayer()
    {
        player = new userProfile();
    }

    @Test
    public void testeInserirDadosUser()
    {   
        player.setFName("Rogerio");
        player.setLName("Silva");
        player.setID(1);
        player.setPassword("1234");
        player.setEmail("rogerio@email.com");
        player.setPhone(40028922);
        player.setLocation("Rua Teste");
        player.setRule("Regra");
    
        assertEquals("Rogerio Silva",player.getFullName());
        assertEquals(1,player.getID());
        assertEquals("1234"1"",player.getPassword());
        assertEquals("rogerio@email.com",player.getEmail());
        assertEquals(40028922,player.getPhone());
        assertEquals("Rua Teste",player.getLocation());
        assertEquals("Regra",player.getRule());
    }
}
