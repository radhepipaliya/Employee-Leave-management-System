    package com.learning.backend.service;


    import com.learning.backend.dto.ReqRes;
    import com.learning.backend.model.Employee;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public interface EmployeeService {
        ReqRes register(ReqRes reg);
    }
