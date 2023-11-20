package test;

import org.junit.Test;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
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

    @Rule
    public final TextFromStandardInputStream systemIn = emptyStandardInputStream();

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
        player.viewInbox();
        assertEquals("Message No.1: Maven não aceita Scanner.", systemOutRule.getLog().trim());
    }
    
    @Test
    public void testeVerificaEmailInboxVazio()
    {
        player.viewInbox();
        assertEquals("Your Inbox is Empty", systemOutRule.getLog().trim());
    }

    @Test
    public void testeAdicionaMultiplosEmailsInbox()
    {
        player.addInbox("Maven não aceita Scanner.");
        player.addInbox("Email de teste 1.");
        player.addInbox("Email de teste 2.");
        player.viewInbox();
        assertEquals("Message No.1: Maven não aceita Scanner.\nMessage No.2: Email de teste 1.\nMessage No.3: Email de teste 2.", systemOutRule.getLog().trim());
    }

    @Test
    public void testeDepositaDinheiro()
    {
        player.getMoney(200);
        assertEquals(1200,player.getBalance());
    }
    @Test
    public void testeGetCancelPlayground()
    {
        player.bookingSlots("12:00","Campo SBC");
        player.CancelBooking("Campo SBC", "12:00");
    }
    @Test
    public void testeCriandoTime()
    {
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        Player player5 = new Player();

        player.createTeam(player2);
        player.createTeam(player3);
        player.createTeam(player4);
        player.createTeam(player5);
    }
    @Test
    public void testeInserindoBalance()
    {
        systemIn.provideLines(4000);
        player.setBalance();
    }
}
