package com.apac.service;

import java.util.List;

import javax.transaction.Transactional;

import com.apac.dto.eTeamDTO;

@Transactional
public interface eTeamService {
	
	eTeamDTO saveTeam(eTeamDTO teamDTO);
	eTeamDTO getTeamByName(String name);
	List<eTeamDTO> listAllTeam();
	void deleteTeam(String name);

}
