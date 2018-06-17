package com.dro.springprojects.testcompany.repository.utils;

import java.util.Comparator;
import java.util.Optional;
import java.util.TreeSet;
import com.dro.springprojects.testcompany.entity.BeneficialOwner;
import com.dro.springprojects.testcompany.entity.Company;

public class MockHelper {

	/**
	 * This method generates an id for a company. As the implementation of the company
	 * is more detailed than the one for the beneficial owners, it is more complex the 
	 * way to deal with the ids
	 * @param companies
	 * @return
	 */
	public static Long generateId(TreeSet<Company> companies) {		
		long id = companies.size() == 0 ? 0 : companies.last().getId();
		return ++id;
	}
	
	/**
	 * This method checks if a beneficial owner exists before inserting it inside a company
	 * @param company
	 * @param name
	 * @return
	 */
	public static boolean checkIfBeneficialOwnerExists(Company company, String name) {
		Optional<BeneficialOwner> beneficialOwnerOptional = company.getBeneficialOwners()
				.stream()
				.filter(s -> s.getName().equals(name))
				.findFirst();
		
		if (beneficialOwnerOptional.isPresent()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * This method returns an incremental id for a beneficial owner on a company
	 * @param company
	 * @return
	 */
	public static long getIdForBeneficialOwner(Company company) {
		Optional<BeneficialOwner> beneficialOwnerOptional = company.getBeneficialOwners()
				.stream().max(Comparator.comparing(BeneficialOwner::getId));
		if (beneficialOwnerOptional.isPresent()) {
			return beneficialOwnerOptional.get().getId() + 1;
		}

		return 0;
	}
}
