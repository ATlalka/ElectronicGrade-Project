package com.example.ElectronicGrade.model.service;

import com.example.ElectronicGrade.model.entity.Subject;
import com.example.ElectronicGrade.model.repository.LessonRepository;
import com.example.ElectronicGrade.model.repository.StudentRepository;
import com.example.ElectronicGrade.model.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public List<Subject> findSubjectsByTeacherId(Long idTeacher) {
        return subjectRepository.findSubjectsByTeacherId(idTeacher);
    }


}
