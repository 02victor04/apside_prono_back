package com.apside.prono.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apside.prono.exception.ContestUnknownException;
import com.apside.prono.exception.InvalidContestDataException;
import com.apside.prono.exception.PlayerUnknownException;
import com.apside.prono.model.Contest;
import com.apside.prono.model.Player;
import com.apside.prono.repository.ContestRepository;

@Service
public class ContestService {

	@Autowired
	private ContestRepository cRepo;


	@Transactional
	public Contest createContest(Contest contest) throws InvalidContestDataException {
		if (contest.getLabel() == null) {
			throw new InvalidContestDataException();
		}else {
			return cRepo.save(contest);
		}
	}

	public Contest getContestById(Long id) throws ContestUnknownException {
		Optional<Contest> ocontest = cRepo.findById(id);
		if(ocontest.isPresent()) {
			return  ocontest.get();
		}else {
			throw new ContestUnknownException(id);
		}

	}

	public Iterable<Contest> getAllContest() {
		return  cRepo.findAll();
	}

	@Transactional
	public Contest updateContest(Contest contest, Long id )  throws ContestUnknownException {
		Optional<Contest> ocontest = cRepo.findById(id);
		if ( ocontest.isPresent()) {
			Contest contestUpdate = ocontest.get();
			contestUpdate.setLabel(contest.getLabel());
			return cRepo.save(contestUpdate);

		}else {
			throw new ContestUnknownException(id);
		}
	}

	@Transactional
	public void deleteContestById(Long id) {
		Optional<Contest> ocontest = cRepo.findById(id);
		if(ocontest.isPresent()) {
			cRepo.deleteById(id);
		}else {
			throw new ContestUnknownException(id); 
		}
	}
}




