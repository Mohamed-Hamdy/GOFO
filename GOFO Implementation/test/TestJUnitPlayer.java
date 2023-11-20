package test;

import org.junit.Test;
import org.junit.Before;
import System.Player;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestJUnitPlayer {
    Player player;
    InputStream sysInBackup = System.in; // backup System.in to restore it later
    InputStream in;
    
    @Before
    public void criandoPlayer()
    {
        player = new Player();
        player.setFName("Rogerio");
        player.setLName("Silva");
        player.setID(1);
        player.setPassword("1234");
        player.setEmail("rogerio@email.com");
        player.setPhone(40028922);
        player.setLocation("Rua Teste");
        player.setRule("Regra");
        player.setBalance(1000);
    }

    @Test
    public void testeVerificaDadosPlayer()
    {
        assertEquals("Rogerio Silva",player.getFullName());
        assertEquals(1,player.getID());
        assertEquals("1234",player.getPassword());
        assertEquals("rogerio@email.com",player.getEmail());
        assertEquals(40028922,player.getPhone());
        assertEquals("Regra",player.getRule());
        assertEquals(1000,player.getBalance());
        //Não existe um getLocation
    }
    
    @Test
    public void testePlayerEscrevendoBalance()
    {
        in = new ByteArrayInputStream("2000".getBytes());
        System.setIn(in);
        player.setBalance();
        assertEquals(2000,player.getBalance());
        System.setIn(sysInBackup);
    }
}
