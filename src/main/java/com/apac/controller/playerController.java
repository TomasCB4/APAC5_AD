package com.apac.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.apac.Apac5CtApplication;
import com.apac.dto.ePlayerDTO;
import com.apac.dto.eTeamDTO;
import com.apac.service.ePlayerService;
import com.apac.service.eTeamService;


@Controller
public class playerController {
	
	private static final Logger myLog=LoggerFactory.getLogger(Apac5CtApplication.class);
	
	@Autowired
	private HttpServletRequest context;
	
	@Autowired
	private ePlayerService playerService;
	
	@Autowired
	private eTeamService teamService;
	
	// Creamos un ePlayer y lo añadimos al eTeam
	@PutMapping("/eTeams/{nomTeam}/player")
	public ResponseEntity<eTeamDTO> addPlayerToTeam(
			@PathVariable String nomTeam, 
			@RequestBody ePlayerDTO newPlayerDTO) {
		
		// Mostrem info a la terminal
		myLog.info(context.getMethod() + context.getRequestURI());
		
		// Agafem el teamDTO
		eTeamDTO teamDTO = teamService.getTeamByName(nomTeam);
		if (teamDTO==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			// Asignamos el team al jugador.
			newPlayerDTO.setTeam(teamDTO);
			// guardamos el ePlayer (generará ID y asignará al eTeam)
			playerService.savePlayer(newPlayerDTO);
			
			// Recargamos el eTeam y lo retornamos
			eTeamDTO teamUPD = teamService.getTeamByName(nomTeam);
			return new ResponseEntity<>(teamUPD, HttpStatus.OK);
		}
	}
	
	@GetMapping("/eTeams/{nomTeam}/players")
	public ResponseEntity<List<ePlayerDTO>> listPlayersTeam(@PathVariable String nomTeam){
		// Optenemos el cliente
		eTeamDTO teamDTO = teamService.getTeamByName(nomTeam);
		
		if (teamDTO==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			// Obtenemos las cuentas
			List<ePlayerDTO> players = playerService.listAllPlayers(teamDTO);
			if (players==null || players.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(players, HttpStatus.OK);
			}
		}
	}
	
	@DeleteMapping("/players/{idPlayer}")
	public ResponseEntity<String> removePlayer(@PathVariable int idPlayer){
		playerService.deletePlayer(idPlayer);
		return new ResponseEntity<>("Cuenta eliminada satisfactoriamente", HttpStatus.OK);
	}
	
}
