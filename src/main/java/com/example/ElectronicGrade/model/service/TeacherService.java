package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.entity.Course;
import com.example.ElectronicGrade.model.entity.Class;
import com.example.ElectronicGrade.model.entity.Lesson;
import com.example.ElectronicGrade.model.entity.Subject;
import com.example.ElectronicGrade.model.entity.users.Student;
import com.example.ElectronicGrade.model.entity.users.Teacher;
import com.example.ElectronicGrade.model.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeacherService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;

    public Optional<Teacher> findById(Long idTeacher) {
        return teacherRepository.findById(idTeacher);
    }

    public List<Student> findStudentsByClassId(Long idClass) {
        return studentRepository.findByClassId(idClass);
    }

    public List<Lesson> findLessonsByCourseId(Long idCourse) {
        return lessonRepository.findByCourseId(idCourse);
    }

    public Map<Subject, List<Class>> findSubjectsByTeacherId(Long idTeacher) {
        List<Course> courses = courseRepository.findCoursesByTeacherId(idTeacher);

        Map<Subject, List<Class>> result = new HashMap<>();
        for (Course course : courses) {
            if (!result.containsKey(course.getSubject())) {
                result.put(course.getSubject(), new ArrayList<>());
            }
            if (!result.get(course.getSubject()).contains(course.getCourseClass())) {
                result.get(course.getSubject()).add(course.getCourseClass());
            }
        }

        return result;
    }
}
