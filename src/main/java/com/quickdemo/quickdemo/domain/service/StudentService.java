package com.quickdemo.quickdemo.domain.service;

import com.quickdemo.quickdemo.domain.model.Student;
import com.quickdemo.quickdemo.http.request.StudentRequest;
import com.quickdemo.quickdemo.repository.StudentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class StudentService {
	@Autowired
	private final StudentRepository studentRepository;
	public Student createStudent(
		@NonNull final StudentRequest studentRequest) {

		final Student savedStudent = studentRepository.save(Student.toModel(studentRequest));

		return savedStudent.toResponse();

	}
}
