package com.dro.springprojects.testcompany.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class represents the main entity in this test project. It includes some validation, although
 * some custom validation for phone and email is to be done for a real application.
 * @author david
 *
 */
public class Company{
	
	private long id;
	
	@NotNull @Size(min=2, max=30)
	private String name;
	
	@NotNull @Size(min=2, max=50)
	private String address;
	
	@NotNull @Size(min=2, max=20)
	private String city;

	@NotNull @Size(min=2, max=20)	
	private String country;
	
	private String email;
	private String phone;
	
	private List<BeneficialOwner> beneficialOwners = new ArrayList<>();
	
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
		this.name = name.trim();
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address.trim();
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city.trim();
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country.trim();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<BeneficialOwner> getBeneficialOwners() {
		return beneficialOwners;
	}

	public void addBeneficialOwner(BeneficialOwner beneficialOwner) {
		this.beneficialOwners.add(beneficialOwner);		
	}
		
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city + ", country="
				+ country + ", email=" + email + ", phone=" + phone + ", beneficialOwners=" + beneficialOwners + "]";
	}
		
}
