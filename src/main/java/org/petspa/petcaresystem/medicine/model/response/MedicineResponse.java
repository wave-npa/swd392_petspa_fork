package org.petspa.petcaresystem.medicine.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.entity.MedicalRecord;

import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicineResponse {
    Long medicine_id;
    String medicineName;
    float price;
    Collection<MedicalRecord> medicalRecords;
    Status status;
}
