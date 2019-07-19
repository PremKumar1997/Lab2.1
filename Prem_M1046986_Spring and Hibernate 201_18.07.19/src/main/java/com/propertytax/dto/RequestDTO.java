package com.propertytax.dto;

import lombok.Data;

/**
 * The class to hold RequestDTO data fields.
 * 
 * @author Prem Kumar
 *
 */
@Data
public class RequestDTO {

	/** Value to Hold zoneId **/
	private String zoneId;

	/** Value to Hold uav Id **/
	private String yearId;

	/** Value to Hold area Id **/
	private double areaId;

	/** Value to Hold total Id **/
	private double totalId;

	/** Value to Hold property Id **/
	private String propertyId;

	/** Value to Hold status **/
	private String status;
}
