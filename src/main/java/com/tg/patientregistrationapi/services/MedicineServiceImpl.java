package com.tg.patientregistrationapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tg.patientregistrationapi.exceptions.MedicineNotFoundException;
import com.tg.patientregistrationapi.models.Medicine;
import com.tg.patientregistrationapi.repositories.MedicineRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MedicineServiceImpl implements MedicineService{
	
	@Autowired
	private MedicineRepository medicineRepo;

	@Override
	public Medicine addMedicine(Medicine medicine) {
		// TODO Auto-generated method stub
		log.info("Adding new address");
		return this.medicineRepo.save(medicine);
	}

	@Override
	public List<Medicine> getAllMedicine() {
		// TODO Auto-generated method stub
		log.info("Fetching all medicines");
		return this.medicineRepo.findAll();
	}

	@Override
	public Medicine getMedicineById(long medicineId) {
		// TODO Auto-generated method stub
		log.info("Fetching medicine by Id:",medicineId);
		return this.medicineRepo.findById(medicineId)
				.orElseThrow(()->new 
						MedicineNotFoundException("Medicine not "
								+ "found with given Id"));
	}

	@Override
	public Medicine updateMedicine(long medicineId,Medicine medicine) {
		// TODO Auto-generated method stub
		Medicine availableMedicine=this.getMedicineById(medicineId);
		if(availableMedicine!=null) {
			availableMedicine.setDosage("Daily 2 times");
			availableMedicine.setMedicineName("Medicine");
			availableMedicine.setPeriod("1Week");
			return this.medicineRepo.save(availableMedicine);
		}else
			throw new MedicineNotFoundException("Medicine not "
					+ "found with given Id");
	}

	@Override
	public boolean deleteMedicine(long medicineId) {
		// TODO Auto-generated method stub
		log.info("Deleting medicine with ID: {}", medicineId);
        Medicine medicine = getMedicineById(medicineId);
        medicineRepo.delete(medicine);
        return true;
	}
	
	
}
