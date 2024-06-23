package org.petspa.petcaresystem.pet.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.medicine.model.entity.Medicine;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateMedicalRecordRequest {
    String desscription;
    Collection<Medicine> medicines;
    LocalDateTime updateTime;
    Status status;
}
