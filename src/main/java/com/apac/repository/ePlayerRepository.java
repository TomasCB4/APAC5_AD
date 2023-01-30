package com.apac.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apac.dto.ePlayerDTO;
import com.apac.model.ePlayer;


@Transactional
public interface ePlayerRepository extends JpaRepository<ePlayer, Integer>{
	
	@Query(value = "select p from ePlayer p where p.team.nomTeam = :nomTeam")
	public List<ePlayer> getPlayersByTeam(@Param("nomTeam") String nomTeam);
}
