package com.ust.controller;

import java.text.ParseException;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.beans.Login;
import com.ust.beans.Vendor;
import com.ust.dao.MainDaoService;



@RestController
public class MainController {
	@Autowired
	MainDaoService dao;
	
	
	//verify login
		@RequestMapping(value = "/api/login/{username}/{password}", method = RequestMethod.GET)
		@ResponseBody
		public Login select(@PathVariable("username") String username,
				@PathVariable("password") String password) {
			return dao.selectRole(username,password);
		}
		
		//view vendors
		
			@RequestMapping(value = "/api/vendor/{vendorName}", method = RequestMethod.GET)
			@ResponseBody
			public List getVendor(Model m, @PathVariable("vendorName") String vendorName) {
				List list;
				if (vendorName.equals("null")) {
					list = dao.getVendor();
				} else {
					list = dao.getVendorByName(vendorName);
				}

				return list;
			}
			
			//view contact by id
			@RequestMapping(value="/api/details/{vendorId}",method=RequestMethod.GET)
			@ResponseBody
			public List getContact(Model m,@PathVariable("vendorId") int vendorId){
			List list;
			list=dao.getContact(vendorId);
			return list;
			}
			
			// view vendor list by id
			@RequestMapping(value = "/api/vendors/{vendorId}", method = RequestMethod.GET)
			@ResponseBody
			public Vendor getVendor(Model m, @PathVariable("vendorId") int vendorId) {
				return dao.getVendorById(vendorId);
			}

			// Add Vendor
			@ResponseBody
			@RequestMapping(value = "/api/insertvendor", method = RequestMethod.POST)
			public void insertVendor(@RequestBody Vendor v) throws ParseException {
				dao.addVendor(v);
			}
			
		    // update Vendor
			@ResponseBody
			@RequestMapping(value = "/api/updatevendor", method = RequestMethod.PUT)
			public void updateVendor(@RequestBody Vendor v) throws ParseException {
				int vendorId = v.getVendorId();
				System.out.println("vendorId"+vendorId);
				dao.updateVendor(vendorId, v);
			}
			
			//disable vendor
			@RequestMapping(value = "/api/disable/{vendorId}", method = RequestMethod.PUT)
			void vendorDisable(@PathVariable("vendorId") int vendorId) {
				dao.disableVendor(vendorId);
			}
			

			
			
			

			

	
	

}
