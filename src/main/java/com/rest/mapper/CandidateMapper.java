package com.rest.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.rest.entity.Candidate;

@Component
public class CandidateMapper {

	public CandidateDto transformToDTO(Candidate user) {
		CandidateDto dto = new CandidateDto();
		BeanUtils.copyProperties(user, dto);
		return dto;
	}

	public Candidate transformToEntity(CandidateDto dto) {
		Candidate user = new Candidate();
		BeanUtils.copyProperties(dto, user);
		return user;
	}

	public Candidate transformToEntityWithoutId(CandidateDto dto) {
		Candidate user = new Candidate();
		BeanUtils.copyProperties(dto, user, "id");
		return user;
	}
	
	public List<CandidateDto> transformToListOfDTO(List<Candidate> users) {
		return users.stream().map(i -> transformToDTO(i)).collect(Collectors.toList());
	}

	public List<CandidateCsvRecord> convertAndTransformList(List<Candidate> users) {
		final List<CandidateCsvRecord> csvs = new ArrayList<>();
		for (Candidate user : users) {
			CandidateCsvRecord csv = new CandidateCsvRecord();
			csv.setId(user.getId());
			csv.setName(user.getName());
			csv.setSurname(user.getSurname());
			csv.setBirthYear(user.getBirthYear().getValue());
			csv.setEmail(user.getEmail());
			csv.setEmployeed(user.getEmployeed());
			csv.setChangeDate(user.getChangeDate());
			csv.setNote(user.getNote());
			csv.setPhoneNumber(user.getPhoneNumber());
			csvs.add(csv);
			System.out.println("Converting " + user.getName() + " into csv record");
		}
		return csvs;
	}
}
