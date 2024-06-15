package com.nt.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "JRTP_INSURANCEPOLICY")
@Data
public class InsurancePolicy {

	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "category_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@Column(name = "POLICY_ID")
	private Integer policyNumber;
	@Column(name = "POLICY_NAME")
	private String policyName;
	@Column(name = "START_DATE")
    private Date startDate;
	@Column(name = "END_DATE")
    private Date endDate;
	@Column(name = "POLICY_TYPE", length = 20)
    private String policyType;
	
    // Coverage Details
	@Column(name = "COVERAGE_AMOUNT")
    private double coverageAmount;
	@Column(name = "PREMIUM_AMOUNT")
    private double premiumAmount;
	@Column(name = "COVERAGE_TYPE")
    private String coverageType; // e.g., Comprehensive, Third-Party
	
}
