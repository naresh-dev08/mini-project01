package com.nt.service;

import java.util.List;
import java.util.Map;

import com.nt.entities.InsurancePolicy;

public interface IInsurancePolicyMgmtService {
	
	public String registerInsurancePolicy(InsurancePolicy insurancePolicy);
	public List<InsurancePolicy> showAllInsurancePolicies();
	public InsurancePolicy showInsurancePolicyById(Integer policyId);
	public String updateInsurancePolicy(InsurancePolicy insurancePolicy);
	public String deleteInsurancePolicyById(Integer policyId);
	public Map<Integer, String> getInsurancePolicyCategories();

}
