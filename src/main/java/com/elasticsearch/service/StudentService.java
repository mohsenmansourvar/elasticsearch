package com.elasticsearch.service;

import com.elasticsearch.domain.Student;

import java.util.List;

public interface StudentService {

    Student save(Student student);

    void update(String id, Student newStudent);

    void delete(String id);

    Student getById(String id);

    Iterable<Student> getAllStudent();

}
