package org.petspa.petcaresystem.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Species {

    DOG("Dog"),
    CAT("Cat"),
    HAMSTER("Hamster");
    String species;
}
