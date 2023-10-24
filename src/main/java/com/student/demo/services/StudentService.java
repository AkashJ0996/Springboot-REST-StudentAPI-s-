package com.student.demo.services;

import java.util.List;

import com.student.demo.entities.Student;

public interface StudentService {
	
	//create abstract methods
	
	public Student insertStudent(Student s);  // POST or INSERT-Operation
	
	public Student getStudentById(Integer id);   // GET or SELECT or RETRIEVE-Operation
	
	public Student updateStudent(Integer id,Student s);  // PUT or updation 
	
	public void deleteStudent(Integer id);   // DELETE-Operation
	
	public List<Student> getListByStdAddress(String stdAddress);   // NATIVE QUERY-Operation
	
	

}
