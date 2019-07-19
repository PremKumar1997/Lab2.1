package com.propertytax.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.propertytax.dto.RequestDTO;
import com.propertytax.entity.ZonalReportEntity;
import com.propertytax.exception.PropertyTaxApplicationException;
import com.propertytax.repository.ZonalReportRepository;
import com.propertytax.service.PropertyTaxService;

/**
 * The controller class for PropertyTax Application
 * 
 * @author Prem Kumar
 *
 */
@RestController
public class PropertyTaxController {

	@Autowired
	private ZonalReportRepository zonalRepository;

	@Autowired
	private PropertyTaxService propertyTaxService;

	/**
	 * Home.
	 *
	 * @param model the model
	 * @return the model and view
	 */
	@GetMapping("/")
	@ResponseBody
	public ModelAndView home(Map<String, Object> model) {
		return new ModelAndView("home");
	}

	/**
	 * View page load.
	 *
	 * @return the model and view
	 */
	@GetMapping("/zonalReport")
	@ResponseBody
	public ModelAndView viewPageLoad() {
		zonalRepository.findAll();
		return new ModelAndView("zonalReport");
	}

	/**
	 * Adds the tax.
	 *
	 * @return the model and view
	 */
	@GetMapping("/payTax")
	public ModelAndView addTax() {
		return new ModelAndView("residentialTaxForm");
	}

	/**
	 * View records.
	 *
	 * @return the list
	 */
	@GetMapping("/view")
	@ResponseBody
	public List<ZonalReportEntity> viewRecords() {
		return propertyTaxService.viewZonalRecords();
	}

	/**
	 * Gets the residential type by zonal id.
	 *
	 * @param zonalId the zonal id
	 * @return the residential type by zonal id
	 */
	@GetMapping("/all/{zonalId}")
	@ResponseBody
	public List<String> getResidentialTypeByZonalId(@PathVariable String zonalId) {
		return propertyTaxService.getResidentialTypeByZonalId(zonalId);
	}

	/**
	 * Gets the all zone.
	 *
	 * @return the all zone
	 */
	@GetMapping("/all")
	@ResponseBody
	public List<String> getAllZone() {
		return propertyTaxService.getAllZonesFromDB();
	}

	/**
	 * Residential tax calculation.
	 *
	 * @param requestDto the request dto
	 * @return the double
	 */
	@PostMapping("/taxValue")
	@ResponseBody
	public double residentialTaxCalculation(@RequestBody RequestDTO requestDto) {
		return propertyTaxService.residentialTaxCalculation(requestDto);
	}

	/**
	 * Pay residential tax.
	 *
	 * @param requestDto the request dto
	 * @return the string
	 * @throws PropertyTaxApplicationException the property tax application exception
	 */
	@PostMapping("/pay")
	@ResponseBody
	public String payResidentialTax(@RequestBody RequestDTO requestDto) throws PropertyTaxApplicationException{
		return propertyTaxService.payResidentialTax(requestDto);
	}

}
