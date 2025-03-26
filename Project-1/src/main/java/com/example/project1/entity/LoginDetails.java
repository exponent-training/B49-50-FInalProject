package com.example.project1.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class LoginDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lid;
	private String email;
	private String password;
	@OneToOne(cascade = CascadeType.DETACH)
	private User user;
}
