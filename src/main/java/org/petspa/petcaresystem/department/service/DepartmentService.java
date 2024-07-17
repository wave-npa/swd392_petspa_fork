package org.petspa.petcaresystem.department.service;

import org.petspa.petcaresystem.department.model.request.CreateDepartmentRequest;
import org.petspa.petcaresystem.department.model.request.UpdateDepartmentRequest;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    ResponseEntity<ResponseObj> ViewAllDepartment();
    ResponseEntity<ResponseObj> ViewDepartmentById(Long Department_id);
    ResponseEntity<ResponseObj> CreateDepartment(String departmentName,String address, Status status);
    ResponseEntity<ResponseObj> UpdateDepartment(Long Department_id, String departmentName, String address, Status status);
    ResponseEntity<ResponseObj> getDepartment(Long Department_id);
    ResponseEntity<ResponseObj> RestoreDepartment(Long Department_id);
}
