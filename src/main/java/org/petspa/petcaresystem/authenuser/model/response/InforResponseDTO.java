package org.petspa.petcaresystem.authenuser.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InforResponseDTO {
    private String message;
    private String timeStamp;
    private int statusCode;
    private HttpStatus statusValue;
}
