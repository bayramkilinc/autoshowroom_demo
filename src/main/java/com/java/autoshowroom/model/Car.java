package com.java.autoshowroom.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
//@JsonIgnoreProperties(value= {"person"}, allowGetters=true)
public class Car extends AuditModel {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private long id;
	
	private String modelName;
	
	private String dateOfConstruction;

	private String plate;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=false, cascade={ CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="person_id", referencedColumnName="id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Person person;

//	@Column(name = "person_id", insertable = false, updatable = false, nullable=false)
//	private long personId;
//	
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

//	public long getPersonId() {
//		return personId;
//	}
//
//	public void setPersonId(long personId) {
//		this.personId = personId;
//	}

}
