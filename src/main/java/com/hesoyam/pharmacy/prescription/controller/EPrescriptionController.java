package com.hesoyam.pharmacy.prescription.controller;

import com.hesoyam.pharmacy.prescription.dto.CompletePrescriptionDTO;
import com.hesoyam.pharmacy.prescription.dto.EPrescriptionUploadResponse;
import com.hesoyam.pharmacy.prescription.exception.InvalidCompleteEPrescriptionException;
import com.hesoyam.pharmacy.prescription.exception.InvalidEPrescriptionFormat;
import com.hesoyam.pharmacy.prescription.model.EPrescription;
import com.hesoyam.pharmacy.prescription.service.IEPrescriptionService;
import com.hesoyam.pharmacy.user.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/eprescription")
public class EPrescriptionController {

    @Autowired
    private IEPrescriptionService prescriptionService;

    @PostMapping("/upload")
    @Secured("ROLE_PATIENT")
    public ResponseEntity<EPrescriptionUploadResponse> getEPrescription(@RequestParam(required = true, name = "file") MultipartFile multipartFile, @AuthenticationPrincipal Patient patient){
        File file;
        try {
            file = new File(System.getProperty("java.io.tmpdir") + File.separator + randomizeFileName(multipartFile));
            multipartFile.transferTo(file);
            return ResponseEntity.ok(prescriptionService.get(file, patient));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (InvalidEPrescriptionFormat e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/complete")
    @Secured("ROLE_PATIENT")
    public ResponseEntity<EPrescription> complete(@RequestBody CompletePrescriptionDTO completePrescriptionDTO, @AuthenticationPrincipal  Patient patient){
        try{
            return ResponseEntity.ok(prescriptionService.complete(completePrescriptionDTO, patient));
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (InvalidCompleteEPrescriptionException e){
            return ResponseEntity.badRequest().build();
        }
    }

    private String randomizeFileName(MultipartFile multipartFile){
        String originalFileName = multipartFile.getOriginalFilename();
        if(originalFileName == null) return "";
        String name = originalFileName.split("\\.")[0];
        String extension = originalFileName.split("\\.")[1];
        name += UUID.randomUUID().toString();
        return name + "." + extension;
    }
}
