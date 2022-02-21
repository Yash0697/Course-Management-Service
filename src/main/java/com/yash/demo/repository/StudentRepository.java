package com.yash.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yash.demo.entity.Course;
import com.yash.demo.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
	@Query(value="Select s.courses from Student s where s.enrollmentNumber= :enrollmentNumber")
	public List<Course> findAllCoursesOfAStudent(@Param("enrollmentNumber") Long enrollmentNumber);
	
	
}
