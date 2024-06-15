package com.nt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entities.InsurancePolicy;
import com.nt.entities.InsurancePolicyCategory;
import com.nt.repo.InsurancePolicyCategoryRepo;
import com.nt.repo.InsurancePolicyRepo;

@Service
public class InsurancePolicyMgmtServiceImpl implements IInsurancePolicyMgmtService {
	
	@Autowired
	public InsurancePolicyRepo insurancePolicyRepo;
	
	@Autowired
	public InsurancePolicyCategoryRepo insurancePolicyCategoryRepo;

	@Override
	public String registerInsurancePolicy(InsurancePolicy insurancePolicy) {
		//save the Obj
		InsurancePolicy saved = insurancePolicyRepo.save(insurancePolicy);
		
		/* if (saved.getPolicyNumber() != null)
			return "Insurance Policy is saved: "+saved.getPolicyNumber();
		else 
			return "Problem in saving the InsurancePolicy";  */
		
		return saved.getPolicyNumber() != null?"Insurance Policy is saved: "+saved.getPolicyNumber():"Problem in saving the InsurancePolicy";
	}

	@Override
	public List<InsurancePolicy> showAllInsurancePolicies() {
		return insurancePolicyRepo.findAll();
	}

	@Override
	public InsurancePolicy showInsurancePolicyById(Integer policyId) {
		
	/*	Optional<InsurancePolicy> opt = insurancePolicyRepo.findById(policyId);
		if (opt.isPresent())
			return opt.get();
		   else
			throw new IllegalArgumentException("Policy not found");  */
		
		return insurancePolicyRepo.findById(policyId).orElseThrow(() -> new IllegalArgumentException("Policy not found"));
		
	}

	@Override
	public String updateInsurancePolicy(InsurancePolicy insurancePolicy) {
		Optional<InsurancePolicy> opt = insurancePolicyRepo.findById(insurancePolicy.getPolicyNumber());
		if (opt.isPresent()) {
			//update the obj
			insurancePolicyRepo.save(insurancePolicy);
			return insurancePolicy.getPolicyNumber()+" Policy is updated";
		}else {
			return "Policy ID: " + insurancePolicy.getPolicyNumber() + " is not found";
		}
	}

	@Override
	public String deleteInsurancePolicyById(Integer policyId) {
		Optional<InsurancePolicy> opt = insurancePolicyRepo.findById(policyId);
		if (opt.isPresent()) {
			//update the obj
			insurancePolicyRepo.deleteById(policyId);
			return policyId +" Policy is deleted";
		}else {
			return "Policy ID: " + policyId + " is not found";
		}
	}

	@Override
	public Map<Integer, String> getInsurancePolicyCategories() {
		// get all policy categories
		List<InsurancePolicyCategory> categories = insurancePolicyCategoryRepo.findAll();
		Map<Integer, String> categoriesMap = new HashMap<Integer, String>();
		categories.forEach(categoryInner -> {
			categoriesMap.put(categoryInner.getCategoryId(), categoryInner.getPolicyType());
		});
		return categoriesMap;
	}

	
}
