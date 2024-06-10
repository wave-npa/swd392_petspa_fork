package org.petspa.petcaresystem.enums;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Gender {
    MALE,
    FEMALE;
    private String gender;
}
