package com.elasticsearch;

import com.elasticsearch.domain.Student;
import com.elasticsearch.repository.StudentRepository;
import com.elasticsearch.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setup() {
        studentRepository.deleteAll();
    }

    @Test
    void save() {
        Student student = Student.builder()
                .firstName("Mohsen")
                .lastName("Mansouri")
                .build();

        studentService.save(student);

        Student studentById = studentService.getById(student.getId());

        assertNotNull(studentById);
        assertEquals("Mohsen", studentById.getFirstName());
        assertEquals("Mansouri", studentById.getLastName());
    }

    @Test
    void delete() {
        Student student = createStudent();
        studentService.save(student);

        studentService.delete(student.getId());

        assertThrows(IllegalArgumentException.class, () -> studentService.getById(student.getId()));
    }

    @Test
    void getById() {
        Student student = createStudent();
        studentService.save(student);

        Student studentById = studentService.getById(student.getId());

        assertNotNull(studentById);
        assertEquals("Mohsen", studentById.getFirstName());
        assertEquals("Mansouri", studentById.getLastName());
    }

    @Test
    void update() {
        Student student = createStudent();
        studentService.save(student);

        Student updatedStudent = Student.builder()
                .lastName("Moradi")
                .build();

        studentService.update(student.getId(), updatedStudent);

        Student studentById = studentService.getById(student.getId());

        assertNotNull(studentById);
        assertEquals("Mohsen", studentById.getFirstName());
        assertEquals("Moradi", studentById.getLastName());
    }

    @Test
    void getAllStudents() {
        Student student1 = createStudent();
        studentService.save(student1);

        Student student2 = Student.builder()
                .firstName("Liam")
                .lastName("Mansouri")
                .build();
        studentService.save(student2);

        Iterable<Student> allStudents = studentService.getAllStudent();
        Iterator<Student> iterator = allStudents.iterator();
        Student savedStudent1 = iterator.next();
        assertEquals("Mohsen", savedStudent1.getFirstName());
        assertEquals("Mansouri", savedStudent1.getLastName());

        Student savedStudent2 = iterator.next();
        assertEquals("Liam", savedStudent2.getFirstName());
        assertEquals("Mansouri", savedStudent2.getLastName());
    }

    private Student createStudent() {
        return Student.builder()
                .firstName("Mohsen")
                .lastName("Mansouri")
                .build();
    }
}
