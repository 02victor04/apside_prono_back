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

import com.apside.prono.model.Contest;
import com.apside.prono.service.ContestService;

@CrossOrigin(origins = "http//localhost:4200")
@RestController
public class ContestRestController {

	@Autowired
	private ContestService cServ;
	
	

	public ContestRestController() {
		super();
	}
	
	

	public ContestRestController(ContestService cServ) {
		super();
		this.cServ = cServ;
	}



	@GetMapping(produces = "application/json", path="/api/contest/{id}")
	public Contest getById(@PathVariable Long id) {
		return cServ.getContestById(id);
	}
	
	@GetMapping(produces = "application/json", path="/api/allcontest")
	public Iterable<Contest> getAllContest() {
		return cServ.getAllContest();
	}
	
	@PostMapping(consumes = "application/json", produces="application/json", path="/api/contest")
	public ResponseEntity<Contest> create(@RequestBody Contest contest, UriComponentsBuilder uriBuilder) {
		URI location = uriBuilder.path("/api/contest/{id}").buildAndExpand(contest.getId()).toUri();
		return ResponseEntity.created(location).body(contest);
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json", path = "/api/contest/{id}")
	public Contest modifyContest(@RequestBody Contest contest) {
		return cServ.updateContest(contest);
	}
	
	@DeleteMapping(path = "/api/contest/{id}")
	public void deleteContestById(@PathVariable long id) {
		cServ.deleteContestById(id);
	}


}

