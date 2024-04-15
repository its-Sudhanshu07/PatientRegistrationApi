package com.tg.patientregistrationapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tg.patientregistrationapi.models.Medicine;


public interface MedicineRepository extends JpaRepository<Medicine, Long>{

}
