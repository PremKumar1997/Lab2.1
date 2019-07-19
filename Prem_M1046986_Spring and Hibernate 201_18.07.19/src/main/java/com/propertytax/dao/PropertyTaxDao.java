package com.propertytax.dao;

import java.util.List;

import com.propertytax.entity.ResidenceTypeEntity;
import com.propertytax.entity.UavEntity;
import com.propertytax.entity.ZonalReportEntity;

/**
 * The Interface PropertyTaxDao.
 *
 * @author Prem Kumar
 */
public interface PropertyTaxDao {

	/**
	 * Find all from zonal report entity.
	 *
	 * @return the list
	 */
	public List<ZonalReportEntity> findAllFromZonalReportEntity();

	/**
	 * Find by zone entity.
	 *
	 * @param zoneCategory the zone category
	 * @return the zonal report entity
	 */
	public ZonalReportEntity findByZoneEntity(String zoneCategory);

	/**
	 * Find by res name and zonal report zonal id.
	 *
	 * @param resName the res name
	 * @param zonalId the zonal id
	 * @return the residence type entity
	 */
	public ResidenceTypeEntity findByResNameAndZonalReportZonalId(String resName, int zonalId);

	/**
	 * Find by residence type entity res id and status.
	 *
	 * @param resId    the res id
	 * @param statusId the status id
	 * @return the uav entity
	 */
	public UavEntity findByResidenceTypeEntityResIdAndStatus(int resId, String statusId);

	/**
	 * Save uav entity.
	 *
	 * @param zonalReportEntity the zonal report entity
	 */
	public void saveUavEntity(ZonalReportEntity zonalReportEntity);
}
