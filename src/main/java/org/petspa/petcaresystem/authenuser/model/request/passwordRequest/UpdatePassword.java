package org.petspa.petcaresystem.authenuser.model.request.passwordRequest;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePassword {
    Long user_id;
    String password;
}
