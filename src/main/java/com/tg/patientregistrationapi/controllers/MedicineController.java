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
import com.tg.patientregistrationapi.models.Medicine;
import com.tg.patientregistrationapi.services.MedicineService;


@RestController
@RequestMapping("/medicines")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;
	
	@PostMapping("/v1.0/")
	public ResponseEntity<ResponseWrapper>addMedicine(@RequestBody Medicine medicine){
		
		Medicine addedMedicine=medicineService.addMedicine(medicine);
		return ResponseEntity.status(HttpStatus.OK).body(new 
    			ResponseWrapper(addedMedicine));
	}
	
	@GetMapping("/v1.0/")
	public ResponseEntity<ResponseWrapper>getAllMedicine() {
		
		List <Medicine>medicines=medicineService.getAllMedicine();
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(medicines));
	}
	
	@GetMapping("/v1.0/{medicineId}")
	public ResponseEntity<ResponseWrapper> getMedicineById(@PathVariable("medicineId") long medicineId){
		
		Medicine medicine=medicineService.getMedicineById(medicineId);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(medicine));
	}
	
	@PutMapping("/v1.0/{medicine}")
	public ResponseEntity<ResponseWrapper> updateMedicine(@PathVariable("medicineId") long medicineId, @RequestBody Medicine medicine) {
		
		Medicine updatedMedicine=medicineService.updateMedicine(medicineId,medicine);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(updatedMedicine));
	}
	
	@DeleteMapping("/v1.0/{medicineId}")
	public ResponseEntity<ResponseWrapper> deleteMedicine(@PathVariable("medicineId") long medicineId) {
		
		boolean deleted = medicineService.deleteMedicine(medicineId);
		if (deleted)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Medicine with ID " + medicineId + " deleted"));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper("Medicine with ID " + medicineId + " not found"));
    
	}
}
