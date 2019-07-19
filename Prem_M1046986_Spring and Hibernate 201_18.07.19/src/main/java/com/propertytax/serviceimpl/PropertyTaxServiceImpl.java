package com.propertytax.serviceimpl;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.propertytax.daoimpl.PropertyTaxDaoImpl;
import com.propertytax.dto.RequestDTO;
import com.propertytax.entity.ResidenceTypeEntity;
import com.propertytax.entity.UavEntity;
import com.propertytax.entity.ZonalReportEntity;
import com.propertytax.exception.PropertyTaxApplicationException;
import com.propertytax.service.PropertyTaxService;

/**
 * The Class PropertyTaxServiceImpl.
 *
 * @author Prem Kumar
 */
@Service
@Transactional
public class PropertyTaxServiceImpl implements PropertyTaxService {

	/** The Constant PROPERTY_TAX_ERROR. */
	private static final String PROPERTY_TAX_ERROR = "property.tax.error";

	/** The Constant PROPERTY_TAX_SUCCESS. */
	private static final String PROPERTY_TAX_SUCCESS = "property.tax.success";

	/** The Constant OWNER. */
	private static final String OWNER = "Owner";

	/** The property tax dao impl. */
	@Autowired
	private PropertyTaxDaoImpl propertyTaxDaoImpl;

	/** The environment. */
	@Autowired
	Environment environment;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.propertytax.service.PropertyTaxService#viewZonalRecords()
	 */
	@Override
	public List<ZonalReportEntity> viewZonalRecords() {
		return propertyTaxDaoImpl.findAllFromZonalReportEntity();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.propertytax.service.PropertyTaxService#getAllZonesFromDB()
	 */
	@Override
	public List<String> getAllZonesFromDB() {
		List<String> zonalList = new ArrayList<>();
		List<ZonalReportEntity> listOfZonesFromDB = propertyTaxDaoImpl.findAllFromZonalReportEntity();
		listOfZonesFromDB.forEach(list -> {
			zonalList.add(list.getZoneCategory());
		});
		return zonalList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.propertytax.service.PropertyTaxService#getResidentialTypeByZonalId(java.
	 * lang.String)
	 */
	@Override
	public List<String> getResidentialTypeByZonalId(String zonalId) {
		List<String> ResidentialTypeList = new ArrayList<>();
		ZonalReportEntity zoneDetails;
		zoneDetails = propertyTaxDaoImpl.findByZoneEntity(zonalId);
		zoneDetails.getResidenceTypeEntities().forEach(value -> {
			ResidentialTypeList.add(value.getResName());
		});
		return ResidentialTypeList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.propertytax.service.PropertyTaxService#residentialTaxCalculation(com.
	 * propertytax.dto.RequestDTO)
	 */
	@Override
	public double residentialTaxCalculation(RequestDTO requestDto) {
		ZonalReportEntity zonalCategory = propertyTaxDaoImpl.findByZoneEntity(requestDto.getZoneId());
		ResidenceTypeEntity resType = propertyTaxDaoImpl.findByResNameAndZonalReportZonalId(requestDto.getPropertyId(),
				zonalCategory.getZonalId());
		UavEntity uavEntity = propertyTaxDaoImpl.findByResidenceTypeEntityResIdAndStatus(resType.getResId(),
				requestDto.getStatus());
		double tax1;
		double tax2;
		double tax3;
		double tax4;
		double tax5;
		tax1 = (float) (Math.round(requestDto.getAreaId() * 100.0) / 100.0 * (uavEntity.getUnitAreaValue()) * 10);
		int yearDepriciationValue = (Year.now().getValue()) - Integer.parseInt(requestDto.getYearId());
		if (yearDepriciationValue > 60) {
			tax2 = tax1 - (60 * tax1) / 100;
			tax3 = tax2 + (tax2 * 20) / 100; // 20% of the tax2
			tax4 = tax3 + (tax3 * 24) / 100; // 24% of the tax3
			tax5 = tax3 + tax4;
		} else {
			tax2 = tax1 - (yearDepriciationValue * tax1) / 100;
			tax3 = tax2 + (tax2 * 20) / 100; // 20% of the tax2
			tax4 = tax3 + (tax3 * 24) / 100; // 24% of the tax3
			tax5 = tax3 + tax4;
		}
		return Math.round(tax5 * 100.0) / 100.0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.propertytax.service.PropertyTaxService#payResidentialTax(com.propertytax.
	 * dto.RequestDTO)
	 */
	@Override
	public String payResidentialTax(RequestDTO requestDto) throws PropertyTaxApplicationException {
		try {
			ZonalReportEntity zonalId = propertyTaxDaoImpl.findByZoneEntity(requestDto.getZoneId());
			if (OWNER.equalsIgnoreCase(requestDto.getStatus())) {
				double ownerTotal = zonalId.getOwnerTotal() + Math.round(requestDto.getTotalId() * 100.0) / 100.0;
				zonalId.setOwnerTotal(ownerTotal);
				propertyTaxDaoImpl.saveUavEntity(zonalId);
			} else {
				double tenantTotal = zonalId.getTenantTotal() + Math.round(requestDto.getTotalId() * 100.0) / 100.0;
				zonalId.setTenantTotal(tenantTotal);
				propertyTaxDaoImpl.saveUavEntity(zonalId);
			}
			return environment.getProperty(PROPERTY_TAX_SUCCESS);
		} catch (Exception e) {
			throw new PropertyTaxApplicationException(environment.getProperty(PROPERTY_TAX_ERROR));
		}

	}

}
