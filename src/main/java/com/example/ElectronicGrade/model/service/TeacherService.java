package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.entity.Subject;
import com.example.ElectronicGrade.model.entity.users.Teacher;
import com.example.ElectronicGrade.model.repository.LessonRepository;
import com.example.ElectronicGrade.model.repository.StudentRepository;
import com.example.ElectronicGrade.model.repository.SubjectRepository;
import com.example.ElectronicGrade.model.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Subject> findSubjectsByTeacherId(Long idTeacher) {
        return subjectRepository.findSubjectsByTeacherId(idTeacher);
    }

    public Optional<Teacher> findById(Long idTeacher) {
        return teacherRepository.findById(idTeacher);
    }
}
