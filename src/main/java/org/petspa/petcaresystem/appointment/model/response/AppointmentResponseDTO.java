package org.petspa.petcaresystem.appointment.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentResponseDTO {
    private AppointmentResponseInfor responseInfor;
    private AppointmentResponseData data;
    private List<AppointmentResponseData> listData;

    public AppointmentResponseDTO(AppointmentResponseInfor responseInfor, AppointmentResponseData data) {
        this.responseInfor = responseInfor;
        this.data = data;
    }

    public AppointmentResponseDTO(AppointmentResponseInfor responseInfor, List<AppointmentResponseData> listData) {
        this.responseInfor = responseInfor;
        this.listData = listData;
    }
}
