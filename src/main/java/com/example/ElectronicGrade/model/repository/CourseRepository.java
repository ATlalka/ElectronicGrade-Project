package com.example.ElectronicGrade.model.repository;

import com.example.ElectronicGrade.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.teacher.id = ?1")
    List<Course> findCoursesByTeacherId(Long idTeacher);
}
