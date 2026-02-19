package com.learning.backend.controller;

import com.learning.backend.dto.ReqLeave;
import com.learning.backend.dto.ReqRes;
import com.learning.backend.model.Employee;
import com.learning.backend.service.EmployeeService;
import com.learning.backend.service.UserManagementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserManagementServices userManagementServices;


    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> regeister(@RequestBody ReqRes reg){
        return ResponseEntity.ok(userManagementServices.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(userManagementServices.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(userManagementServices.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(userManagementServices.getAllUsers());

    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUSerByID(@PathVariable Integer userId){
        return ResponseEntity.ok(userManagementServices.getUsersById(Long.valueOf(userId)));

    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody Employee reqres){
        return ResponseEntity.ok(userManagementServices.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = userManagementServices.getMyInfo(email);
        return  ResponseEntity.status(response.getStatuscode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer userId){
        return ResponseEntity.ok(userManagementServices.deleteUser(Long.valueOf(userId)));
    }

    @GetMapping("/public/get-user-info/{employeeId}")
    public ResponseEntity<ReqRes> getUserInfo(@PathVariable Long employeeId) {
        return ResponseEntity.ok(userManagementServices.getUsersById(employeeId));

    }

}
