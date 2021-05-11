package com.rest.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entity.Candidate;
import com.rest.repo.CandidateRepo;
import com.rest.service.CandidateService;

//test controller
@RestController
@RequestMapping(path = "/users")
public class Controller {

	@Autowired
	private CandidateRepo repo;
	
	@Autowired
	private CandidateService service;
	
	@PostMapping(path = "/add")
	public String add(@RequestBody Candidate user) {
		repo.save(user);
		return "bla";
	}
	
	@DeleteMapping(path = "/delete")
	public String delete(@RequestBody Candidate user) {
		service.deleteCandidate(10L);
		return "znja";
	}
}
