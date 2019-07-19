package com.propertytax.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class {@link ZonalReportEntity}
 * 
 * @author Prem Kumar
 *
 */
@Entity
@Data
@EqualsAndHashCode
@Table(name = "zonal_report")
public class ZonalReportEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Value to Hold zonal Id **/
	@Id
	@Column(name = "zonal_id")
	private int zonalId;

	/** Value to Hold zone Category **/
	@Column(name = "zone_Category")
	private String zoneCategory;

	/** Value to Hold tenant Total **/
	@Column(name = "tenant_Total")
	private double tenantTotal;

	/** Value to Hold owner Total **/
	@Column(name = "owner_total")
	private double ownerTotal;

	/** Value to Hold residence Type Entities **/
	@OneToMany(mappedBy = "zonalReport")
	private List<ResidenceTypeEntity> residenceTypeEntities;
}
