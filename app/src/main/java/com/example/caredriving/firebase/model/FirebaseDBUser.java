package com.example.caredriving.firebase.model;

import com.example.caredriving.firebase.model.dataObject.RequestObj;
import com.example.caredriving.firebase.model.dataObject.StudentObj;
import com.example.caredriving.firebase.model.dataObject.TeacherObj;
import com.example.caredriving.firebase.model.dataObject.UserObj;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class FirebaseDBUser extends FirebaseBaseModel{
    FirebaseUser myAuto;
    String myUid;

    public FirebaseDBUser(){
        myAuto = FirebaseAuth.getInstance().getCurrentUser();
        myUid = myAuto.getUid();
    }

    public void writeUserToDB(UserObj user){
        if(user instanceof TeacherObj) {
            String userFullName = user.getFirstName() + " " + user.getLastName();
            myref.child("Search Teachers").child("Location").child(user.getCity()).child(myUid).setValue(userFullName);
            myref.child("Search Teachers").child("Transmission").child(((TeacherObj) user).getTransmission()).child(myUid).setValue(userFullName);
            myref.child("Search Teachers").child("Car Type").child(((TeacherObj) user).getCarType()).child(myUid).setValue(userFullName);
            myref.child("Search Teachers").child("Price").child(((TeacherObj) user).getLessonPrice()).child(myUid).setValue(userFullName);
            user.setId(myUid);
            myref.child("users").child(myUid).child("info").setValue((TeacherObj) user);
            myref.child("users").child(myUid).child("type").setValue("teacher");
        }
        else if(user instanceof StudentObj) {
            myref.child("users").child(myUid).child("info").setValue((StudentObj) user);
            myref.child("users").child(myUid).child("type").setValue("student");
        }
    }

    public UserObj readUserFromDB(DataSnapshot dataSnapshot){
        String type = dataSnapshot.child("users").child(myUid).child("type").getValue().toString();
        if(type.equals("teacher")) {
            TeacherObj user;
            user = (TeacherObj) dataSnapshot.child("users").child(myUid).child("info").getValue(TeacherObj.class);
            user.loadStudents(prepareMyStudentsList(dataSnapshot));
            user.loadRequests(prepareMyRequestsList(dataSnapshot));
            return user;
        }
        else if(type.equals("student")){
            StudentObj user;
            user = (StudentObj) dataSnapshot.child("users").child(myUid).child("info").getValue(StudentObj.class);
            return user;
        }
        return null;
    }

    /**
     *
     * @param dataSnapshot
     * @return
     */
    private ArrayList<String> prepareMyStudentsList(DataSnapshot dataSnapshot){
        return new ArrayList<>();
    }

    /**
     * Prepare list of requests for current teacher -
     * find correct requests for this teacher from "Requests" tree in firebase.
     * @param dataSnapshot
     * @return
     */
    private ArrayList<String> prepareMyRequestsList(DataSnapshot dataSnapshot){
        ArrayList<String> requests = new ArrayList<>();
        for (DataSnapshot request : dataSnapshot.child("Requests").getChildren()){
            RequestObj requestObj = (RequestObj) request.getValue(RequestObj.class);
            if(requestObj.getTeacherId().equals(myUid))
                requests.add(requestObj.getRequestId());
        }
        return requests;
    }

    public DatabaseReference getUserRefFromDB(){
        return myref.child("users").child(myUid);
    }

    public String getMyUid(){return myUid;}
}
