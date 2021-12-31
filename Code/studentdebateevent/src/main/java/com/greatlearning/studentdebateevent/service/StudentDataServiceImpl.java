package com.greatlearning.studentdebateevent.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.studentdebateevent.domain.entity.Student;
import com.greatlearning.studentdebateevent.repository.StudentRepository;

@Service
public class StudentDataServiceImpl implements StudentDataService {
	
	@Autowired
	StudentRepository studentRepository;

	@Override
	@Transactional
	public List<Student> findAll() {
		List<Student> students = studentRepository.findAll();
		return students;
	}

	@Override
	@Transactional
	public Student findById(int id) {
		Student student = new Student();
		student = studentRepository.findById(id).get();
		return student;
	}

	@Override
	@Transactional
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		studentRepository.deleteById(id);
	}
}
