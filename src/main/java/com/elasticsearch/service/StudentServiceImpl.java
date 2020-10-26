package com.elasticsearch.service;

import com.elasticsearch.domain.Student;
import com.elasticsearch.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void update(String id, Student newStudent) {
        Student student = getById(id);

        if (newStudent.getFirstName() != null) {
            student.setFirstName(newStudent.getFirstName());
        }
        if (newStudent.getLastName() != null) {
            student.setLastName(newStudent.getLastName());
        }
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void delete(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student getById(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Student by id"));
    }

    @Override
    public Iterable<Student> getAllStudent() {
        return studentRepository.findAll();
    }
}
