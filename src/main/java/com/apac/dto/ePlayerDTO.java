package com.apac.dto;

import java.io.Serializable;
import com.apac.model.ePlayer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class ePlayerDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idPlayer;
	private String alias;
	private int age;
	private String rol;
	private String nacionalitat;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonBackReference("elsJugadors")
	private eTeamDTO team;
	
	// Convierte una entidad a un objeto DTO con todos los datos
	public static ePlayerDTO convertToDTO(ePlayer player, eTeamDTO teamDTO) {
		
		// Creamos el clienteDTO y asignamos los valores basicos
		ePlayerDTO playerDTO = new ePlayerDTO();
		playerDTO.setIdPlayer(player.getIdPlayer());
		playerDTO.setAlias(player.getAlias());
		playerDTO.setAge(player.getAge());
		playerDTO.setRol(player.getRol());
		playerDTO.setNacionalitat(player.getNacionalitat());
		
		playerDTO.setTeam(teamDTO);
		
		return playerDTO;		
	}
	
	public static ePlayer convertToEntity(ePlayerDTO playerDTO) {
		
		ePlayer player = new ePlayer();
		player.setIdPlayer(playerDTO.getIdPlayer());
		player.setAlias(playerDTO.getAlias());
		player.setAge(playerDTO.getAge());
		player.setRol(playerDTO.getRol());
		player.setNacionalitat(playerDTO.getNacionalitat());
		
		return player;
	}
}
