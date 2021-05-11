package com.rest.service;

import java.util.List;

import com.rest.entity.Candidate;
import com.rest.mapper.CandidateDto;

public interface CandidateService {

	/**
	 * find dto candidate by id
	 * 
	 * @param id
	 * @return
	 */
	CandidateDto findCandidate(Long id);
	
	/**
	 * find all dto candidates 
	 * 
	 * @return
	 */
	List<CandidateDto> findCandidates();
	
	/**
	 * find all candidate
	 * 
	 * @return
	 */
	List<Candidate> findAllCandidates();
	
	/**
	 * save candidate
	 * 
	 * @param dto
	 * @return
	 */
	CandidateDto saveCandidate(CandidateDto dto);
	
	/**
	 * update candidate
	 * 
	 * @param id
	 * @param dto
	 * @return
	 */
	CandidateDto updateCandidate(Long id, CandidateDto dto);
	
	/**
	 * delete candidate by id
	 * 
	 * @param id
	 */
	void deleteCandidate(Long id);
}
