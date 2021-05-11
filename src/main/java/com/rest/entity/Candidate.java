package com.rest.entity;

import java.time.Year;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.rest.converter.YearAttributeConverter;

@Entity
@Table(name = "user_table")
public class Candidate {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "surname", nullable = false, length = 50)
	private String surname;

	@Column(name = "birth_year", columnDefinition = "smallint", nullable = false)
	@Convert(converter = YearAttributeConverter.class)
	private Year birthYear;

	@Column(name = "email", nullable = false, length = 50, unique = true)
	private String email;

	@Column(name = "phone_number", nullable = false, length = 50, unique = true)
	private String phoneNumber;

	@Column(name = "note", length = 350)
	private String note;

	@Column(name = "employeed", nullable = false)
	private Boolean employeed;

	@Temporal(TemporalType.DATE)
	private Date changeDate;

	public Candidate() {
	}

	public Candidate(Long id, String name) {
		this.id = id;
		this.name = name;
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
