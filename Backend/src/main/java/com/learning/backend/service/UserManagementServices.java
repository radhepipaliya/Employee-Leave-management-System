package com.learning.backend.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learning.backend.dto.ReqRes;
import com.learning.backend.model.Employee;
import com.learning.backend.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagementServices implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper ;



    public ReqRes register(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();

        try {
            /*Employee employee = modelMapper.map(registrationRequest, Employee.class);
            Employee ourUsersResult = employeeRepository.save(employee);*/
            Employee employee = new Employee();
            employee.setEmail(registrationRequest.getEmail());
            employee.setFirstName(registrationRequest.getFirstName());
            employee.setLastName(registrationRequest.getLastName());
            employee.setRole(registrationRequest.getRole());
            employee.setPhoneNum(registrationRequest.getPhoneNum());
            employee.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            Employee ourUsersResult = employeeRepository.save(employee);
            if (ourUsersResult.getId()>0) {
                resp.setEmployee((ourUsersResult));
                resp.setMessage("User Saved Successfully");
                resp.setStatuscode(200);
            }

        }catch (Exception e){
            resp.setStatuscode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ReqRes login(ReqRes loginRequest){
        ReqRes response = new ReqRes();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));
            var user = employeeRepository.findByEmail(loginRequest.getEmail());
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatuscode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setId(user.getId());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In");

        }catch (Exception e){
            response.setStatuscode(500);

            response.setMessage(e.getMessage());
        }
        return response;
    }





    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
        ReqRes response = new ReqRes();
        try{
            String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
            Employee users = (Employee) employeeRepository.findByEmail(ourEmail);
            if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), (UserDetails) users)) {
                var jwt = jwtUtils.generateToken((UserDetails) users);
                response.setStatuscode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenReqiest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatuscode(200);
            return response;

        }catch (Exception e){
            response.setStatuscode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }


    public ReqRes getAllUsers() {
        ReqRes reqRes = new ReqRes();

        try {
            List<Employee> result = employeeRepository.findAll();
            if (!result.isEmpty()) {
                reqRes.setEmployeesList(result);
                reqRes.setStatuscode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatuscode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setMessage("Successful");
            reqRes.setStatuscode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
            return reqRes;
        }
    }


    public ReqRes getUsersById(Long id) {
        ReqRes reqRes = new ReqRes();
        try {
            Employee usersById = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
            reqRes.setEmployee(usersById);
            reqRes.setStatuscode(200);
            reqRes.setMessage("Users with id '" + id + "' found successfully");
        } catch (Exception e) {
            reqRes.setStatuscode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }


    public ReqRes deleteUser(Long userId) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<Employee> userOptional = employeeRepository.findById(userId);
            if (userOptional.isPresent()) {
                employeeRepository.deleteById(userId);
                reqRes.setStatuscode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatuscode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatuscode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes updateUser(Integer userId, Employee updatedUser) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<Employee> userOptional = employeeRepository.findById(Long.valueOf(userId));
            if (userOptional.isPresent()) {
                Employee existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setFirstName(updatedUser.getFirstName());
                existingUser.setLastName(updatedUser.getLastName());
                existingUser.setRole(updatedUser.getRole());

                // Check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                Employee savedUser = employeeRepository.save(existingUser);
                reqRes.setEmployee(savedUser);
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


    public ReqRes getMyInfo(String email){
        ReqRes reqRes = new ReqRes();
        try {
            Optional<Employee> employeeOptional = Optional.ofNullable(employeeRepository.findByEmail(email));
            if (employeeOptional.isPresent()) {
                reqRes.setStatuscode(employeeOptional.get().getId().intValue());
                reqRes.setStatuscode(200);
                reqRes.setMessage("successful");
            } else {
                reqRes.setStatuscode(404);
                reqRes.setMessage("User not found for update");
            }

        }catch (Exception e){
            reqRes.setStatuscode(500);
            reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;

    }

    
}
