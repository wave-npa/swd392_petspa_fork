package org.petspa.petcaresystem.medicine.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.entity.MedicalRecord;

import java.util.Collection;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateMedicineRequest {
    private String medicineName;
    private float Price;
    Collection<MedicalRecord> MedicalRecord;
    private Status status;
}
