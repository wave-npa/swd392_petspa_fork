package org.petspa.petcaresystem.pet.mapper;

import org.petspa.petcaresystem.pet.model.entity.MedicalRecord;
import org.petspa.petcaresystem.pet.model.response.MedicalRecordResponse;

public class MedicalRecordMapper {
    public static MedicalRecordResponse toMedicalRecordResponse(MedicalRecord medicalRecord){

        return MedicalRecordResponse.builder()
                .medicalRecord_id(medicalRecord.getMedicalrecord_id())
                .pet(medicalRecord.getPet())
                .desscription(medicalRecord.getMedical_description())
                .medicines(medicalRecord.getPetMedicine())
                .createTime(medicalRecord.getLast_update())
                .status(medicalRecord.getStatus())
                .build();
    }
}
