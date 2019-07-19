package com.propertytax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.propertytax.entity.UavEntity;

/**
 * 
 * The Repository for UavRepository.
 * 
 * @author Prem Kumar
 *
 */
public interface UavRepository extends JpaRepository<UavEntity, String> {

	/**
	 * Find by residence type entity res id and status.
	 *
	 * @param resId the res id
	 * @param statusId the status id
	 * @return the uav entity
	 */
	UavEntity findByResidenceTypeEntityResIdAndStatus(int resId, String statusId);

}
