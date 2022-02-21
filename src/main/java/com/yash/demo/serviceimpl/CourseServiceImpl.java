package com.yash.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.demo.entity.Course;
import com.yash.demo.entity.Student;
import com.yash.demo.exception.CourseNotFoundException;
import com.yash.demo.repository.CourseRepository;
import com.yash.demo.repository.StudentRepository;
import com.yash.demo.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	StudentRepository studentRepo;

	@Override
	public void createCourse(Course course) {
		courseRepo.save(course);

	}

	@Override
	public Course updateCourse(Course course) throws CourseNotFoundException {
		Optional<Course> course1 = courseRepo.findCourseByNameandInstructor(course.getCourseName(), course.getInstructorName());
		Course course2 = null;
		if(course1.isPresent()) {
			course2 = courseRepo.save(course);
		}
		else {
			throw new CourseNotFoundException("Course with details does not exists");
		}
		return course2;
	}

	@Override
	public Course readCourse(Long courseId) throws CourseNotFoundException {
		Optional<Course> course1 = courseRepo.findById(courseId);
		Course course2 = null;
		if(course1.isPresent()) {
			course2 = course1.get();
		}
		else {
			throw new CourseNotFoundException("Course with details does not exists");
		}
		return course2;
	}

	@Override
	public void deleteCourse(Long courseId) throws CourseNotFoundException {
		Optional<Course> course1 = courseRepo.findById(courseId);
		if(course1.isPresent()) {
			courseRepo.delete(course1.get());;
		}
		else {
			throw new CourseNotFoundException("Course with details does not exists");
		}

	}

	@Override
	public List<Student> getAllStudentsForACourse(Long courseId) {
		// TODO Auto-generated method stub
		return null;
	}

}
