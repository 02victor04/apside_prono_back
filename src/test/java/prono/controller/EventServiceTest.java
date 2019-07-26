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
import com.apside.prono.repository.EventRepository;
import com.apside.prono.repository.PlayerRepository;
import com.apside.prono.service.EventService;
import com.apside.prono.service.PlayerService;

@RunWith(SpringJUnit4ClassRunner.class)
public class EventServiceTest{
	
	@Mock
	private EventRepository eRepo;
	
	@InjectMocks
	private EventService eServ;

	@Before
	public void setup() {MockitoAnnotations.initMocks(this);}
	
	private Contest contestMock = new Contest(1L, "Coupe du monde de test"); 
	
	private Event eventMock = new Event(1L,"france vs test",
			new Date(2019-04-11),
			new Date(2019-04-01),
			new Date(2019-04-06),
			1.12,
			contestMock);
	
	@Test
	public void CanGetAllEvent() throws Exception {
		List<Event> leventMock = new ArrayList<>();
		leventMock.add(eventMock);
		
		when(eRepo.findAll()).thenReturn(leventMock);
		
		Iterable<Event> iEvent = eServ.getAllEvent();
		List<Event> levent = new ArrayList<>();
		for (Event event : iEvent) {
			levent.add(event);
		}
		
		assertEquals("france vs test", levent.get(0).getLabel());
	}
	
	
	
	
	
	@Test
	public void CanGetOneEventById() throws Exception {
		Optional<Event> oEvent = Optional.of(eventMock);
		when(eRepo.findById(1L)).thenReturn(oEvent);
		
		Event event = eServ.getEventById(eventMock.getId());
		
		assertEquals("france vs test",event.getLabel() );
	}
	
	@Test
	public void CanUpdateEventById() throws Exception {
		Optional<Event> oevent = Optional.of(eventMock);
		
		Event eventUpdate = new Event();
		eventUpdate.setLabel("Coupe du monde" );
		eventUpdate.setCoeff(1.12);
		eventUpdate.setId(1L);
		
		when(eRepo.findById(1L)).thenReturn(oevent);
		
		eServ.updateEvent(eventUpdate,eventUpdate.getId());
		

		verify(eRepo, times(1)).findById(eventUpdate.getId());
	}
	
	@Test
	public void CanCreateEvent() throws Exception {

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
	
	@Test(expected = EventUnknownException.class)
	public void cannotDeletedEvent() throws Exception {
		Event event = new Event();
		event.setId(55L);
		eServ.updateEvent(event, event.getId());
	}
}
