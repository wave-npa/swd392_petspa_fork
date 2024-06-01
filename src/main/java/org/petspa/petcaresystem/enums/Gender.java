package org.petspa.petcaresystem.enums;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Gender {

    MALE("Male"),
    FEMALE("Female");

    String gender;

}
