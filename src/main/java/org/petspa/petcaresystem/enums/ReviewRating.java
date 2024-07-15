package org.petspa.petcaresystem.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ReviewRating {
    E_VERY_BAD,//1 sao
    D_BAD,//2 sao
    C_SATISFIED,//3 sao
    B_GOOD,//4 sao
    A_VERY_GOOD;//5 sao
}
