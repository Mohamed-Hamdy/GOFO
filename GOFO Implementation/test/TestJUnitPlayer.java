package test;

import org.junit.Test;
import org.junit.BeforeEach;
import org.junit.AfterEach;
import org.junit.Before;
import System.Player;

import java.beans.Transient;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.lang.annotation.Target;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestJUnitPlayer {
    Player player;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();    
    
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
    

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
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
    public void testeCriandoTime()
    {
        player.createTeam("Ricardo");
        player.createTeam("Rodrigo");
        player.createTeam("Reinaldo");
        player.createTeam("Richard");
        player.createTeam("Rian");
    }

    @Test
    public void testeAdicionaEmailInbox()
    {
        player.addInbox("Maven não aceita Scanner.");
    }
    
    @Test
    public void testeVerificaEmailInbox()
    {
        player.viewInbox();
        Assert.assertEquals("Maven não aceita Scanner.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testeDepositaDinheiro()
    {
        player.getMoney(200);
        assertEquals(1200,player.getBalance());
    }
}
