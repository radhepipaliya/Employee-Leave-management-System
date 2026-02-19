package com.learning.backend.service;


import com.learning.backend.dto.ReqLeave;
import com.learning.backend.dto.ReqRes;
import com.learning.backend.model.LeaveDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface LeaveService {
    ReqLeave applyLeave(ReqLeave leave);

    ReqLeave getAllApplication();

    ReqLeave updateLeaveDetails(Integer applicationId, LeaveDetails reqLeave);

    ReqLeave updateApplicationStatus(Long applicationId,  Map<String, String> statusPayload);

    ReqLeave findApplicationByEmployeeId(Long employeeId);
}
