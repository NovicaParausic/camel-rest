package com.rest.route;

import static org.apache.camel.model.rest.RestParamType.body;
import static org.apache.camel.model.rest.RestParamType.path;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.bean.validator.BeanValidationException;
import org.apache.camel.component.file.GenericFileOperationFailedException;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.rest.entity.Candidate;
import com.rest.mapper.CandidateCsvRecord;
import com.rest.mapper.CandidateDto;
import com.rest.mapper.CandidateMapper;

@Component
public class CamelRouter extends RouteBuilder {

	@Autowired
	private CandidateMapper mapper;
	
	@Override
	public void configure() throws Exception {

		rest("/users").description("User REST endpoints")
			.consumes("application/json")
			.produces("application/json")
			
			.get().description("Find all candidates").outType(CandidateDto[].class)
				.responseMessage().code(200).message("All candidates successfully returned").endResponseMessage()
				.to("bean:candidateService?method=findCandidates")
				
			.get("/{id}").description("Find candidate by ID")
				.outType(CandidateDto.class)
				.param().name("id").type(path).description("The ID of the candidate").dataType("long").endParam()
				.responseMessage().code(200).message("Candidate successfully returned").endResponseMessage()
				.to("bean:candidateService?method=findCandidate(${header.id})")
			
			.post("/add").description("Update a candidate")
				.type(CandidateDto.class)
				.param().name("body").type(body).description("The candidate to add").endParam()
				.responseMessage().code(201).message("Candidate successfully saved").endResponseMessage()
				.to("direct:add")
				
			.put("/update/{id}").description("Update a candidate")
				.type(CandidateDto.class)
				.param().name("id").type(path).description("The ID of the candidate to update").dataType("long").endParam()
				.param().name("body").type(body).description("The candidate to update").endParam()
				.responseMessage().code(204).message("Candidate successfully updated").endResponseMessage()
				.to("direct:update")
			
			.delete("/delete/{id}").description("Delete candidate by ID")
				.outType(Candidate.class)
				.param().name("id").type(path).description("The ID of the candidate").dataType("long").endParam()
				.responseMessage().code(200).message("Candidate successfully returned").endResponseMessage()
				.to("bean:candidateService?method=deleteCandidate(${header.id})")
				
			.get("/csv").description("Get all candidates and put them into csv file")
				.responseMessage().code(200).message("All candidates successfully returned").endResponseMessage()
				.to("direct:csv-start");
		
		//exception handling when dto constraints are violated 
		onException(BeanValidationException.class)
			.handled(true)
			.transform(exceptionMessage())
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400)); 
				
		//exception handling when someone try to fetch, update or delete candidate with wrong id   
		onException(IllegalArgumentException.class)
			.handled(true)		    
			.transform(exceptionMessage())  //simple("Error reported: ${exception.message} - cannot process this message.")
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400)); 
				
		//exception handling when entity constraints are violated 
		onException(DataIntegrityViolationException.class)
			.handled(true)		 
			.transform(exceptionMessage())
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400)); 
				
		//exception handling when there is some unrecognized field
		onException(UnrecognizedPropertyException.class)
			.handled(true)		 
			.transform(exceptionMessage())
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400)); 
		
		//handling exception when file is used by another party
		onException(GenericFileOperationFailedException.class)
			.handled(true)		 
			.transform(exceptionMessage())
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400));
		
		//bean validating for saving candidates
		from("direct:add")
			.to("bean-validator://xyz")
			.to("bean:candidateService?method=saveCandidate(${body})");
		
		//bean validating for updating candidates
		from("direct:update")
			.to("bean-validator://xyz")
			.to("bean:candidateService?method=updateCandidate(${header.id}, ${body})");
			
		final BindyCsvDataFormat bindyCsv = new BindyCsvDataFormat(CandidateCsvRecord.class);

		//write data to csv file located at src/main/resources
		from("direct:csv-start")
			.to("bean:candidateService?method=findAllCandidates")
			.transacted()
			.bean(mapper, "convertAndTransformList")
			.marshal(bindyCsv)
			.to("file://src/main/resources?noop=true&fileName=test.csv");
		
	}
}

