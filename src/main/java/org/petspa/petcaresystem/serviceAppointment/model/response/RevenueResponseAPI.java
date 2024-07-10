package org.petspa.petcaresystem.serviceAppointment.model.response;

import java.util.List;

import org.petspa.petcaresystem.serviceAppointment.model.request.ServiceRevenueRequestDTO;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RevenueResponseAPI {
    private String message;
    private String timeStamp;
    private int statusCode;
    private HttpStatus statusValue;
    private List<ServiceRevenueRequestDTO> revenue;

    public RevenueResponseAPI(String message, String timeStamp, int statusCode, HttpStatus statusValue, List<ServiceRevenueRequestDTO> revenue) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.statusValue = statusValue;
        this.revenue = revenue;
    }
}
