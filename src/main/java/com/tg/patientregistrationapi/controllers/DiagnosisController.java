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
import com.tg.patientregistrationapi.models.Diagnosis;
import com.tg.patientregistrationapi.services.DiagnosisService;

@RestController
@RequestMapping
public class DiagnosisController {

	@Autowired
    private DiagnosisService diagnosisService;

    @PostMapping("/v1.0/")
    public ResponseEntity<ResponseWrapper> addDiagnosis(@RequestBody Diagnosis diagnosis) {
        Diagnosis addedDiagnosis = diagnosisService.addDiagnosis(diagnosis);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(addedDiagnosis));
    }

    @GetMapping("/v1.0/")
    public ResponseEntity<ResponseWrapper> getAllDiagnoses() {
        List<Diagnosis> diagnoses = diagnosisService.getAllDiagnoses();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(diagnoses));
    }

    @GetMapping("/v1.0/{diagnosisId}")
    public ResponseEntity<ResponseWrapper> getDiagnosisById(@PathVariable("diagnosisId") long diagnosisId) {
        Diagnosis diagnosis = diagnosisService.getDiagnosisById(diagnosisId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(diagnosis));
    }

    @PutMapping("/v1.0/{diagnosisId}")
    public ResponseEntity<ResponseWrapper> updateDiagnosis(@PathVariable("diagnosisId") long diagnosisId, @RequestBody Diagnosis diagnosis) {
        Diagnosis updatedDiagnosis = diagnosisService.updateDiagnosis(diagnosisId, diagnosis);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(updatedDiagnosis));
    }

    @DeleteMapping("/v1.0/{diagnosisId}")
    public ResponseEntity<ResponseWrapper> deleteDiagnosis(@PathVariable("diagnosisId") long diagnosisId) {
        boolean deleted = diagnosisService.deleteDiagnosis(diagnosisId);
        if (deleted)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Diagnosis with ID " + diagnosisId + " deleted"));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper("Diagnosis with ID " + diagnosisId + " not found"));
    }
}
