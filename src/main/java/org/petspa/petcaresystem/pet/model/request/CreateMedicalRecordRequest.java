package org.petspa.petcaresystem.pet.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.medicine.model.entity.Medicine;
import org.petspa.petcaresystem.pet.model.entity.Pet;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMedicalRecordRequest {
    String description;
    Collection<Medicine> medicines;
    LocalDateTime createTime;
    Status status;
}
