package com.apac.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "team")
public class eTeam {
	
	@Id
    @Column
    private String nomTeam; // ID del eTeam
    
    @Column
    private int pressupost; // Columna de la tabla.
    
    
    // ==================Cree la relació amb la classe ePlayer===================
    @OneToMany(mappedBy="team", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ePlayer> elsJugadors = new ArrayList<ePlayer>();


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		eTeam other = (eTeam) obj;
		return Objects.equals(nomTeam, other.nomTeam);
	}


	@Override
	public int hashCode() {
		return Objects.hash(nomTeam);
	} 
    
    
    

}
