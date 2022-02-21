package com.yash.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.demo.entity.Course;
import com.yash.demo.entity.Student;
import com.yash.demo.exception.CourseNotFoundException;
import com.yash.demo.exception.StudentNotFoundException;
import com.yash.demo.repository.CourseRepository;
import com.yash.demo.repository.StudentRepository;
import com.yash.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	CourseRepository courseRepo;
	
	@Override
	public void createStudent(Student student) {
		List<Course> courses = student.getCourses();
		for(Course course: courses) {
			courseRepo.save(course);
		}
		studentRepo.save(student);
		
	}

	@Override
	public Student updateStudent(Student student) throws StudentNotFoundException {
		Optional<Student> std = studentRepo.findById(student.getEnrollmentNumber());
		if(std.isPresent()) {
			student = studentRepo.save(student);
		}
		else {
			throw new StudentNotFoundException("Student with details does not exists");
		}
		return student;
	}

	@Override
	public Student readStudent(Long enrollmentNumber) throws StudentNotFoundException {
		Optional<Student> std = studentRepo.findById(enrollmentNumber);
		Student student = null;
		if(std.isPresent()) {
			student = std.get();
		}
		else {
			throw new StudentNotFoundException("Student with details does not exists");
		}
		return student;
	}

	@Override
	public void deleteStudent(Long enrollmentNumber) throws StudentNotFoundException{
		Optional<Student> std = studentRepo.findById(enrollmentNumber);
		if(std.isPresent()) {
			studentRepo.deleteById(enrollmentNumber);;
		}
		else {
			throw new StudentNotFoundException("Student with details does not exists");
		}
		
	}

	@Override
	public Student registerStudentToCourse(Long enrollmentNumber, Course course) throws 
	StudentNotFoundException, CourseNotFoundException {
		Optional<Student> std = studentRepo.findById(enrollmentNumber);
		Student student = null;
		if(std.isPresent()) {
			student = std.get();
			List<Course> courses = student.getCourses();
			courses = courses == null ? new ArrayList<Course>() : courses;
			Optional<Course> isCoursePresent = courseRepo.findCourseByNameandInstructor(course.getCourseName(), course.getInstructorName());
			if(isCoursePresent.isPresent()) {
				courses.add(course);
				student.setCourses(courses);
				studentRepo.save(student);
			} 
			else {
				throw new CourseNotFoundException("Provided Course Details do not exist");
			}
		}
		else {
			throw new StudentNotFoundException("Student with details does not exists");
		}
		return student;
	}

	@Override
	public Student unregisterStudentFromCourse(Long enrollmentNumber, Course course)  throws StudentNotFoundException, CourseNotFoundException {
		Optional<Student> std = studentRepo.findById(enrollmentNumber);
		Student student = null;
		if(std.isPresent()) {
			student = std.get();
			List<Course> courses = student.getCourses();
			courses = courses == null ? new ArrayList<Course>() : courses;
			boolean isCoursePresent = courses.contains(course);
			if(isCoursePresent) {
				courses.remove(course);
				student.setCourses(courses);
				studentRepo.save(student);
			} 
			else {
				throw new CourseNotFoundException("Student is not enrolled in given course");
			}
		}
		else {
			throw new StudentNotFoundException("Student with details does not exists");
		}
		return student;
	}

	@Override
	public List<Course> getAllCoursesofStudent(Long enrollmentNumber) throws StudentNotFoundException {
		Optional<Student> std = studentRepo.findById(enrollmentNumber);
		List<Course> courses = null;
		if(std.isPresent()) {
			courses = studentRepo.findAllCoursesOfAStudent(enrollmentNumber);
		}
		else {
			throw new StudentNotFoundException("Student with details does not exists");
		}
		return courses;
	}

}
