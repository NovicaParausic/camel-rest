package com.rest.mapper;

import java.time.Year;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CandidateDto {

	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String surname;
	
	@Past
	@NotNull
	private Year birthYear;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String phoneNumber;
	
	@NotNull
	private String note;
	
	@NotNull
	private Boolean employeed;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date changeDate;
	
	public CandidateDto() {}

	public CandidateDto(Long id, String name, String surname, Year birthYear, String email, String phoneNumber,
			String note, Boolean employeed, Date changeDate) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthYear = birthYear;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.note = note;
		this.employeed = employeed;
		this.changeDate = changeDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Year getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Year birthYear) {
		this.birthYear = birthYear;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getEmployeed() {
		return employeed;
	}

	public void setEmployeed(Boolean employeed) {
		this.employeed = employeed;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}	
}