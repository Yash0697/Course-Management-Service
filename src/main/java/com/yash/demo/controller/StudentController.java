package com.yash.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.demo.entity.Course;
import com.yash.demo.entity.Student;
import com.yash.demo.exception.CourseNotFoundException;
import com.yash.demo.exception.StudentNotFoundException;
import com.yash.demo.service.StudentService;

@RestController
@RequestMapping(value="/students/")
public class StudentController {
	
	@Autowired
	StudentService stdService;
	
	@PostMapping("addStudent")
	public ResponseEntity<String> createStudent(@RequestBody Student student) {
		stdService.createStudent(student);
		return new ResponseEntity<String>("Student Created Successfully", HttpStatus.OK);
	}
	
	@PostMapping("updateStudent")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) throws StudentNotFoundException {
		Student updatedStd = stdService.updateStudent(student);
		return new ResponseEntity<Student>(updatedStd, HttpStatus.OK);
	}
	
	@GetMapping("{studentId}")
	public Student getStudent(@PathVariable("studentId") Long studentId) throws StudentNotFoundException {
		return stdService.readStudent(studentId);
	}
	
	@DeleteMapping("deleteStudent")
	public ResponseEntity<String> deleteStudent(@RequestParam("enrollmentNumber") Long enrollmentNumber) throws StudentNotFoundException {
		stdService.deleteStudent(enrollmentNumber);
		return new ResponseEntity<String>("Studen Deleted Successfully", HttpStatus.OK);
	}
	
	@PostMapping("{studnetId}")
	public ResponseEntity<Student> registerStudentIntoACourse(@PathVariable("studentId") Long enrollmentNumber, @RequestBody Course course) throws StudentNotFoundException, CourseNotFoundException {
		Student registeredStudent = stdService.registerStudentToCourse(enrollmentNumber, course);
		return new ResponseEntity<Student>(registeredStudent, HttpStatus.OK);
	}
	@PostMapping("{enrollmentId}")
	public ResponseEntity<Student> unregisterStudentFromACourse(@PathVariable("studentId") Long enrollmentNumber, @RequestBody Course course) throws StudentNotFoundException, CourseNotFoundException {
		Student registeredStudent = stdService.unregisterStudentFromCourse(enrollmentNumber, course);
		return new ResponseEntity<Student>(registeredStudent, HttpStatus.OK);
	}
	
	@GetMapping("{studentId}/courses")
	public ResponseEntity<List<Course>> getAllCoursesOfAStudent(@PathVariable("studentId") Long enrollmentNumber) throws StudentNotFoundException {
		List<Course> courses = stdService.getAllCoursesofStudent(enrollmentNumber);
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
}
