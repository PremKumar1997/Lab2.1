package com.propertytax.service;

import java.util.List;

import com.propertytax.dto.RequestDTO;
import com.propertytax.entity.ZonalReportEntity;
import com.propertytax.exception.PropertyTaxApplicationException;

/**
 * The interface for {@link PropertyTaxService}.
 * 
 * @author Prem Kumar
 *
 */
public interface PropertyTaxService {

	/**
	 * View zonal records.
	 *
	 * @return the list
	 */
	public List<ZonalReportEntity> viewZonalRecords();

	/**
	 * Gets the all zones from DB.
	 *
	 * @return the all zones from DB
	 */
	public List<String> getAllZonesFromDB();

	/**
	 * Gets the residential type by zonal id.
	 *
	 * @param zonalId the zonal id
	 * @return the residential type by zonal id
	 */
	public List<String> getResidentialTypeByZonalId(String zonalId);

	/**
	 * Residential tax calculation.
	 *
	 * @param requestDto the request dto
	 * @return the double
	 */
	public double residentialTaxCalculation(RequestDTO requestDto);

	/**
	 * Pay residential tax.
	 *
	 * @param requestDto the request dto
	 * @return the string
	 * @throws PropertyTaxApplicationException the property tax application
	 *                                         exception
	 */
	public String payResidentialTax(RequestDTO requestDto) throws PropertyTaxApplicationException;
}
