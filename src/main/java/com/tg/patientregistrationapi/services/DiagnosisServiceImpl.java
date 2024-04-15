package com.tg.patientregistrationapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tg.patientregistrationapi.exceptions.DiagnosisNotFoundException;
import com.tg.patientregistrationapi.models.Diagnosis;
import com.tg.patientregistrationapi.repositories.DiagnosisRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DiagnosisServiceImpl implements DiagnosisService{

	@Autowired
    private DiagnosisRepository diagnosisRepo;

    @Override
    public Diagnosis addDiagnosis(Diagnosis diagnosis) {
    	log.info("Adding new diagnosis");
        return diagnosisRepo.save(diagnosis);
    }

    @Override
    @Cacheable(value = "Diagnosis")
    public List<Diagnosis> getAllDiagnoses() {
        log.info("Fetching all diagnoses");
        return diagnosisRepo.findAll();
    }

    @Override
    @Cacheable(value = "Diagnosis", key = "#diagnosisId", condition = "#diagnosisId > 0")
    public Diagnosis getDiagnosisById(long diagnosisId) {
        log.info("Fetching diagnosis by ID: {}", diagnosisId);
        return this.diagnosisRepo.findById(diagnosisId)
				.orElseThrow(()->new DiagnosisNotFoundException("Address not found with given ID"));
                
    }

    @Override
    @CachePut(value = "Diagnosis", key = "#diagnosisId")
    public Diagnosis updateDiagnosis(long diagnosisId, Diagnosis diagnosis) {
        log.info("Updating diagnosis with ID: {}", diagnosisId);
        Diagnosis existingDiagnosis = getDiagnosisById(diagnosisId);
        existingDiagnosis.setDiagnosisDescription(diagnosis.getDiagnosisDescription());
        existingDiagnosis.setTreatmentHistory(diagnosis.getTreatmentHistory());
        return diagnosisRepo.save(existingDiagnosis);
    }

    @Override
    @CacheEvict(value = "Diagnosis", key = "#diagnosisId")
    public boolean deleteDiagnosis(long diagnosisId) {
        log.info("Deleting diagnosis with ID: {}", diagnosisId);
        Diagnosis diagnosis = getDiagnosisById(diagnosisId);
        diagnosisRepo.delete(diagnosis);
        return true;
    }
}
