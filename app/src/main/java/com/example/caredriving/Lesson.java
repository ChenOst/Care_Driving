package com.example.caredriving;

import java.util.UUID;

public class Lesson {

    private String date;
    private String time;
    private String duration;

//    private final String id;
    private final String studentId;
    private final String teacherId;


    public Lesson(String studentId, String teacherId) {
//        this.id = UUID.randomUUID().toString();
        this.studentId = studentId;
        this.teacherId = teacherId;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
