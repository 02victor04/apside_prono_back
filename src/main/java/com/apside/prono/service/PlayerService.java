package com.apside.prono.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apside.prono.model.Player;
import com.apside.prono.repository.PlayerRepository;

@Service
public class PlayerService {

	
	@Autowired
	private PlayerRepository pRepo;
	

	@Transactional
	public void createPlayer(Player player) {
		pRepo.save(player);
	}
	
	public Player getPlayerById(Long id) {
		return  pRepo.findById(id).get();
	}
	
	public Iterable<Player> getAllPlayer() {
		return  pRepo.findAll();
	}
	
	@Transactional
	public Player updatePlayer(Player player ) {
		return pRepo.save(player);
	}
	
	@Transactional
	public void deletePlayerById(Long id) {
		pRepo.deleteById(id);
	}

}














