package com.quickdemo.quickdemo.domain.model;

import com.quickdemo.quickdemo.http.request.StudentRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	public static Student toModel(@NonNull final StudentRequest studentRequest) {
		return Student.builder().firstname(studentRequest.getFirstname())
			.lastname(studentRequest.getLastname()).build();
	}

	public Student toResponse() {
		return Student.builder().id(this.getId()).firstname(this.firstname).lastname(this.lastname)
			.build();
	}
}