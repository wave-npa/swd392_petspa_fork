package org.petspa.petcaresystem.enums;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Status {
    ACTIVE,
    INACTIVE;
}
