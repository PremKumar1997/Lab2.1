package com.propertytax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.propertytax.entity.ResidenceTypeEntity;

/**
 * The Repository for ResidenceTypeRepository.
 * 
 * @author Prem Kumar
 *
 */
public interface ResidenceTypeRepository extends JpaRepository<ResidenceTypeEntity, String> {

	/**
	 * Find by res name and zonal report zonal id.
	 *
	 * @param resName the res name
	 * @param zonalId the zonal id
	 * @return the residence type entity
	 */
	ResidenceTypeEntity findByResNameAndZonalReportZonalId(String resName, int zonalId);

}
