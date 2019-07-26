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

import com.apside.prono.exception.EventUnknownException;
import com.apside.prono.exception.InvalidEventDataException;
import com.apside.prono.exception.InvalidPlayerDataException;
import com.apside.prono.exception.PlayerUnknownException;
import com.apside.prono.model.Contest;
import com.apside.prono.model.Event;
import com.apside.prono.model.Player;
import com.apside.prono.repository.ContestRepository;
import com.apside.prono.repository.EventRepository;
import com.apside.prono.repository.PlayerRepository;
import com.apside.prono.service.ContestService;
import com.apside.prono.service.EventService;
import com.apside.prono.service.PlayerService;

@RunWith(SpringJUnit4ClassRunner.class)
public class ContestRestTest{
	
	@Mock
	private ContestRepository cRepo;
	
	@InjectMocks
	private ContestService cServ;

	@Before
	public void setup() {MockitoAnnotations.initMocks(this);}
	
	private Contest contestMock = new Contest(1L, "coupe du monde de bloublou");
	
	@Test
	public void CanGetAllContest() throws Exception {
		List<Contest> lcontestMock = new ArrayList<>();
		lcontestMock.add(contestMock);
		
		when(cRepo.findAll()).thenReturn(lcontestMock);
		
		Iterable<Contest> iContest = cServ.getAllContest();
		List<Contest> lcontest = new ArrayList<>();
		for (Contest contest : iContest) {
			lcontest.add(contest);
		}
		
		assertEquals("coupe du monde de bloublou", lcontest.get(0).getLabel());
	}
	
	
	
	
	
	@Test
	public void CanGetOneContestById() throws Exception {
		Optional<Contest> oContest = Optional.of(contestMock);
		when(cRepo.findById(1L)).thenReturn(oContest);
		
		Contest contest = cServ.getContestById(contestMock.getId());
		
		assertEquals("coupe du monde de bloublou",contest.getLabel() );
	}
	
//	@Test
//	public void CanUpdateEventById() throws Exception {
//		Optional<Event> oevent = Optional.of(eventMock);
//		
//		Event eventUpdate = new Event();
//		eventUpdate.setLabel("Coupe du monde" );
//		eventUpdate.setCoeff(1.12);
//		eventUpdate.setId(1L);
//		
//		when(eRepo.findById(1L)).thenReturn(oevent);
//		
//		eServ.updateEvent(eventUpdate,eventUpdate.getId());
//		Event event = eRepo.findById(eventUpdate.getId()).get();
//
//		assertEquals("france vs test", event.getLabel());
//	}
	
	@Test
	public void CanCreateContest() throws Exception {

		Event eventCreated = new Event();
		eventCreated.setLabel("Coupe du monde" );
		eventCreated.setCoeff(1.12);
		eventCreated.setId(1L);
		eventCreated.setCloseDate(new Date());
		eventCreated.setEventDate(new Date());
		eventCreated.setOpenDate(new Date());
		
		
		when(eRepo.save(eventCreated)).thenReturn(eventMock);
		
		Event event = eServ.createEvent(eventCreated);
		
		assertEquals("france vs test", event.getLabel());
		
	}
	
	@Test
	public void CanDeleteEvent() throws Exception {
		Optional<Event> oevent = Optional.of(eventMock);
		Event eventDelete = new Event();
		eventDelete.setId(3L);
		
		
		when(eRepo.findById(eventDelete.getId())).thenReturn(oevent);
		
		eServ.deleteEventById(eventDelete.getId());
		
		verify(eRepo,times(1)).deleteById(3L);
	}
	
	@Test(expected = InvalidEventDataException.class)
	public void cannotCreateEvent() throws Exception {
		
		Event  event = new Event();
		eServ.createEvent(event);
		
	}
	
	@Test(expected = EventUnknownException.class)
	public void cannotGetPlayerById() throws Exception {
		Event event = new Event();
		event.setId(79L);
		eServ.getEventById(event.getId());
	}
	
	@Test(expected = EventUnknownException.class)
	public void cannotUpdatePlayer() throws Exception {
		Event bddEvent =  new Event();
		bddEvent.setLabel("bloublou");
		bddEvent.setId(1L);
		
		eServ.updateEvent(bddEvent,bddEvent.getId());
	}
	
	
}
