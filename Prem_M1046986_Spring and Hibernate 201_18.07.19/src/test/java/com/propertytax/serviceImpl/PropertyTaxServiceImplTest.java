package com.propertytax.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.WebApplicationContext;

import com.propertytax.daoimpl.PropertyTaxDaoImpl;
import com.propertytax.dto.RequestDTO;
import com.propertytax.entity.ResidenceTypeEntity;
import com.propertytax.entity.UavEntity;
import com.propertytax.entity.ZonalReportEntity;
import com.propertytax.repository.ResidenceTypeRepository;
import com.propertytax.repository.UavRepository;
import com.propertytax.repository.ZonalReportRepository;
import com.propertytax.serviceimpl.PropertyTaxServiceImpl;

/**
 * The Class PropertyTaxServiceImplTest.
 *
 * @author Prem Kumar
 */
@ContextConfiguration(classes = { TestContext.class, WebApplicationContext.class })
@RunWith(SpringRunner.class)
@WebMvcTest
public class PropertyTaxServiceImplTest {

	/** The property tax service impl. */
	@InjectMocks
	PropertyTaxServiceImpl propertyTaxServiceImpl;

	/** The zonal repository. */
	@Mock
	ZonalReportRepository zonalRepository;

	/** The residence type repository. */
	@Mock
	ResidenceTypeRepository residenceTypeRepository;

	/** The uav repository. */
	@Mock
	UavRepository uavRepository;
	
	/** The property tax dao impl. */
	@InjectMocks
	private PropertyTaxDaoImpl propertyTaxDaoImpl;
	
	/** The environment. */
	@Autowired
	Environment environment;
	
	/**
	 * Before.
	 */
	@Before
	public void before() {
		ReflectionTestUtils.setField(propertyTaxServiceImpl, "environment", environment);
		ReflectionTestUtils.setField(propertyTaxServiceImpl, "propertyTaxDaoImpl", propertyTaxDaoImpl);
		ReflectionTestUtils.setField(propertyTaxDaoImpl, "residenceTypeRepository", residenceTypeRepository);
		ReflectionTestUtils.setField(propertyTaxDaoImpl, "uavRepository", uavRepository);
		ReflectionTestUtils.setField(propertyTaxDaoImpl, "zonalRepository", zonalRepository);
	}

	/**
	 * Test view zonal records.
	 */
	@Test
	public void testViewZonalRecords() {
		List<ZonalReportEntity> value = new ArrayList<ZonalReportEntity>();
		ZonalReportEntity zonalValue = new ZonalReportEntity();
		zonalValue.setOwnerTotal(120.00);
		zonalValue.setTenantTotal(1234.349);
		zonalValue.setZoneCategory("Zone A");
		value.add(zonalValue);
		Mockito.when(zonalRepository.findAll()).thenReturn(value);
		ReflectionTestUtils.invokeMethod(propertyTaxServiceImpl, "viewZonalRecords");
	}

	/**
	 * Test get all zones from DB.
	 */
	@Test
	public void testGetAllZonesFromDB() {
		List<ZonalReportEntity> value = new ArrayList<ZonalReportEntity>();
		ZonalReportEntity zonalValue = new ZonalReportEntity();
		zonalValue.setOwnerTotal(120.00);
		zonalValue.setTenantTotal(1234.349);
		zonalValue.setZoneCategory("Zone A");
		value.add(zonalValue);
		Mockito.when(zonalRepository.findAll()).thenReturn(value);
		ReflectionTestUtils.invokeMethod(propertyTaxServiceImpl, "getAllZonesFromDB");
	}

	/**
	 * Test residential tax calculation.
	 */
	@Test
	public void testResidentialTaxCalculation() {
		RequestDTO dto = new RequestDTO();
		dto.setAreaId(100.00);
		dto.setYearId("2004");
		dto.setStatus("Owner");
		dto.setZoneId("Zone A");
		dto.setPropertyId("RCC buildings");
		ZonalReportEntity zoneEntity = new ZonalReportEntity();
		zoneEntity.setZonalId(1);
		Mockito.when(zonalRepository.findByZoneCategory(Mockito.anyString())).thenReturn(zoneEntity);
		ResidenceTypeEntity value =new ResidenceTypeEntity();
		value.setResId(2);
		Mockito.when(residenceTypeRepository
				.findByResNameAndZonalReportZonalId(Mockito.anyString(), Mockito.anyInt())).thenReturn(value );
		UavEntity uanValue =new UavEntity();
		uanValue.setUnitAreaValue(2.34);
		Mockito.when(uavRepository.findByResidenceTypeEntityResIdAndStatus(Mockito.anyInt(),
				Mockito.anyString())).thenReturn(uanValue);
		ReflectionTestUtils.invokeMethod(propertyTaxServiceImpl, "residentialTaxCalculation", dto);
	}

