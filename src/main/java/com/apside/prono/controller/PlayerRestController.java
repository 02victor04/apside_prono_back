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

import com.apside.prono.model.Player;
import com.apside.prono.service.PlayerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PlayerRestController {

	@Autowired
	private PlayerService pServ;
	
	
	
	public PlayerRestController() {
		super();
	}

	public PlayerRestController(PlayerService pServ) {
		super();
		this.pServ = pServ;
	}

	@GetMapping(produces = "application/json", path="/api/player/{id}")
	public Player getPlayerById(@PathVariable Long id) {
		return pServ.getPlayerById(id);
	}
	
	@GetMapping(produces = "application/json", path="/api/allplayer")
	public Iterable<Player> getAllPlayer() {
		return pServ.getAllPlayer();
	}
	
	@PostMapping(consumes = "application/json", produces="application/json", path="/api/player")
	public ResponseEntity<Player> createPlayer(@RequestBody Player player, UriComponentsBuilder uriBuilder) {
		pServ.createPlayer(player);
		URI location = uriBuilder.path("/api/player/{id}").buildAndExpand(player.getId()).toUri();
		return ResponseEntity.created(location).body(player);
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json", path = "/api/player/{id}")
	public Player modifyPlayer(@RequestBody Player player) {
		return pServ.updatePlayer(player);
	}
	
	@DeleteMapping(path = "/api/player/{id}")
	public void deletePlayerById(@PathVariable long id) {
		pServ.deletePlayerById(id);
	}
	
	
	
}
