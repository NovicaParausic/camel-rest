package com.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.entity.Candidate;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long>{

}
