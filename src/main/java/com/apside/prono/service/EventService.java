package com.apside.prono.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apside.prono.model.Event;
import com.apside.prono.repository.EventRepository;

@Service
public class EventService {

	
	@Autowired
	private EventRepository eRepo;
	

	@Transactional
	public void createEvent(Event event) {
		eRepo.save(event);
	}
	
	public Event getEventById(Long id) {
		return  eRepo.findById(id).get();
	}
	
	public Iterable<Event> getAllEvent() {
		return  eRepo.findAll();
	}
	
	@Transactional
	public Event updateEvent(Event event ) {
		return eRepo.save(event);
	}
	
	@Transactional
	public void deleteEventById(Long id) {
		eRepo.deleteById(id);
	}

}




