package org.petspa.petcaresystem.pet.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.medicine.model.entity.Medicine;
import org.petspa.petcaresystem.medicine.model.response.MedicineResponse;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalRecordResponse {
    String medicalRecord_id;
    String pet_id;
    String desscription;
    Collection<Medicine> medicines;
    LocalDateTime createTime;
    Status status;
}
