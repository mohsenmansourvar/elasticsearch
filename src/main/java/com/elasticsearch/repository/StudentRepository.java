package com.elasticsearch.repository;

import com.elasticsearch.domain.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StudentRepository extends ElasticsearchRepository<Student, String> {
}
