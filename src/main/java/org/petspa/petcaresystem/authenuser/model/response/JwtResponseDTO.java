package org.petspa.petcaresystem.authenuser.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JwtResponseDTO {
    private String accessToken;
    private String message;
    private String timeStamp;
    private int statusCode;
    private HttpStatus statusValue;
}
