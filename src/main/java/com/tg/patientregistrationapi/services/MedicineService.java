package com.tg.patientregistrationapi.services;

import java.util.List;

import com.tg.patientregistrationapi.models.Medicine;


public interface MedicineService {
	
	Medicine addMedicine(Medicine medicine);
	List<Medicine>getAllMedicine();
	Medicine getMedicineById(long medicineId);
	Medicine updateMedicine(long medicineId,Medicine medicine);
	boolean deleteMedicine(long medicineId);
}
