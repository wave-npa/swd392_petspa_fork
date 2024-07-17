package org.petspa.petcaresystem.pet.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.medicine.model.entity.Medicine;
import org.petspa.petcaresystem.pet.model.entity.Pet;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalRecordResponse {
    Long medicalRecord_id;
    Pet pet;
    String desscription;
    Collection<Medicine> medicines;
    LocalDateTime createTime;
    Status status;
}
