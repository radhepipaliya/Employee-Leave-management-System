package com.learning.backend.service;

import com.learning.backend.dto.ReqLeave;
import com.learning.backend.model.Employee;
import com.learning.backend.model.LeaveDetails;
import com.learning.backend.repository.EmployeeRepository;
import com.learning.backend.repository.LeaveDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveDetailsRepository leaveDetailsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReqLeave applyLeave(ReqLeave leave) {
        ReqLeave resp = new ReqLeave();

        try {
            Optional<Employee> employeeOpt = employeeRepository.findById(leave.getEmployeeid());
            if (employeeOpt.isEmpty()) {
                resp.setStatuscode(404);
                resp.setMessage("Employee not found");
                return resp;
            }


            LeaveDetails leaveDetails = new LeaveDetails();
            leaveDetails.setStartDate(leave.getStartDate());
            leaveDetails.setEndDate(leave.getEndDate());
            leaveDetails.setLeaveType(leave.getLeaveType());
            leaveDetails.setReason(leave.getReason());
            leaveDetails.setStatus("Pending");
            leaveDetails.setEmployee(employeeOpt.get());


            LeaveDetails ourLeaveResult = leaveDetailsRepository.save(leaveDetails);
            System.out.println("->>>>" + ourLeaveResult);

            if (ourLeaveResult.getId() > 0) {
                resp.setLeaveDetails(ourLeaveResult); // Set a single LeaveDetails object
                resp.setMessage("Leave Applied Successfully");
                resp.setStatuscode(200);
            }
        } catch (Exception e) {
            resp.setStatuscode(500);
            resp.setError(e.getMessage());
        }
        return resp;    
    }

    public ReqLeave updateLeaveDetails(Integer applicationId, LeaveDetails leaveDetails) {
        ReqLeave reqRes = new ReqLeave();
        try {
            Optional<LeaveDetails> leaveDetailsOptional = leaveDetailsRepository.findById(Long.valueOf(applicationId));
            if (leaveDetailsOptional.isPresent()) {
                LeaveDetails details = leaveDetailsOptional.get();
                details.setStatus(leaveDetails.getStatus());

                LeaveDetails savedUser = leaveDetailsRepository.save(details);
                reqRes.setLeaveDetails(savedUser); // Set a single LeaveDetails object
                reqRes.setStatuscode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatuscode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatuscode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqLeave getAllApplication() {
        ReqLeave reqLeave = new ReqLeave();

        try {
            List<LeaveDetails> result = leaveDetailsRepository.findAll();
            if (!result.isEmpty()) {
                reqLeave.setLeaveDetailsList(result); // Set the list of LeaveDetails
                reqLeave.setStatuscode(200);
                reqLeave.setMessage("Successful");
            } else {
                reqLeave.setStatuscode(404);
                reqLeave.setMessage("No users found");
            }
            return reqLeave;
        } catch (Exception e) {
            reqLeave.setStatuscode(500);
            reqLeave.setMessage("Error occurred: " + e.getMessage());
            return reqLeave;
        }
    }

    public ReqLeave updateApplicationStatus(Long applicationId,  Map<String, String> statusPayload) {
        ReqLeave reqLeave = new ReqLeave();
        try {
            Optional<LeaveDetails> leaveDetailsOptional = leaveDetailsRepository.findById(applicationId);
            if (leaveDetailsOptional.isPresent()) {
                LeaveDetails leaveDetails = leaveDetailsOptional.get();
                String status = statusPayload.get("status");
                leaveDetails.setStatus(status);
                LeaveDetails savedLeaveDetails = leaveDetailsRepository.save(leaveDetails);
                reqLeave.setLeaveDetails(savedLeaveDetails);
                reqLeave.setStatuscode(200);
                reqLeave.setMessage("Leave application status updated successfully");
            } else {
                reqLeave.setStatuscode(404);
                reqLeave.setMessage("Leave application not found");
            }
        } catch (Exception e) {
            reqLeave.setStatuscode(500);
            reqLeave.setMessage("Error occurred while updating leave application status: " + e.getMessage());
        }
        return reqLeave;
    }

    @Override
    public ReqLeave findApplicationByEmployeeId(Long employeeId) {
        ReqLeave reqLeave = new ReqLeave();

        try {
            List<LeaveDetails> result = leaveDetailsRepository.findByEmployeeId(employeeId);
            if (!result.isEmpty()) {
                reqLeave.setLeaveDetailsList(result); // Set the list of LeaveDetails
                reqLeave.setStatuscode(200);
                reqLeave.setMessage("Successful");
            } else {
                reqLeave.setStatuscode(404);
                reqLeave.setMessage("No users found");
            }
            return reqLeave;
        } catch (Exception e) {
            reqLeave.setStatuscode(500);
            reqLeave.setMessage("Error occurred: " + e.getMessage());
            return reqLeave;
        }
    }
}