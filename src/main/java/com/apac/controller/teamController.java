package com.apac.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.apac.Apac5CtApplication;
import com.apac.dto.eTeamDTO;
import com.apac.service.ePlayerService;
import com.apac.service.eTeamService;

@RestController
public class teamController {
	
	private static final Logger myLog = LoggerFactory.getLogger(Apac5CtApplication.class);
	
	@Autowired
	private HttpServletRequest context;
	
	@Autowired
	private eTeamService teamService;
	
	@Autowired
	private ePlayerService playerService;
	
	@Value("${aplicacion.nombre}")
	private String nombreAplicacion;
	
	@Value("${asignatura}")
	private String nombreAsignatura;
	
	@GetMapping("/")
	public String index() {
		// Mostra en consola el que estem fent.
		myLog.info(context.getMethod() + " from " + context.getRemoteHost());		
		return "Benvingut a " + nombreAplicacion + " de " + nombreAsignatura;		
	}
	
	// ==========MOSTREM ETEAM/S==========
	
	// Mostrem tots els eTeam
	@GetMapping("/eTeams")
	public ResponseEntity<List<eTeamDTO>> listTeams(){
		// Mostra en consola el que estem fent.
		myLog.info(context.getMethod() + " from " + context.getRemoteHost());
		
		// Cride a la funció del servici que em retorna la llista de teams.
		List<eTeamDTO> losTeams = teamService.listAllTeam();
		
		// Si la list está buida o no existeix:
		if (losTeams==null || losTeams.isEmpty()) {
			// Retornem una resposta de no contingut.
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			// Retornem una resposta amb els equips i un Status ok (200).
			return new ResponseEntity<>(losTeams,HttpStatus.OK);
		}
	}
	
	// Mostrem un eTeam en concret.
	@GetMapping("/eTeam/{nomTeam}")
	public ResponseEntity<eTeamDTO> showTeamById(@PathVariable String nomTeam){
		// Mostra en consola el que estem fent.
		myLog.info(context.getMethod() + context.getRequestURI() + " from " + context.getRemoteHost());
		
		// Agafem el team cridant al servici i passant-li el nom del team.
		eTeamDTO elTeam = teamService.getTeamByName(nomTeam);
		
		// Si el team és null:
		if (elTeam==null) {
			// Retornem un Status de no encontrat.
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			// Retornem el team i el Status ok (200).
			return new ResponseEntity<>(elTeam,HttpStatus.OK);
		}
	}
	
	// ==========AFEGIM ETEAM==========
	
	// Afegim un nou eTeam (la informació anira en el cos).
	@PostMapping("/eTeam")
	public ResponseEntity<eTeamDTO> addTeam(@RequestBody eTeamDTO newTeam){
		myLog.info(context.getMethod() + context.getRequestURI()); 
		
		// Guardem el team amb el repositori i guardem el team que ens retorna guardat.
		eTeamDTO elTeam = teamService.saveTeam(newTeam);
		
		if(elTeam==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(elTeam,HttpStatus.OK);
		}
	}
	
	// ==========ACTUALITZEM ETEAM==========
	
	@PutMapping("/eTeam")
	public ResponseEntity<eTeamDTO> updateTeam(@RequestBody eTeamDTO updTeam ){
		myLog.info(context.getMethod() + context.getRequestURI());
		
		// Busque si existeix.
		eTeamDTO elTeam = teamService.getTeamByName(updTeam.getNomTeam());
		
		if(elTeam==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			// Com existeix l'actualitzem.
			eTeamDTO elTeamUPD = teamService.saveTeam(updTeam);
			return new ResponseEntity<>(elTeamUPD,HttpStatus.OK);
		}		
	}
	
	// ==========BORREM ETEAM==========
	
	@DeleteMapping("/eTeams/{nomTeam}")
	public ResponseEntity<String> deleteTeam(@PathVariable String nomTeam){
		teamService.deleteTeam(nomTeam);
		return new ResponseEntity<>("Cliente borrado satisfactoriamente",HttpStatus.OK);
	}
}
