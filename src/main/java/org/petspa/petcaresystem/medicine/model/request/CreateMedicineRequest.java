package org.petspa.petcaresystem.medicine.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.enums.Status;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMedicineRequest {
    private String medicineName;
    private float Price;
    private Status status;
}
