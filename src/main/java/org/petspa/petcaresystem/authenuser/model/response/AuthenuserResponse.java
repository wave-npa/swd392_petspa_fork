package org.petspa.petcaresystem.authenuser.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.enums.Gender;
import org.springframework.context.annotation.Primary;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenuserResponse {
    String full_name;
    Gender gender;
    String address;
    String phone;
}




