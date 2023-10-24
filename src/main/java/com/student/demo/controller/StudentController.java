package com.student.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.demo.entities.Student;
import com.student.demo.services.StudentService;

@RestController
@RequestMapping("/std")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@PostMapping("/insert")  //  http://localhost:8081/std/insert
	public ResponseEntity<Student> insertion(@RequestBody Student student){
		
		Student s = service.insertStudent(student);
		return new ResponseEntity<Student>(s,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}") //  http://localhost:8081/std/get/2
	public ResponseEntity<Student> getViaId(@PathVariable("id") Integer id ){
		Student sid = service.getStudentById(id);
		return new ResponseEntity<Student>(sid,HttpStatus.OK);
	}
	
	@PutMapping("/put/{id}") //  http://localhost:8081/std/put/2
	public ResponseEntity<Student> putViaId(@PathVariable("id") Integer id ,@RequestBody Student s){
		Student sid = service.updateStudent(id, s);
		return new ResponseEntity<Student>(sid,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/del/{id}")  //  http://localhost:8081/std/del/3
	public String deleteViaId(@PathVariable("id")Integer id) {
		service.deleteStudent(id);
		String status = "Record with id "+ id +" Deleted Successfully ..";
		return status ;
	}
	
	@GetMapping("/getAdd/{add}") //  http://localhost:8081/std/getAdd/5
	public ResponseEntity<List<Student>> getListAdd(@PathVariable("add") String stdAddress ){
		List<Student> sid = service.getListByStdAddress(stdAddress);
		return new ResponseEntity<List<Student>>(sid,HttpStatus.OK);
	}
}
