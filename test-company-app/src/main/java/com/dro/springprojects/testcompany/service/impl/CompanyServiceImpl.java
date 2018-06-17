package com.dro.springprojects.testcompany.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dro.springprojects.testcompany.entity.BeneficialOwner;
import com.dro.springprojects.testcompany.entity.Company;
import com.dro.springprojects.testcompany.repository.CompanyRepository;
import com.dro.springprojects.testcompany.service.CompanyService;

/**
 * Service layer class without any logic, that just redirect to the repository for saving, 
 * getting or updating the data
 * @author david
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
    private CompanyRepository companyRepository;
	
	/**
	 * Method that saves a new company. A check for an existing company is
	 * done before the insertion
	 */
	@Override
	public Company save(Company entity) {
        return companyRepository.save(entity);
	}

	/**
	 * Method for getting all the companies
	 */
	@Override
	public List<Company> getAll() {
		return companyRepository.getAll();
	}

	/**
	 * Method for getting a company by its id, and it returns null in case it does not exists
	 */
	@Override
	public Company getCompanyById(long id) {
		return companyRepository.getCompanyById(id);
	}

	/**
	 * Method for updating the data of a company. It retrieves the company, checks if it 
	 * exists and in that case it substitutes the old data by the new
	 */
	@Override
	public Company updateCompany(Company company) {
		return companyRepository.updateCompany(company);
	}

	/**
	 * This method adds all the beneficial owners at one after checking the existence of the company. 
	 * It calls another method that adds the objects one by one
	 */
	@Override
	public Company addBeneficialOwnersToCompany(BeneficialOwner[] beneficialOwners, long id) {
		return companyRepository.addBeneficialOwnersToCompany(beneficialOwners, id);
	}

}
