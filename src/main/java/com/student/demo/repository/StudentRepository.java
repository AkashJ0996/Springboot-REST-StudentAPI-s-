package com.student.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.student.demo.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	@Query(value = "select * from student s where std_address = ? ", nativeQuery = true)
	List<Student> findByAddress(String stdAddress);

}
