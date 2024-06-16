package com.nt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.nt.config.AppConfigProperties;
import com.nt.constants.InsurancePolicyConstantMessages;
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

	private final Map<String, String> messages;

	@Autowired
	public InsurancePolicyMgmtServiceImpl(AppConfigProperties appConfigProperties) {
		messages= appConfigProperties.getMessages();
	}

	@Override
	public String registerInsurancePolicy(InsurancePolicy insurancePolicy) {
		//save the Obj
		InsurancePolicy saved = insurancePolicyRepo.save(insurancePolicy);
		
		/* if (saved.getPolicyNumber() != null)
			return "Insurance Policy is saved: "+saved.getPolicyNumber();
		else 
			return "Problem in saving the InsurancePolicy";  */
		
		return saved.getPolicyNumber() != null?messages.get(InsurancePolicyConstantMessages.SAVE_SUCCESS) +" "+ saved.getPolicyNumber():messages.get(InsurancePolicyConstantMessages.SAVE_FAILURE);
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
		
		return insurancePolicyRepo.findById(policyId).orElseThrow(() -> new IllegalArgumentException(messages.get(InsurancePolicyConstantMessages.FIND_BY_ID_FAILURE)));
		
	}

	@Override
	public String updateInsurancePolicy(InsurancePolicy insurancePolicy) {
		Optional<InsurancePolicy> opt = insurancePolicyRepo.findById(insurancePolicy.getPolicyNumber());
		if (opt.isPresent()) {
			//update the obj
			insurancePolicyRepo.save(insurancePolicy);
			return insurancePolicy.getPolicyNumber() +" "+ messages.get(InsurancePolicyConstantMessages.UPDATE_SUCCESS);
		}else {
			return "Policy ID: " + insurancePolicy.getPolicyNumber() +" "+ messages.get(InsurancePolicyConstantMessages.UPDATE_FAILURE);
		}
	}

	@Override
	public String deleteInsurancePolicyById(Integer policyId) {
		Optional<InsurancePolicy> opt = insurancePolicyRepo.findById(policyId);
		if (opt.isPresent()) {
			//update the obj
			insurancePolicyRepo.deleteById(policyId);
			return policyId +" "+ messages.get(InsurancePolicyConstantMessages.DELETE_SUCCESS);
		}else {
			return "Policy ID: " + policyId +" "+ messages.get(InsurancePolicyConstantMessages.DELETE_FAILURE);
		}
	}

	@Override
	public Map<Integer, String> getInsurancePolicyCategories() {
		// get all policy categories
		List<InsurancePolicyCategory> categories = insurancePolicyCategoryRepo.findAll();
		/*Map<Integer, String> categoriesMap = new HashMap<Integer, String>();
		categories.forEach(categoryInner -> {
			categoriesMap.put(categoryInner.getCategoryId(), categoryInner.getPolicyType());
		});*/
		Map<Integer, String> categoriesMapStream = categories.stream()
				.collect(Collectors.toMap(InsurancePolicyCategory::getCategoryId, InsurancePolicyCategory::getPolicyType));
		return categoriesMapStream;
	}

	
}
