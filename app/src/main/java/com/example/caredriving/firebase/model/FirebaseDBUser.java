package com.example.caredriving.firebase.model;

import com.example.caredriving.firebase.model.dataObject.TeacherObj;
import com.example.caredriving.firebase.model.dataObject.UserObj;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

public class FirebaseDBUser extends FirebaseBaseModel{
    FirebaseUser myAuto;
    String myUid;

    public FirebaseDBUser(){
        myAuto = FirebaseAuth.getInstance().getCurrentUser();
        myUid = myAuto.getUid();
    }

    public UserObj readUserFromDB(DataSnapshot dataSnapshot){
        UserObj user = (TeacherObj) dataSnapshot.child("info").getValue(TeacherObj.class);
        return user;
    }

    public DatabaseReference getUserRefFromDB(){
        return myref.child("users").child(myUid);
    }

    public String getMyUid(){return myUid;}
}
