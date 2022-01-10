package com.example.ElectronicGrade.model.repository;

import com.example.ElectronicGrade.model.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("SELECT l FROM Lesson l WHERE l.course.subject.id = ?1 AND l.course.courseClass.id = ?2")
    List<Lesson> findBySubjectAndClassId(Long idSubject, Long idClass);
}
