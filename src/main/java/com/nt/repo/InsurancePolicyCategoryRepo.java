package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entities.InsurancePolicyCategory;

public interface InsurancePolicyCategoryRepo extends JpaRepository<InsurancePolicyCategory, Integer> {

}
