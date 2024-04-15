package com.tg.patientregistrationapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tg.patientregistrationapi.models.Diagnosis;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long>{

}
