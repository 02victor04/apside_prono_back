package com.apside.prono.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apside.prono.exception.PlayerUnknownException;
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

	public Player getPlayerById(Long id) throws PlayerUnknownException {
		Optional<Player> oplayer = pRepo.findById(id);
		if(oplayer.isPresent()) {
			return oplayer.get();
		}else {
			throw new PlayerUnknownException(id);
		}
	}

	public Iterable<Player> getAllPlayer() {
		return pRepo.findAll();
	}

	@Transactional
	public Player updatePlayer(Player player,Long id ) throws PlayerUnknownException {
		Optional<Player> obddPlayer = pRepo.findById(id);
		if(obddPlayer.isPresent()) {
			Player bddPlayer = obddPlayer.get();
			bddPlayer.setFirstName(player.getFirstName());
			bddPlayer.setLastName(player.getLastName());
			bddPlayer.setMail(player.getMail());
			bddPlayer.setSubscriptionDate(player.getSubscriptionDate());
			return pRepo.save(bddPlayer);
		}else {
			throw new PlayerUnknownException(id);
		}
	}

	@Transactional
	public void deletePlayerById(Long id) throws PlayerUnknownException {
		Optional<Player> obddPlayer = pRepo.findById(id);
		if(obddPlayer.isPresent()) {
		pRepo.deleteById(id);
		}else {
			throw new PlayerUnknownException(id);
		}
	}

}














