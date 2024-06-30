package org.petspa.petcaresystem.enums;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Status {
    ACTIVE,
    INACTIVE,
    IN_PROGRESS,
    COMPLETED,
    USING,
    EMPTY;
}
