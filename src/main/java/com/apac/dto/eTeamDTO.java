package com.apac.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.apac.model.ePlayer;
import com.apac.model.eTeam;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class eTeamDTO implements Serializable{
	
	private static final long serialVersionUID = 4L;	
	private String nomTeam; // ID del eTeam
	private int pressupost; // Columna de la tabla.
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonManagedReference("elsJugadors")
	 private List<ePlayerDTO> elsJugadors = new ArrayList<ePlayerDTO>();
	 
	 // Passem un eTeam a eTeamDTO 
	 public static eTeamDTO convertToDTO(eTeam team) {
		 
		 eTeamDTO teamDTO = new eTeamDTO(); // Cree un teamDTO.
		 
		 teamDTO.setNomTeam(team.getNomTeam()); // Guarde en el teamDTO el nom del team.
		 teamDTO.setPressupost(team.getPressupost()); // Guarde en el teamDTO el pressupost del team.
		 
		 for(ePlayer player : team.getElsJugadors()) { // Recorrec els jugadors
			 
			 ePlayerDTO playerDTO = ePlayerDTO.convertToDTO(player, teamDTO); // El player el convertixc a playerDTO passant-li també el team.
			 
			 teamDTO.getElsJugadors().add(playerDTO); // Guarde el player en la list.
		 }
		 
		return teamDTO;		 
	 }
	 
	 //Passem un eTeamDTO a eTeam
	 public static eTeam convertToEntity(eTeamDTO teamDTO) {
		 
		 eTeam team = new eTeam(); // Cree la entitat team
		 
		 team.setNomTeam(teamDTO.getNomTeam()); // Assignem el nom
		 team.setPressupost(teamDTO.getPressupost()); // Assignem el pressupost
		 
		 for(int i=0; i<teamDTO.getElsJugadors().size();i++) { // Recorrem els jugadors del teamDTO
			 ePlayer player = ePlayerDTO.convertToEntity(teamDTO.getElsJugadors().get(i)); // Convertixc a entitat el jugador.
			 team.getElsJugadors().add(player);
		 }		 
		 
		 return team;
	 }
}
