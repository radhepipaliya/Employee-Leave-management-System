    package com.learning.backend.dto;

    import com.learning.backend.model.Employee;
    import com.learning.backend.model.LeaveDetails;

    import java.util.List;

    public class ReqLeave {

        private Long id;
        private Long employeeid;
        private String StartDate;
        private String EndDate;
        private String LeaveType;
        private String Reason;
        private String Status;
        private int statuscode;
        private String error;
        private String message;
        private LeaveDetails leaveDetails;
        private List<LeaveDetails> leaveDetailsList;

        public ReqLeave() {
        }

        public ReqLeave(Long id, Long employeeid, String startDate, String endDate, String leaveType, String reason, String status, int statuscode, String error, String message, LeaveDetails leaveDetails, List<LeaveDetails> leaveDetailsList) {

            this.id = id;
            this.employeeid = employeeid;
            StartDate = startDate;
            EndDate = endDate;
            LeaveType = leaveType;
            Reason = reason;
            Status = status;
            this.statuscode = statuscode;
            this.error = error;
            this.message = message;
            this.leaveDetails = leaveDetails;
            this.leaveDetailsList = leaveDetailsList;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getEmployeeid() {
            return employeeid;
        }

        public void setEmployeeid(Long employeeid) {
            this.employeeid = employeeid;
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

        public int getStatuscode() {
            return statuscode;
        }

        public void setStatuscode(int statuscode) {
            this.statuscode = statuscode;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public LeaveDetails getLeaveDetails() {
            return leaveDetails;
        }

        public void setLeaveDetails(LeaveDetails leaveDetails) {
            this.leaveDetails = leaveDetails;
        }

        public List<LeaveDetails> getLeaveDetailsList() {
            return leaveDetailsList;
        }

        public void setLeaveDetailsList(List<LeaveDetails> leaveDetailsList) {
            this.leaveDetailsList = leaveDetailsList;
        }
    }