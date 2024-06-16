package com.nt.ms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entities.InsurancePolicy;
import com.nt.service.IInsurancePolicyMgmtService;

@RestController
@RequestMapping("/policy")
public class InsurancePolicyRestApi {

	@Autowired
	public IInsurancePolicyMgmtService insurancePolicyMgmtService;
	
	@PostMapping("/register")
	public ResponseEntity<String> saveInsurancePolicy(@RequestBody InsurancePolicy insurancePolicy){
		//use service
		try {
			String msg = insurancePolicyMgmtService.registerInsurancePolicy(insurancePolicy);
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllPolicies() {
		//user service
		try {
			List<InsurancePolicy> insurancePolicies = insurancePolicyMgmtService.showAllInsurancePolicies();
			return new ResponseEntity<List<InsurancePolicy>>(insurancePolicies, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findPolicy/{policyId}")
	public ResponseEntity<?> findPolicyById(@PathVariable Integer policyId) {
		//user service
		try {
			InsurancePolicy insurancePolicy = insurancePolicyMgmtService.showInsurancePolicyById(policyId);
			return new ResponseEntity<InsurancePolicy>(insurancePolicy, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deletePolicy/{policyId}")
	public ResponseEntity<?> deletePolicyById(@PathVariable Integer policyId) {
		//user service
		try {
			String msg = insurancePolicyMgmtService.deleteInsurancePolicyById(policyId);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
