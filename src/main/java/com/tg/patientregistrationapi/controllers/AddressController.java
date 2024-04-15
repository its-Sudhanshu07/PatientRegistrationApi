package com.tg.patientregistrationapi.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tg.patientregistrationapi.dtos.ResponseWrapper;
import com.tg.patientregistrationapi.models.Address;
import com.tg.patientregistrationapi.services.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/v1.0/")
	public ResponseEntity<ResponseWrapper> addAddress(@RequestBody Address address){ 
	
		Address addedAddress=addressService.addAddress(address);
		return ResponseEntity.status(HttpStatus.OK).body(new 
    			ResponseWrapper(addedAddress));
	}
	
	@GetMapping("/v1.0/")
	public ResponseEntity<ResponseWrapper> getAllAddress(){
		List<Address>addresses=addressService.getAllAddress();
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(addresses));
	}
	
	@GetMapping("/v1.0/{addressId}")
    public ResponseEntity<ResponseWrapper> getAddressById(@PathVariable("addressId") long addressId) {
        Address address = addressService.getAddressById(addressId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(address));
    }
	
	@PutMapping("/v1.0/{address}")
	public ResponseEntity<ResponseWrapper> updateAddress(@PathVariable("addressId") long addressId, @RequestBody Address address) {
		
		Address updatedAddress=addressService.updateAddress(addressId, address);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(updatedAddress));
	}
	
	@DeleteMapping("/v1.0/{addressId}")
    public ResponseEntity<ResponseWrapper> deleteAddress(@PathVariable("addressId") long addressId) {
        boolean deleted = addressService.deleteAddress(addressId);
        if (deleted)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Address with ID " + addressId + " deleted"));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper("Address with ID " + addressId + " not found"));
    }
}
