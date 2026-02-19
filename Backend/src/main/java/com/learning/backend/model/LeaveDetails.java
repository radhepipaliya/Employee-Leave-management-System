package com.learning.backend.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="leave_details")
@Data
public class LeaveDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @JsonProperty("startDate")
    @Column(name="start_date")
    private String StartDate;
    @JsonProperty("endDate")
    @Column(name="end_date")
    private String EndDate;
    @Column(name="leave_type")
    private String LeaveType;
    @Column(name="reason")
    private String Reason;
    @Column(name="status")
    private String Status;

    public LeaveDetails() {
    }

    public LeaveDetails(Long id, Employee employee, String startDate, String endDate, String leaveType, String reason, String status) {
        this.id = id;
        this.employee = employee;
        StartDate = startDate;
        EndDate = endDate;
        LeaveType = leaveType;
        Reason = reason;
        Status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getLeaveType() {
        return LeaveType;
    }

    public void setLeaveType(String leaveType) {
        LeaveType = leaveType;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
