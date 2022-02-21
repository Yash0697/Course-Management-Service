package com.yash.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yash.demo.entity.Course;
import com.yash.demo.entity.Student;
import com.yash.demo.exception.StudentNotFoundException;
import com.yash.demo.exception.CourseNotFoundException;

@Service
public interface StudentService {
	public void createStudent(Student student);
	public Student updateStudent(Student student) throws StudentNotFoundException;
	public Student readStudent(Long enrollmentNumber) throws StudentNotFoundException;
	public void deleteStudent(Long enrollmentNumber) throws StudentNotFoundException;
	public Student registerStudentToCourse(Long enrollmentNumber, Course course)throws StudentNotFoundException, CourseNotFoundException;
	public Student unregisterStudentFromCourse(Long enrollmentNumber, Course course) throws StudentNotFoundException, CourseNotFoundException;
	public List<Course> getAllCoursesofStudent(Long enrollmentNumber) throws StudentNotFoundException;
}
