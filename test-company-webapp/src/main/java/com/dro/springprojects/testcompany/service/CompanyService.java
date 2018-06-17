package com.dro.springprojects.testcompany.service;

import java.util.List;

import com.dro.springprojects.testcompany.entity.BeneficialOwner;
import com.dro.springprojects.testcompany.entity.Company;

public interface CompanyService {

	Company save(Company entity);

	List<Company> getAll();

	Company getCompanyById(long id);

	Company updateCompany(Company company);

	Company addBeneficialOwnersToCompany(BeneficialOwner[] beneficialOwners, long id);
}
