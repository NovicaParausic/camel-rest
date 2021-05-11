package com.rest.csvmapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.entity.Candidate;
import com.rest.mapper.CandidateCsvRecord;

@Component
public class UserToCsvRecordMapper {

	public CandidateCsvRecord convertAndTransform(Candidate user) {
		final CandidateCsvRecord csv = new CandidateCsvRecord();
		csv.setName(user.getName());
		csv.setSurname(user.getSurname());
		System.out.println("Converting " + user.getName() + " into csv record");
		return csv;
	}
	
	public List<CandidateCsvRecord> convertAndTransformList(List<Candidate> users) {
		final List<CandidateCsvRecord> csvs = new ArrayList<>();
		for (Candidate user : users) {
			CandidateCsvRecord csv = new CandidateCsvRecord();		
			csv.setName(user.getName());
			csv.setSurname(user.getSurname());
			csvs.add(csv);
			System.out.println("Converting " + user.getName() + " into csv record");
		}
		return csvs;
	}
}
