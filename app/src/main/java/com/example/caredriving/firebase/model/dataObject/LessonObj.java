package com.example.caredriving.firebase.model.dataObject;

import com.example.caredriving.firebase.model.dataObject.validation.LessonDate;

import java.util.UUID;

public class LessonObj {

    private String duration;
    private LessonDate date;
//    private  String lessonId;
    private String studentId;
    private String teacherId;


    public LessonObj(String studentId, String teacherId) {
//        this.lessonId = UUID.randomUUID().toString();
        this.studentId = studentId;
        this.teacherId = teacherId;
    }


    public void setDate(String date, String time) {
        this.date = new LessonDate(date, time);
    }

//    public void setTime(String time) {
//        this.time = time;
//    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LessonDate getDate(){
        return date;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getDuration() {
        return duration;
    }
}
