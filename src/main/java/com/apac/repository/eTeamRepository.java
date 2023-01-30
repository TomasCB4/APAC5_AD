package com.apac.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apac.model.eTeam;

@Transactional
public interface eTeamRepository extends JpaRepository<eTeam, String>{

}
