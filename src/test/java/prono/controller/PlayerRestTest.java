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

import com.apside.prono.exception.InvalidPlayerDataException;
import com.apside.prono.exception.PlayerUnknownException;
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
		List<Player> lplayerMock = new ArrayList<>();
		lplayerMock.add(playerMock);
		
		when(pRepo.findAll()).thenReturn(lplayerMock);
		
		Iterable<Player> iplayer = pServ.getAllPlayer();
		List<Player> lplayer = new ArrayList<>();
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
	
//	@Test
//	public void CanUpdatePlayerById() throws Exception {
//		
//		Optional<Player> oplayer = Optional.of(playerMock);
//		Player playerUpdate = new Player();
//		playerUpdate.setFirstName("test");
//		playerUpdate.setLastName("blibli");
//		playerUpdate.setSubscriptionDate(new Date());
//		
//		when(pRepo.findById(1L)).thenReturn(oplayer);
//		
//		pServ.updatePlayer(playerUpdate,1L);
//		Player player = pRepo.findById(1L).get();
//		
//		
//		assertEquals("test", player.getFirstName());
//	}
	
	@Test
	public void CanCreatePlayer() throws Exception {

		Player playerCreated = new Player();
		playerCreated.setFirstName("toto");
		playerCreated.setLastName("bloublou");
		playerCreated.setMail("dhhghdghdhgd");
		
		
		when(pRepo.save(playerCreated)).thenReturn(playerMock);
		
		Player player = pServ.createPlayer(playerCreated);
		
		assertEquals("test", player.getFirstName());
		
	}

	
	
	@Test
	public void CanDeletePlayer() throws Exception {
		Optional<Player> oplayer = Optional.of(playerMock);
		Player playerDelete = new Player();
		playerDelete.setId(3L);
		
		
		when(pRepo.findById(playerDelete.getId())).thenReturn(oplayer);
		
		pServ.deletePlayerById(playerDelete.getId());
		
		verify(pRepo,times(1)).deleteById(3L);
	}
	
	@Test(expected = InvalidPlayerDataException.class)
	public void cannotCreatePlayer() throws Exception {
		
		Player player = new Player();
		pServ.createPlayer(player);
		
	}
	
	@Test(expected = PlayerUnknownException.class)
	public void cannotGetPlayerById() throws Exception {
		Player player = new Player();
		player.setId(79L);
		pServ.getPlayerById(player.getId());
	}
	
	@Test(expected = PlayerUnknownException.class)
	public void cannotUpdatePlayer() throws Exception {
		Player bddPlayer =  new Player();
		bddPlayer.setFirstName("bloublou");
		bddPlayer.setId(1L);
		
		pServ.updatePlayer(bddPlayer,bddPlayer.getId());
	}
	
}
