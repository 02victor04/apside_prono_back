package com.apside.prono.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apside.prono.exception.InvalidContestDataException;
import com.apside.prono.model.Contest;
import com.apside.prono.repository.ContestRepository;

@Service
public class ContestService {

	@Autowired
	private ContestRepository cRepo;


	@Transactional
	public void createContest(Contest contest) throws InvalidContestDataException {
		if ( contest.getLabel() == null) {
			throw new InvalidContestDataException();
		}else {
			cRepo.save(contest);
		}
	}

	public Contest getContestById(Long id) {
		return  cRepo.findById(id).get();
	}

	public Iterable<Contest> getAllContest() {
		return  cRepo.findAll();
	}

	@Transactional
	public Contest updateContest(Contest contest, Long id )  throws InvalidContestDataException {

		if ( contest.getLabel() == null) {
			throw new InvalidContestDataException();
		}else {
			Contest contestUpdate = cRepo.findById(id).get();
			contestUpdate.setLabel(contest.getLabel());
			return cRepo.save(contestUpdate);
		}
	}

	@Transactional
	public void deleteContestById(Long id) {
		cRepo.deleteById(id);
	}

}




