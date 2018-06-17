package com.dro.springprojects.testcompany.repository;

import java.util.List;

import com.dro.springprojects.testcompany.entity.BeneficialOwner;
import com.dro.springprojects.testcompany.entity.Company;

public interface CompanyRepository {

	Company save(Company company);

	List<Company> getAll();

	Company getCompanyById(long id);

	Company updateCompany(Company company);

	Company addBeneficialOwnersToCompany(BeneficialOwner[] beneficialOwners, long id);
}
