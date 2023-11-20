package test;

import org.junit.Test;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.Before;
import System.Player;

import java.beans.Transient;
import java.lang.annotation.Target;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestJUnitPlayer {
    Player player;
  
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

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
    public void testeAdicionaEmailInbox()
    {
        player.addInbox("Maven não aceita Scanner.");
        player.addInbox("Teste");
        player.addInbox("Teste2");
    }
    
    @Test
    public void testeVerificaEmailInbox()
    {
        player.viewInbox();
        assertEquals("Maven não aceita Scanner.", systemOutRule.getLog().trim());
    }

    @Test
    public void testeDepositaDinheiro()
    {
        player.getMoney(200);
        assertEquals(1200,player.getBalance());
    }
}
