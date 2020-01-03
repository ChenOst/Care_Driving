package com.example.caredriving.firebase.model;

import com.example.caredriving.firebase.model.dataObject.LessonObj;

import java.util.Map;

public class FirebaseDBLesson extends FirebaseBaseModel{
    private LessonObj lessonObj;



    public void addLessonToDB(String studentId, String teacherId, String [] lessonInfo){

        LessonObj lessonObj = new LessonObj(studentId, teacherId);
        lessonObj.setDuration(lessonInfo[0]);
        lessonObj.setDate(lessonInfo[1], lessonInfo[2]);

        String lessonId = myref.push().getKey();

        myref.child("lessons").child(lessonId).setValue(lessonObj);

        myref.child("lessonsPerUser").child("students").child(studentId).setValue(lessonId);
        myref.child("lessonsPerUser").child("teachers").child(teacherId)
                .child(lessonObj.getDate().getYear()+"")
                .child(lessonObj.getDate().getMonth()+"")
                .child((lessonObj.getDate().getDay())+"")
                .setValue(lessonId);

    }

    public void readLessonFromDB(String lessonId){

    }
}
