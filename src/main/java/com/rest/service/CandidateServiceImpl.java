package com.rest.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entity.Candidate;
import com.rest.mapper.CandidateDto;
import com.rest.mapper.CandidateMapper;
import com.rest.repo.CandidateRepo;

@Service("candidateService")
@Transactional
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepo repo;
	
	@Autowired
	private CandidateMapper mapper;
	
	@Override
	public CandidateDto findCandidate(Long id) {
		Optional<Candidate> optional = repo.findById(id);
		if (!optional.isPresent()) {
			  throw new IllegalArgumentException
              	("Candidate with the following id = " + id + " is not found.");
		}
		return mapper.transformToDTO(optional.get());
	}

	@Override
	public List<CandidateDto> findCandidates() {
		return mapper.transformToListOfDTO(repo.findAll());
	}

	@Override
	public CandidateDto saveCandidate(CandidateDto dto) {
		Candidate candidate = mapper.transformToEntity(dto);
		candidate.setChangeDate(new Date());
		return mapper.transformToDTO(repo.save(candidate));
	}
	
	@Override
	public CandidateDto updateCandidate(Long id, CandidateDto dto) {
		System.out.println("ID: " + id + ", name: " + dto.getName());
		Optional<Candidate> optional = repo.findById(id);
		if (!optional.isPresent()) {
			  throw new IllegalArgumentException
              	("Candidate with the following id = " + id + " is not found.");
		}
		
		Candidate candidate = optional.get();
		candidate.setName(dto.getName());
		candidate.setSurname(dto.getSurname());
		candidate.setEmail(dto.getEmail());
		candidate.setBirthYear(dto.getBirthYear());
		candidate.setPhoneNumber(dto.getPhoneNumber());
		candidate.setNote(dto.getNote());
		candidate.setEmployeed(dto.getEmployeed());
		candidate.setChangeDate(new Date());
		return mapper.transformToDTO(repo.save(candidate)); 
	}

	@Override
	public void deleteCandidate(Long id) {
		System.out.println("Id: " + id);
		Optional<Candidate> optional = repo.findById(id);
		if (!optional.isPresent()) {
			  throw new IllegalArgumentException
              	("Candidate with the following id = " + id + " is not found.");
		}
		
		repo.deleteById(id);
	}

	@Override
	public List<Candidate> findAllCandidates() {
		return repo.findAll();
	}
}
