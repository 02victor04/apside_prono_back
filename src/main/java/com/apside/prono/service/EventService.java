package com.apside.prono.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apside.prono.exception.EventUnknownException;
import com.apside.prono.exception.InvalidEventDataException;
import com.apside.prono.model.Event;
import com.apside.prono.model.Player;
import com.apside.prono.repository.EventRepository;

@Service
public class EventService {


	@Autowired
	private EventRepository eRepo;


	@Transactional
	public Event createEvent(Event event) throws InvalidEventDataException{
		if ((event.getLabel() == null) 		||
				(event.getOpenDate() == null) 	||
				(event.getEventDate() == null)	||
				(event.getCloseDate() == null) 	||
				(event.getCoeff() < 1)) {
			throw new InvalidEventDataException();
		}else {
			return eRepo.save(event);
		}

	}

	public Event getEventById(Long id) throws EventUnknownException {
		Optional<Event> oevent = eRepo.findById(id);
		if(oevent.isPresent()) {
			return  eRepo.findById(id).get();
		}else {
			throw new EventUnknownException(id);
		}
	}

	public Iterable<Event> getAllEvent() {
		return  eRepo.findAll();
	}

	@Transactional
	public Event updateEvent(Event event, Long id ) throws EventUnknownException {
		Optional<Event> oevent = eRepo.findById(id);
		if(oevent.isPresent()) {
			Event eventUpdate = eRepo.findById(id).get();
			eventUpdate.setLabel(event.getLabel());
			eventUpdate.setOpenDate(event.getOpenDate());
			eventUpdate.setCloseDate(event.getCloseDate());
			eventUpdate.setEventDate(event.getEventDate());
			eventUpdate.setCoeff(event.getCoeff());
			eventUpdate.setContest(event.getContest());
			return eRepo.save(eventUpdate);
		}else {
			throw new EventUnknownException(id);
		}
	}

	@Transactional
	public void deleteEventById(Long id) {
		eRepo.deleteById(id);
	}

}




