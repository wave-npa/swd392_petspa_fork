package org.petspa.petcaresystem.department.service.implement;

import org.petspa.petcaresystem.department.mapper.DepartmentMapper;
import org.petspa.petcaresystem.department.model.entity.Departments;
import org.petspa.petcaresystem.department.model.request.CreateDepartmentRequest;
import org.petspa.petcaresystem.department.model.request.UpdateDepartmentRequest;
import org.petspa.petcaresystem.department.model.response.DepartmentResponse;
import org.petspa.petcaresystem.department.repository.DepartmentRepository;
import org.petspa.petcaresystem.department.service.DepartmentService;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public ResponseEntity<ResponseObj> ViewAllDepartment() {
        try {
            List<Departments> departmentList = departmentRepository.findAll();
            if (departmentList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Department List is empty")
                        .data(null)
                        .build();
                return ResponseEntity.ok().body(responseObj);
            }
            List<DepartmentResponse> departmentResponseList = new ArrayList<>();
            for (Departments department : departmentList) {
                DepartmentResponse departmentResponse = DepartmentMapper.toDepartmentResponse(department);
                departmentResponseList.add(departmentResponse);
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Load Department Successfully")
                    .data(departmentResponseList)
                    .build();
            return ResponseEntity.ok().body(responseObj);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }

    @Override
    public ResponseEntity<ResponseObj> ViewDepartmentById(Long Department_id) {
        try {
            Departments viewdepartment = departmentRepository.getById(Department_id);
            List<Departments> departmentList = departmentRepository.findAll();
            if (departmentList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Department List is empty")
                        .data(null)
                        .build();
                return ResponseEntity.ok().body(responseObj);
            }

            for (Departments department : departmentList) {
                if (department.equals(viewdepartment) && department.getStatus() == Status.ACTIVE) {
                    DepartmentResponse departmentResponse = DepartmentMapper.toDepartmentResponse(department);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Load Department Successfully")
                            .data(departmentResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Department not fount")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }

    @Override
    public ResponseEntity<ResponseObj> CreateDepartment(String departmentName, String departmentAddress, Status status) {
        try {

            Departments department = new Departments();

            department.setDepartmentName(departmentName);

            department.setAddress(departmentAddress);

            department.setStatus(status);

            Departments createDepartment = departmentRepository.save(department);

            DepartmentResponse departmentResponse = DepartmentMapper.toDepartmentResponse(createDepartment);

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Create Department Successfully")
                    .data(departmentResponse)
                    .build();
            return ResponseEntity.ok().body(responseObj);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }

    @Override
    public ResponseEntity<ResponseObj> UpdateDepartment(Long departmentId, String departmentName, String address, Status status) {
        try {
                Departments department = departmentRepository.findByDepartmentId(departmentId);

                if(department == null){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }

                Departments checkNameExist= departmentRepository.findByDepartmentName(departmentName);
                if(checkNameExist != null){
                    if(!department.getDepartmentName().equals(departmentName)) {
                        ResponseObj responseObj = ResponseObj.builder()
                                .message("Department's name existed!")
                                .data(null)
                                .build();
                        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseObj);
                    }
                }

                    department.setDepartmentName(departmentName);

                    department.setAddress(address);

                    department.setStatus(status);

                    Departments updateDepartment = departmentRepository.save(department);

                    DepartmentResponse departmentResponse = DepartmentMapper.toDepartmentResponse(updateDepartment);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Update Department Successfully")
                            .data(departmentResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }

    @Override
    public ResponseEntity<ResponseObj> getDepartment(Long departmentId) {
        try {

            Departments department = departmentRepository.findByDepartmentId(departmentId);
            if(department == null){
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Department not found!")
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Department found")
                    .data(department)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(responseObj);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }

    @Override
    public ResponseEntity<ResponseObj> RestoreDepartment(Long Department_id) {
        try {
            Departments department = departmentRepository.getReferenceById(Department_id);
            List<Departments> departmentList = departmentRepository.findAll();
            for (Departments department1 : departmentList) {
                if (department1.equals(department) && department1.getStatus().equals(Status.INACTIVE)) {

                    department.setStatus(Status.ACTIVE);

                    Departments restoreDepartment = departmentRepository.save(department);

                    DepartmentResponse departmentResponse = DepartmentMapper.toDepartmentResponse(restoreDepartment);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Restore Department Successfully")
                            .data(departmentResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Department not exist")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }
}
