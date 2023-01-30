package com.apac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apac.dto.eTeamDTO;
import com.apac.model.eTeam;
import com.apac.repository.eTeamRepository;

@Service
public class eTeamServiceImpl implements eTeamService{
	
	@Autowired
	private eTeamRepository teamRepository;
	

	@Override
	public eTeamDTO saveTeam(eTeamDTO teamDTO) {
		eTeam team = eTeamDTO.convertToEntity(teamDTO); // Convertim el DTO a entity per a guardar
		eTeam newTeam = teamRepository.save(team); // Guardem el team en la BBDD y el recuperem per a mostrar-lo.
		return eTeamDTO.convertToDTO(newTeam); // El retornem convertit a DTO.
	}

	@Override
	public eTeamDTO getTeamByName(String name) {
		Optional<eTeam> team = teamRepository.findById(name); // Busquem el team y el passem a optional per si no existeix.
		
		if(team.isPresent()) { // Si existeix el team
			return eTeamDTO.convertToDTO(team.get()); // Retornem el eTeam com DTO
		}else {	// Si no existeix retornem null
			return null;
		}		
	}

	@Override
	public List<eTeamDTO> listAllTeam() {
		List<eTeam> lista = teamRepository.findAll(); // Agafe tots els eTeam
		List<eTeamDTO> listaResultado = new ArrayList<eTeamDTO>(); // Creem una list per a retornar DTO's.
		
		for(int i = 0; i < lista.size(); i++) {	// Recorrec per a agafar un a un.
			
			listaResultado.add(eTeamDTO.convertToDTO(lista.get(i))); // Convertim cada entitat a DTO y la guardem en la nova llista.			
		}
		return listaResultado;
	}

	@Override
	public void deleteTeam(String name) {
		teamRepository.deleteById(name);
		
	}
	
}
