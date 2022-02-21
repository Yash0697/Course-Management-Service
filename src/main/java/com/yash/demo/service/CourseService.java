package com.yash.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yash.demo.entity.Course;
import com.yash.demo.entity.Student;
import com.yash.demo.exception.CourseNotFoundException;

@Service
public interface CourseService {

	public void createCourse(Course course);
	public Course updateCourse(Course course) throws CourseNotFoundException;
	public Course readCourse(Long courseId) throws CourseNotFoundException;
	public void deleteCourse(Long courseId) throws CourseNotFoundException;
	public List<Student> getAllStudentsForACourse(Long courseId) throws CourseNotFoundException;
	
}
