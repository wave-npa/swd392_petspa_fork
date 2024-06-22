package org.petspa.petcaresystem.medicine.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.enums.Status;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicineResponse {
    String medicineName;
    float medicinePrice;
    Status status;
}
