package com.propertytax.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * This class will contain data of UavEntity.
 * 
 * @author Prem Kumar
 *
 */
@Entity
@Data
@EqualsAndHashCode
@Table(name = "uav")
public class UavEntity {

	/** Value to Hold uav Id **/
	@Id
	@Column(name = "uav_id")
	private int uavId;

	/** Value to Hold unit Area Value **/
	@Column(name = "unit_area_value")
	private double unitAreaValue;

	/** Value to Hold status **/
	@Column(name = "status")
	private String status;

	/** Value to Hold residence Type Entity **/
	@ManyToOne
	@JoinColumn(name = "res_id", insertable = false, updatable = false)
	@JsonIgnore
	private ResidenceTypeEntity residenceTypeEntity;

}
