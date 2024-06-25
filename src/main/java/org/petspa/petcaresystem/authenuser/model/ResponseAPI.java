package org.petspa.petcaresystem.authenuser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAPI {
    private String message;
    private String timeStamp;
    private int statusCode;
    private HttpStatus statusValue;
    private List<AuthenUser> data;
}
