package com.yash.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yash.demo.entity.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

	@Query(value="Select c from Course c where c.courseName= :courseName and c.instructorName= :instructorName")
	public Optional<Course> findCourseByNameandInstructor(@Param("courseName") String courseName, @Param("instructorName") String instructorName);
}
