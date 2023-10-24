package com.student.demo.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.demo.entities.Student;
import com.student.demo.repository.StudentRepository;
import com.student.demo.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository repository;

	@Override
	public Student insertStudent(Student s) {

		return this.repository.save(s);
	}

	@Override
	public Student getStudentById(Integer id) {
		Student s = repository.findById(id).orElseThrow(()->new RuntimeException("Student with this id does not exist"));
		return s;
	}

	@Override
	public Student updateStudent(Integer id , Student s) {
		
		Student st = getStudentById(id);
		if(s.getStdName()!= null) {
			st.setStdName(s.getStdName());
		}
		if(s.getStdAddress()!= null) {
			st.setStdAddress(s.getStdAddress());
		}
		repository.save(st);
		return st;
	}

	@Override
	public void deleteStudent(Integer id) {
		repository.deleteById(id);
		
	}

	@Override
	public List<Student> getListByStdAddress(String stdAddress) {
		List<Student> stuAdd = repository.findByAddress(stdAddress);
		return stuAdd;
	}

}
