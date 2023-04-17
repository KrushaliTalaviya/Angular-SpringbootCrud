package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Mark class as a Entity Using Java persistence API link this class to database
 * table. Getter, Setter and constructor are call using this class
 * 
 * @author Krushali Talaviya
 * @version openjdk "11.0.16.1"
 * @since April 2023
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "bigint(20)")
	private Integer id;

	@Column(columnDefinition = "VARCHAR(30)", nullable = false)
	private String firstName;

	@Column(columnDefinition = "VARCHAR(30)", nullable = false)
	private String lastName;

	@Column(columnDefinition = "DATE", nullable = false)
	private Date dateOfBirth;

	@Column(columnDefinition = "VARCHAR(17)", nullable = false, unique = true)
	private String mobileNo;

	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String address1;

	@Column(columnDefinition = "VARCHAR(50)", nullable = true)
	private String address2;

	@Column(columnDefinition = "INT(3)", nullable = false)
	private Integer age;

	@Column(columnDefinition = "VARCHAR(70)", nullable = false, unique = true)
	private String email;

	@Column(columnDefinition = " ENUM('Male', 'Female') NOT NULL")
	private String gender;

	@Transient
	private boolean isError = false;

}