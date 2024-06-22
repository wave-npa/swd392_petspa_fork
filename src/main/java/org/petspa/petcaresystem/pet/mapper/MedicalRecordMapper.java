package org.petspa.petcaresystem.pet.mapper;

import org.petspa.petcaresystem.medicine.model.entity.Medicine;
import org.petspa.petcaresystem.pet.model.entity.MedicalRecord;
import org.petspa.petcaresystem.pet.model.response.MedicalRecordResponse;

import java.util.List;
import java.util.stream.Collectors;

public class MedicalRecordMapper {
    public static MedicalRecordResponse toMedicalRecordResponse(MedicalRecord medicalRecord){

        List<Medicine> medicines = medicalRecord.getMedicines().stream()
                .map(medicine -> new Medicine(
                        medicine.getMedicine_id(),
                        medicine.getMedicine_name(),
                        medicine.getPrice(),
                        medicine.getStatus()
                ))
                .collect(Collectors.toList());

        return MedicalRecordResponse.builder()
                .medicalRecord_id(String.valueOf(medicalRecord.getMedicalrecord_id()))
                .pet_id(String.valueOf(medicalRecord.getPetId()))
                .desscription(medicalRecord.getMedical_description())
                .medicines(medicines)
                .createTime(medicalRecord.getLast_update())
                .status(medicalRecord.getStatus())
                .build();
    }
}
