package com.nt.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "JRTP_INSURANCEPOLICY_CATEGORY")
@Data
public class InsurancePolicyCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CATEGORY_ID")
	private Integer categoryId;
	@Column(name = "POLICY_TYPE")
    private String policyType;
	@Column(name = "DESCRIPTION")
    private String description;
}
