package org.petspa.petcaresystem.authenuser.model.request.profileRequest;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.enums.Gender;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateProfileRequest {
    String full_name;
    Gender gender;
    String address;
    String email;
    String phone;
}
