package com.apac.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "player")
public class ePlayer {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPlayer;
    
    @Column
    private String alias;
    
    @Column
    private int age;
    
    @Column
    private String rol;
    
    @Column
    private String nacionalitat;
	
 // ===============Relació amb la classe eTeam===============
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="team")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private eTeam team;
}
