package com.tg.patientregistrationapi.services;

import java.util.List;

import com.tg.patientregistrationapi.models.Diagnosis;

public interface DiagnosisService {
	
	 Diagnosis addDiagnosis(Diagnosis diagnosis);
	 List<Diagnosis> getAllDiagnoses();
	 Diagnosis getDiagnosisById(long diagnosisId);
	 Diagnosis updateDiagnosis(long diagnosisId, Diagnosis diagnosis);
	 boolean deleteDiagnosis(long diagnosisId);
}
