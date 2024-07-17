package org.petspa.petcaresystem.authenuser.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class ResponseAPI {
    private String message;
    private String timeStamp;
    private int statusCode;
    private HttpStatus statusValue;
    private AuthenUser user;
    private List<AuthenUser> list;
    private Optional<AuthenUser> data;

    public ResponseAPI(String message, String timeStamp, int statusCode, HttpStatus statusValue, AuthenUser user) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.statusValue = statusValue;
        this.user = user;
    }


    public ResponseAPI(String message, String timeStamp, int statusCode, HttpStatus statusValue, List<AuthenUser> list) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.statusValue = statusValue;
        this.list = list;
    }

    public ResponseAPI(String message, String timeStamp, int statusCode, HttpStatus statusValue, Optional<AuthenUser> data) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.statusValue = statusValue;
        this.data = data;
    }
}