	/**
	 * Test residential tax calculation greater than 60 years.
	 */
	@Test
	public void testResidentialTaxCalculation_greaterThan60Years() {
		RequestDTO dto = new RequestDTO();
		dto.setAreaId(100.00);
		dto.setYearId("1950");
		dto.setStatus("Owner");
		dto.setZoneId("Zone A");
		dto.setPropertyId("RCC buildings");
		ZonalReportEntity zoneEntity = new ZonalReportEntity();
		zoneEntity.setZonalId(1);
		Mockito.when(zonalRepository.findByZoneCategory(Mockito.anyString())).thenReturn(zoneEntity);
		ResidenceTypeEntity value =new ResidenceTypeEntity();
		value.setResId(2);
		Mockito.when(residenceTypeRepository
				.findByResNameAndZonalReportZonalId(Mockito.anyString(), Mockito.anyInt())).thenReturn(value );
		UavEntity uanValue =new UavEntity();
		uanValue.setUnitAreaValue(2.34);
		Mockito.when(uavRepository.findByResidenceTypeEntityResIdAndStatus(Mockito.anyInt(),
				Mockito.anyString())).thenReturn(uanValue);
		ReflectionTestUtils.invokeMethod(propertyTaxServiceImpl, "residentialTaxCalculation", dto);
	}
	
	/**
	 * Test pay residential tax owner.
	 */
	@Test
	public void testPayResidentialTax_Owner() {
		RequestDTO dto = new RequestDTO();
		dto.setAreaId(100.00);
		dto.setYearId("2004");
		dto.setStatus("Owner");
		dto.setZoneId("Zone A");
		dto.setPropertyId("RCC buildings");
		dto.setTotalId(32435.32);
		ZonalReportEntity zoneEntity = new ZonalReportEntity();
		zoneEntity.setZonalId(1);
		zoneEntity.setOwnerTotal(2023.12);
		Mockito.when(zonalRepository.findByZoneCategory(Mockito.anyString())).thenReturn(zoneEntity);
		ReflectionTestUtils.invokeMethod(propertyTaxServiceImpl, "payResidentialTax", dto);
	}
	
	/**
	 * Test pay residential tax tenant.
	 */
	@Test
	public void testPayResidentialTax_Tenant() {
		RequestDTO dto = new RequestDTO();
		dto.setAreaId(100.00);
		dto.setYearId("2004");
		dto.setStatus("Tenanted");
		dto.setZoneId("Zone A");
		dto.setPropertyId("RCC buildings");
		dto.setTotalId(32435.32);
		ZonalReportEntity zoneEntity = new ZonalReportEntity();
		zoneEntity.setZonalId(1);
		zoneEntity.setTenantTotal(2023.12);
		Mockito.when(zonalRepository.findByZoneCategory(Mockito.anyString())).thenReturn(zoneEntity);
		ReflectionTestUtils.invokeMethod(propertyTaxServiceImpl, "payResidentialTax", dto);
	}
	
	/**
	 * Test get residential type by zonal id.
	 */
	@Test
	public void testGetResidentialTypeByZonalId() {
		ZonalReportEntity zoneEntity = new ZonalReportEntity();
		zoneEntity.setZonalId(1);
		zoneEntity.setTenantTotal(2023.12);
		List<ResidenceTypeEntity> residenceTypeEntities = new ArrayList<>();
		ResidenceTypeEntity  entity=new ResidenceTypeEntity();
		entity.setResName("RCC buildings");
		ResidenceTypeEntity  entity1=new ResidenceTypeEntity();
		entity1.setResName("RCC buildings with cement or red-oxide flooring");
		ResidenceTypeEntity  entity2=new ResidenceTypeEntity();
		entity2.setResName("Tiled/Sheet of all kinds");
		residenceTypeEntities.add(entity);
		residenceTypeEntities.add(entity1);
		residenceTypeEntities.add(entity2);
		zoneEntity.setResidenceTypeEntities(residenceTypeEntities);
		Mockito.when(zonalRepository.findByZoneCategory(Mockito.anyString())).thenReturn(zoneEntity);
		ReflectionTestUtils.invokeMethod(propertyTaxServiceImpl, "getResidentialTypeByZonalId", "Zone A");
	}
}
