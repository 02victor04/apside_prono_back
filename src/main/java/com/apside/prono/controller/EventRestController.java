package com.apside.prono.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.apside.prono.exception.InvalidEventDataException;
import com.apside.prono.model.Event;
import com.apside.prono.service.EventService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EventRestController {


	@Autowired
	private EventService eServ;



	public EventRestController() {
		super();
	}



	public EventRestController(EventService eServ) {
		super();
		this.eServ = eServ;
	}



	@GetMapping(produces = "application/json", path="/api/event/{id}")
	public Event getById(@PathVariable Long id) {
		return eServ.getEventById(id);
	}

	@GetMapping(produces = "application/json", path="/api/allevent")
	public Iterable<Event> getAllEvent() {
		return eServ.getAllEvent();
	}

	@PostMapping(consumes = "application/json", produces="application/json", path="/api/event")
	public ResponseEntity<Event> create(@RequestBody Event event, UriComponentsBuilder uriBuilder) throws InvalidEventDataException {
		if ( event == null ) {
			throw new InvalidEventDataException();
		}else {
			eServ.createEvent(event);
			URI location = uriBuilder.path("/api/event/{id}").buildAndExpand(event.getId()).toUri();
			return ResponseEntity.created(location).body(event);
		}
	}

	@PutMapping(consumes = "application/json", produces = "application/json", path = "/api/event/{id}")
	public Event modifyEvent(@PathVariable Long id, @RequestBody Event event) throws InvalidEventDataException {
		if ( event == null ) {
			throw new InvalidEventDataException();
		}else {
			return eServ.updateEvent(event,id);
		}
	}

	@DeleteMapping(path = "/api/event/{id}")
	public void deleteEventById(@PathVariable long id) {
		eServ.deleteEventById(id);
	}
}


