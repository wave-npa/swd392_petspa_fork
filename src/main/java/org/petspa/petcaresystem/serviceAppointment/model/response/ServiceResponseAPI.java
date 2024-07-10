package org.petspa.petcaresystem.serviceAppointment.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.petspa.petcaresystem.serviceAppointment.model.request.ServiceRatingRequestDTO;
import org.petspa.petcaresystem.serviceAppointment.model.request.ServiceRevenueRequestDTO;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class ServiceResponseAPI {
    private String message;
    private String timeStamp;
    private int statusCode;
    private HttpStatus statusValue;
    private List<Services> list;
    private Optional<Services> data;
    private ServiceRatingRequestDTO rating;

    public ServiceResponseAPI(String message, String timeStamp, int statusCode, HttpStatus statusValue, List<Services> list) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.statusValue = statusValue;
        this.list = list;
    }

    public ServiceResponseAPI(String message, String timeStamp, int statusCode, HttpStatus statusValue, Optional<Services> data) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.statusValue = statusValue;
        this.data = data;
    }

    public ServiceResponseAPI(String message, String timeStamp, int statusCode, HttpStatus statusValue, ServiceRatingRequestDTO rating) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.statusValue = statusValue;
        this.rating = rating;
    }

}
