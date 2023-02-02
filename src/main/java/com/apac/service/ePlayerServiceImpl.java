package com.apac.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apac.dto.ePlayerDTO;
import com.apac.dto.eTeamDTO;
import com.apac.model.ePlayer;
import com.apac.model.eTeam;
import com.apac.repository.ePlayerRepository;
import com.apac.repository.eTeamRepository;

@Service
public class ePlayerServiceImpl implements ePlayerService{
	
	@Autowired
	private ePlayerRepository playerRepository;
	
	@Autowired
	private eTeamRepository teamRepository;

	@Override
	public List<ePlayerDTO> listAllPlayers(eTeamDTO teamDTO) {
		
		// Obtengo la lista de jugadores del team.
		List<ePlayer> lista = playerRepository.getPlayersByTeam(teamDTO.getNomTeam());
		
		// Creamos una lista de ePlayerDTO que será la que devolvamos al controlador
		List<ePlayerDTO> listaResultado = new ArrayList<ePlayerDTO>();
		
		// Recorremos la lista de player y las mapeamos a DTO
		for (int i = 0; i < lista.size(); i++) {
			listaResultado.add(ePlayerDTO.convertToDTO(lista.get(i), teamDTO));
		}
		
		return listaResultado;
	}

	@Override
	public void savePlayer(ePlayerDTO playerDTO) {
		

		eTeam team;
		
		//creem un nou eTeam desde el DTO,  i ens falla, ja existeix en la BBDD !!!!!
		//team=eTeamDTO.convertToEntity(playerDTO.getTeam());
		
		// recuperem el team desde la BBDD
		team=teamRepository.findById(playerDTO.getTeam().getNomTeam()).get();
		
		ePlayer player=ePlayerDTO.convertToEntity(playerDTO,team);
		team.getElsJugadors().add(player);


		playerRepository.save(player);
		
	}

	@Override
	public void deletePlayer(int id) {
		playerRepository.deleteById(id);
		
	}

}
