package com.propertytax.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class ResidenceTypeEntity.
 * 
 * @author Prem Kumar
 *
 */
@Entity
@EqualsAndHashCode
@Data
@Table(name = "residenceType")
public class ResidenceTypeEntity {

	/** Value to Hold residence Id **/
	@Id
	@Column(name = "res_id")
	private int resId;

	/** Value to Hold residence Name **/
	@Column(name = "res_name")
	private String resName;

	/** Value to Hold propertyEntities **/
	@OneToMany(mappedBy = "residenceTypeEntity")
	private List<UavEntity> propertyEntities;

	/** Value to Hold zonal Report **/
	@ManyToOne
	@JoinColumn(name = "zonal_id", insertable = false, updatable = false)
	@JsonIgnore
	private ZonalReportEntity zonalReport;

}
