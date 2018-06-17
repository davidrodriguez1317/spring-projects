package com.dro.springprojects.testcompany.repository.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import org.springframework.stereotype.Repository;
import com.dro.springprojects.testcompany.entity.BeneficialOwner;
import com.dro.springprojects.testcompany.entity.Company;
import com.dro.springprojects.testcompany.repository.CompanyRepository;
import com.dro.springprojects.testcompany.repository.utils.MockHelper;

/**
 * This implementation of the interface CompanyRepository mocks the access to the database
 * for the application
 * 
 * @author david
 *
 */

@Repository
public class CompanyRepositoryMock implements CompanyRepository{

	private static TreeSet<Company> companies;
	
	/**
	 * This constructor initializes some data for mocking the existence of some data on the database just
	 * at the start of the application
	 */
	public CompanyRepositoryMock() {
		
		Comparator<Company> compById = (Company o1, Company o2) -> (Long.compare(o1.getId(), o2.getId()));
		
		companies = new TreeSet<Company>(compById);
		Company company00 = new Company();
		Long id = MockHelper.generateId(companies);
		company00.setId(id);
		company00.setName("Large Company S.L.");
		company00.setAddress("Street large");
		company00.setCity("Palma");
		company00.setCountry("Spain");
		company00.setEmail("thisemail@google.com");
		company00.setPhone("+0034 971 55 55 55");
		
		companies.add(company00);
	}
	
	/**
	 * Method that saves a new company. A check for an existing company is
	 * done before the insertion
	 */
	@Override
	public Company save(Company company) {
		
		Optional<Company> companyOptional = companies.stream()
				.filter(s -> s.getName().trim().equals(company.getName()))
				.findFirst();
		
		if(companyOptional.isPresent()) {
			return null;
		}
		
		Long id = MockHelper.generateId(companies);
		company.setId(id);				
		companies.add(company);
				
		return company;
	}

	/**
	 * Method for getting all the companies
	 */
	@Override
	public List<Company> getAll() {
		return new ArrayList<>(companies);
	}
	
	/**
	 * Method for getting a company by its id, and it returns null in case it does not exists
	 */
	@Override
	public Company getCompanyById(long id) {
		Optional<Company> companyOptional = companies.stream()
				.filter(s -> s.getId() == id)
				.findFirst();
		return companyOptional.isPresent() ? companyOptional.get() : null;
	}
	
	/**
	 * Method for updating the data of a company. It retrieves the company, checks if it 
	 * exists and in that case it substitutes the old data by the new
	 */
	@Override
	public Company updateCompany(Company company) {
		
		Company companyToUpdate = getCompanyById(company.getId());
		
		if (companyToUpdate == null) {
			return null;
		}
				
		companies.remove(companyToUpdate);
		companies.add(company);
		
		return company;
	}
	
	/**
	 * This method adds all the beneficial owners at one after checking the existence of the company. 
	 * It calls another method that adds the objects one by one
	 */
	@Override
	public Company addBeneficialOwnersToCompany(BeneficialOwner[] beneficialOwners, long id) {
		
		Optional<Company> companyOptional = companies.stream()
				.filter(s -> s.getId() == id)
				.findFirst();
		
		if (!companyOptional.isPresent()) {
			return null;
		}
		
		Company company = companyOptional.get();
		
		for(BeneficialOwner beneficialOwner: beneficialOwners) {
			addBeneficialOwnerToCompany(beneficialOwner, company);
		}
		
		return company;
	}
	
	/**
	 * Method for adding just one beneficial owner
	 * @param beneficialOwner
	 * @param company
	 */
	private void addBeneficialOwnerToCompany(BeneficialOwner beneficialOwner, Company company) {
			
		if(!MockHelper.checkIfBeneficialOwnerExists(company, beneficialOwner.getName().trim())) {
			long ibBeneficialOwner = MockHelper.getIdForBeneficialOwner(company);
			
			beneficialOwner.setId(ibBeneficialOwner);
			company.addBeneficialOwner(beneficialOwner);
		}	
	}
			
}
