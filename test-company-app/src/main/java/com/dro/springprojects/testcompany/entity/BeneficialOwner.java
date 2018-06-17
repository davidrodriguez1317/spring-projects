package com.dro.springprojects.testcompany.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class represents one component of the companies. In a real scenario, they should be in its
 * own database and managed independently
 * @author david
 *
 */
public class BeneficialOwner  {

	private Long id;
	
	@NotNull @Size(min=2, max=30)
	private String name;
	
	@NotNull
	private float percentageOwnership;
	
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public float getPercentageOwnership() {
		return percentageOwnership;
	}

	public void setPercentageOwnership(float percentageOwnership) {
		this.percentageOwnership = percentageOwnership;
	}
	@Override
	public String toString() {
		return "BeneficialOwner [name=" + name + ", percentageOwnership=" + percentageOwnership + "]";
	}

	
}
