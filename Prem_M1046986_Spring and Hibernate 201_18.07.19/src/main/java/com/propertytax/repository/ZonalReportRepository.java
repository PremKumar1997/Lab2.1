package com.propertytax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.propertytax.entity.ZonalReportEntity;

/**
 * 
 * The Repository for ZonalReportRepository.
 * 
 * @author Prem Kumar
 *
 */
public interface ZonalReportRepository extends JpaRepository<ZonalReportEntity, String> {

	/**
	 * Find by zone category.
	 *
	 * @param zoneCategory the zone category
	 * @return the zonal report entity
	 */
	ZonalReportEntity findByZoneCategory(String zoneCategory);

}
