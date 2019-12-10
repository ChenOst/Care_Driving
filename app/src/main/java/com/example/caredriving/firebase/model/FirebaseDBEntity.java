package com.example.caredriving.firebase.model;

import com.example.caredriving.firebase.model.dataObject.StudentObj;
import com.example.caredriving.firebase.model.dataObject.TeacherObj;
import com.example.caredriving.firebase.model.dataObject.UserObj;

import java.io.Serializable;
import java.util.HashMap;

public class FirebaseDBEntity implements Serializable {
    Object user;
    String type;

    public UserObj getUserObj(){
        if(type.equals("teacher"))
            return new TeacherObj((HashMap<String, String>) user);
        if(type.equals("student"))
            return new StudentObj((HashMap<String, String>) user);
        return null;
    }

    public void setType(String type){this.type = type;}
    public void setInfo(Object user){this.user = user;}
    public String getType(){return type;}
    public Object getInfo(){return user;}
}
