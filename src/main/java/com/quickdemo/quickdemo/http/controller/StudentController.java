package com.quickdemo.quickdemo.http.controller;

import com.quickdemo.quickdemo.domain.model.Student;
import com.quickdemo.quickdemo.domain.service.StudentService;
import com.quickdemo.quickdemo.http.request.StudentRequest;
import com.quickdemo.quickdemo.repository.StudentRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentService studentService;

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Student> createTransaction(
		@RequestBody @NonNull final StudentRequest studentRequest) {

		final Student student = studentService.createStudent(studentRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(student);
	}

	@GetMapping
	public List<Student> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		return students;
	}

}

