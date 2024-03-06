package com.app.entities;

import javax.persistence.*;
import javax.persistence.Id;

public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
}
