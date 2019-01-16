package com.java.autoshowroom.model;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Car extends AuditModel {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private long id;
	
	private String modelName;
	
	private String dateOfConstruction;

	private String plate;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="person_id", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JsonIgnore
	private Person person;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getDateOfConstruction() {
		return dateOfConstruction;
	}

	public void setDateOfConstruction(String dateOfConstruction) {
		this.dateOfConstruction = dateOfConstruction;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
}
