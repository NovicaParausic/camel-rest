package com.rest.mapper;

import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.springframework.stereotype.Component;

@Component
@CsvRecord(separator = ",", skipFirstLine = true)
public class CandidateCsvRecord {

	@DataField(pos = 1, required = true, trim = true)
    private Long id;
	
	@DataField(pos = 2, required = true, trim = true)
    private String name;
    
	@DataField(pos = 3, required = true, trim = true)
    private String surname;

	@DataField(pos = 4, required = true, trim = true)
	private int birthYear;

	@DataField(pos = 5, required = true, trim = true)
	private String email;

	@DataField(pos = 6, required = true, trim = true)
	private String phoneNumber;

	@DataField(pos = 7, required = true, trim = true)
	private String note;

	@DataField(pos = 8, required = true, trim = true)
	private Boolean employeed;

	@DataField(pos = 9, required = true, trim = true, pattern="dd/MM/yyyy")
	private Date changeDate;
    
    public CandidateCsvRecord() {}

	public CandidateCsvRecord(String name, String surname) {
		this.name = name;
		this.surname = surname;
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

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
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
