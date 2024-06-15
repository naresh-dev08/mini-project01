package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entities.InsurancePolicy;

public interface InsurancePolicyRepo extends JpaRepository<InsurancePolicy, Integer> {

}
