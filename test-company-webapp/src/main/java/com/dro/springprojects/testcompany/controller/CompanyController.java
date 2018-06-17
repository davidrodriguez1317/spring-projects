package com.dro.springprojects.testcompany.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dro.springprojects.testcompany.entity.BeneficialOwner;
import com.dro.springprojects.testcompany.entity.Company;
import com.dro.springprojects.testcompany.exceptions.ResourceAlreadyExistsException;
import com.dro.springprojects.testcompany.exceptions.ResourceNotFoundException;
import com.dro.springprojects.testcompany.exceptions.ResourceNotValidatedException;
import com.dro.springprojects.testcompany.service.CompanyService;

/**
 * This class is the point of entry for the TestCompanyApp.
 * It has GET, POST and PUT methods on it for handling companies
 * and beneficial owners
 * @author david
 *
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	/**
	 * Method to create a company. The id is not received and it is generated incrementally
	 * @param company
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			throw new ResourceNotValidatedException("The object is not well formed for the company with name " + company.getName());
		}
		
		Company companyCreated = companyService.save(company);
		
		if (companyCreated == null) {
			throw new ResourceAlreadyExistsException("It already exist a company with the name " + company.getName());
		}
		
		return new ResponseEntity<>(companyCreated, HttpStatus.CREATED);
	}

	/**
	 * Method to show all the companies with all the details no each of them
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Company>> getCompanies() {
		List<Company> companies = companyService.getAll();
		return new ResponseEntity<>(companies, HttpStatus.OK);
	}
	
	/**
	 * Method to get the details on the company with the id we receive on the URL
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> getCompanyById(@PathVariable long id) {
		Company company = companyService.getCompanyById(id);
		
		if (company == null) {
			throw new ResourceNotFoundException("No company with id " + id);
		}
		return new ResponseEntity<>(company, HttpStatus.OK);
	}
	
	/**
	 * Method tu update the attributes of one company using its id
	 * @param company
	 * @param id
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value="/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> updateCompanyById(@Valid @RequestBody Company company, @PathVariable long id, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			throw new ResourceNotValidatedException("The object is not well formed for the company with name " + company.getName());
		}
		
		Company companyUpdated = companyService.updateCompany(company);
		
		if (companyUpdated == null) {
			throw new ResourceNotFoundException("No company with id " + id);
		}
		return new ResponseEntity<>(companyUpdated, HttpStatus.OK);
	}
	
	/**
	 * Method to update a company adding some beneficial owners to it
	 * @param beneficialOwners
	 * @param id
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value="/beneficialowner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> addBeneficialOwnersToCompany(@Valid @RequestBody BeneficialOwner[] beneficialOwners, @PathVariable long id, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			throw new ResourceNotValidatedException("The object is not well formed for the beneficial owners");
		}
		
		Company companyUpdated = companyService.addBeneficialOwnersToCompany(beneficialOwners, id);
		
		if (companyUpdated == null) {
			throw new ResourceNotFoundException("No company with id " + id);
		}
		
		return new ResponseEntity<>(companyUpdated, HttpStatus.OK);
	}

}
