package com.learning.backend.controller;

import com.learning.backend.dto.ReqLeave;
import com.learning.backend.dto.ReqRes;
import com.learning.backend.model.Employee;
import com.learning.backend.model.LeaveDetails;
import com.learning.backend.repository.LeaveDetailsRepository;
import com.learning.backend.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin( "*")
@RestController
public class LeaveDetailsController {
    @Autowired
    private LeaveDetailsRepository leaveDetailsRepository;

    @Autowired
    private LeaveService leaveService;


    @PostMapping("/public/applyleave")
    public ResponseEntity<ReqLeave> applyLeave(@RequestBody ReqLeave reg){
        return ResponseEntity.ok(leaveService.applyLeave(reg));
    }

    @GetMapping("/public/applications")
    public ResponseEntity<ReqLeave> getallapplication(){
        return ResponseEntity.ok(leaveService.getAllApplication());

    }

    @PutMapping("/user/update/{applicationId}")
    public ResponseEntity<ReqLeave> updateaApplication(@PathVariable Integer applicationId, @RequestBody LeaveDetails reqLeave){
        return ResponseEntity.ok(leaveService.updateLeaveDetails(applicationId, reqLeave));
    }

    @PutMapping("/public/update/{applicationId}")
    public ResponseEntity<?> updateApplicationStatus(@PathVariable Long applicationId, @RequestBody Map<String, String> statusPayload) {
        return ResponseEntity.ok(leaveService.updateApplicationStatus(applicationId, statusPayload));
    }
    

    @GetMapping("/public/user-application/{employeeId}")
    public ResponseEntity<ReqLeave> getUserApplications(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveService.findApplicationByEmployeeId(employeeId));

    }

}
