/*
 * 
 */
package com.propertytax.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.propertytax.dao.PropertyTaxDao;
import com.propertytax.entity.ResidenceTypeEntity;
import com.propertytax.entity.UavEntity;
import com.propertytax.entity.ZonalReportEntity;
import com.propertytax.repository.ResidenceTypeRepository;
import com.propertytax.repository.UavRepository;
import com.propertytax.repository.ZonalReportRepository;

/**
 * The Class PropertyTaxDaoImpl.
 *
 * @author Prem Kumar
 */
@Service
public class PropertyTaxDaoImpl implements PropertyTaxDao {

	/** The zonal repository. */
	@Autowired
	ZonalReportRepository zonalRepository;

	/** The residence type repository. */
	@Autowired
	ResidenceTypeRepository residenceTypeRepository;

	/** The uav repository. */
	@Autowired
	UavRepository uavRepository;

	/* (non-Javadoc)
	 * @see com.propertytax.dao.PropertyTaxDao#findAllFromZonalReportEntity()
	 */
	@Override
	public List<ZonalReportEntity> findAllFromZonalReportEntity() {
		return zonalRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.propertytax.dao.PropertyTaxDao#findByZoneEntity(java.lang.String)
	 */
	@Override
	public ZonalReportEntity findByZoneEntity(String zoneCategory) {
		return zonalRepository.findByZoneCategory(zoneCategory);
	}

	/* (non-Javadoc)
	 * @see com.propertytax.dao.PropertyTaxDao#findByResNameAndZonalReportZonalId(java.lang.String, int)
	 */
	@Override
	public ResidenceTypeEntity findByResNameAndZonalReportZonalId(String resName, int zonalId) {
		return residenceTypeRepository.findByResNameAndZonalReportZonalId(resName, zonalId);
	}

	/* (non-Javadoc)
	 * @see com.propertytax.dao.PropertyTaxDao#findByResidenceTypeEntityResIdAndStatus(int, java.lang.String)
	 */
	@Override
	public UavEntity findByResidenceTypeEntityResIdAndStatus(int resId, String statusId) {
		return uavRepository.findByResidenceTypeEntityResIdAndStatus(resId, statusId);
	}

	/* (non-Javadoc)
	 * @see com.propertytax.dao.PropertyTaxDao#saveUavEntity(com.propertytax.entity.ZonalReportEntity)
	 */
	@Override
	public void saveUavEntity(ZonalReportEntity zonalReportEntity) {
		zonalRepository.save(zonalReportEntity);
	}

}
