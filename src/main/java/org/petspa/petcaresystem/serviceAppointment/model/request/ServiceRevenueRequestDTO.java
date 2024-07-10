package org.petspa.petcaresystem.serviceAppointment.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServiceRevenueRequestDTO {

    private Long service_id;
    private String service_name;
    private float total_revenue;
    private Integer total_count;

    public ServiceRevenueRequestDTO(Long service_id, String service_name, float total_revenue) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.total_revenue = total_revenue;
    }

    public ServiceRevenueRequestDTO(Long service_id, String service_name, Integer total_count) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.total_count = total_count;
    }

}
