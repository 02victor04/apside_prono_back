package prono.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.apside.prono.model.Player;
import com.apside.prono.repository.PlayerRepository;
import com.apside.prono.service.PlayerService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlayerRestTest{
	
	@Mock
	private PlayerRepository pRepo;
	
	@InjectMocks
	private PlayerService pServ;

	@Before
	public void setup() {MockitoAnnotations.initMocks(this);}
	
	private Player playerMock = new Player(1L, "test", "Bloblou", "hdgd@gfgsf.com", new Date());
	
	@Test
	public void CanGetAllPlayer() throws Exception {
		List<Player> lplayerMock = new ArrayList<Player>();
		lplayerMock.add(playerMock);
		
		when(pRepo.findAll()).thenReturn(lplayerMock);
		
		Iterable<Player> iplayer = pServ.getAllPlayer();
		List<Player> lplayer = new ArrayList<Player>();
		for (Player player : iplayer) {
			lplayer.add(player);
		}
		
		assertEquals("test", lplayer.get(0).getFirstName());
	}
	
	
	
	
	
	@Test
	public void CanGetOnePlayerById() throws Exception {
		Optional<Player> oplayer = Optional.of(playerMock);
		when(pRepo.findById(1L)).thenReturn(oplayer);
		
		Player player = pServ.getPlayerById(1L);
		
		assertEquals("test", player.getFirstName());
	}
	
	@Test
	public void CanUpdatePlayerById() throws Exception {
		
		Optional<Player> oplayer = Optional.of(playerMock);
		Player playerUpdate = new Player();
		playerUpdate.setFirstName("test");
		playerUpdate.setLastName("blibli");
		playerUpdate.setSubscriptionDate(new Date());
		
		when(pRepo.findById(1L)).thenReturn(oplayer);
		
		pServ.updatePlayer(playerUpdate,1L);
		Player player = pRepo.findById(1L).get();
		
		
		assertEquals("test", player.getFirstName());
	}
	
	@Test
	public void CanCreatePlayerById() throws Exception {
		
		Optional<Player> oplayer = Optional.of(playerMock);
		Player playerCreated = new Player();
		playerCreated.setFirstName("test");
		
		
		when(pRepo.findById(1L)).thenReturn(oplayer);
		
		
	}
}
