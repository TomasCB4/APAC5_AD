package com.apac.service;

import java.util.List;
import com.apac.dto.ePlayerDTO;
import com.apac.dto.eTeamDTO;

public interface ePlayerService {
	List<ePlayerDTO> listAllPlayers(eTeamDTO teamDTO);
	void savePlayer(ePlayerDTO playerDTO);
	void deletePlayer(int id);
}
