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
public class AppointmentResponseDTO2 {
    private AppointmentResponseInfor responseInfor;
    private AppointmentResponseData2 data;
    private List<AppointmentResponseData2> listData;

    public AppointmentResponseDTO2(AppointmentResponseInfor responseInfor, AppointmentResponseData2 data) {
        this.responseInfor = responseInfor;
        this.data = data;
    }

    public AppointmentResponseDTO2(AppointmentResponseInfor responseInfor, List<AppointmentResponseData2> listData) {
        this.responseInfor = responseInfor;
        this.listData = listData;
    }
}
