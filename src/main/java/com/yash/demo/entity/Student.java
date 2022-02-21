package com.yash.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
public class Student implements Serializable {
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	@Id
	private Long enrollmentNumber;
	@OneToMany(cascade = CascadeType.PERSIST, targetEntity = Course.class)
	private List<Course> courses;
	
}
