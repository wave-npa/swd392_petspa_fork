package org.petspa.petcaresystem.schedule.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleResponseDTO {
    private ResponseInfor responseInfor;
    private ScheduleResponseData data;
    private List<ScheduleResponseData> listData;
}
