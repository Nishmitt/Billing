package com.cg.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.billing.beans.Customer;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.exceptions.BillingServicesDownException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.services.IBillingServices;

@RestController
public class BillingController {
	
	@Autowired
	private IBillingServices services;
	
	@RequestMapping(value="/acceptCustomerDetail",method=RequestMethod.POST,consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String>acceptProductDetail(@ModelAttribute Customer customer) throws BillingServicesDownException{
		services.acceptCustomerDetails(customer);
		return new ResponseEntity<>("Customer details succesfully added",HttpStatus.OK);
	}

	@RequestMapping(value="/openPostpaidMobileAccount",method=RequestMethod.POST,consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String>openPostpaidMobileAccount(@ModelAttribute PostpaidAccount account) throws BillingServicesDownException{
		try {
			services.openPostpaidMobileAccount(account.getCustomer().getCustomerID(), account.getPlan().getPlanID(), account);
		} catch (PlanDetailsNotFoundException e) {
			
			e.printStackTrace();
		} catch (CustomerDetailsNotFoundException e) {
			
			e.printStackTrace();
		}
		return new ResponseEntity<>("Customer details succesfully added",HttpStatus.OK);
	}
	
}
