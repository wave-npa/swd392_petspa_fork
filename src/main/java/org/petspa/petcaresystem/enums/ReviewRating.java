package org.petspa.petcaresystem.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ReviewRating {
    VERY_BAD,//1 sao
    BAD,//2 sao
    SATISFIED,//3 sao
    GOOD,//4 sao
    VERY_GOOD;//5 sao
}
