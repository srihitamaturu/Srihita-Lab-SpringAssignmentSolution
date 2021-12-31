package com.greatlearning.studentdebateevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.studentdebateevent.domain.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
