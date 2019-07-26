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

import com.apside.prono.exception.ContestUnknownException;
import com.apside.prono.exception.EventUnknownException;
import com.apside.prono.exception.InvalidContestDataException;
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
public class ContestServiceTest{
	
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
	
	@Test
	public void CanUpdateContestById() throws Exception {
		Optional<Contest> ocontest = Optional.of(contestMock);
		
		Contest contest = new Contest();
		contest.setLabel("Coupe du monde");
		contest.setId(1L);
		
		when(cRepo.findById(1L)).thenReturn(ocontest);
		
		cServ.updateContest(contest,1L);

		verify(cRepo,times(1)).findById(contest.getId());
	}
	
	@Test
	public void CanCreateContest() throws Exception {

		Contest contestCreated = new Contest();
		contestCreated.setLabel("Coupe du monde" );

		when(cRepo.save(contestCreated)).thenReturn(contestMock);
		
		Contest contest = cServ.createContest(contestCreated);
		
		assertEquals("coupe du monde de bloublou", contest.getLabel());
		
	}
	
	@Test
	public void CanDeleteContest() throws Exception {
		Optional<Contest> ocontest = Optional.of(contestMock);
		Contest contestDelete = new Contest();
		contestDelete.setId(3L);
		
		
		when(cRepo.findById(contestDelete.getId())).thenReturn(ocontest);
		
		cServ.deleteContestById(contestDelete.getId());
		
		verify(cRepo,times(1)).deleteById(3L);
	}
	
	@Test(expected = InvalidContestDataException.class)
	public void cannotCreateContest() throws Exception {
		
		Contest contest = new Contest();
		cServ.createContest(contest);
		
	}
	
	@Test(expected = ContestUnknownException.class)
	public void cannotGetPlayerById() throws Exception {
		Contest contest = new Contest();
		contest.setId(79L);
		cServ.getContestById(contest.getId());
	}
	
	@Test(expected = ContestUnknownException.class)
	public void cannotUpdateContest() throws Exception {
		Contest bddContest = new Contest();
		bddContest.setLabel(null);
		bddContest.setId(1L);
		
		cServ.updateContest(bddContest,bddContest.getId());
	}
	
	@Test(expected = ContestUnknownException.class)
	public void cannotDeletedContest() throws Exception {
		Contest contest = new Contest();
		contest.setId(55L);
		cServ.updateContest(contest, contest.getId());
	}
	
}
