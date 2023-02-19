package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entities.Student;
import com.masai.repository.StudentRepository;



@RestController
public class StudentController {
	

private StudentRepository sRepo;

@Autowired
public StudentController(StudentRepository sRepo) {
	this.sRepo=sRepo;
}



@PostMapping("/students")
public ResponseEntity<Student> saveStudentHandler(@RequestBody Student student){

	Student saveStudent=sRepo.save(student);
	
	return new ResponseEntity<>(saveStudent,HttpStatus.OK);
}

@GetMapping("/students/{roll}")
public ResponseEntity<Student> getStudentByRoll(@PathVariable Integer roll){
	Student students=sRepo.findById(roll).get();
	return new ResponseEntity<Student>(students,HttpStatus.OK);
}

@GetMapping("/students")
public List<Student> getAllStudents(){
	return sRepo.findAll();
}

@DeleteMapping("/students/{roll}")
public ResponseEntity<Student>deleteStudentHandler(@PathVariable Integer roll){
	Student students=sRepo.findById(roll).get();
	sRepo.delete(students);
	return new ResponseEntity<Student>(students,HttpStatus.OK);
}

}
