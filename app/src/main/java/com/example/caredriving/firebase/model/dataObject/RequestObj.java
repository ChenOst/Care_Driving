package com.example.caredriving.firebase.model.dataObject;

public class RequestObj {

    private String studentId;
    private String teacherId;
    private String requestId;
    private String status;

    public RequestObj(String studentId, String teacherId){
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.requestId = studentId+teacherId;
        this.status = "0";
    }

    public void acceptRequest(){
        if(!status.equals("1")){
            status = "1";
        }
    }

    public void rejectRequest(){
        if(status.equals("0")){
            status = "-1";
        }
    }

    public String getStudentId(){ return studentId;}
    public String getTeacherId(){ return teacherId;}
    public String getStatus() { return status;}
    public String getRequestId(){ return requestId;}
}
