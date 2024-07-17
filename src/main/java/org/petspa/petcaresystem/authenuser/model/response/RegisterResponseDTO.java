package org.petspa.petcaresystem.authenuser.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.authenuser.model.payload.CustomAuthenUserForRegister;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterResponseDTO {
    private String message;
    private String timeStamp;
    private int statusCode;
    private HttpStatus statusValue;
    private CustomAuthenUserForRegister customAuthenUserForRegister;
}
