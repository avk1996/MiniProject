package com.app.entities;

import javax.persistence.*;
import javax.persistence.Id;

import lombok.Data;

@Data
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
}
