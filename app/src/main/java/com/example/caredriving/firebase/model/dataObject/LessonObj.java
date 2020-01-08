package com.example.caredriving.firebase.model.dataObject;

import com.example.caredriving.firebase.model.dataObject.validation.LessonDate;

import java.io.Serializable;
import java.util.UUID;

public class LessonObj implements Serializable {

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

    public String getLessonTime() {
        int start = date.getHour();
        int end = start + Integer.parseInt(duration);
        return start + "-" + end;
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

    public LessonDate getDate() {
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

    public boolean isLessonActualForStudent(String fullDateFromDB) {
        String[] lessonInfo = fullDateFromDB.split("t");

        String lessonDate = lessonInfo[0];
        String lessonTime = lessonInfo[1];

        String myDate = date.getFullDate();

        if (lessonDate.equals(myDate)) {
            return isAvailableTime(lessonTime);
        }

        return true;
    }

    public boolean isLessonActualForTeacher(String timeInfo) {
        return isAvailableTime(timeInfo);
    }

    private boolean isAvailableTime(String timeInfo) {
        String[] time = timeInfo.split("-");
        int lessonStart = Integer.parseInt(time[0]);
        int lessonEnd = Integer.parseInt(time[1]);

        int myStart = date.getHour();
        int myEnd = date.getHour() + Integer.parseInt(duration);

//        System.out.println("TIME TO CHECK: " + myStart+"-"+myEnd +" <---> "+ timeInfo);

        if (myStart == lessonStart) {
            return false;
        } else if (lessonEnd > myStart && myStart > lessonStart) {
            return false;
        } else if (myStart < lessonStart && lessonStart < myEnd) {
            return false;
        }
        return true;
    }
}
